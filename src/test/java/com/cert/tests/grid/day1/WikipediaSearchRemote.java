package com.cert.tests.grid.day1;

import com.cert.tests.grid.BaseGridTest;
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.*;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.time.Duration;

public class WikipediaSearchRemote extends BaseGridTest {

    private static final By SEARCH_INPUT = By.cssSelector("#searchInput"); // portada global tiene id estable

    @Test(groups = "day1")
    public void searchWikipedia() {
        driver.manage().window().maximize();
        // Usamos la portada global (estable) en vez de la portada en español
        driver.get("https://www.wikipedia.org/");

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(25));

        // input siempre existe en esta página
        WebElement box = wait.until(ExpectedConditions.elementToBeClickable(SEARCH_INPUT));

        // Anti-stale: relocaliza antes de cada acción
        for (int i = 0; i < 3; i++) {
            try {
                box = wait.until(ExpectedConditions.refreshed(ExpectedConditions.elementToBeClickable(SEARCH_INPUT)));
                box.clear();
                box.sendKeys("Selenium (software)");
                box.sendKeys(Keys.ENTER);
                break;
            } catch (StaleElementReferenceException e) {
                if (i == 2) throw e;
            }
        }

        // aterrizaremos en la página del artículo o en resultados de búsqueda; validamos ambos
        wait.until(d -> d.getTitle().toLowerCase().contains("selenium"));
        Assert.assertTrue(driver.getTitle().toLowerCase().contains("selenium"),
                "El título no contiene 'Selenium'. Título actual: " + driver.getTitle());
    }
}

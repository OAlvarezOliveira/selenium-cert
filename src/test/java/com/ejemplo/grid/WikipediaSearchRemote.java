package com.ejemplo.grid;

import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.*;
import org.testng.Assert;
import org.testng.annotations.Test;
import java.time.Duration;

public class WikipediaSearchRemote extends BaseGridTest {

    @Test
    public void searchWikipedia() {
        driver.manage().window().maximize();
        driver.get("https://es.wikipedia.org/wiki/Wikipedia:Portada");

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(15));

        try {
            By acceptBtn = By.cssSelector("#onetrust-accept-btn-handler, button[aria-label*='Aceptar']");
            wait.withTimeout(Duration.ofSeconds(5))
                .until(ExpectedConditions.elementToBeClickable(acceptBtn)).click();
        } catch (Exception ignored) {}

        WebElement box = tryFindVisible(wait,
                By.id("searchInput"),
                By.cssSelector("input[type='search']"),
                By.name("search"));

        if (box == null) {
            try {
                By toggle = By.cssSelector("button[aria-controls='p-search'], button[aria-label*='Buscar'], a[aria-label*='Buscar']");
                wait.until(ExpectedConditions.elementToBeClickable(toggle)).click();
            } catch (Exception ignored) {}
            box = tryFindVisible(wait, By.id("searchInput"), By.name("search"));
        }

        Assert.assertNotNull(box, "No se encontró la caja de búsqueda");
        box.sendKeys("Selenium (software)");
        box.sendKeys(Keys.ENTER);

        wait.until(ExpectedConditions.titleContains("Selenium"));
        Assert.assertTrue(driver.getTitle().contains("Selenium"));
    }

    private WebElement tryFindVisible(WebDriverWait wait, By... locators) {
        for (By by : locators) {
            try {
                WebElement e = wait.until(ExpectedConditions.presenceOfElementLocated(by));
                if (e.isDisplayed() && e.isEnabled()) return e;
            } catch (Exception ignored) {}
        }
        return null;
    }
}

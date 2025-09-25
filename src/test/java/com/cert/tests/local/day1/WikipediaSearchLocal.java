package com.cert.tests.local.day1;

import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.*;
import org.testng.annotations.Test;

import java.time.Duration;

public class WikipediaSearchLocal {

    @Test(groups = "day1")
    public void wikipediaSearch() {
        WebDriver driver = new ChromeDriver();
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(15));

        try {
            driver.manage().window().maximize();
            driver.get("https://es.wikipedia.org/wiki/Wikipedia:Portada");

            // Aceptar cookies si aparece
            try {
                By accept = By.cssSelector("#onetrust-accept-btn-handler, button[aria-label*='Aceptar']");
                wait.withTimeout(Duration.ofSeconds(5))
                    .until(ExpectedConditions.elementToBeClickable(accept)).click();
            } catch (Exception ignored) {
                wait.withTimeout(Duration.ofSeconds(15));
            }

            // Input de búsqueda (varía según skin)
            WebElement box = tryFindVisible(wait,
                    By.id("searchInput"),
                    By.name("search"),
                    By.cssSelector("form[role='search'] input[name='search']")
            );
            if (box == null) {
                // Desplegar buscador si está colapsado
                try {
                    By toggle = By.cssSelector("button[aria-controls='p-search'], button[aria-label*='Buscar'], a[aria-label*='Buscar']");
                    wait.until(ExpectedConditions.elementToBeClickable(toggle)).click();
                } catch (Exception ignored) {}
                box = tryFindVisible(wait, By.id("searchInput"), By.name("search"));
            }

            if (box == null) throw new NoSuchElementException("No se encontró el input de búsqueda.");

            box.sendKeys("Selenium (software)");
            box.sendKeys(Keys.ENTER);

            wait.until(ExpectedConditions.titleContains("Selenium"));
            System.out.println("Título: " + driver.getTitle());
        } finally {
            driver.quit();
        }
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

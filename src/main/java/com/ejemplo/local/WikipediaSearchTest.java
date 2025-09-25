package com.ejemplo.local;

import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.*;

import java.time.Duration;

public class WikipediaSearchTest {
    public static void main(String[] args) {
        WebDriver driver = new ChromeDriver();
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(15));
        try {
            driver.manage().window().maximize();
            driver.get("https://es.wikipedia.org/wiki/Wikipedia:Portada");

            try {
                By accept = By.cssSelector("#onetrust-accept-btn-handler, button[aria-label*='Aceptar']");
                wait.withTimeout(Duration.ofSeconds(5)).until(ExpectedConditions.elementToBeClickable(accept)).click();
            } catch (Exception ignored) { wait.withTimeout(Duration.ofSeconds(15)); }

            WebElement box = wait.until(ExpectedConditions.presenceOfElementLocated(By.id("searchInput")));
            box.sendKeys("Selenium (software)");
            box.sendKeys(Keys.ENTER);

            wait.until(ExpectedConditions.titleContains("Selenium"));
            System.out.println("Título: " + driver.getTitle());
        } finally {
            driver.quit();
        }
    }
}

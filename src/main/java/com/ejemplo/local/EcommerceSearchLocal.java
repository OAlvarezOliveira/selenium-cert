package com.ejemplo.local;

import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.*;

import java.time.Duration;

public class EcommerceSearchLocal {
    public static void main(String[] args) {
        WebDriver driver = new ChromeDriver();
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));
        try {
            driver.manage().window().maximize();
            driver.get("https://ecommerce-playground.lambdatest.io/");
            WebElement search = wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector("input[name='search']")));
            search.sendKeys("iPhone");
            search.sendKeys(Keys.ENTER);
            wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.cssSelector(".product-thumb")));
            System.out.println("Resultados listos.");
        } finally {
            driver.quit();
        }
    }
}

package com.cert.tests.local.day1;

import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.*;
import org.testng.annotations.Test;

import java.time.Duration;

public class EcommerceSearchLocal {

    @Test(groups = "day1")
    public void searchIphone() {
        WebDriver driver = new ChromeDriver();
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));

        try {
            driver.manage().window().maximize();
            driver.get("https://ecommerce-playground.lambdatest.io/");

            WebElement search = wait.until(
                    ExpectedConditions.elementToBeClickable(By.cssSelector("input[name='search']"))
            );
            search.sendKeys("iPhone");
            search.sendKeys(Keys.ENTER);

            wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.cssSelector(".product-thumb")));
            System.out.println("Resultados listos.");
        } finally {
            driver.quit();
        }
    }
}

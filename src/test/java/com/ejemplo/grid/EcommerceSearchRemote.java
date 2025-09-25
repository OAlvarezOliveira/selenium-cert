package com.ejemplo.grid;

import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.*;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.time.Duration;

public class EcommerceSearchRemote extends BaseGridTest {

    @Test
    public void searchAndOpenProduct() {
        driver.manage().window().maximize();
        driver.get("https://ecommerce-playground.lambdatest.io/");
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));

        WebElement search = wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector("input[name='search']")));
        search.sendKeys("iPhone");
        search.sendKeys(Keys.ENTER);

        wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.cssSelector(".product-thumb")));
        WebElement first = wait.until(ExpectedConditions.elementToBeClickable(
            By.xpath("(//div[contains(@class,'product-thumb')]//a[@title])[1]")
        ));
        String productName = first.getAttribute("title");
        first.click();

        WebElement h1 = wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("h1")));
        Assert.assertTrue(h1.getText().toLowerCase().contains("iphone"));
        System.out.println("Detalle de producto: " + productName);
    }
}

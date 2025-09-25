package com.cert.tests.grid.day1;

import com.cert.tests.grid.BaseGridTest;
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.*;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.time.Duration;

public class EcommerceSearchRemote extends BaseGridTest {

    @Test(groups = "day1")
    public void searchAndOpenProduct() {
        driver.manage().window().maximize();
        driver.get("https://ecommerce-playground.lambdatest.io/");

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30));

        // Buscador
        WebElement search = wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector("input[name='search']")));
        search.sendKeys("iPhone");
        search.sendKeys(Keys.ENTER);

        // Esperar resultados y usar un locator más estable: el <h4><a> del producto
        wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.cssSelector(".product-thumb")));
        By firstProductLink = By.cssSelector(".product-thumb h4 a");

        // Asegurar visibilidad y clickabilidad; si el click normal falla, usar JS
        WebElement first = wait.until(ExpectedConditions.visibilityOfElementLocated(firstProductLink));
        scrollIntoView(first);
        try {
            wait.until(ExpectedConditions.elementToBeClickable(firstProductLink)).click();
        } catch (Exception clickBlocked) {
            jsClick(first);
        }

        WebElement h1 = wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("h1")));
        Assert.assertTrue(h1.getText().toLowerCase().contains("iphone"));
        System.out.println("Detalle de producto: " + h1.getText());
    }

    private void scrollIntoView(WebElement e) {
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView({block:'center',inline:'center'});", e);
    }

    private void jsClick(WebElement e) {
        ((JavascriptExecutor) driver).executeScript("arguments[0].click();", e);
    }
}

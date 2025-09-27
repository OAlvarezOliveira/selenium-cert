package com.cert.tests.grid.day1;

import com.cert.tests.grid.BaseGridTest;
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.time.Duration;

public class EcommerceSearchRemote extends BaseGridTest {

    @Test(groups = {"day1"})
    public void searchAndOpenProduct() {
        // 1) Ir al home
        driver.get("https://ecommerce-playground.lambdatest.io/");
        waitForDocumentReady();

        // 2) Buscar "iPhone"
        By searchBox = By.name("search");
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(15));
        WebElement input = wait.until(ExpectedConditions.visibilityOfElementLocated(searchBox));
        input.clear();
        input.sendKeys("iPhone");
        input.sendKeys(Keys.ENTER);

        // 3) Esperar resultados y clicar el producto "iPhone" de forma robusta (anti-stale)
        By iphoneResult = By.xpath("//h4/a[contains(.,'iPhone')]");
        wait.until(ExpectedConditions.presenceOfElementLocated(iphoneResult));
        wait.until(ExpectedConditions.visibilityOfElementLocated(iphoneResult));
        safeClick(iphoneResult); // <- reencuentra + retry + fallback JS

        // 4) Validar el H1 de la ficha
        By h1 = By.tagName("h1");
        String title = wait.until(ExpectedConditions.visibilityOfElementLocated(h1)).getText().trim();
        System.out.println("Detalle de producto: " + title);
        Assert.assertTrue(title.toLowerCase().contains("iphone"), "El H1 no contiene 'iPhone'");
    }

    /**
     * Click robusto:
     * - Reencuentra SIEMPRE el elemento a partir del locator (nunca guarda WebElement viejo).
     * - Usa elementToBeClickable + refreshed para evitar staleness.
     * - 3 reintentos si el DOM se refresca.
     * - Click nativo primero; si está bloqueado, fallback a click por JS.
     */
    private void safeClick(By locator) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(12));
        int attempts = 0;
        while (attempts < 3) {
            try {
                WebElement el = wait.until(ExpectedConditions.refreshed(
                        ExpectedConditions.elementToBeClickable(locator)));

                // Asegura visibilidad en viewport
                ((JavascriptExecutor) driver)
                        .executeScript("arguments[0].scrollIntoView({block:'center', inline:'center'})", el);

                try {
                    el.click(); // intenta click nativo
                } catch (WebDriverException e) {
                    // Fallback a JS si algo lo bloquea (banners, overlays, etc.)
                    ((JavascriptExecutor) driver).executeScript("arguments[0].click();", el);
                }
                return; // Éxito
            } catch (StaleElementReferenceException e) {
                attempts++; // DOM cambió → reintenta
            }
        }
        // Si no pudo tras 3 intentos, deja el error claro
        throw new StaleElementReferenceException("No se pudo clicar tras reintentos: " + locator);
    }

    /** Espera a que el document.readyState sea 'complete' */
    private void waitForDocumentReady() {
        new WebDriverWait(driver, Duration.ofSeconds(15)).until(d ->
                "complete".equals(((JavascriptExecutor) d).executeScript("return document.readyState")));
    }
}

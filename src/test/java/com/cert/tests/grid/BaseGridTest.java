package com.cert.tests.grid;

import org.openqa.selenium.MutableCapabilities;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.ITestResult;
import org.testng.annotations.*;

import java.lang.reflect.Method;
import java.net.URL;
import java.time.Duration;
import java.util.HashMap;
import java.util.Map;

public class BaseGridTest {

    protected RemoteWebDriver driver;

    @Parameters({"browserName", "browserVersion", "platformName"})
    @BeforeMethod(alwaysRun = true)
    public void setup(@Optional("Chrome") String browserName,
                      @Optional("latest") String browserVersion,
                      @Optional("Windows 11") String platformName,
                      Method testMethod) throws Exception {

        String username = System.getenv("LT_USERNAME");
        String accessKey = System.getenv("LT_ACCESS_KEY");
        if (username == null || accessKey == null) {
            throw new IllegalStateException("Define LT_USERNAME y LT_ACCESS_KEY como variables de entorno (Eclipse Run Config > Environment).");
        }

        // Capacidades W3C
        MutableCapabilities caps = new MutableCapabilities();
        caps.setCapability("browserName", browserName);
        caps.setCapability("browserVersion", browserVersion);
        caps.setCapability("platformName", platformName);

        // LT:Options — artefactos y metadatos requeridos por la certificación
        Map<String, Object> ltOptions = new HashMap<>();
        ltOptions.put("user", username);
        ltOptions.put("accessKey", accessKey);

        ltOptions.put("build", "Cert-101-Java");
        ltOptions.put("project", "Selenium-Cert");
        ltOptions.put("name", testMethod.getDeclaringClass().getSimpleName() + "." + testMethod.getName());

        // ✅ Artefactos obligatorios
        ltOptions.put("network", true);       // network logs
        ltOptions.put("video", true);         // video recording
        ltOptions.put("visual", true);        // screenshots
        ltOptions.put("console", "true");     // console logs

        // Otros útiles
        ltOptions.put("selenium_version", "4.35.0"); // o el que uses en tu POM
        ltOptions.put("w3c", true);
        ltOptions.put("timezone", "Europe/Madrid");
        ltOptions.put("idleTimeout", 120);

        caps.setCapability("LT:Options", ltOptions);

        URL hub = new URL("https://" + username + ":" + accessKey + "@hub.lambdatest.com/wd/hub");
        driver = new RemoteWebDriver(hub, caps);

        // Timeouts
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(0));
        driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(300));
        driver.manage().timeouts().scriptTimeout(Duration.ofSeconds(30));

        System.out.println("LT Session ID: " + driver.getSessionId());
    }

    @AfterMethod(alwaysRun = true)
    public void teardown(ITestResult result) {
        try {
            if (driver != null) {
                String status = (result.getStatus() == ITestResult.SUCCESS) ? "passed" : "failed";
                // ✅ Marca el estado del job en LambdaTest
                try {
                    ((JavascriptExecutor) driver).executeScript("lambda-status=" + status);
                } catch (Exception ignore) {
                    // No rompas el build si por lo que sea no se intercepta
                }

                // (Opcional) NOTAS — desactivadas para evitar el SyntaxError del guion (-)
                // Si quisieras probar, deja esto en comentario:
                // try {
                //     String reason = (result.getThrowable() == null) ? "All assertions passed"
                //                     : result.getThrowable().getMessage();
                //     // Algunas cuentas necesitan URL-encode; aun así podría no interceptarse siempre.
                //     String encoded = java.net.URLEncoder.encode(reason, java.nio.charset.StandardCharsets.UTF_8);
                //     ((JavascriptExecutor) driver).executeScript("lambda-notes=" + encoded);
                // } catch (Exception ignore) { }
            }
        } finally {
            if (driver != null) driver.quit();
        }
    }

}


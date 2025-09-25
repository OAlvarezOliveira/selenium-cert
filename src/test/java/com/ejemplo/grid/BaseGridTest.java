package com.ejemplo.grid;

import org.openqa.selenium.MutableCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.annotations.*;

import java.net.URL;
import java.time.Duration;
import java.util.HashMap;
import java.util.Map;

public class BaseGridTest {

    protected RemoteWebDriver driver;

    @Parameters({"browserName", "browserVersion", "platformName"})
    @BeforeMethod(alwaysRun = true)
    public void setup(String browserName, String browserVersion, String platformName) throws Exception {
        String username = System.getProperty("LT_USERNAME", System.getenv("LT_USERNAME"));
        String accessKey = System.getProperty("LT_ACCESS_KEY", System.getenv("LT_ACCESS_KEY"));

        if (username == null || accessKey == null) {
            throw new IllegalStateException("Faltan credenciales LT_USERNAME / LT_ACCESS_KEY en variables de entorno.");
        }

        // W3C caps
        MutableCapabilities caps = new MutableCapabilities();
        caps.setCapability("browserName", browserName);
        caps.setCapability("browserVersion", browserVersion);
        caps.setCapability("platformName", platformName);

        // LT:Options (habilitamos logs, vídeo, screenshots, consola)
        Map<String, Object> ltOptions = new HashMap<>();
        ltOptions.put("project", "Selenium Certification Prep");
        ltOptions.put("build", "Build-1");
        ltOptions.put("name", this.getClass().getSimpleName());
        ltOptions.put("selenium_version", "4.14.0");
        ltOptions.put("console", "true");     // console logs
        ltOptions.put("network", true);       // network logs
        ltOptions.put("video", true);         // video recording
        ltOptions.put("visual", true);        // screenshots
        ltOptions.put("w3c", true);
        ltOptions.put("timezone", "Madrid");

        caps.setCapability("LT:Options", ltOptions);

        URL hub = new URL("https://" + username + ":" + accessKey + "@hub.lambdatest.com/wd/hub");
        driver = new RemoteWebDriver(hub, caps);
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(0));
        driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(300));
        driver.manage().timeouts().scriptTimeout(Duration.ofSeconds(30));
    }

    @AfterMethod(alwaysRun = true)
    public void teardown() {
        if (driver != null) driver.quit();
    }
}

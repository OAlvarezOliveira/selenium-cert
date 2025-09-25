package com.cert.tests.grid;

import org.openqa.selenium.MutableCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Parameters;

import java.net.URL;
import java.time.Duration;
import java.util.HashMap;
import java.util.Map;

public class BaseGridTest {

    protected RemoteWebDriver driver;

    @Parameters({"browserName", "browserVersion", "platformName"})
    @BeforeMethod(alwaysRun = true)
    public void setup(String browserName, String browserVersion, String platformName) throws Exception {
        String username  = System.getenv("LT_USERNAME");
        String accessKey = System.getenv("LT_ACCESS_KEY");
        if (username == null || accessKey == null) {
            throw new IllegalStateException("Faltan variables LT_USERNAME / LT_ACCESS_KEY");
        }

        // W3C caps
        MutableCapabilities caps = new MutableCapabilities();
        caps.setCapability("browserName", browserName);
        caps.setCapability("browserVersion", browserVersion);
        caps.setCapability("platformName", platformName);

        // LambdaTest options: evidencias y metadata
        Map<String, Object> lt = new HashMap<>();
        lt.put("project", "Selenium Certification Prep");
        lt.put("build", "Build-1");
        lt.put("name", this.getClass().getSimpleName());
        lt.put("w3c", true);
        lt.put("console", "true");   // console logs
        lt.put("network", true);     // network logs
        lt.put("video", true);       // video recording
        lt.put("visual", true);      // screenshots
        lt.put("timezone", "Madrid");
        caps.setCapability("LT:Options", lt);

        URL hub = new URL("https://" + username + ":" + accessKey + "@hub.lambdatest.com/wd/hub");
        driver = new RemoteWebDriver(hub, caps);

        // timeouts estándar
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(0));
        driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(300));
        driver.manage().timeouts().scriptTimeout(Duration.ofSeconds(30));

        System.out.println("LT Session ID: " + driver.getSessionId());
    }

    @AfterMethod(alwaysRun = true)
    public void teardown() {
        if (driver != null) driver.quit();
    }
}

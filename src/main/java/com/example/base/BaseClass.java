package com.example.base;

import java.time.Duration;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

public class BaseClass {
    public static WebDriver driver;
    private static final ThreadLocal<WebDriver> threadLocalDriver = new ThreadLocal<>();

    public static void setup(String browser, String url) {
        WebDriver webDriver;
        switch (browser.toLowerCase()) {
            case "chrome":
                webDriver = new ChromeDriver();
                break;
            case "edge":
                webDriver = new EdgeDriver();
                break;
            case "firefox":
                webDriver = new FirefoxDriver();
                break;
            default:
                throw new IllegalArgumentException("Invalid Browser Name: " + browser);
        }
        threadLocalDriver.set(webDriver);
        driver = webDriver;
        getDriver().manage().window().maximize();
        getDriver().manage().deleteAllCookies();
        getDriver().manage().timeouts().implicitlyWait(Duration.ofSeconds(6));
        getDriver().get(url);
    }

    public static WebDriver getDriver() {
        return threadLocalDriver.get() != null ? threadLocalDriver.get() : driver;
    }

    public static void tearDown() {
        WebDriver webDriver = getDriver();
        if (webDriver != null) {
            webDriver.quit();
            threadLocalDriver.remove();
            driver = null;
        }
    }

}

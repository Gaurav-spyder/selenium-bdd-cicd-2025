package com.selenium.bdd.utilities;

import java.time.Duration;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import com.selenium.bdd.config.ConfigReader;

import io.github.bonigarcia.wdm.WebDriverManager;

public class DriverFactory {

    public static final ThreadLocal<WebDriver> driver = new ThreadLocal<>();

    public static WebDriver getDriver() {
        return driver.get();
    }

    public static void initilization() {
        String browser = ConfigReader.getProperty("browser");

        switch (browser.toLowerCase()) {

        case "chrome":
            WebDriverManager.chromedriver().setup();
            ChromeOptions options = new ChromeOptions();

            // IMPORTANT FOR GITHUB ACTIONS CI/CD
            if (isCIEnvironment()) {
                options.addArguments("--headless=new");
                options.addArguments("--no-sandbox");
                options.addArguments("--disable-dev-shm-usage");
                options.addArguments("--disable-gpu");
                options.addArguments("--disable-extensions");
                options.addArguments("--window-size=1920,1080");
            }

            driver.set(new ChromeDriver(options));
            break;

        default:
            throw new RuntimeException("Browser not supported: " + browser);
        }

        getDriver().manage().timeouts()
                .implicitlyWait(Duration.ofSeconds(Long.parseLong(ConfigReader.getProperty("implicitWait"))));
        getDriver().manage().window().maximize();
    }

    public static void quitDriver() {
        if (getDriver() != null) {
            getDriver().quit();
            driver.remove();
        }
    }

    // Detect if test is running inside GitHub Actions
    private static boolean isCIEnvironment() {
        return System.getenv("GITHUB_ACTIONS") != null;
    }
}

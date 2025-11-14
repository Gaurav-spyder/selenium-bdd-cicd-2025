package com.selenium.bdd.hooks;

import com.selenium.bdd.config.ConfigReader;
import com.selenium.bdd.utilities.DriverFactory;
import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Paths;

public class Hooks {

    @Before
    public void setUp(Scenario scenario) {
        DriverFactory.initilization();
        String url = ConfigReader.getProperty("baseUrl");
        DriverFactory.getDriver().get(url);

        System.out.println("----- STARTING SCENARIO: " + scenario.getName() + " -----");
    }

    @After
    public void tearDown(Scenario scenario) {

        try {
            if (scenario.isFailed()) {

                // ⭐ 1. Take screenshot as bytes for report
                byte[] screenshotBytes = ((TakesScreenshot) DriverFactory.getDriver())
                        .getScreenshotAs(OutputType.BYTES);

                scenario.attach(screenshotBytes, "image/png", "Failed Screenshot");

                // ⭐ 2. Save screenshot to disk
                File screenshotFolder = new File("reports/extent/screenshots");
                screenshotFolder.mkdirs();

                File srcFile = ((TakesScreenshot) DriverFactory.getDriver())
                        .getScreenshotAs(OutputType.FILE);

                String fileName = "reports/extent/screenshots/" 
                        + scenario.getName().replace(" ", "_")
                        + "_" + System.currentTimeMillis() + ".png";

                Files.copy(srcFile.toPath(), Paths.get(fileName));
            }

            if (scenario.isFailed()) {
                System.out.println("❌ FAILED → " + scenario.getName());
            } else {
                System.out.println("✅ PASSED → " + scenario.getName());
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        if (DriverFactory.getDriver() != null) {
            DriverFactory.getDriver().quit();
        }

        System.out.println("----- END SCENARIO: " + scenario.getName() + " -----");
    }
}

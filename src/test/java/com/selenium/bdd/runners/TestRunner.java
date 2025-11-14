package com.selenium.bdd.runners;

import org.testng.annotations.DataProvider;

import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;

@CucumberOptions(
        features = "src/test/resources/features",
        glue = {"com.selenium.bdd.stepDefinitions", "com.selenium.bdd.hooks"},
        plugin = {
                "pretty",
                "html:reports/cucumberhtmlreport.html",
                "com.aventstack.extentreports.cucumber.adapter.ExtentCucumberAdapter:"
        },
        monochrome = true
)

public class TestRunner extends AbstractTestNGCucumberTests {
@Override
@DataProvider(parallel=true)
public Object [][] scenarios(){
	return super.scenarios();
}

}

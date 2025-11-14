package com.selenium.bdd.stepDefinitions;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.openqa.selenium.Alert;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;

import com.selenium.bdd.pageObjects.LoginPage;
import com.selenium.bdd.utilities.DriverFactory;
import com.selenium.bdd.utilities.waitUtils;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class LoginSteps {
	WebDriver driver = DriverFactory.getDriver();
	LoginPage loginPage;
	
	Map<String, String> userData = new HashMap<>();

	public  LoginSteps(){
		loginPage = new LoginPage(driver);
	}
	
	 @Given("User is on Login Page")
	    public void user_is_on_login_page() {
		 String title=driver.getTitle();
		   Assert.assertTrue(title.contains("STORE"));
	
}
	 @When("User click on Login button")
	 public void user_click_on_login_button() {
	     	 	loginPage.clickOnLoginButton();
	 }
	 @Then("User will see Login page")
	 public void user_will_see_login_page() {
	     	 	loginPage.checkForLoginPage();
	 }
	 @When("User give Login Username and Password")
	 public void user_give_username_and_password() {
		 List<Map<String, String>> loginData =waitUtils.readDataFromExcel("src/test/resources/testdata/signupdata.xlsx", "logindata");
		 Map<String, String> row = loginData.get(0);
		    String username = row.get("username");
		    String password = row.get("password");
		    loginPage.login(username, password);
	 }
	 @When("click on Login button")
	 public void click_on_login_button() {
	     loginPage.clickOnLoginBtn();
	 }
	 @Then("User will see popup as Login successful message")
	 public void user_will_see_popup_as_login_successful_message() throws InterruptedException {
		 
	 }
}

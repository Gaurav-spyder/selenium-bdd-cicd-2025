package com.selenium.bdd.stepDefinitions;

import java.util.List;
import java.util.Map;

import org.openqa.selenium.Alert;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;

import com.selenium.bdd.pageObjects.SignupPage;
import com.selenium.bdd.utilities.DriverFactory;
import com.selenium.bdd.utilities.waitUtils;

import io.cucumber.java.en.*;

public class SignupSteps {
WebDriver driver = DriverFactory.getDriver();
SignupPage signupPage;


public  SignupSteps(){
	signupPage = new SignupPage(driver);
		
}
@Given("User is on Sign Page")
public void user_is_on_sign_page() throws InterruptedException {
 
   String title=driver.getTitle();
   Assert.assertTrue(title.contains("STORE"));
  
}

@When("User click on Signup button")
public void user_click_on_signup_button() {
    

	signupPage.clickOnSignupButton();
}

@Then("User will see Signup page")
public void user_will_see_signup_page() {
    

	signupPage.checkForSignUpPage();
}

@When("User give Signup Username and Password")
public void user_give_username_and_password() {
    
	List<Map<String, String>> loginData =waitUtils.readDataFromExcel("src/test/resources/testdata/signupdata.xlsx", "signupdata");
	Map<String, String> row = loginData.get(0);
    String username = row.get("username");
    String password = row.get("password");
    signupPage.login(username, password);
}

@When("click on Signup button")
public void click_on_signup_button() {
   signupPage.clickOnSignupBtn();
}

@Then("User will see popup as Signup successful message")
public void user_will_see_popup_as_signup_successful_message() throws InterruptedException {
   
Thread.sleep(2000);
    Alert al = driver.switchTo().alert();
    String alertText=al.getText();
    if(!alertText.contains("Sign up successful.")) {
    	throw new AssertionError("Unexpected alert message "+alertText);
    }
    al.accept();
}

}

package com.selenium.bdd.pageObjects;

import java.time.Duration;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import com.selenium.bdd.utilities.waitUtils;

public class LoginPage {
	WebDriver driver;
	WebDriverWait wait;
	 public LoginPage(WebDriver driver) {
	        this.driver = driver;
	        PageFactory.initElements(driver, this);
	        wait = new WebDriverWait(driver, Duration.ofSeconds(10));
	    }
	 
	 @FindBy(xpath="//a[text()='Log in']")
	 WebElement loginBtn;
	 
	 @FindBy(id="logInModalLabel")
	 WebElement logInModalLabel;
	 
	 @FindBy(id="loginusername")
	 WebElement loginusername;
	 
	 @FindBy(id="loginpassword")
	 WebElement loginpassword;
	 
	 @FindBy(xpath="//button[text()='Log in']")
	 WebElement loginButton;
	 
	 public void clickOnLoginButton() {
	    	waitUtils.waitForElementToBeVisible(driver, loginBtn, 10);
	    	loginBtn.click();
	    }
	 public void checkForLoginPage() {
	    	waitUtils.waitForElementToBeVisible(driver, logInModalLabel, 10);
	    	Assert.assertTrue(logInModalLabel.getText().contains("Log in"));
	    }
	 
	 public void login(String uName, String pass) {
	      waitUtils.waitForElementToBeVisible(driver, loginusername, 10);
	      loginusername.sendKeys(uName);
	      waitUtils.waitForElementToBeVisible(driver, loginpassword, 10);
	      loginpassword.sendKeys(pass);
	    }
	 public void clickOnLoginBtn() {
	       waitUtils.waitForElementToBeVisible(driver, loginButton, 10);
	       loginButton.click();
	    }
	 
	 
}

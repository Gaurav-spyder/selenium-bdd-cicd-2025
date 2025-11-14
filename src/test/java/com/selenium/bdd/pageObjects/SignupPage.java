package com.selenium.bdd.pageObjects;

import java.time.Duration;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.selenium.bdd.utilities.waitUtils;

public class SignupPage {
    WebDriver driver;
    WebDriverWait wait;

    public SignupPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
        wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }

    @FindBy(id = "signin2")
    WebElement signupBtn;

    @FindBy(id = "signInModalLabel")
    WebElement signupHeading;

    @FindBy(id = "sign-username")
    WebElement userName;

    @FindBy(id = "sign-password")
    WebElement password;

    @FindBy(xpath = "//button[text()='Sign up']")
    WebElement signUpButton;


    public void clickOnSignupButton() {
    	waitUtils.waitForElementToBeVisible(driver, signupBtn, 1);
    	signupBtn.click();
        //wait.until(ExpectedConditions.elementToBeClickable(signupBtn)).click();
    }

    
    public boolean checkForSignUpPage() {
        try {
            wait.until(ExpectedConditions.visibilityOf(signupHeading));
            return signupHeading.getText().contains("Sign up");
        } catch (Exception e) {
            return false;
        }
    }

    
    public void login(String uName, String pass) {
        wait.until(ExpectedConditions.visibilityOf(userName)).sendKeys(uName);
        wait.until(ExpectedConditions.visibilityOf(password)).sendKeys(pass);
    }

    
    public void clickOnSignupBtn() {
        wait.until(ExpectedConditions.elementToBeClickable(signUpButton)).click();
    }
}

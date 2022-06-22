package com.alexnova.kathi.oke.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class LoginPage {

    WebDriver driver;
    By registerButtonField = By.xpath("//*[@id=\"keyboard-nav-3\"]/div[2]/div[3]/a");
    By loginButtonField = By.xpath("//*[@id=\"customer_login\"]/div[3]/input");
    By emailField = By.name("customer[email]");
    By passwordField = By.name("customer[password]");
    By errorBannerField = By.xpath("//*[@id=\"customer_login\"]/p");

    public LoginPage(WebDriver driver) {
        this.driver = driver;
    }

    public RegisterPage clickRegisterButton()
    {;
        driver.findElement(registerButtonField).click();
        return new RegisterPage(driver);
    }
    public void setEmailField(String email)
    {
        driver.findElement(emailField).sendKeys(email);
    }
    public void setPasswordField(String password)
    {
        driver.findElement(passwordField).sendKeys(password);
    }
    public AccountPage clickLoginButton()
    {
        driver.findElement(loginButtonField).click();
        return new AccountPage(driver);
    }
    public String getErrorBanner()
    {
        return driver.findElement(errorBannerField).getText();
    }

}

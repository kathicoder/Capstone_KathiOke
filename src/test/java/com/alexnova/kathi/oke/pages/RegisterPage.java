package com.alexnova.kathi.oke.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class RegisterPage {

    WebDriver driver;
    By firstNameField = By.id("ispbxii_1");
    By lastNameField = By.id("ispbxii_2");
    By emailField = By.id("ispbxii_3");
    By passwordField = By.name("customer[password]");
    By registerButton = By.xpath("//*[@id=\"create_customer\"]/div[5]/input");
    By pageTitleField = By.className("page-title");

    public RegisterPage(WebDriver driver) {
        this.driver = driver;
    }
    public String checkPageTitle()
    {
        return driver.findElement(pageTitleField).getText();
    }
    public void setFirstNameField(String name)
    {
        driver.findElement(firstNameField).sendKeys(name);
    }
    public void setLastNameField(String name)
    {
        driver.findElement(lastNameField).sendKeys(name);
    }
    public void setEmailField(String email)
    {
        driver.findElement(emailField).sendKeys(email);
    }
    public void setPasswordField(String password)
    {
        driver.findElement(passwordField).sendKeys(password);
    }
    public void clickRegisterButton()
    {
        driver.findElement(registerButton).click();
    }
}

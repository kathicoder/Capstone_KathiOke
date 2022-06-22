package com.alexnova.kathi.oke.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class ShippingPage {

    WebDriver driver;
    By continueButton = By.id("continue_button");
    public ShippingPage(WebDriver driver) {
        this.driver = driver;
    }

    public PaymentPage clickContinueButton()
    {
        driver.findElement(continueButton).click();
        return new PaymentPage(driver);
     }

}

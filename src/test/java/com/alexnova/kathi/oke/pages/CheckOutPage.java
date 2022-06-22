package com.alexnova.kathi.oke.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;

public class CheckOutPage {

    WebDriver driver;
    By quantityField = By.className("product-thumbnail__quantity");
    By discountField = By.id("checkout_reduction_code");
    By discountEnteredField = By.className("reduction-code__text");
    By emailField = By.id("checkout_email");
    By firstNameField = By.id("checkout_shipping_address_first_name");
    By lastNameField = By.id("checkout_shipping_address_last_name");
    By addressField = By.id("checkout_shipping_address_address1");
    By cityField = By.id("checkout_shipping_address_city");
    By zipField = By.id("checkout_shipping_address_zip");
    By continueButton = By.id("continue_button");
    public CheckOutPage(WebDriver driver) {
        this.driver = driver;
    }

    public String getQuantity()
    {
        return driver.findElement(quantityField).getText();
    }
    public void setDiscountField(String code)
    {
        driver.findElement(discountField).sendKeys(code + Keys.ENTER);
    }
    public String getDiscountEnteredField()
    {
        return driver.findElement(discountEnteredField).getText();
    }
    public void setEmailField(String email)
    {
        driver.findElement(emailField).sendKeys(email);
    }
    public void setFirstNameField(String fname)
    {
        driver.findElement(firstNameField).sendKeys(fname);
    }
    public void setLastNameField(String lname)
    {
        driver.findElement(lastNameField).sendKeys(lname);
    }
    public void setAddressField(String address)
    {
        driver.findElement(addressField).sendKeys(address);
    }
    public void setCityField(String city)
    {
        driver.findElement(cityField).sendKeys(city);
    }
    public void setZipField(String zip)
    {
        driver.findElement(zipField).sendKeys(zip);
    }
    public ShippingPage clickContinueButton()
    {
        driver.findElement(continueButton).click();
        return new ShippingPage(driver);
    }

}

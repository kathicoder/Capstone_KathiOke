package com.alexnova.kathi.oke.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class ClearancePage {

    WebDriver driver;
    By productTitleField = By.linkText("Sabrina Plush Skirt Leggings");

    public ClearancePage(WebDriver driver) {
        this.driver = driver;
    }
    public ProductPage clickProductTitle()
    {
        driver.findElement(productTitleField).click();
        return new ProductPage(driver);
    }

}

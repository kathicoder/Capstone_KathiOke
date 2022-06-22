package com.alexnova.kathi.oke.pages;

import org.checkerframework.checker.units.qual.C;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class AccountPage {

    WebDriver driver;
    By titleField = By.className("page-title");
    By clearanceLink = By.id("navigation-clearance");
    public AccountPage(WebDriver driver) {
        this.driver = driver;
    }

    public String getPageTitle()
    {
        return driver.findElement(titleField).getText();
    }
    public ClearancePage clickClearanceLink()
    {
        driver.findElement(clearanceLink).click();
        return new ClearancePage(driver);
    }
}

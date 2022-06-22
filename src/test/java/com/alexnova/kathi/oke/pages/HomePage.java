package com.alexnova.kathi.oke.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;

public class HomePage {
    WebDriver driver;
    public HomePage(WebDriver driver) {
        this.driver = driver;
    }
    By accountLinkField = By.id("customer_login_link");
    By searchField = By.id("ispbxii_0");
    By searchButtonField = By.className("header-search-button");
    By springSummerItemsLink = By.id("navigation-s-s-2022");

    public LoginPage clickAccountLink()
    {
        driver.findElement(accountLinkField).click();
        return new LoginPage(driver);
    }
    public SearchResultsPage setSearchField(String letter)
    {
        driver.findElement(searchField).sendKeys(letter +Keys.ENTER);
        return new SearchResultsPage(driver);
    }
    public SearchResultsPage clickSearchButton()
    {
        driver.findElement(searchButtonField).click();
        return new SearchResultsPage(driver);
    }
    public SpringSummerPage clickSSItems()
    {
        driver.findElement(springSummerItemsLink).click();
        return new SpringSummerPage(driver);
    }

}

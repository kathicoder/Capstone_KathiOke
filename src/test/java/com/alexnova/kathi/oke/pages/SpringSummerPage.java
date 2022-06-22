package com.alexnova.kathi.oke.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class SpringSummerPage {
    WebDriver driver;
    By productLink = By.linkText("Let's Learn Animals Early Educational Reading Cloth Books");

    public SpringSummerPage(WebDriver driver) {
        this.driver = driver;
    }
    public ProductPage clickProductLink()
    {
        driver.findElement(productLink).click();
        return new ProductPage(driver);
    }

}

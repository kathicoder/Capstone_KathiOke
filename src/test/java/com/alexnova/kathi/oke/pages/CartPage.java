package com.alexnova.kathi.oke.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;

public class CartPage {

    WebDriver driver;

    By cartQuantityField = By.name("updates[]");
    By cartUpdateButton = By.xpath("//*[@id=\"shopify-section-cart\"]/section/form/div/div/div[2]/input");
    By itemPriceField = By.xpath("//*[contains(@data-title,'Price')]");
//    By itemTotalField = By.xpath("//*[@id=\"shopify-section-cart\"]/section/form/table/tbody/tr/td[4]/span/span");

    By itemTotalField = By.xpath("//*[contains(@data-title,'Total')]");
    //*[contains(@attribute_name,'attribute_value')]
    By emptyMessageField = By.xpath("//*[@id=\"shopify-section-cart\"]/section/p");

    public CartPage(WebDriver driver) {
        this.driver = driver;
    }
    public String getQuantity()
    {
        return driver.findElement(cartQuantityField).getAttribute("value");
    }
    public  void clearQuantity()
    {
        driver.findElement(cartQuantityField).clear();
    }
    public void setQuantity(String quantity)
    {
        driver.findElement(cartQuantityField).sendKeys(quantity + Keys.ENTER);
    }
    public void clickCartUpdate()
    {
        driver.findElement(cartUpdateButton).click();
    }
    public String getItemPrice()
    {
        return driver.findElement(itemPriceField).getText();
    }
    public String getItemTotal()
    {
        return driver.findElement(itemTotalField).getText();
    }
    public String getEmptyMessage()
    {
        return driver.findElement(emptyMessageField).getText();
    }

}

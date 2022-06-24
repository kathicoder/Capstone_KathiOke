package com.alexnova.kathi.oke.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class ProductPage {
    WebDriver driver;
    By productPriceField = By.className("money");
    By productSizeField = By.xpath("//*[@id=\"bcpo-select-option-0\"]/div[2]/label");
    By productColorField = By.xpath("//*[@id=\"bcpo-select-option-1\"]/div[5]/label");
    By addToCartButton = By.className("add-to-cart");
    By buyNowButton = By.className("alexnova-moreOptions-btn");
    By cartLink = By.linkText("cart");
    By checkoutLink = By.linkText("check out");
    By bookTypeField = By.xpath("//*[@id=\"bcpo-select-option-0\"]/div[3]/label");

    public ProductPage(WebDriver driver) {
        this.driver = driver;
    }
    public String getProductPrice()
    {
       return driver.findElement(productPriceField).getText();
    }
    public void clickSizeField()
    {
       driver.findElement(productSizeField).click();
    }
    public void clickColorField()
    {
        driver.findElement(productColorField).click();
    }
    public void clickAddToCart()
    {
        driver.findElement(addToCartButton).click();
    }
    public CheckOutPage clickBuyNowButton()
    {
        driver.findElement(buyNowButton).click();
        return new CheckOutPage(driver);
    }
    public CartPage clickCartLink()
    {
        driver.findElement(cartLink).click();
        return new CartPage(driver);
    }
    public void clickBookTypeField()
    {
        driver.findElement(bookTypeField).click();
    }
    public CheckOutPage clickCheckOutLink()
    {
        driver.findElement(checkoutLink).click();
        return new CheckOutPage(driver);
    }
}

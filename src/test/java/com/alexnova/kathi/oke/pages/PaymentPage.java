package com.alexnova.kathi.oke.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
public class PaymentPage {

    WebDriver driver;
    By visaField = By.xpath("//li[@class='payment-icon payment-icon--visa']");
    By mastercardField = By.xpath("//li[@class='payment-icon payment-icon--master']");
    By paypalField = By.xpath("//div[@data-gateway-group='express']");
    By shopPayField = By.xpath("/html/body/div/div/div/main/div[1]/div/form/div[1]/div[2]/div[2]/fieldset/div[4]/div[2]/label/span");
    By expDateField = By.id("expiry");
    By nameOnCardField = By.id("name");
    By securityField = By.name("verification_value");
    By creditCardNumberField = By.id("number");
    By cardNumberFrame = By.xpath("*//iframe[contains(@id,'card-fields-number')]");
    By verificationCodeFrame = By.xpath("*//iframe[contains(@id,'card-fields-verification_value')]");
    By expDateFrame = By.xpath("*//iframe[contains(@id,'card-fields-expiry')]");
    By cardNameFrame = By.xpath("*//iframe[contains(@id,'card-fields-name')]");
    By payNowButton = By.id("continue_button");
    By noticeField = By.xpath("/html/body/div/div/div/main/div[1]/div/form/div[1]/div[2]/div[2]/div/p");

    public PaymentPage(WebDriver driver) {
        this.driver = driver;
    }
    public String getVisaField()
    {
        return driver.findElement(visaField).getAttribute("data-payment-icon");
    }
    public String getMastercardField()
    {
        return driver.findElement(mastercardField).getAttribute("data-payment-icon");
    }
    public String getPaypalField()
    {
        return driver.findElement(paypalField).getAttribute("data-gateway-name");
    }
    public String getShopPayField()
    {
        return driver.findElement(shopPayField).getText();
    }
    public void switchToCardNameFrame()
    {
        WebElement iframe = driver.findElement(cardNameFrame);
        driver.switchTo().frame(iframe);
    }
    public void switchToVerificationCodeFrame()
    {
        WebElement iframe = driver.findElement(verificationCodeFrame);
        driver.switchTo().frame(iframe);
    }
    public void switchToCardNumberFrame()
    {
        WebElement iframe = driver.findElement(cardNumberFrame);
        driver.switchTo().frame(iframe);
    }
    public void switchToExpDateFrame()
    {
        WebElement iframe = driver.findElement(expDateFrame);
        driver.switchTo().frame(iframe);
    }
    public void setExpDateField(String date)
    {
        driver.findElement(expDateField).sendKeys(date);
    }
    public void setSecurityField(String code)
    {
        driver.findElement(securityField).sendKeys(code);
    }
    public void setCreditCardNumberField(String ccnum)
    {
        driver.findElement(creditCardNumberField).sendKeys(ccnum);
    }
    public void setNameOnCardField(String name)
    {
        driver.findElement(nameOnCardField).sendKeys(name);
    }
    public void clickPayNowButton()
    {
        driver.findElement(payNowButton).click();
    }
    public String getNoticeField()
    {
        return driver.findElement(noticeField).getText();
    }
}

package com.alexnova.kathi.oke.testcases;

import com.alexnova.kathi.oke.library.SelectBrowser;
import com.alexnova.kathi.oke.pages.*;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import java.time.Duration;
public class CheckOutTests extends BaseTests{
    HomePage homePage;
    SpringSummerPage springSummerPage;
    ProductPage productPage;
    CheckOutPage checkOutPage;
    ShippingPage shippingPage;
    PaymentPage paymentPage;

    @BeforeClass
    @Parameters("browserName")
    public void start_class(String browserName)
    {
        driver = SelectBrowser.StartBrowser(browserName);
        driver.manage().deleteAllCookies();
        driver.get("chrome://settings/clearBrowserData");
        driver.findElement(By.xpath("//settings-ui")).sendKeys(Keys.ENTER);
        driver.get("https://www.alexandnova.com/");
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30));
    }

    //***********************************************************************************************
    // This test asserts that a discount code can be applied at checkout
    //**********************************************************************************************
    @Test(priority = 16, enabled = true)
    public void tc0016_verify_discount_code_application_test()
    {
        homePage = new HomePage(driver);
        springSummerPage = homePage.clickSSItems();
        JavascriptExecutor jse = (JavascriptExecutor)driver;
        jse.executeScript("window.scrollBy(0,400)");
        productPage = springSummerPage.clickProductLink();
        productPage.clickBookTypeField();
        JavascriptExecutor jse1 = (JavascriptExecutor)driver;
        jse1.executeScript("window.scrollBy(0,400)");
        productPage.clickAddBookToCart();
        checkOutPage = productPage.clickCheckOutLink();
        String expectedDiscountEntered = "OHBABY15";
        checkOutPage.setDiscountField(expectedDiscountEntered);
        String actualDiscountEntered =  checkOutPage.getDiscountEnteredField();
        Assert.assertEquals(actualDiscountEntered, expectedDiscountEntered);
    }
    //***********************************************************************************************
    // This test asserts that payment modes are available at checkout
    //**********************************************************************************************
    @Test(priority = 17, enabled = true)
    public void tc0017_verify_payment_modes_test()
    {
        checkOutPage = new CheckOutPage(driver);
        checkOutPage.setEmailField("Peach@comcast.net");
        checkOutPage.setFirstNameField("Peach");
        checkOutPage.setLastNameField("Livefish");
        checkOutPage.setAddressField("1234 Shearwater Pt.");
        checkOutPage.setCityField("Sydney");
        checkOutPage.setZipField("80023");
        shippingPage = checkOutPage.clickContinueButton();
        paymentPage = shippingPage.clickContinueButton();
        String actualMastercardValue = paymentPage.getMastercardField();
        String actualVisaValue = paymentPage.getVisaField();
        String expectedMastercardValue = "master";
        String expectedVisaValue = "visa";
        Assert.assertEquals(actualMastercardValue, expectedMastercardValue);
        Assert.assertEquals(actualVisaValue, expectedVisaValue);
        String actualPaypalValue = paymentPage.getPaypalField();
        String expectedPaypalValue = "paypal";
        Assert.assertEquals(actualPaypalValue, expectedPaypalValue);
        String actualShopPayValue = paymentPage.getShopPayField();
        System.out.println("Actual ShopPay value = " +actualShopPayValue);
        String expectedShopPayValue = "Shop Pay";
        Assert.assertTrue(actualShopPayValue.contains(expectedShopPayValue));
    }

    //***********************************************************************************************
    // This test asserts that error message is received if any required CC fields are missing
    //**********************************************************************************************
    @Test(priority = 18, enabled = true)
    public void tc0018_negative_credit_card_field_test()
    {
        paymentPage = new PaymentPage(driver);
        paymentPage.switchToCardNumberFrame();
        paymentPage.setCreditCardNumberField("5544778833229911");
        driver.switchTo().parentFrame();
        paymentPage.switchToCardNameFrame();
        paymentPage.setNameOnCardField("John Fink");
        driver.switchTo().parentFrame();
        paymentPage.switchToVerificationCodeFrame();
        paymentPage.setSecurityField("678");
        driver.switchTo().parentFrame();
        paymentPage.clickPayNowButton();
        JavascriptExecutor jse1 = (JavascriptExecutor)driver;
        jse1.executeScript("window.scrollBy(0,-400)");
        driver.switchTo().parentFrame();

        String actualNotice = paymentPage.getNoticeField();
        String expectedNotice = "Your payment details couldn’t be verified. Check your card details and try again.";
        Assert.assertTrue(actualNotice.contains(expectedNotice));
    }
    //***********************************************************************************************
    // This test asserts that error message is received if invalid CC information is submitted
    //**********************************************************************************************
    @Test(priority = 19, enabled = true)
    public void tc0019_negative_credit_card_info_test()
    {
        paymentPage = new PaymentPage(driver);
        paymentPage.switchToCardNumberFrame();
        paymentPage.setCreditCardNumberField("36985214769874");
        driver.switchTo().parentFrame();
        paymentPage.switchToCardNameFrame();
        paymentPage.setNameOnCardField("John Fink");
        driver.switchTo().parentFrame();
        paymentPage.switchToVerificationCodeFrame();
        paymentPage.setSecurityField("222");
        driver.switchTo().parentFrame();
        paymentPage.switchToExpDateFrame();
        paymentPage.setExpDateField("06");
        paymentPage.setExpDateField("2023");
        driver.switchTo().parentFrame();
        JavascriptExecutor jse1 = (JavascriptExecutor)driver;
        jse1.executeScript("window.scrollBy(0,400)");
        paymentPage.clickPayNowButton();
        String actualNotice = paymentPage.getNoticeField();
        String expectedNotice = "Your payment details couldn’t be verified. Check your card details and try again.";
        Assert.assertTrue(actualNotice.contains(expectedNotice));
    }

    @AfterClass
    public void closeBrowser()
    {
        driver.quit();
    }
}

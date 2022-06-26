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
import java.text.DecimalFormat;
import java.time.Duration;

public class ProductAddToCartTests extends BaseTests{

    private static final DecimalFormat dfZero = new DecimalFormat("0.00");

    ProductPage productPage;
    CheckOutPage checkOutPage;
    LoginPage loginPage;
    AccountPage accountPage;
    ClearancePage clearancePage;
    CartPage cartPage;

    //****************************************************************************************************
    //  Logging into the website so item can be added to cart as registered user
    //****************************************************************************************************
    @BeforeClass
    @Parameters("browserName")
    public void start_class(String browserName)
    {
        driver = SelectBrowser.StartBrowser(browserName);
        driver.manage().deleteAllCookies();
        driver.get("chrome://settings/clearBrowserData");
        driver.findElement(By.xpath("//settings-ui")).sendKeys(Keys.ENTER);
        driver.get("https://www.alexandnova.com/account/login");
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30));

    }
    //***********************************************************************************************
    // This test asserts that the product page display price
    //**********************************************************************************************
    @Test(priority = 10, enabled = true)
    public void tc0010_validate_product_price_test() throws InterruptedException {
        accountPage = new AccountPage(driver);
        clearancePage = accountPage.clickClearanceLink();
        productPage = clearancePage.clickProductTitle();
        Thread.sleep(3000);
        String actualPrice = productPage.getProductPrice();
        System.out.println("Actual Price = " +actualPrice);
        String expectedPrice = "$34.95 USD";
        Assert.assertEquals(actualPrice, expectedPrice);
    }
    //***********************************************************************************************
    // This test asserts that the product is added to the cart
    //**********************************************************************************************
    @Test(priority = 11, enabled = true)
    public void tc0011_validate_product_add_to_cart_test()
    {
        productPage = new ProductPage(driver);
        driver.manage().window().maximize();
        productPage.clickSizeField();
        productPage.clickColorField();
        productPage.clickAddToCart();
        JavascriptExecutor jse = (JavascriptExecutor)driver;
        jse.executeScript("window.scrollBy(0,400)");
        cartPage = productPage.clickCartLink();
        String actualQuantity = cartPage.getQuantity();
        Assert.assertTrue(actualQuantity.contentEquals("1"));
    }
    //***********************************************************************************************
    // This test asserts that the product is still in cart after page refresh
    //**********************************************************************************************
    @Test(priority = 12, enabled = true)
    public void tc0012_validate_product_present_in_cart_test()
    {
        cartPage = new CartPage(driver);
        String quantityBeforeRefresh = cartPage.getQuantity();
        driver.navigate().refresh();
        String quantityAfterRefresh = cartPage.getQuantity();
        Assert.assertEquals(quantityAfterRefresh, quantityBeforeRefresh);
    }
    //***********************************************************************************************
    // This test asserts that the quantity in the cart can be increased
    //**********************************************************************************************
    @Test(priority = 13, enabled = true)
    public void tc0013_quantity_increase_in_cart_test()
    {
        cartPage = new CartPage(driver);
        cartPage.clearQuantity();
        String quantitySet = "3";
        cartPage.setQuantity(quantitySet);
        JavascriptExecutor jse = (JavascriptExecutor)driver;
        jse.executeScript("window.scrollBy(0,400)");
        cartPage.clickCartUpdate();
        String quantityAfterSet = cartPage.getQuantity();
        Assert.assertEquals(quantityAfterSet, quantitySet);
    }
    //***********************************************************************************************
    // This test asserts that the quantity * price = total amount in cart
    //**********************************************************************************************
    @Test(priority = 14, enabled = true)
    public void tc0014_verify_quantity_matches_cost_in_cart_test()
    {
        cartPage = new CartPage(driver);
        Integer quantity = Integer.valueOf(cartPage.getQuantity());

        String price = cartPage.getItemPrice();
        String priceSplit = price.substring(1,6);
        Double numericPrice = Double.parseDouble(priceSplit);
        String total = cartPage.getItemTotal();
        String totalSplit = total.substring(1,7);
        Double numericTotal = Double.parseDouble(totalSplit);
        Double expectedTotal = (numericPrice * quantity);
        Double expectedRounded  = Math.round(expectedTotal*100.0)/100.0;

        Assert.assertEquals(numericTotal, expectedRounded);
    }
    //***********************************************************************************************
    // This test asserts that the quantity can be removed from the cart
    //**********************************************************************************************
    @Test(priority = 15, enabled = true)
    public void tc0015_verify_quantity_can_be_removed_from_cart_test() throws InterruptedException {
        cartPage = new CartPage(driver);
        cartPage.clearQuantity();
        String quantitySet = "0";
        cartPage.setQuantity(quantitySet);
        Thread.sleep(3000);
        String actualMessage = cartPage.getEmptyMessage();
        String expectedMessage = "You don't have any items in your cart yet.";
        Assert.assertTrue(actualMessage.contains(expectedMessage));
    }
}

package com.alexnova.kathi.oke.testcases;

import com.alexnova.kathi.oke.library.SelectBrowser;
import com.alexnova.kathi.oke.pages.AccountPage;
import com.alexnova.kathi.oke.pages.HomePage;
import com.alexnova.kathi.oke.pages.LoginPage;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.testng.Assert;
import org.testng.annotations.*;
import java.time.Duration;

public class LoginTests extends BaseTests{
    LoginPage loginPage;
    HomePage homePage;
    AccountPage accountPage;

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
    // This test asserts that login is working
    //**********************************************************************************************
    @Test(priority = 6, enabled = true)
    public void tc0006_verify_user_login() throws InterruptedException {
        homePage = new HomePage(driver);
        loginPage = homePage.clickAccountLink();
        loginPage.setEmailField("SM06@gmail.com");
        loginPage.setPasswordField("Meeple");
        accountPage = loginPage.clickLoginButton();
        //***********************************************************************
        // Thread.sleep added to manually respond to captcha
        //***********************************************************************
        Thread.sleep(5000);

        String actualTitle = accountPage.getPageTitle();
        System.out.println("actual title = " +actualTitle);
        String expectedTitle = "Welcome, Shelby";
        System.out.println("expected title = " +expectedTitle);
        Assert.assertTrue(actualTitle.contains(expectedTitle));
    }
    //***********************************************************************************************
    // This test asserts that login fails when incorrect email is used
    //**********************************************************************************************
    @Test(priority = 7, enabled = true)
    public void tc0007_verify_invalid_login()
    {
        driver.get("https://www.alexandnova.com/account/login");

        loginPage = new LoginPage(driver);
        loginPage.setEmailField("SM01@gmail.com");
        loginPage.setPasswordField("Meeple");
        loginPage.clickLoginButton();
        String actualErrorBanner = loginPage.getErrorBanner();
        String expectedError = "Sorry! Please try that again.";

        Assert.assertEquals(actualErrorBanner, expectedError);
    }

    @AfterClass
    public void closeBrowser()
    {
        driver.quit();
    }
}

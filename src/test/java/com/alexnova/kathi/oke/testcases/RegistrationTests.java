package com.alexnova.kathi.oke.testcases;

import com.alexnova.kathi.oke.library.SelectBrowser;
import com.alexnova.kathi.oke.pages.AccountPage;
import com.alexnova.kathi.oke.pages.HomePage;
import com.alexnova.kathi.oke.pages.LoginPage;
import com.alexnova.kathi.oke.pages.RegisterPage;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.markuputils.ExtentColor;
import com.aventstack.extentreports.markuputils.MarkupHelper;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.testng.Assert;
import org.testng.annotations.*;
import java.time.Duration;

public class RegistrationTests extends BaseTests{
    HomePage homePage;
    LoginPage loginPage;
    RegisterPage registerPage;
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
    // This test asserts that a new user is able to get to the registration page from the home page
    //**********************************************************************************************
    @Test(priority = 1, enabled = true)
    public void tc0001_get_Registration_Page_Test()
    {
        homePage = new HomePage(driver);
        loginPage = homePage.clickAccountLink();
        registerPage = loginPage.clickRegisterButton();
        String actualPageTitle = registerPage.checkPageTitle();
        String expectedPageTitle = "Sign up";
        Assert.assertEquals(actualPageTitle, expectedPageTitle);
    }

    //***********************************************************************************************
    // This test asserts that a new user is able to register
    //**********************************************************************************************
    @Test(priority = 2, enabled = true)
    public void tc0002_registration_New_User_Test() throws InterruptedException {
        // enter user info on signup page
        registerPage = new RegisterPage(driver);
        registerPage.setFirstNameField("Shelby");
        registerPage.setLastNameField("Oke");
        registerPage.setEmailField("SMO19@gmail.com");
        registerPage.setPasswordField("Meeple");
        registerPage.clickRegisterButton();
        //***********************************************************************
        // Thread.sleep added to manually respond to captcha
        //***********************************************************************
        Thread.sleep(6000);

        // sign in as new user
        loginPage = new LoginPage(driver);
        loginPage.setEmailField("SMO19@gmail.com");
        loginPage.setPasswordField("Meeple");
        loginPage.clickLoginButton();
        //***********************************************************************
        // Thread.sleep added to manually respond to captcha
        //***********************************************************************
        Thread.sleep(6000);
        accountPage = new AccountPage(driver);
        String actualTitle = accountPage.getPageTitle();
        String expectedTitle = "Welcome, Shelby";
        Assert.assertTrue(actualTitle.contains(expectedTitle));
    }

    @DataProvider (name = "data-provider")
    public Object[][] getEmail(){
        return new Object[][] {{"Without @", "testgmail.com"}, {"without period", "test@gmailcom"},
                {"with random characters", "mwmfkguh"}, {"with AT instead of @", "susanATgmail.com"}};
    }

    //***********************************************************************************************
    // This test asserts that email validation is working
    //**********************************************************************************************
    @Test(priority = 3, enabled = true, dataProvider = "data-provider")
    public void tc0003_registration_verify_email(String desc, String email) throws InterruptedException {
        driver.get("https://www.alexandnova.com/account/register");

        System.out.println("Passed Parameter Desc is : " + desc);
        System.out.println("Passed Parameter Is : " + email);
        //**********************************************************
        // Try to register with email without @ sign, without period,
        // with random string, and with AT instead of @
        //******************************************************************
        test.log(Status.INFO, MarkupHelper.createLabel("Email validation using email " +desc, ExtentColor.ORANGE));

        registerPage = new RegisterPage(driver);
        registerPage.setFirstNameField("Susan");
        registerPage.setLastNameField("Smith");
        registerPage.setEmailField(email);
        registerPage.setPasswordField("campergirl");
        registerPage.clickRegisterButton();
        //***********************************************************************
        // Thread.sleep added to manually respond to captcha
        //***********************************************************************
        Thread.sleep(5000);


        loginPage = new LoginPage(driver);

        String actualTitle = loginPage.getErrorBanner();
        String expectedTitle = "Sorry! Please try that again.";
        Assert.assertTrue(actualTitle.contains(expectedTitle));
    }
    //***********************************************************************************************
    // This test asserts that mandatory field validation is working
    //**********************************************************************************************
    @Test(priority = 4, enabled = true)
    public void tc0004_verify_mandatory_fields()
    {
        driver.get("https://www.alexandnova.com/account/register");

        // Leave user input fields blank and try to register
        registerPage = new RegisterPage(driver);
        registerPage.clickRegisterButton();
        loginPage = new LoginPage(driver);
        String actualErrorBanner = loginPage.getErrorBanner();
        String expectedError = "Sorry! Please try that again.";

        Assert.assertEquals(actualErrorBanner, expectedError);
    }
    //***********************************************************************************************
    // This test asserts that password validation is working
    //**********************************************************************************************
    @Test(priority = 5, enabled = true)
    public void tc0005_verify_password_validation() {
        driver.get("https://www.alexandnova.com/account/register");

        // enter user info on signup page
        registerPage = new RegisterPage(driver);
        registerPage.setFirstNameField("Stefanie");
        registerPage.setLastNameField("Oke");
        registerPage.setEmailField("SAMO3@gmail.com");
        registerPage.setPasswordField("pass");
        registerPage.clickRegisterButton();
        // sign in as new user
        loginPage = new LoginPage(driver);
        String actualErrorBanner = loginPage.getErrorBanner();
        System.out.println("Actual Error = " +actualErrorBanner);
        String expectedError = "Sorry! Please try that again.";
        System.out.println("Expected Title = " +expectedError);

        Assert.assertEquals(actualErrorBanner, expectedError);
    }

    @AfterClass
    public void closeBrowser()
    {
        driver.quit();
    }
}

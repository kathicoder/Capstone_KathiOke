package com.alexnova.kathi.oke.testcases;

import com.alexnova.kathi.oke.library.SelectBrowser;
import com.alexnova.kathi.oke.pages.HomePage;
import com.alexnova.kathi.oke.pages.SearchResultsPage;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import java.time.Duration;

public class SearchProductsTests extends BaseTests {
    HomePage homePage;
    SearchResultsPage searchResultsPage;

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
    // This test asserts that the search field returns search results
    //**********************************************************************************************
    @Test(priority = 8, enabled = true)
    public void tc0008_validate_search_test()
    {
        homePage = new HomePage(driver);
        searchResultsPage = homePage.setSearchField("baby shoes");
        String expectedResults = "baby shoes";
        String actualResults = searchResultsPage.getSearchResults();
        Assert.assertTrue(actualResults.contains(expectedResults));
    }
    //***********************************************************************************************
    // This test asserts that a blank search field returns no search results
    //**********************************************************************************************
    @Test(priority = 9, enabled = true)
    public void tc0009_blank_search_test()
    {
        driver.get("https://www.alexandnova.com/");
        homePage = new HomePage(driver);
        searchResultsPage = homePage.clickSearchButton();
        String actualBanner = searchResultsPage.getSearchBanner();
        System.out.println("Banner = " +actualBanner);
        String expectedBanner = "No results found. Showing top popular products you might want to consider...";
        Assert.assertEquals(actualBanner, expectedBanner);
    }
    @AfterClass
    public void closeBrowser()
    {
        driver.quit();
    }
}

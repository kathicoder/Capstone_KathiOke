package com.alexnova.kathi.oke.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class SearchResultsPage {
    WebDriver driver;
    By searchResultsField = By.xpath("//*[@id=\"keyboard-nav-3\"]/div[2]/div[1]/p");
    By searchBannerField = By.className("isp_no_results_title");

    public SearchResultsPage(WebDriver driver) {
        this.driver = driver;
    }

    public String getSearchResults()
    {
        return driver.findElement(searchResultsField).getText();
    }
    public String getSearchBanner()
    {
        return driver.findElement(searchBannerField).getText();
    }
}

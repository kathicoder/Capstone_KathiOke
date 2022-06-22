package com.alexnova.kathi.oke.library;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;

public class SelectBrowser {
    static WebDriver driver;

    public static WebDriver StartBrowser(String browser)
    {
        // ---If the browser is Firefox----
        if (browser.equalsIgnoreCase("Firefox")) {
            // Set the path for geckodriver.exe
            System.setProperty("webdriver.gecko.driver","resources/geckodriver.exe");
            driver = new FirefoxDriver();
        }
        //---- If the browser is Chrome--
        else if (browser.equalsIgnoreCase("Chrome")) {
            // Set the path for chromedriver.exe
            System.setProperty("webdriver.chrome.driver", "resources/chromedriver.exe");
            driver = new ChromeDriver();
        }
        // ----If the browser is  EdgeIE----
        else if (browser.equalsIgnoreCase("EdgeExplore")) {
            // Set the path for IEdriver
            System.setProperty("webdriver.edge.driver", "resources/msedgedriver.exe");
            // Instantiate a EdgeDriverclass.
            EdgeOptions options = new EdgeOptions();
            driver = new EdgeDriver(options);
        }
        driver.manage().window().maximize();
        return driver;
    }

}

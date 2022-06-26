package com.alexnova.kathi.oke.testcases;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;
import com.aventstack.extentreports.reporter.configuration.ChartLocation;
import com.aventstack.extentreports.reporter.configuration.Theme;
import com.google.common.io.Files;
import org.openqa.selenium.*;
import org.testng.ITestResult;
import org.testng.annotations.*;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.Method;

public class BaseTests {
    WebDriver driver;
    static ExtentHtmlReporter htmlReporter;
    static ExtentReports extent;
    static ExtentTest test;

    @BeforeSuite
    public void setUpReport()
    {
        htmlReporter = new ExtentHtmlReporter(System.getProperty("user.dir") + "/test-output/xml/CapstoneReports.html");
        extent = new ExtentReports();

        extent.attachReporter(htmlReporter);
        extent.setSystemInfo("Host Name", "Kathi-Dell-7586");
        extent.setSystemInfo("Environment", "QA");
        extent.setSystemInfo("User Name", "Kathi Oke");
        htmlReporter.config().setChartVisibilityOnOpen(true);
        htmlReporter.config().setDocumentTitle("Alex and Nova Automation Tests report");
        htmlReporter.config().setReportName("Alex and Nova Automation Tests Report");
        htmlReporter.config().setTestViewChartLocation(ChartLocation.TOP);
        htmlReporter.config().setTheme(Theme.DARK);
    }

    @BeforeMethod
    public void setupTestReports(Method method) throws IOException
    {
        String methodName = method.getName();
        test = extent.createTest(methodName);
        test.addScreenCaptureFromPath(methodName +".png");
    }

    @AfterMethod
    public void recordTestResults(ITestResult result) throws IOException {

        if(result.getStatus() == ITestResult.FAILURE)
        {
            File screenshotFile = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
            Files.move(screenshotFile, new File("test-output/xml/" +result.getName() +".png"));
            test.log(Status.FAIL,result.getThrowable());
        }
        else if(result.getStatus() == ITestResult.SUCCESS)
        {
            File screenshotFile = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
            Files.move(screenshotFile, new File("test-output/xml/" +result.getName() +".png"));
            test.log(Status.PASS, result.getTestName());
        }
        else
        {
            test.log(Status.SKIP, result.getTestName());
        }
    }
    @AfterClass
    public void closeBrowser()
    {
        driver.quit();
    }
    @AfterSuite
    public void tearDown()
    {
        extent.flush();
    }

}

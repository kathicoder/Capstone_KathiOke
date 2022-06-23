# Capstone_KathiOke
This project represents an automation testing capstone project. 

UI testing runs against Alex and Nova commerce website https://www.alexandnova.com/

## UI Project Technical Environment

Maven project in IntelliJ
Java 8
Selnium WebDriver
POM model
TestNG and Extent reports

## Dependencies added:

* org.seleniumhq.selenium,selenium-java, version 4.0
* org.testng,testng, version 6.14.3
* org.testng,reportng, version 1.2.2
* commons-io, commons-io, version 2.11
* com.aventstack, extentreports, version 3.1.5

## Plugins added:

* org.apache.maven.plugins, maven-compiler-plugin,version 3.5.1
* org.apache.maven.plugins, maven-surefire-plugin, version 3.0.0-M6

## Environment setup to use this project:
Chrome Chromedriver, Edge and Firefox browsers installed
(UI tests use Chrome by default, can be changed in config)
Have Jenkins installed
Java 8

## The project includes the following features:

implicit wait at each class level
a few Thread.sleep instances were added to deal with website captcha interference where manual intervention was required

## The following TestNG annotations are used:

@Parameter
used in the testng.xml file to pass browser choice 
@Test
used for each test case
@BeforeMethod
gets testcase name from Java Reflection and sets up extent report at test level and sets up screen shot png file
@AfterMethod
ITestResult is used to get result status of test and screenshots are taken

@BeforeClass
starts browser
deletes cookies
starts implicit wait
@AfterClass
quits driver 
@BeforeSuite
sets up extent reports 
@AfterSuite
flushes extent reports

The project is broken into separate modules for API and UI testing

API testing was run using: https://gorest.co.in/public/v2/users https://gorest.co.in/public/v2/posts http://api.openweathermap.org/data/2.5/weather?q=New York&appid=1f3c5ae0f38df8fd7bc09ad6874a403

Reports for each module are written into their respective /target directories after a successful run.

UI acceptance tests result in a HTML report for each feature in. In the case of test failures, a screen-shot of the UI at the point of failure is embedded into the report.

API acceptance tests result in a HTML report for each feature.

# Capstone_KathiOke
This project represents an automation testing capstone project. 
The project is broken into separate modules for API and UI testing
Reports for each module are written into their respective /target directories after a successful run.

UI testing runs against Alex and Nova commerce website https://www.alexandnova.com/
UI acceptance tests result in a HTML report for each feature in. In the case of test failures, a screen-shot of the UI at the point of failure is embedded into the report.

## UI Project Technical Environment

* Maven project in IntelliJ
* Java 8
* Selenium WebDriver
* POM model
* TestNG and Extent reports

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

* **@Parameter** <br />In the testng.xml file to pass browser choice 
* **@Test** <br />For each test case
* **@BeforeMethod** <br />Gets testcase name from Java Reflection and sets up extent report at test level and sets up screen shot png file
* **@AfterMethod** <br />ITestResult is used to get result status of test and screenshots are taken
* **@BeforeClass**<br />Starts browser<br />deletes cookies<br />starts implicit wait
* **@AfterClass** <br />Quits driver 
* **@BeforeSuite** <br />Sets up extent reports 
* **@AfterSuite** <br />Flushes extent reports
* **@DataProvider** <br />Used to rerun the email verification test case bringing in different email values

## UI Challenges

* **Captcha** <br /> Thread.sleep instances were added to deal with website captcha interference where manual intervention was required
* **Requirements** <br /> Incomplete requirements and missing details required some assumptions since there was no project manager to QA manager to refer to for clarification. Learned the importance of the requirements process.
* **No Test Environment** <br /> Testing in a live production environment was challenging since data and responses kept changing. Learned the importance of a good test environment.
* **Report creation** <br /> I wanted my reports to accurately reflect passes and fails and to show screenshots and logging. I researched these topics and taught myself about the ITestResults class and used it in my code.
* **Duplicate Code** <br /> I wanted to separate out duplicate code into a BaseTest class that could be inherited in all test classes. To accomplish this I needed to research using @Parameters for cross browser testing and using the Java Reflection Method to retrieve test case names to remove hard coding.<br /> Another way I reduced duplicate code was to use @Dataprovider to rerun the email verification test case bringing in different email values.
* **iframes** <br /> I already knew how to handle iframes but was challenged by the check out page where every credit card field was its own iframe. This was great iframe processing practice.
* **Locating elements** <br /> I had a hard time locating some of the elements that didn't have unique names. When I looked deeper into the fields that were used in the definition of some of these elements I discovered Custom Attributes. I had never heard of these before and taught myself how to reference these attributes to select them and retrieve data from them.  

## API Testing

API testing was run using: 
* https://gorest.co.in/public/v2/users
* https://gorest.co.in/public/v2/posts 
* http://api.openweathermap.org/data/2.5/weather?q=New%20York&appid=1f3c5ae0f38df8fd7bc09ad6874a4039


API tests were created in Postman. <br />Postman sharelink was run using newman and also run using Jenkins.<br />API tests result in a HTML report.

package com.training.seleniumutils;


import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import javax.swing.text.html.HTMLDocument;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.*;

public class SeleniumUtils {
    public WebDriver driver;
    protected static final String AUTOMATE_USERNAME = "sivakrishnamenik_2rEls3";
    protected static final String AUTOMATE_ACCESS_KEY = "J54LuSXjSv7BsDBezpeQ";
    protected static final String BROWSERSTACK_URL = "https://" + AUTOMATE_USERNAME + ":" + AUTOMATE_ACCESS_KEY
            + "@hub-cloud.browserstack.com/wd/hub";

    public static String getScreenshot(WebDriver driver, String screenshotName) throws Exception {

        String dateName = new SimpleDateFormat("yyyyMMddhhmmss").format(new Date());
        TakesScreenshot ts = (TakesScreenshot) driver;
        File source = ts.getScreenshotAs(OutputType.FILE);
        String destination = System.getProperty("user.dir") + "/TestScreenshots/" + screenshotName + dateName + ".png";
        File finalDestination = new File(destination);
        FileUtils.copyFile(source, finalDestination);
        Thread.sleep(1000);
        return destination;
    }

    public static String makeDir() {
        String timeStamp = new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss").format(new Date());
        File theDir = new File(System.getProperty("user.dir") + File.separator + "test-output"  + File.separator + "Test_ExecutionReport_" + timeStamp);
        if (!theDir.exists()) {
            theDir.mkdirs();
        }

        return theDir.toString();
    }
    public void browserLaunch(String browser, String urlValue,String testcaseName){
        browserLaunch(browser, urlValue, "windows", "10", "latest",testcaseName);
    }
    public void browserLaunch(String browser, String urlValue, String platform, String os_version, String version,String testcaseName) {
        DesiredCapabilities capabilities = new DesiredCapabilities();
        capabilities.setCapability("os", platform);
        capabilities.setCapability("os_version", os_version);
        capabilities.setCapability("resolution", "1920x1080");

        capabilities.setCapability("browser", browser);
        capabilities.setCapability("browser_version", version);
        capabilities.setCapability("browserstack.video", "true");
        capabilities.setCapability("browserstack.debug", "true");
        capabilities.setCapability("browserstack.selenium_version", "3.141.59");
        capabilities.setCapability("browserstack.console", "verbose");
        capabilities.setCapability("browserstack.networkLogs", "true");

        try {
            driver = new RemoteWebDriver(new URL(BROWSERSTACK_URL), capabilities);
            JavascriptExecutor jse = (JavascriptExecutor)driver;

            // Setting name of the test
            jse.executeScript("browserstack_executor: {\"action\": \"setSessionName\", \"arguments\": {\"name\":\""+testcaseName+" \" }}");

            driver.manage().window().maximize();
            driver.get(urlValue);
        } catch (MalformedURLException e) {
            System.out.println("Invalid grid URL");
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
//        }
    }

    public void elementClick(WebDriver driver, By locator, ExtentTest test, String logText) throws Exception {
        try {
            JavascriptExecutor js = (JavascriptExecutor) driver;
            js.executeScript("arguments[0].setAttribute('style', 'border: 2px solid red;');", driver.findElement(locator));
            WebDriverWait wait = new WebDriverWait(driver, 20);
            wait.until(ExpectedConditions.elementToBeClickable(driver.findElement(locator))).click();
            System.out.println("================================================");
            test.log(LogStatus.PASS, logText + test.addScreenCapture(getScreenshot(driver, "test")));
            Thread.sleep(1000);
        } catch (Exception e) {
            System.out.println("Exception occurred is " + e.getMessage());
            test.log(LogStatus.FAIL, e.getMessage() + test.addScreenCapture(getScreenshot(driver, "test")));
        }
    }

    public List<WebElement> elementList(WebDriver driver, By locator) {
        List<WebElement> list;
        try {
            list = driver.findElements(locator);
            return list;
        } catch (Exception e) {
            System.out.println("Exception occurred is " + e.getMessage());
        }
        return null;
    }

    public void enterText(WebDriver driver, By locator, String textValue, ExtentTest test, String logText) throws Exception {
        try {
            JavascriptExecutor js = (JavascriptExecutor) driver;
            js.executeScript("arguments[0].setAttribute('style', 'border: 2px solid red;');", driver.findElement(locator));
            WebDriverWait wait = new WebDriverWait(driver, 20);
            driver.findElement(locator).clear();
            wait.until(ExpectedConditions.elementToBeClickable(driver.findElement(locator))).sendKeys(textValue);
            test.log(LogStatus.PASS, logText + test.addScreenCapture(getScreenshot(driver, "test")));
        } catch (Exception e) {
            System.out.println("Exception occurred is " + e.getMessage());
            test.log(LogStatus.FAIL, e.getMessage() + test.addScreenCapture(getScreenshot(driver, "test")));
            org.junit.Assert.fail();
        }
    }

    public boolean verifyElementExist(WebDriver driver, By locator) {
        boolean flag = false;
        try {
            List<WebElement> el = driver.findElements(locator);
            System.out.println(el.size());
            if (el.size() > 0) {
                flag = true;
            }

            return flag;
        } catch (Exception e) {
            System.out.println("Exception occurred is " + e.getMessage());
            org.junit.Assert.fail();
            return flag;
        }

    }

    public boolean verifyElementExist(WebDriver driver, By locator, ExtentTest test, String passLogText, String logFailText, String screenshotName) throws Exception {
        boolean flag = false;
        boolean b = verifyElementExist(driver, locator);
        if (b) {
            JavascriptExecutor js = (JavascriptExecutor) driver;
            js.executeScript("arguments[0].setAttribute('style', 'border: 2px solid red;');", driver.findElement(locator));
            test.log(LogStatus.PASS, passLogText + test.addScreenCapture(getScreenshot(driver, screenshotName)));
            flag = true;
        } else {
            test.log(LogStatus.FAIL, logFailText + test.addScreenCapture(getScreenshot(driver, screenshotName)));
        }
        return flag;
    }

    public void verifyElementExistByWebElement(WebDriver driver, WebElement element, ExtentTest test, String passLogText, String screenshotName) throws Exception {
        try {
            JavascriptExecutor js = (JavascriptExecutor) driver;
            js.executeScript("arguments[0].setAttribute('style', 'border: 2px solid red;');", element);
            test.log(LogStatus.PASS, passLogText + test.addScreenCapture(getScreenshot(driver, screenshotName)));
        } catch (Exception e) {
            System.out.println("Exception occurred is " + e.getMessage());
            test.log(LogStatus.FAIL, e.getMessage() + test.addScreenCapture(getScreenshot(driver, "test")));
            org.junit.Assert.fail();
        }

    }

    public void mouseHover(WebDriver driver, By locator, ExtentTest test, String logText) throws Exception {
        try {
            Actions act = new Actions(driver);
            JavascriptExecutor js = (JavascriptExecutor) driver;
            js.executeScript("arguments[0].setAttribute('style', 'border: 2px solid red;');", driver.findElement(locator));
            act.moveToElement(driver.findElement(locator)).build().perform();
            test.log(LogStatus.INFO, logText + test.addScreenCapture(getScreenshot(driver, "test")));
        } catch (Exception e) {
            System.out.println("Exception occurred is " + e.getMessage());
            test.log(LogStatus.FAIL, e.getMessage() + test.addScreenCapture(getScreenshot(driver, "test")));
            org.junit.Assert.fail();
        }
    }

    public void mouseHoverClickElement(WebDriver driver, By locator, ExtentTest test, String logText) throws Exception{

        try {
            Actions act = new Actions(driver);
            JavascriptExecutor js = (JavascriptExecutor) driver;
            js.executeScript("arguments[0].setAttribute('style', 'border: 2px solid red;');", driver.findElement(locator));
            act.moveToElement(driver.findElement(locator)).click(driver.findElement(locator)).build().perform();
            test.log(LogStatus.INFO, logText + test.addScreenCapture(getScreenshot(driver, "test")));
        } catch (Exception e) {
            System.out.println("Exception occurred is " + e.getMessage());
            test.log(LogStatus.FAIL, e.getMessage() + test.addScreenCapture(getScreenshot(driver, "test")));
            org.junit.Assert.fail();
        }

    }

    public String propDataReader(String key) {
        String value;
        try {
            File file = new File("C:\\Users\\INDIAN\\Desktop\\JavaProject\\SeleniumJavaProject2\\src\\test\\resources\\Config.properties");
            FileInputStream fileInput = new FileInputStream(file);
            Properties properties = new Properties();
            properties.load(fileInput);
            fileInput.close();
            value = properties.getProperty(key);
            return value;

        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static boolean alertChecker(WebDriver driver, ExtentTest test) {
        try {
            Alert alert = driver.switchTo().alert();
            return true;
        }
        catch(Exception e) {
            System.out.println(e.getMessage());
            return false;
        }
    }
    public static void alertHandler(WebDriver driver, ExtentTest test, String alertAction){

        if(alertChecker(driver,test)){

            switch(alertAction){

                case "accept":

                    driver.switchTo().alert().accept();
                    break;
                case "dismiss":

                    driver.switchTo().alert().dismiss();
                    break;

            }
        }
    }

    public void dropdownSelect(WebDriver driver, By locator, String selectType, String selectValue){

        WebDriverWait wait = new WebDriverWait(driver,20);
        wait.until(ExpectedConditions.elementToBeClickable(driver.findElement(locator)));
        Select select = new Select(driver.findElement(locator));
        switch(selectType) {
            case "byText":
                select.selectByVisibleText(selectValue);
                break;

            case "byValue":
                select.selectByValue(selectValue);
                break;

            case "byIndex":
                select.selectByIndex(Integer.parseInt(selectValue));
        }
    }
    public void switchToWindowByIndex(WebDriver driver, int index) {
        try {
            Set<String> windowHandles = driver.getWindowHandles();
            List<String> windowStrings = new ArrayList<>(windowHandles);
            String reqWindow = windowStrings.get(index);
            driver.switchTo().window(reqWindow);
        }
        catch (Exception e){

            System.out.println(e.toString());
        }
    }

    public void switchToWindow(WebDriver driver) {
        try{

            String parentWindow = driver.getWindowHandle();
            Set<String> windows = driver.getWindowHandles();
            Iterator<String> itr = windows.iterator();
            while(itr.hasNext()){

                String windowName = itr.next();
                if(!windowName.equals(parentWindow)){

                    driver.switchTo().window(windowName);
                    break;
                }
            }

        }
        catch (Exception e){

            System.out.println(e.toString());
        }
    }


    public void windowhandler(WebDriver driver){

    }
    public void tearDown() {
        driver.quit();
    }

}

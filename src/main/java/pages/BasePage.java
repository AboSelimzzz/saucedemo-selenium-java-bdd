package pages;

import driver.DriverFactory;
import utils.LoggerUtil;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.*;
import utils.WaitUtil;

public class BasePage {

    protected WebDriver driver;

    private static final Logger log = LoggerUtil.getLogger(BasePage.class);

    public BasePage(){ this.driver = DriverFactory.getDriver();}

    protected void click(By locator){
        log.info("Clicking element: {}", locator);
        WaitUtil.waitForClickability(locator).click();
    }

    protected void type(By locator, String text){
        log.info("Typing '{}' into element: {}", text, locator);
        WebElement element = WaitUtil.waitForVisibility(locator);
        element.clear();
        element.sendKeys(text);
    }

    protected String getText(By locator){
        log.info("Getting text from element: {}", locator);
        return WaitUtil.waitForVisibility(locator).getText();
    }

    protected boolean isDisplayed(By locator){
        try{
            return driver.findElement(locator).isDisplayed();
        } catch (NoSuchElementException e) {
            log.warn("Element not found: {}", locator);
            return false;
        }
    }

    protected boolean isEnabled(By locator){
        try{
            return driver.findElement(locator).isEnabled();
        } catch (NoSuchElementException e) {
            log.warn("Element not found: {}", locator);
            return false;
        }
    }

    protected String getPageTitle(){
        log.info("Getting page title: {}", driver.getTitle());
        return driver.getTitle();
    }

    protected String getCurrentUrl(){
        log.info("Getting current url: {}", driver.getCurrentUrl());
        return driver.getCurrentUrl();
    }

    protected void navigateTo(String url){
        log.info("Navigating to: {}", url);
        driver.get(url);
    }

    protected void refreshPage(){
        log.info("Refreshing page");
        driver.navigate().refresh();
    }
}

package pages;

import driver.DriverFactory;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import utils.LoggerUtil;
import utils.WaitUtil;
import utils.WindowManager;

public class BasePage {

    protected WebDriver driver;

    protected final WindowManager windowManager;

    private static final Logger log = LoggerUtil.getLogger(BasePage.class);

    public BasePage(){
        this.driver = DriverFactory.getDriver();
         windowManager = new WindowManager(driver);
    }

    protected WebElement find(By locator){
        return driver.findElement(locator);
    }

    protected void click(By locator){
        log.debug("Clicking element: {}", locator);
        WaitUtil.waitForClickability(locator).click();
    }

    protected void type(By locator, String text){
        log.debug("Typing '{}' into element: {}", text, locator);
        WebElement element = WaitUtil.waitForVisibility(locator);
        element.clear();
        element.sendKeys(text);
    }

    protected String getText(By locator){
        log.debug("Getting text from element: {}", locator);
        return WaitUtil.waitForVisibility(locator).getText();
    }

    protected boolean isDisplayed(By locator){
        try{
            return find(locator).isDisplayed();
        } catch (NoSuchElementException e) {
            log.warn("Element not found: {}", locator);
            return false;
        }
    }

    protected boolean isEnabled(By locator){
        try{
            return find(locator).isEnabled();
        } catch (NoSuchElementException e) {
            log.warn("Element not found: {}", locator);
            return false;
        }
    }

    protected boolean isHighlighted(By locator){
        try{
            return find(locator).getAttribute("class").contains("error");
        } catch (NoSuchElementException e) {
            log.warn("Element not found: {}", locator);
            return false;
        }
    }
}

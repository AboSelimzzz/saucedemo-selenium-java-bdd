package utils;

import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;

import java.util.Set;

public class WindowManager {
    private final WebDriver driver;
    private final WebDriver.Navigation navigate;
    private static final Logger log = LoggerUtil.getLogger(WindowManager.class);

    public WindowManager(WebDriver driver){
        this.driver = driver;
        this.navigate = driver.navigate();
    }

    public String getPageTitle(){
        log.debug("Getting page title: {}", driver.getTitle());
        return driver.getTitle();
    }

    public String getCurrentUrl(){
        log.debug("Getting current url: {}", driver.getCurrentUrl());
        return driver.getCurrentUrl();
    }

    public void navigateTo(String url){
        log.debug("Navigating to: {}", url);
        driver.get(url);
    }

    public void refreshPage(){
        log.debug("Refreshing page");
        driver.navigate().refresh();
    }


    public void goBack(){
        log.debug("Going back");
        navigate.back();
    }

    public void goForward(){
        log.debug("Going forward");
        navigate.forward();
    }

    public Set<String> getCurrentWindows(){
        log.debug("Getting all the window handles");
        return driver.getWindowHandles();
    }

    public void switchToTab(String tabTitle){
        Set<String> windows = getCurrentWindows();

        for(String window : windows){
            log.debug("Switching to window: {}", window);
            driver.switchTo().window(window);
            log.debug("Current window title: {}", driver.getTitle());
            if(tabTitle.equals(driver.getTitle()))
                break;
        }
    }

    public void switchToNewTab(Set<String> existingWindows){
        for(String handle : getCurrentWindows())
            if (!existingWindows.contains(handle)) {
                driver.switchTo().window(handle);
                break;
            }
    }
}
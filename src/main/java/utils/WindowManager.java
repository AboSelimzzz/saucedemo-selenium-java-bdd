package utils;

import org.openqa.selenium.WebDriver;

import java.util.Set;

public class WindowManager {
    private final WebDriver driver;
    private final WebDriver.Navigation navigate;

    public WindowManager(WebDriver driver){
        this.driver = driver;
        this.navigate = driver.navigate();
    }

    public void goBack(){
        navigate.back();
    }

    public void goForward(){
        navigate.forward();
    }

    public void refresh(){
        navigate.refresh();
    }

    public void goTo(String url){
        navigate.to(url);
    }

    public Set<String> getCurrentWindows(){return driver.getWindowHandles();}

    public void switchToTab(String tabTitle){
        Set<String> windows = getCurrentWindows();

        System.out.println("Number of tabs: " + windows.size());

        System.out.println("Windows Handles:");
        windows.forEach(System.out::println);

        for(String window :windows){
            System.out.println("Switching to window: " + window);
            driver.switchTo().window(window);

            System.out.println("Current window title: " + driver.getTitle());
            if(tabTitle.equals(driver.getTitle()))
                break;
        }
    }
    public void switchToNewTab(Set<String> existingWindows){
        for(String handle: getCurrentWindows())
            if (!existingWindows.contains(handle)) {
                driver.switchTo().window(handle);
                break;
            }
    }
}
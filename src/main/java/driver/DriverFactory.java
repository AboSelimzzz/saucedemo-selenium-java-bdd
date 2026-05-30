package driver;

import config.ConfigReader;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;
import utils.LoggerUtil;

import java.time.Duration;

public class DriverFactory {
    private static final Logger log = LoggerUtil.getLogger(DriverFactory.class);

    private static WebDriver driver;

    private DriverFactory(){}

    public static WebDriver getDriver(){return driver;}

    public static void initDriver(){
        String browser = System.getProperty("browser") != null
                ? System.getProperty("browser").toLowerCase()
                : ConfigReader.get("browser").toLowerCase();
        log.info("Initializing browser: {}", browser);

        switch(browser){
            case "edge":
                EdgeOptions edgeOptions = new EdgeOptions();
                edgeOptions.addArguments("--start-maximized");
                edgeOptions.addArguments("--disable-notifications");
                driver = new EdgeDriver(edgeOptions);
                break;
            case "chrome":
                ChromeOptions chromeOptions = new ChromeOptions();
                chromeOptions.addArguments("--start-maximized");
                chromeOptions.addArguments("--disable-notifications");
                driver = new ChromeDriver(chromeOptions);
                break;
            default:
                throw new IllegalArgumentException(
                        "Browser '" + browser + "' not supported. Use: chrome or edge"
                );
        }
        long implicitWait = Long.parseLong(ConfigReader.get("implicitWait"));
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(implicitWait));
        log.debug("Browser launched successfully: {}", browser);
    }

    public static void quitDriver() {
        if (driver != null) {
            driver.quit();
            driver = null;  // ← simply set to null, no ThreadLocal cleanup needed
        }
    }
}

package features.support;


import config.ConfigReader;
import driver.DriverFactory;
import utils.*;
import io.cucumber.java.*;
import org.apache.logging.log4j.Logger;

public class Hooks {
    private static final Logger log = LoggerUtil.getLogger(Hooks.class);

    @Before
    public void setUp(Scenario scenario) {
        log.info("========== STARTING: {} ==========", scenario.getName());
        DriverFactory.initDriver();
        DriverFactory.getDriver().get(ConfigReader.get("baseUrl"));
        log.info("Navigated to: {}", ConfigReader.get("baseUrl"));
    }
    @After
    public void tearDown(Scenario scenario){
        if(scenario.isFailed()){
            log.error("Scenario Failed: {}", scenario.getName());
            WaitUtil.hardWait(1);
            ScreenshotUtil.captureScreenshot(DriverFactory.getDriver(), scenario.getName());
            log.info("Screenshot attached to report");
        } else {
            log.info("Scenario PASSED: {}", scenario.getName());
        }
        DriverFactory.quitDriver();
        log.info("========== FINISHED: {} ==========", scenario.getName());
    }

}

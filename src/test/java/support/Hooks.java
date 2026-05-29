package support;


import com.aventstack.extentreports.ExtentTest;
import driver.DriverFactory;
import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;
import org.apache.logging.log4j.Logger;
import utils.ExtentReportManager;
import utils.LoggerUtil;
import utils.ScreenshotUtil;
import utils.WaitUtil;

public class Hooks {
    private static final Logger log = LoggerUtil.getLogger(Hooks.class);

    @Before
    public void setUp(Scenario scenario) {
        System.setProperty("log4j.skipJansi", "false");
        log.info("========== STARTING: {} ==========", scenario.getName());
        DriverFactory.initDriver();
        ExtentTest extentTest = ExtentReportManager.getInstance().createTest(scenario.getName());
        ExtentReportManager.setTest(extentTest);
    }
    @After
    public void tearDown(Scenario scenario){
        if(scenario.isFailed()){
            log.error("Scenario Failed: {}", scenario.getName());
            WaitUtil.hardWait(1);
            ScreenshotUtil.captureScreenshot(DriverFactory.getDriver(), scenario.getName());
            log.info("Screenshot attached to report");

            ExtentReportManager.getTest().fail("Scenario Failed: " + scenario.getName());

        } else {
            log.info("Scenario PASSED: {}", scenario.getName());
            ExtentReportManager.getTest().pass("Scenario passed");
        }
        DriverFactory.quitDriver();
        ExtentReportManager.flushReports();
        log.info("========== FINISHED: {} ==========", scenario.getName());
    }

}

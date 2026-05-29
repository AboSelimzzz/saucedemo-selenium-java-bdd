package support;


import com.aventstack.extentreports.ExtentTest;
import driver.DriverFactory;
import utils.*;
import io.cucumber.java.*;
import org.apache.logging.log4j.Logger;

public class Hooks {
    private static final Logger log = LoggerUtil.getLogger(Hooks.class);

    @Before
    public void setUp(Scenario scenario) {
        System.out.println("=== SYSTEM OUT WORKS ===");
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

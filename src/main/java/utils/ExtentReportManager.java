package utils;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;
import config.ConfigReader;
import org.apache.logging.log4j.Logger;

public class ExtentReportManager {

    private static final Logger log = LoggerUtil.getLogger(ExtentReportManager.class);
    private static ExtentReports extent;
    private static ExtentTest test;

    private ExtentReportManager() {}

    public static ExtentReports getInstance(){
        if (extent == null) {
            ExtentSparkReporter reporter = new ExtentSparkReporter(
                    "test-output/extent-reports/report.html"
            );
            reporter.config().setDocumentTitle("SauceDemo Test Report");
            reporter.config().setReportName("Selenium BDD Report");
            reporter.config().setTheme(Theme.DARK);

            extent = new ExtentReports();
            extent.attachReporter(reporter);
            extent.setSystemInfo("Author",      "AboSelimzzz");
            extent.setSystemInfo("Framework",   "Selenium + Cucumber BDD");
            extent.setSystemInfo("Browser",     ConfigReader.get("browser"));
            extent.setSystemInfo("Environment", ConfigReader.get("baseUrl"));

            log.info("ExtentReports initialized");
        }
        return extent;
    }

    public static void setTest(ExtentTest extentTest) {
        test = extentTest;               // ← simple assignment
    }

    public static ExtentTest getTest() {
        return test;                     // ← simple return
    }

    public static void flushReports() {
        if (extent != null)
            extent.flush();
    }

}

package runner;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(
        features = "src/test/resources/features/",
        glue = {
                "stepDefinitions.web",
                "support"
        },
        plugin = {
                "pretty",                                               // readable console output
                "html:target/cucumber-reports/report.html",            // HTML report
                "json:target/cucumber-reports/report.json",            // for CI/CD integration
                "rerun:target/cucumber-reports/rerun.txt"              // re-run only failed tests
        },
        // Run sprint-1 scenarios — change to @smoke or @regression for filtered runs
        tags = "@sprint1",

        // Removes ANSI color codes from console output — cleaner logs
        monochrome = true
)
public class TestRunner {
    // This class stays empty — annotations do all the work
}

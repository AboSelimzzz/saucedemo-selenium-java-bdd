package features.stepDefinitions.web;

import io.cucumber.java.en.*;
import org.apache.logging.log4j.Logger;
import org.junit.Assert;
import pages.LoginPage;
import utils.LoggerUtil;
import utils.TestDataReader;

public class LoginSteps {

    private final LoginPage loginPage = new LoginPage();

    private static final Logger log = LoggerUtil.getLogger(LoginSteps.class);

    @Then("the error message {string} should be displayed")
    public void theErrorMessageShouldBeDisplayed(String message) {
        log.info("Verifying the error message {}", message);
        String actualMessage = loginPage.getErrorMessage();
        Assert.assertTrue("Expected message '" + message + "' to be displayed but it was not", loginPage.isErrorDisplayed());
        Assert.assertEquals("Expected message : " + message + " but was: " + actualMessage, message, actualMessage);
    }

    @When("the user logs in as {string} from {string}")
    public void theUserLogsInAs(String userType, String category) {
        String username = TestDataReader.getUsername(category, userType);
        String password = TestDataReader.getPassword(category, userType);
        log.info("Logging in as '{}' from category '{}'", userType, category);
        loginPage.fillFieldWith("username", username);
        loginPage.fillFieldWith("password", password);
        loginPage.clickOn("login");
    }
}

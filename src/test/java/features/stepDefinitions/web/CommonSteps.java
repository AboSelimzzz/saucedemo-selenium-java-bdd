package features.stepDefinitions.web;

import config.*;
import pages.*;
import utils.*;
import driver.*;
import io.cucumber.java.en.*;
import org.apache.logging.log4j.Logger;
import org.junit.Assert;

import java.util.HashMap;
import java.util.Map;

public class CommonSteps {

    private static final Logger log = LoggerUtil.getLogger(CommonSteps.class);

    private final Map<String, PageInteractions> pageRegistry = new HashMap<>();

    public CommonSteps() {
//        pageRegistry.put("inventory",             new ProductsPage());
        pageRegistry.put("saucedemo.com", new LoginPage());
//        pageRegistry.put("cart",                  new CartPage());
//        pageRegistry.put("checkout-step-one",     new CheckoutPage());
//        pageRegistry.put("checkout-step-two",     new CheckoutPage());
//        pageRegistry.put("checkout-complete",     new CheckoutPage());

    }

    public PageInteractions getCurrentPage() {
        String currentUrl = DriverFactory.getDriver().getCurrentUrl();
        return pageRegistry.entrySet()
                .stream()
                .filter(entry -> currentUrl.contains(entry.getKey()))
                .map(Map.Entry::getValue)
                .findFirst()
                .orElseThrow(() -> new IllegalStateException(
                        "No page registered for URL: " + currentUrl
                ));
    }


    @Given("the user opens the {string} website")
    public void theUserOpensThePage(String websiteName) {
        log.info("Opening website: {}", websiteName);
        new LoginPage().openLoginPage();
    }

    @Then("the current page should be the {string} page")
    public void thePageShouldBeDisplayed(String pageName) {
        log.info("Verifying the page title is: {}", pageName);
        String actualUrl = DriverFactory.getDriver().getCurrentUrl();
        String expectedUrl = switch (pageName) {
            case "Sauce Demo" -> ConfigReader.get("baseUrl");
            case "Products" -> ConfigReader.get("productsUrl");
            case "Product Detail" -> ConfigReader.get("productDetailUrl");
            case "Cart" -> ConfigReader.get("cartUrl");
            case "Checkout Step 1" -> ConfigReader.get("checkoutStep1Url");
            case "Checkout Step 2" -> ConfigReader.get("checkoutStep2Url");
            case "Checkout Complete" -> ConfigReader.get("checkoutCompleteUrl");
            default -> throw new IllegalArgumentException(
                    "Page name not recognized: '" + pageName + "'"
            );
        };
        boolean isDisplayed = WaitUtil.waitForUrlToContain(expectedUrl);
        Assert.assertTrue("Expected url to contain: " + expectedUrl + " but was: " + actualUrl, isDisplayed);
    }

    @And("the {string} {string} should be displayed")
    public void theFieldShouldBeDisplayed(String elementName, String elementType) {
        log.info("Verifying {} '{}' is displayed", elementType, elementName);
        boolean isDisplayed = getCurrentPage().isElementDisplayed(elementType, elementName);
        Assert.assertTrue("Expected " + elementType + " '" + elementName + "' to be displayed but it was not",
                isDisplayed);
    }

    @When("the user enters {string} in the {string} field")
    public void theUserEntersInTheField(String value, String field) {
        log.info("Filling the {} field with {}", field, value);
        getCurrentPage().fillFieldWith(field, value);

    }

    @And("the user clicks the {string} button")
    public void theUserClicksOnTheButton(String buttonName){
        log.info("Clicking on {}", buttonName);
        getCurrentPage().clickOn(buttonName);
    }
}

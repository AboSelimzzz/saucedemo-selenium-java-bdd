package pages;

import config.ConfigReader;
import org.openqa.selenium.By;

public class LoginPage extends BasePage implements PageInteractions {

    // Locators
    private final By usernameField = By.id("user-name");
    private final By passwordField = By.id("password");
    private final By loginButton = By.id("login-button");
    private final By errorMessage = By.cssSelector("[data-test='error']");
    private final By errorCloseButton = By.className("error-button");


    // Constructor
    public LoginPage() {
        super();
    }

    // Methods
    public String getErrorMessage() {
        return getText(errorMessage);
    }

    public boolean isErrorDisplayed() {
        return isDisplayed(errorMessage);
    }

    public void openLoginPage() {
        windowManager.navigateTo(ConfigReader.get("baseUrl"));
    }

    @Override
    public boolean isElementDisplayed(String elementType, String elementName) {
        return switch (elementType.toLowerCase()) {
            case "field" -> switch (elementName) {
                case "username" -> isDisplayed(usernameField);
                case "password" -> isDisplayed(passwordField);
                default -> throw new IllegalArgumentException(
                        "Field not recognized on Login page: " + elementName
                );
            };
            case "button" -> switch (elementName) {
                case "login" -> isDisplayed(loginButton);
                case "error close" -> isDisplayed(errorCloseButton);
                default -> throw new IllegalArgumentException(
                        "Button not recognized on Login page: " + elementName
                );
            };
            default -> throw new IllegalArgumentException(
                    "Element type not recognized: '" + elementType + "'. Use: field or button"
            );
        };
    }

    @Override
    public void fillFieldWith(String fieldName, String value) {
        switch (fieldName) {
            case "username":
                type(usernameField, value);
                break;
            case "password":
                type(passwordField, value);
                break;
            default:
                throw new IllegalArgumentException(
                        "Field not recognized on Login page: " + fieldName
                );
        }
    }

    @Override
    public void clickOn(String buttonName) {
        switch (buttonName) {
            case "login":
                click(loginButton);
                break;
            case "error close":
                click(errorCloseButton);
                break;
            default:
                throw new IllegalArgumentException(
                        "Button not recognized on Login page: " + buttonName
                );
        }
    }

    @Override
    public boolean isElementHighlighted(String elementType, String elementName){
        return switch (elementType.toLowerCase()) {
            case "field" -> switch (elementName) {
                case "username" -> isHighlighted(usernameField);
                case "password" -> isHighlighted(passwordField);
                default -> throw new IllegalArgumentException(
                        "Field not recognized on Login page: " + elementName
                );
            };
            default -> throw new IllegalArgumentException(
                    "Element type not recognized: '" + elementType + "'. Use: field or button"
            );
        };
    }
}

package utils;

import config.ConfigReader;
import driver.DriverFactory;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;


public class WaitUtil {

    private static final Logger log = LoggerUtil.getLogger(WaitUtil.class);

    private WaitUtil(){}

    private static WebDriverWait getWait() {
        return new WebDriverWait(
                DriverFactory.getDriver(),
                Duration.ofSeconds(Long.parseLong(ConfigReader.get("explicitWait")))
        );
    }

    private static WebDriverWait getWait(int seconds) {
        return new WebDriverWait(
                DriverFactory.getDriver(),
                Duration.ofSeconds(seconds)
        );
    }

    // ─────────────────────────────────────────
    // VISIBILITY
    // ─────────────────────────────────────────

    // Wait until element is visible on the page
    public static WebElement waitForVisibility(By locator) {
        log.info("Waiting for visibility of: {}", locator);
        return getWait().until(ExpectedConditions.visibilityOfElementLocated(locator));
    }

    // Wait until element is visible — with custom timeout
    public static WebElement waitForVisibility(By locator, int seconds) {
        log.info("Waiting {}s for visibility of: {}", seconds, locator);
        return getWait(seconds).until(ExpectedConditions.visibilityOfElementLocated(locator));
    }

    // Wait until element is NOT visible (disappears from page)
    public static boolean waitForInvisibility(By locator) {
        log.info("Waiting for invisibility of: {}", locator);
        return getWait().until(ExpectedConditions.invisibilityOfElementLocated(locator));
    }

    // ─────────────────────────────────────────
    // CLICKABILITY
    // ─────────────────────────────────────────

    // Wait until element is visible AND enabled — ready to click
    public static WebElement waitForClickability(By locator) {
        log.info("Waiting for clickability of: {}", locator);
        return getWait().until(ExpectedConditions.elementToBeClickable(locator));
    }

    // Wait until element is clickable — with custom timeout
    public static WebElement waitForClickability(By locator, int seconds) {
        log.info("Waiting {}s for clickability of: {}", seconds, locator);
        return getWait(seconds).until(ExpectedConditions.elementToBeClickable(locator));
    }

    // ─────────────────────────────────────────
    // PRESENCE
    // ─────────────────────────────────────────

    // Wait until element exists in DOM — even if not visible
    public static WebElement waitForPresence(By locator) {
        log.info("Waiting for presence of: {}", locator);
        return getWait().until(ExpectedConditions.presenceOfElementLocated(locator));
    }

    // ─────────────────────────────────────────
    // TEXT & ATTRIBUTE
    // ─────────────────────────────────────────

    // Wait until an element contains a specific text
    public static boolean waitForTextToBe(By locator, String expectedText) {
        log.info("Waiting for text '{}' in: {}", expectedText, locator);
        return getWait().until(ExpectedConditions.textToBe(locator, expectedText));
    }

    // Wait until an element contains a specific attribute value
    public static boolean waitForAttributeToBe(By locator, String attribute, String value) {
        log.info("Waiting for attribute '{}' to be '{}' in: {}", attribute, value, locator);
        return getWait().until(
                ExpectedConditions.attributeToBe(locator, attribute, value)
        );
    }

    // ─────────────────────────────────────────
    // URL & TITLE
    // ─────────────────────────────────────────

    // Wait until page URL contains a specific string
    public static boolean waitForUrlToContain(String urlFragment) {
        log.info("Waiting for URL to contain: {}", urlFragment);
        return getWait().until(ExpectedConditions.urlContains(urlFragment));
    }

    // Wait until page title contains a specific string
    public static boolean waitForTitleToContain(String title) {
        log.info("Waiting for title to contain: {}", title);
        return getWait().until(ExpectedConditions.titleContains(title));
    }

    // ─────────────────────────────────────────
    // ALERT
    // ─────────────────────────────────────────

    // Wait until a browser alert appears
    public static Alert waitForAlert() {
        log.info("Waiting for alert to appear");
        return getWait().until(ExpectedConditions.alertIsPresent());
    }

    // ─────────────────────────────────────────
    // HARD WAIT — USE SPARINGLY
    // Only when no other option works
    // ─────────────────────────────────────────

    public static void hardWait(int seconds) {
        log.warn("Using hardWait for {}s — consider replacing with explicit wait", seconds);
        try {
            Thread.sleep(Duration.ofSeconds(seconds).toMillis());
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            log.error("hardWait interrupted: {}", e.getMessage());
        }
    }
}
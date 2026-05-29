package pages;

public interface PageInteractions {
    boolean isElementDisplayed(String elementType, String elementName);
    void fillFieldWith(String field, String value);
    void clickOn(String buttonName);
}

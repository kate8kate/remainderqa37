package framework;

import io.appium.java_client.AppiumDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class BaseHelper {
    AppiumDriver driver;

    public BaseHelper(AppiumDriver driver) {
        this.driver = driver;
    }
    protected boolean isElementPresent(By locator) {
        waitForElementVisibilityByLocator(locator, 15);
        return driver.findElements(locator).size()>0;
    }
    protected void type(By locator, String text, int time) {
        waitForElementVisibilityByLocator(locator, time);
        tap(locator, time); // the same with click on web
        WebElement el = driver.findElement(locator);
        el.clear();
        el.sendKeys(text);
        driver.hideKeyboard();
    }

    protected void tap(By locator, int time) {
        waitForElementVisibilityByLocator(locator, time);
        driver.findElement(locator).click();
    }
    public void pause(int millis) {
        try {
            Thread.sleep(millis);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
    public void waitForElementVisibilityByLocator(By locator, int time) {
        WebDriverWait wait = new WebDriverWait(driver, time);
        wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(locator));
    }
    public String getText(By locator, int time) {
        waitForElementVisibilityByLocator(locator, time);
        return driver.findElement(locator).getText().trim();
    }
    public String getValueByAttribute(By locator, int time, String attributeName) {
        waitForElementVisibilityByLocator(locator, time);
        return driver.findElement(locator).getAttribute(attributeName);
    }
}

package framework;

import io.appium.java_client.AppiumDriver;
import org.openqa.selenium.remote.DesiredCapabilities;

import java.net.MalformedURLException;
import java.net.URL;

public class AppiumConfig {
    MainScreenHelper mainScreenHelper;
    ReminderHelper reminderHelper;
    AppiumDriver driver;

    public void init() {
        DesiredCapabilities capabilities = new DesiredCapabilities();
        capabilities.setCapability("platformName","Android");
        capabilities.setCapability("platformVersion","10");
        capabilities.setCapability("deviceName","mob");
        capabilities.setCapability("automationName","Appium");
        capabilities.setCapability("appPackage","com.blanyal.remindly");
        capabilities.setCapability("appActivity","com.blanyal.remindme.MainActivity");
        capabilities.setCapability("app","/Users/kategonshteyn/Tools/remindly.apk");

        try {
            driver = new AppiumDriver<>(new URL("http://localhost:4723/wd/hub"), capabilities);
        } catch (MalformedURLException e) {
            throw new RuntimeException(e);
        }
        mainScreenHelper = new MainScreenHelper(driver);
        reminderHelper = new ReminderHelper(driver);
    }
    public MainScreenHelper getMainScreenHelper() {
        return mainScreenHelper;
    }
    public ReminderHelper getReminderHelper() {
        return reminderHelper;
    }
    public void tearDown() {
        driver.quit();
    }
}

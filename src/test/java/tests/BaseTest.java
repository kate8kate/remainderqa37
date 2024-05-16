package tests;

import framework.AppiumConfig;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

public class BaseTest {
    protected static AppiumConfig app = new AppiumConfig();
    @BeforeMethod
    public void setUp() {
        app.init();
    }
    @AfterMethod
    public void quit() {
        app.tearDown();
    }
}

package framework;

import io.appium.java_client.AppiumDriver;
import org.openqa.selenium.By;

public class MainScreenHelper extends BaseHelper {
    public MainScreenHelper(AppiumDriver driver) {
        super(driver);
    }
    By btnOkPopUp = By.id("android:id/button1");
    public void tapBtnOkPopUp() {
        tap(btnOkPopUp, 10);
    }
    public boolean isBtnOkPopUpDisplays() {
        return isElementPresent(btnOkPopUp);
    }
}

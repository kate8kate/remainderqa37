package tests;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class MainScreenTests extends BaseTest {

    @Test
    public void isAppOpens() {
        if (app.getMainScreenHelper().isBtnOkPopUpDisplays()) {
            app.getMainScreenHelper().tapBtnOkPopUp();
        }
        Assert.assertTrue(app.getReminderHelper().isAddNewReminderDisplays());

    }
    @BeforeMethod
    public void preconditions() {
        if (app.getMainScreenHelper().isBtnOkPopUpDisplays()) {
            app.getMainScreenHelper().tapBtnOkPopUp();
        }
    }
    @Test(description = "check the license")
    public void checkTheLicense() {
         app.getReminderHelper().tapMoreOptionsBtn();
         app.getReminderHelper().chooseLicenseOption();
//         Assert.assertTrue(app.getReminderHelper().verifyLicenseTextDisplays());
         app.getReminderHelper().tapBackToReminderBtn();
    }

}
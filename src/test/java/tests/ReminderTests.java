package tests;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class ReminderTests extends BaseTest{

    @BeforeMethod
    public void preconditions() {
        if (app.getMainScreenHelper().isBtnOkPopUpDisplays()) {
            app.getMainScreenHelper().tapBtnOkPopUp();
        }
    }
    @Test(description = "add new reminder with the name only")
    public void addNewReminderOnlyNamePositiveTest() {
        app.getReminderHelper().tapBtnAddNewReminder();
        String vacations = "vacations";
        app.getReminderHelper().fillFieldTitleReminder(vacations);
        app.getReminderHelper().tapApplyNewReminder();

        Assert.assertTrue(app.getReminderHelper().verifyReminderByNameCreated(vacations));
    }
    @Test(description = "add new reminder with the name and date")
    public void addNewReminderNameAndDataTest() {
        app.getReminderHelper().tapBtnAddNewReminder();
        String vacations = "vacations";
        app.getReminderHelper().fillFieldTitleReminder(vacations);

        app.getReminderHelper().tapOnDateField();
        app.getReminderHelper().swipeToMonth("future", 1, "June");
       // app.getReminderHelper().pause(30000);
        app.getReminderHelper().selectDate(0);
        app.getReminderHelper().tapOnYear();
        app.getReminderHelper().swipeToYear("future","2025");
        app.getReminderHelper().tapOnOk();

        app.getReminderHelper().tapApplyNewReminder();

        Assert.assertTrue(app.getReminderHelper().verifyReminderDateCorrect("1/6/2025"));


    }
}

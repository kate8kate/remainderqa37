package framework;

import com.google.common.base.Objects;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import io.appium.java_client.PerformsTouchActions;
import io.appium.java_client.TouchAction;
import io.appium.java_client.touch.offset.PointOption;
import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebElement;

import java.util.List;


public class ReminderHelper extends BaseHelper {
    public ReminderHelper(AppiumDriver driver) {
        super(driver);
    }

    By btnAddNewRemind = By.xpath("//*[@resource-id='com.blanyal.remindly:id/add_reminder']");
    By inputTitleAddReminder = By.xpath("//*[@resource-id='com.blanyal.remindly:id/reminder_title']");
    By btnApplyNewReminder = By.xpath("//*[@resource-id='com.blanyal.remindly:id/save_reminder']");
    By titleReminderName = By.xpath("//*[@resource-id='com.blanyal.remindly:id/recycle_title']");
    By btnReminderDate = By.id("date");
    By monthOnCalendar = By.xpath("//*[@resource-id='com.blanyal.remindly:id/animator']");
    By takeDateOnCalendar = By.className("android.view.View");
    By btnShowAllYears = By.xpath("//*[@resource-id='com.blanyal.remindly:id/date_picker_year']");
    By textSelectedYear = By.id("date_picker_year");
    By btnMoreOptions = By.className("android.widget.ImageView");
    By textLicense = By.id("title");
    By btnNavigateUp = By.className("android.widget.ImageButton");

    public void tapCreateNewReminder() {
        tap(btnAddNewRemind, 10);
    }

    public boolean isAddNewReminderDisplays() {
        waitForElementVisibilityByLocator(btnAddNewRemind, 10);
        return isElementPresent(btnAddNewRemind);
    }

    public void tapBtnAddNewReminder() {
        tap(btnAddNewRemind, 10);
    }

    public void fillFieldTitleReminder(String str) {
        type(inputTitleAddReminder, str, 10);
    }

    public void tapApplyNewReminder() {
        tap(btnApplyNewReminder, 5);
    }

    public boolean verifyReminderByNameCreated(String actualRes) {
        String expectedResult = getText(titleReminderName, 10);
        return expectedResult.equals(actualRes);
    }

    public void tapOnDateField() {
        tap(btnReminderDate, 5);
    }

    public void swipeToMonth(String period, int number, String month) {
        // swipe to one month only, if we need to swipe a lot - we should use while()
        if (!getSelectedMonthAndYear().contains(month)) {
            if (period.equals("future")) {
                swipe(0.7, 0.4);
            } else if (period.equals("past")) {
                swipe(0.6, 0.8);
            }
        }
    }

    public void swipe(double start, double stop) {
        // return screen size
        Dimension size = driver.manage().window().getSize();

        int x = size.width / 2;
        int startY = (int) (size.height * start);
        int stopY = (int) (size.height * stop);
        new TouchAction<>(driver).longPress(PointOption.point(x, startY))
                .moveTo(PointOption.point(x, stopY))
                .release().perform();
    }

    protected String getSelectedMonthAndYear() {
        System.out.println(getValueByAttribute(monthOnCalendar, 15, "content-desc"));
        return getValueByAttribute(monthOnCalendar, 15, "content-desc");
    }

    public void selectDate(int index) {
        waitForElementVisibilityByLocator(takeDateOnCalendar,10);
        List<MobileElement> list = driver.findElements(takeDateOnCalendar);
        list.get(index).click();
    }

    public void tapOnYear() {
        tap(btnShowAllYears, 5);

    }
    public void swipeToYear(String period, String year) {
        if(!getSelectedYear().contains(year)) {
            if(period.equals("future")) {
                swipeUntilNeededYear(year, 0.6, 0.5);
            } else if (period.equals("past")) {
                swipeUntilNeededYear(year, 0.5, 0.6);
            }
        }
        tap(By.id("month_text_view"), 10);
    }

    public void swipeUntilNeededYear(String year, double x, double y) {
        while(!getYearFromList().equals(year)) {
            swipeByElement(By.className("android.widget.ListView"), x, y);
            getYearFromList();
        }
    }

    private void swipeByElement(By locator, double startPoint, double stopPoint) {
        Dimension size = driver.manage().window().getSize();

//get activity points
        int y = (int) (size.height * startPoint);
        int y2 = (int) (size.height * stopPoint);

//get locator's point
        WebElement element = driver.findElement(locator);
        int leftX = element.getLocation().getX();
        int rightX = leftX + element.getSize().getWidth();
        int middleX = (leftX + rightX)/2;

        new TouchAction((PerformsTouchActions) driver).longPress(PointOption.point(middleX,y))
                .moveTo(PointOption.point(middleX,y2))
                .release().perform();
    }

    private String getYearFromList() {
        return getText(By.id("month_text_view"),10);
    }

    public String getSelectedYear() {
        return getText(textSelectedYear, 10);
    }
    public void tapOnOk() {
        tap(By.id("ok"),5);
    }
    public boolean verifyReminderDateCorrect(String expectedRes) {
        String actualRes = getText(By.id("recycle_date_time"),10);
        return actualRes.contains(expectedRes);
    }
    public void tapOnTimeField() {
        tap(By.id("time"), 5);
    }

    public void selectTime(String timeOfDay, int xHour, int yHour, int xMin, int yMin) {
        pause(500);
        if (timeOfDay.equals("am")) {
            tapWithCoordinates(285,1324);
        } else if (timeOfDay.equals("pm")) {
            tapWithCoordinates(801,1324);
        }
        tapWithCoordinates(xHour,yHour);
        tapWithCoordinates(xMin,yMin);
    }

    public void tapWithCoordinates(int x, int y) {

        new TouchAction((PerformsTouchActions) driver).tap(PointOption.point(x,y))
                .release().perform();
    }

    public void saveReminder() {
        tap(By.id("save_reminder"), 5);
    }

    public void tapMoreOptionsBtn() {
        tap(btnMoreOptions, 5);
    }

    public void chooseLicenseOption() {
        tap(textLicense,5);
    }

//    public boolean verifyLicenseTextDisplays(String expectedRes) {
//        String actualRes = getText(By.id("scroll_view"),5);
//        return actualRes.contains(expectedRes);
//
//    }

    public void tapBackToReminderBtn() {
        tap(btnNavigateUp,5);
    }
}

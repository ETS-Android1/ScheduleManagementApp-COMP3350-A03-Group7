package comp3350.team7.scheduleapp;

/*
 * Created By Thai Tran on 28 March,2021
 *
 */

import android.app.Activity;

import androidx.test.espresso.intent.Intents;
import androidx.test.ext.junit.rules.ActivityScenarioRule;
import androidx.test.ext.junit.runners.AndroidJUnit4;

import org.junit.After;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.util.Calendar;

import comp3350.team7.scheduleapp.application.UserClient;
import comp3350.team7.scheduleapp.logic.EventController;
import comp3350.team7.scheduleapp.logic.exceptions.EventControllerException;
import comp3350.team7.scheduleapp.presentation.activity.EventCreationActivity;
import comp3350.team7.scheduleapp.presentation.activity.ScrollingActivity;
import comp3350.team7.scheduleapp.ultils.TestHelper;

import static androidx.test.espresso.Espresso.onData;
import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.clearText;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.action.ViewActions.closeSoftKeyboard;
import static androidx.test.espresso.action.ViewActions.typeText;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.isChecked;
import static androidx.test.espresso.matcher.ViewMatchers.isCompletelyDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.isNotChecked;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static org.hamcrest.Matchers.is;

// for assertions on Java 8 types (Streams and java.util.Optional)

@RunWith(AndroidJUnit4.class)
public class AddEventSystemTest {
    // Should put these string in resource string for cleaner look
    private final static String descriptionErrorDialogMessage = "Something went wrong, contact admin if needed\nInvalid Event Description\nOnly accept Word character or number";
    private final static String titleErrorDialogMessage = "Something went wrong, contact admin if needed\nInvalid Event Title\nOnly accept Word character or number";
    private final static String startTimeErrorDialogMessage = "Something went wrong, contact admin if needed\nInvalid Event Start Time\nStart time has to be set in the future";
    private final static String alarmErrorDialogMessage = "Something went wrong, contact admin if needed\nInvalid Alarm, Alarm should be set between current time and event'starting time";
    private final static String eventCreatedToastmessage = "Event Created Successfully";
    private static final int alarmSpinnerItemIndex = 0;
    private static Calendar validDate, validTime, invalidDate, invalidTime;
    private static int timePickerId, datePickerId, descriptionTextViewId, titleTextViewId,
            alarmSpinnerId, alarmSwitchId, saveButtonId;
    private static String validTitleTestId;
    private static String invalidTitleTestId;
    private static String validDescriptionTestId;
    private static String invalidDescriptionTestId;
    private static int testId;
    private static EventController eventController;
    @Rule
    public ActivityScenarioRule<EventCreationActivity> activityRule = new ActivityScenarioRule<EventCreationActivity>(EventCreationActivity.class);

    @Before
    public void setup() {
        validDate = TestHelper.getCustomizeCalendarInstance(Calendar.DAY_OF_MONTH, 0);
        validTime = TestHelper.getCustomizeCalendarInstance(Calendar.MINUTE, 30);
        invalidDate = TestHelper.getCustomizeCalendarInstance(Calendar.DAY_OF_MONTH, -1);
        invalidTime = TestHelper.getCustomizeCalendarInstance(Calendar.MINUTE, -1);
        validTitleTestId = "Valid Event Title " + testId;
        invalidTitleTestId = "Invalid Title " + testId;
        validDescriptionTestId = "Valid Event Description " + testId;
        invalidDescriptionTestId = "Invalid Event Description " + testId;
        timePickerId = R.id.time_picker_text;
        datePickerId = R.id.date_picker_text;
        descriptionTextViewId = R.id.description;
        titleTextViewId = R.id.event_name_text;
        alarmSpinnerId = R.id.reminder;
        alarmSwitchId = R.id.switch1;
        saveButtonId = R.id.save_event_button;

        testId = 0;
        UserClient.setUserId("ttran");
        eventController = new EventController();
        Intents.init();
    }


    @After
    public void teardown() {
        Intents.release();
        TestHelper.cleanDb(eventController);
        activityRule.getScenario().close();
    }

    @Ignore("Grouping with other test")
    private void addEventWith(CASE option) {

        ++testId;
        switch (option) {
            case Invalid_Empty_Title:
                onView(withId(titleTextViewId))
                        .check(matches(isCompletelyDisplayed()))
                        .perform(click(), clearText(), typeText(""), closeSoftKeyboard());
                break;
            case Invalid_Too_Long_Title:
                onView(withId(titleTextViewId))
                        .check(matches(isCompletelyDisplayed()))
                        .perform(click(), clearText(), typeText(String.format("%60s", invalidTitleTestId)), closeSoftKeyboard());
                break;
            default:
                onView(withId(titleTextViewId))
                        .check(matches(isCompletelyDisplayed()))
                        .perform(click(), clearText(), typeText(validTitleTestId), closeSoftKeyboard());

        }
        switch (option) {
            case Invalid_Empty_Description:
                onView(withId(descriptionTextViewId))
                        .check(matches(isCompletelyDisplayed()))
                        .perform(click(), typeText(""), closeSoftKeyboard());
                break;
            case Invalid_Too_Long_Description:
                onView(withId(descriptionTextViewId))
                        .check(matches(isCompletelyDisplayed()))
                        .perform(click(), typeText(String.format("%120s", invalidDescriptionTestId)), closeSoftKeyboard());
                break;
            default:
                onView(withId(descriptionTextViewId))
                        .check(matches(isCompletelyDisplayed()))
                        .perform(click(), typeText(validDescriptionTestId), closeSoftKeyboard());
                break;
        }

        switch (option) {
            case Invalid_Time_Minus_One_Minute:
                TestHelper.setTime(timePickerId, invalidTime);
                break;
            default:
                TestHelper.setTime(timePickerId, validTime);
                break;
        }
        switch (option) {
            case Invalid_Date_Minus_One_Day:
                TestHelper.setDate(datePickerId, invalidDate);
                break;
            default:
                TestHelper.setDate(datePickerId, validDate);
                break;
        }
        switch (option) {
            case No_Alarm:
                break;
            case Valid_Alarm_Spinner_Position_1:
                pickAlarmSpinnerItems(1);
                checkAlarmOption();
                break;
            case Valid_Alarm_Spinner_Position_2:
                pickAlarmSpinnerItems(2);
                checkAlarmOption();
                break;
            case Valid_Alarm_Spinner_Position_0:
            default:
                pickAlarmSpinnerItems(0);
                checkAlarmOption();
                break;


        }

    }

    @Ignore("Grouping with other test")
    private void pickAlarmSpinnerItems(int spinnerPosition) throws ArrayIndexOutOfBoundsException {
        Activity activity = TestHelper.getActivity(activityRule);
        String[] min_prior_alarm_array_string = activity.getResources().getStringArray(R.array.min_prior_alarm_array_string);
        int size = min_prior_alarm_array_string.length;
        if (spinnerPosition < size) {
            onView(withId(R.id.reminder)).perform(click());
            onData(is(min_prior_alarm_array_string[spinnerPosition])).perform(click());
        }
        if (alarmSpinnerItemIndex >= size)
            throw new ArrayIndexOutOfBoundsException("Invalid input \"int spinnerPosition\"");
    }

    @Ignore("Grouping with other test")
    private void checkAlarmOption() {
        onView(withId(alarmSwitchId))
                .check(matches(isNotChecked()))
                .perform(click())
                .check(matches(isChecked()));
    }

    @Ignore("Grouping with other test")
    private void clickSaveButton() {
        onView(withId(saveButtonId))
                .check(matches(isCompletelyDisplayed()))
                .perform(click());
    }


    @Test
    public void addValidEventWithoutAlarm() throws EventControllerException {

        addEventWith(CASE.No_Alarm);
        clickSaveButton();
        int eventSize = TestHelper.matchEventListSize(eventController, testId);
        TestHelper.testMatchReceivedIntent(ScrollingActivity.class.getName());
        TestHelper.matchItemWithTextOnRecyclerViewAndPerformClick(validTitleTestId);
        TestHelper.matchItemAtPositionWithTextOnRecyclerView(eventSize - 1, validTitleTestId);

    }

    //  }
    @Test
    public void addEventWithEmptyEventTitle() {
        addEventWith(CASE.Invalid_Empty_Title);
        clickSaveButton();
        TestHelper.matchDisplayDialogMessage(titleErrorDialogMessage);
    }

    @Test
    public void addEventWithEmptyEventDescription() {
        addEventWith(CASE.Invalid_Empty_Description);
        clickSaveButton();
        TestHelper.matchDisplayDialogMessage(descriptionErrorDialogMessage);
    }

    @Test
    public void addEventWithInvalidEventStartDate() {
        addEventWith(CASE.Invalid_Date_Minus_One_Day);
        clickSaveButton();
        TestHelper.matchDisplayDialogMessage(startTimeErrorDialogMessage);
    }

    @Test
    public void addEventWithInvalidEvetStartTime() {
        addEventWith(CASE.Invalid_Time_Minus_One_Minute);
        clickSaveButton();
        TestHelper.matchDisplayDialogMessage(startTimeErrorDialogMessage);
    }

    @Test
    public void addValidEventWithSettingAlarm5MinutePiorEventStart() throws EventControllerException {
        addEventWith(CASE.Valid_Alarm_Spinner_Position_0);
        clickSaveButton();
        int eventSize = TestHelper.matchEventListSize(eventController, testId);
        TestHelper.testMatchReceivedIntent(ScrollingActivity.class.getName());
        TestHelper.matchItemWithTextOnRecyclerViewAndPerformClick(validTitleTestId);
        TestHelper.matchItemAtPositionWithTextOnRecyclerView(eventSize - 1, validTitleTestId);
    }

    @Test
    public void addValidEventWithSettingAlarm10MinutePiorEventStart() throws EventControllerException {

        addEventWith(CASE.Valid_Alarm_Spinner_Position_1);
        clickSaveButton();

        int eventSize = TestHelper.matchEventListSize(eventController, testId);
        TestHelper.testMatchReceivedIntent(ScrollingActivity.class.getName());
        TestHelper.matchItemWithTextOnRecyclerViewAndPerformClick(validTitleTestId);
        TestHelper.matchItemAtPositionWithTextOnRecyclerView(eventSize - 1, validTitleTestId);   // check position of the item


    }

    @Test
    public void addValidEventWithSettingAlarm25MinutePiorEventStart() throws EventControllerException, InterruptedException {

        addEventWith(CASE.Valid_Alarm_Spinner_Position_2);
        clickSaveButton();
        int eventSize = TestHelper.matchEventListSize(eventController, testId);
        TestHelper.testMatchReceivedIntent(ScrollingActivity.class.getName());
        TestHelper.matchItemWithTextOnRecyclerViewAndPerformClick(validTitleTestId);
        TestHelper.matchItemAtPositionWithTextOnRecyclerView(eventSize - 1, validTitleTestId);  // check position of the item
    }

    @Test
    public void addInValidAlarmWithSetting5MinutePiorEventStart() {
        addEventWith(CASE.Valid_Alarm_Spinner_Position_0);
        validDate = TestHelper.getCustomizeCalendarInstance(Calendar.DATE, 0);
        invalidTime = TestHelper.getCustomizeCalendarInstance(Calendar.MINUTE, 4);
        TestHelper.setDate(datePickerId, validDate);
        TestHelper.setTime(timePickerId, invalidTime);
        clickSaveButton();
        TestHelper.matchDisplayDialogMessage(alarmErrorDialogMessage);

    }

    @Test
    public void addInValidAlarmAndTimeWithSetting10MinutePiorEventStart() {
        addEventWith(CASE.Valid_Alarm_Spinner_Position_1);
        validDate = TestHelper.getCustomizeCalendarInstance(Calendar.DATE, 0);
        invalidTime = TestHelper.getCustomizeCalendarInstance(Calendar.MINUTE, 9);
        TestHelper.setDate(datePickerId, validDate);
        TestHelper.setTime(timePickerId, invalidTime);
        clickSaveButton();
        TestHelper.matchDisplayDialogMessage(alarmErrorDialogMessage);
    }

    @Test
    public void addInValidAlarmAndTimeWithSetting25MinutePiorEventStart() {
        addEventWith(CASE.Valid_Alarm_Spinner_Position_2);
        validDate = TestHelper.getCustomizeCalendarInstance(Calendar.DATE, 0);
        invalidTime = TestHelper.getCustomizeCalendarInstance(Calendar.MINUTE, 24);
        TestHelper.setDate(datePickerId, validDate);
        TestHelper.setTime(timePickerId, invalidTime);
        clickSaveButton();
        TestHelper.matchDisplayDialogMessage(alarmErrorDialogMessage);
    }

    @Ignore()
    public enum CASE {
        Invalid_Empty_Title,
        Invalid_Too_Long_Title,   // more than 60 character
        Invalid_Empty_Description,
        Invalid_Too_Long_Description, // more than 120 character
        Invalid_Date_Minus_One_Day,
        Invalid_Time_Minus_One_Minute,
        Valid_Alarm_Spinner_Position_0,
        Valid_Alarm_Spinner_Position_1,
        Valid_Alarm_Spinner_Position_2,
        No_Alarm,
        Valid


    }
}

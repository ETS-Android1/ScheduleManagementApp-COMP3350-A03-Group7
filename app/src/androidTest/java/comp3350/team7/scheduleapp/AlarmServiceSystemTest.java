package comp3350.team7.scheduleapp;

/*
 * Created By Thai Tran on 04 April,2021
 *
 */

import android.os.SystemClock;

import androidx.test.espresso.intent.Intents;
import androidx.test.ext.junit.rules.ActivityScenarioRule;

import org.junit.After;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Rule;
import org.junit.Test;

import java.util.Calendar;

import comp3350.team7.scheduleapp.application.UserClient;
import comp3350.team7.scheduleapp.logic.EventController;
import comp3350.team7.scheduleapp.logic.TimeController;
import comp3350.team7.scheduleapp.logic.exceptions.InvalidEventException;
import comp3350.team7.scheduleapp.presentation.activity.EventCreationActivity;
import comp3350.team7.scheduleapp.ultils.TestHelper;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.clearText;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.action.ViewActions.closeSoftKeyboard;
import static androidx.test.espresso.action.ViewActions.typeText;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.isCompletelyDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.withId;

public class AlarmServiceSystemTest {

    @Rule
    public ActivityScenarioRule<EventCreationActivity> activityRule = new ActivityScenarioRule<EventCreationActivity>(EventCreationActivity.class);
    private Calendar start, alarm;
    private int startOffSet, alarmOffSet, timeTillAlarmFire;

    @Before
    public void setup() {
        UserClient.setUserId("ttran");
        Intents.init();
    }

    @After
    public void teardown() {
        Intents.release();
        TestHelper.cleanDb(new EventController());
        activityRule.getScenario().close();
    }

    @Test
    public void testAlarmFireBeforeStart5Min() throws InvalidEventException, InterruptedException {
        startOffSet = 30;
        buildAndAddEventWithAlarmSetup(startOffSet, 0);
        activityRule.getScenario().onActivity(activity -> {
//          Calendar currTime = TestHelper.getCustomizeCalendarInstance(Calendar.MINUTE, 24);
//          SystemClock.setCurrentTimeMillis(currTime.getTimeInMillis());
        });
        SystemClock.sleep(TimeController.minToMilliseconds(24));
        Thread.yield();
        //Thread.sleep(TimeController.minToMilliseconds(0));

    }

    @Test
    public void testAlarmFireBeforeStart10Min() {

    }

    @Test
    public void testAlarmFireBeforeStart25Min() {

    }

    @Ignore("Build and add valid event with alarm to the system")
    private void buildAndAddEventWithAlarmSetup(int startOffSet, int alarmSpinnerPosition) throws InvalidEventException {
        int[] min_prior_alarm_array_int = getAlarmResource();
        if (startOffSet <= min_prior_alarm_array_int[alarmSpinnerPosition])
            throw new InvalidEventException("Start offset must be greater than alarm offset ");
        start = TestHelper.getCustomizeCalendarInstance(Calendar.MINUTE, startOffSet);
        onView(withId(R.id.event_name_text))
                .check(matches(isCompletelyDisplayed()))
                .perform(click(), clearText(), typeText("alarm testing"), closeSoftKeyboard());
        onView(withId(R.id.description))
                .check(matches(isCompletelyDisplayed()))
                .perform(click(), clearText(), typeText("obviously alarm testing"), closeSoftKeyboard());
        TestHelper.setDate(R.id.date_picker_text, start);
        TestHelper.setTime(R.id.time_picker_text, start);
        TestHelper.pickAlarmSpinnerItems(alarmSpinnerPosition, activityRule);
        onView(withId(R.id.switch1))
                .check(matches(isCompletelyDisplayed()))
                .perform(click());
        onView(withId(R.id.save_event_button))
                .check(matches(isCompletelyDisplayed()))
                .perform(click());
//        EventController eventController = new EventController();
//            activityRule.getScenario().onActivity(activity -> {
//                Event testEvent = null;
//                try {
//                    testEvent = eventController.buildEvent(UserClient.getUserId(), "Testing Title", "Testing Description", start, null, alarm);
//                    eventController.addEvent(testEvent);
//                    AlarmController.setAlarmService(activity.getApplicationContext(),"Testing Alarm" , timeTillAlarmFire);
//                } catch (EventControllerException e) {
//                    activity.onError(e.getMessage());
//                }
//            }).recreate();
        //}

    }

    @Ignore
    private int[] getAlarmResource() {
        final EventCreationActivity currActivity = TestHelper.getActivity(activityRule);
        return currActivity.getResources().getIntArray(R.array.min_prior_alarm_array_int);
    }

}
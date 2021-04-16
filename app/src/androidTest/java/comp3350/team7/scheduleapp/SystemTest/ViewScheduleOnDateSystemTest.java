package comp3350.team7.scheduleapp.SystemTest;

/*
 * Created By Thai Tran on 28 March,2021
 *
 */

import android.os.SystemClock;

import androidx.test.espresso.intent.Intents;
import androidx.test.ext.junit.rules.ActivityScenarioRule;

import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;

import java.util.Calendar;

import comp3350.team7.scheduleapp.R;
import comp3350.team7.scheduleapp.application.UserClient;
import comp3350.team7.scheduleapp.logic.EventController;
import comp3350.team7.scheduleapp.logic.exceptions.EventControllerException;
import comp3350.team7.scheduleapp.objects.Event;
import comp3350.team7.scheduleapp.presentation.activity.ScrollingActivity;
import comp3350.team7.scheduleapp.ultils.TestHelper;


/*
 * Created By Thai Tran on 16 April,2021
 *
 */

public class ViewScheduleOnDateSystemTest {
    @Rule
    public ActivityScenarioRule<ScrollingActivity> activityRule = new ActivityScenarioRule<ScrollingActivity>(ScrollingActivity.class);
    Calendar start;

    @Before
    public void setup() {
        SystemClock.sleep(500);
        UserClient.setUserId("ttran");
        start = Calendar.getInstance();
        start.add(Calendar.MINUTE, 30);
        EventController eventController = new EventController();
        activityRule.getScenario().onActivity(activity -> {
            Event testEvent = null;
            try {
                for (int i = 0; i < 6; i++) {

                    start.add(Calendar.DAY_OF_MONTH, 1);
                    start.add(Calendar.MONTH, 0);
                    testEvent = eventController.buildEvent(UserClient.getUserId(), "Testing Title", "Testing Description", start);
                    eventController.addEvent(testEvent);

                }
            } catch (EventControllerException e) {
                activity.onError(e.getMessage());
            }
        }).recreate();
        Intents.init();
    }

    @After
    public void teardown() {
        Intents.release();
        TestHelper.cleanDb(new EventController());
        activityRule.getScenario().close();
    }

    @Test
    public void testViewScheduleOnDate() {
        Calendar onCurrentDay;
        TestHelper.matchItemAtPositionWithTextOnRecyclerView(0, "Testing Title");
        onCurrentDay = TestHelper.getCustomizeCalendarInstance(Calendar.DAY_OF_MONTH, 1);
        TestHelper.setDate(R.id.date_picker_text_1, onCurrentDay);
        SystemClock.sleep(500);

        TestHelper.matchItemAtPositionWithTextOnRecyclerView(0, "Testing Title");
        onCurrentDay = TestHelper.getCustomizeCalendarInstance(Calendar.DAY_OF_MONTH, 2);
        TestHelper.setDate(R.id.date_picker_text_1, onCurrentDay);
        SystemClock.sleep(500);

        TestHelper.matchItemAtPositionWithTextOnRecyclerView(0, "Testing Title");
        onCurrentDay = TestHelper.getCustomizeCalendarInstance(Calendar.DAY_OF_MONTH, 3);
        TestHelper.setDate(R.id.date_picker_text_1, onCurrentDay);
        SystemClock.sleep(500);

        TestHelper.matchItemAtPositionWithTextOnRecyclerView(0, "Testing Title");
        onCurrentDay = TestHelper.getCustomizeCalendarInstance(Calendar.DAY_OF_MONTH, 4);
        TestHelper.setDate(R.id.date_picker_text_1, onCurrentDay);
        SystemClock.sleep(500);

        TestHelper.matchItemAtPositionWithTextOnRecyclerView(0, "Testing Title");
        onCurrentDay = TestHelper.getCustomizeCalendarInstance(Calendar.DAY_OF_MONTH, 5);
        TestHelper.setDate(R.id.date_picker_text_1, onCurrentDay);
        SystemClock.sleep(500);
        TestHelper.matchItemAtPositionWithTextOnRecyclerView(0, "Testing Title");
        onCurrentDay = TestHelper.getCustomizeCalendarInstance(Calendar.DAY_OF_MONTH, 6);
        TestHelper.setDate(R.id.date_picker_text_1, onCurrentDay);
        SystemClock.sleep(500);

    }

}

package comp3350.team7.scheduleapp;

/*
 * Created By Thai Tran on 28 March,2021
 *
 */

import androidx.test.espresso.ViewInteraction;
import androidx.test.espresso.contrib.RecyclerViewActions;
import androidx.test.espresso.intent.Intents;
import androidx.test.ext.junit.rules.ActivityScenarioRule;

import org.junit.After;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Rule;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import comp3350.team7.scheduleapp.application.UserClient;
import comp3350.team7.scheduleapp.logic.EventController;
import comp3350.team7.scheduleapp.logic.TimeController;
import comp3350.team7.scheduleapp.logic.exceptions.EventControllerException;
import comp3350.team7.scheduleapp.objects.Event;
import comp3350.team7.scheduleapp.presentation.activity.EventCreationActivity;
import comp3350.team7.scheduleapp.presentation.activity.ScrollingActivity;
import comp3350.team7.scheduleapp.ultils.TestHelper;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.isChecked;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.Matchers.allOf;
import static org.hamcrest.Matchers.containsString;

public class EditEventSystemTest {
    private static EventController eventController;
    @Rule
    public ActivityScenarioRule<ScrollingActivity> activityRule = new ActivityScenarioRule<ScrollingActivity>(ScrollingActivity.class);
    //private static EventController eventController;
    Calendar start, alarm;

    @Before
    public void setup() throws EventControllerException {
        start = TestHelper.getCustomizeCalendarInstance(Calendar.MINUTE, 30);
        alarm = TestHelper.getCustomizeCalendarInstance(Calendar.MINUTE, 20);
        UserClient.setUserId("ttran");
        eventController = new EventController();
        activityRule.getScenario().onActivity(activity -> {
            Event testEvent = null;
            List<Event> list = new ArrayList<>();
            try {
                testEvent = eventController.buildEvent(UserClient.getUserId(), "Testing Title", "Testing Description", start, null, alarm);
                list = eventController.getEventList(UserClient.getUserId());
                eventController.addEvent(testEvent);
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

    @Ignore
    public static void matchEditButtonOnRecyclerView(int Position) {
        ViewInteraction viewInteraction = onView(allOf(withId(R.id.recylerview),
                isDisplayed()));
        viewInteraction.perform(RecyclerViewActions.actionOnItemAtPosition(Position, click()));
        onView(withId(R.id.edit_ic)).perform(click());

    }

    @Test
    public void checkClickableEditButton() {

        // Assert if a text match the view at position 0 in recycler view
        // Assert match event title
        TestHelper.matchItemAtPositionWithTextOnRecyclerView(0, "Testing Title");
        // Assert if a text match the view at position 0 in recycler view
        // Assert match event description
        TestHelper.matchItemAtPositionWithTextOnRecyclerView(0, "Testing Description");
        // Assert if there is a clickable edit button, perform click
        matchEditButtonOnRecyclerView(0);
        // Assert if the if EventCreation Activity receive a correct bundle containing correct data
        TestHelper.testMatchReceivedIntentData(EventCreationActivity.class.getName(), "EVENT_USER_ID", UserClient.getUserId());
        // Assert view display a correct event title
        onView(allOf(withId(R.id.event_name_text), isDisplayed()))
                .check(matches(withText(containsString("Testing Title"))));
        // Assert view display a correct event description
        onView(allOf(withId(R.id.description), isDisplayed()))
                .check(matches(withText(containsString("Testing Description"))));
        // Assert view display a correct event's date
        onView(allOf(withId(R.id.date_picker_text), isDisplayed()))
                .check(matches(withText(containsString((TimeController.dateFormatHelper(start))))));
        // Assert view display a correct event's time
        onView(allOf(withId(R.id.time_picker_text), isDisplayed()))
                .check(matches(withText(containsString((TimeController.timeFormatHelper(start))))));
        // Assert switch button is on
        onView(allOf(withId(R.id.switch1), isDisplayed()))
                .check(matches(isChecked()));

    }
}

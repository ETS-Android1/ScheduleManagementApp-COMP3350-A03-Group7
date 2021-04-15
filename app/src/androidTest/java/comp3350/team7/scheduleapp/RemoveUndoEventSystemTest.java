package comp3350.team7.scheduleapp;

/*
 * Created By Thai Tran on 28 March,2021
 *
 */

import androidx.test.espresso.ViewInteraction;
import androidx.test.espresso.assertion.ViewAssertions;
import androidx.test.espresso.intent.Intents;
import androidx.test.espresso.matcher.ViewMatchers;
import androidx.test.ext.junit.rules.ActivityScenarioRule;

import com.google.android.material.snackbar.Snackbar;

import org.junit.After;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Rule;
import org.junit.Test;

import java.util.Calendar;

import comp3350.team7.scheduleapp.application.UserClient;
import comp3350.team7.scheduleapp.logic.EventController;
import comp3350.team7.scheduleapp.logic.exceptions.EventControllerException;
import comp3350.team7.scheduleapp.objects.Event;
import comp3350.team7.scheduleapp.presentation.activity.ScrollingActivity;
import comp3350.team7.scheduleapp.ultils.TestHelper;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.hasDescendant;
import static androidx.test.espresso.matcher.ViewMatchers.isAssignableFrom;
import static androidx.test.espresso.matcher.ViewMatchers.isCompletelyDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.isDescendantOfA;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withResourceName;
import static androidx.test.espresso.matcher.ViewMatchers.withText;
import static comp3350.team7.scheduleapp.ultils.TestHelper.withRecyclerViewSize;
import static org.hamcrest.Matchers.allOf;
import static org.hamcrest.Matchers.containsString;

public class RemoveUndoEventSystemTest {
    private final static int ItemsTestSize = 1;
    @Rule
    public ActivityScenarioRule<ScrollingActivity> activityRule = new ActivityScenarioRule<ScrollingActivity>(ScrollingActivity.class);
    Calendar start, alarm;

    @Before
    public void setup() {
        start = TestHelper.getCustomizeCalendarInstance(Calendar.MINUTE, 30);
        alarm = TestHelper.getCustomizeCalendarInstance(Calendar.MINUTE, 20);
        UserClient.setUserId("ttran");
        EventController eventController = new EventController();
        activityRule.getScenario().onActivity(activity -> {
            Event testEvent = null;
            try {
                testEvent = eventController.buildEvent(UserClient.getUserId(), "Testing Title", "Testing Description", start, null, alarm);
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

    @Test
    public void testSwipeLeft() {
        TestHelper.swipeRecyclerItemInDir("LEFT")
                .check(ViewAssertions.matches(allOf(
                        ViewMatchers.hasDescendant(withId(R.id.backGround)),
                        ViewMatchers.hasDescendant(withText(containsString("Done!"))),
                        withRecyclerViewSize(ItemsTestSize - 1)))); /* item removed on swiped left,
                                                                     the size should be reduced by 1 */
    }

    @Test
    public void testSwipeRight() {
        TestHelper.swipeRecyclerItemInDir("RIGHT")
                .check(matches(withRecyclerViewSize(ItemsTestSize)));  /* no remove item on swipe right
                                                                      so, the size is unchange */
    }

    @Test
    public void testSwipeUp() {
        TestHelper.swipeRecyclerItemInDir("UP")
                .check(matches(withRecyclerViewSize(ItemsTestSize)));  /* no remove item on swipe right
                                                                      so, the size is unchange */
    }

    @Test
    public void testSwipeDown() {
        TestHelper.swipeRecyclerItemInDir("DOWN")
                .check(matches(withRecyclerViewSize(ItemsTestSize)));  /* no remove item on swipe right
                                                                      so, the size is unchange */

    }

    @Test
    public void undo() throws InterruptedException {
        testSwipeLeft();
        verifySnackBarShowContentAndgetAction()
                .perform(click());
        Thread.sleep(1000);
        verifyRecyclerViewWithSize(1);
    }

    @Ignore
    private ViewInteraction verifySnackBarShowContentAndgetAction() throws InterruptedException {
        withText("Event removed!")
                .matches(allOf(
                        isDescendantOfA(isAssignableFrom(Snackbar.SnackbarLayout.class)),
                        isCompletelyDisplayed()));
        Thread.sleep(350);
        return onView(withText("UNDO"))
                .check(matches(allOf(
                        withResourceName("snackbar_action")
                )));

    }

    @Ignore
    private void verifyRecyclerViewWithSize(int size) {
        onView(allOf(
                withId(R.id.recylerview),
                hasDescendant(withId(R.id.textView3)),
                hasDescendant(withId(R.id.textView4)),
                withRecyclerViewSize(size)
        ));
    }

}

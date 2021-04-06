package comp3350.team7.scheduleapp.ultils;

/*
 * Created By Thai Tran on 28 March,2021
 *
 */

import android.app.Activity;
import android.util.Log;
import android.view.View;
import android.widget.DatePicker;
import android.widget.TimePicker;

import androidx.recyclerview.widget.RecyclerView;
import androidx.test.espresso.ViewAction;
import androidx.test.espresso.ViewInteraction;
import androidx.test.espresso.contrib.PickerActions;
import androidx.test.espresso.contrib.RecyclerViewActions;
import androidx.test.espresso.intent.Intents;
import androidx.test.espresso.intent.matcher.BundleMatchers;
import androidx.test.espresso.intent.matcher.IntentMatchers;
import androidx.test.espresso.matcher.ViewMatchers;
import androidx.test.ext.junit.rules.ActivityScenarioRule;

import org.hamcrest.Description;
import org.hamcrest.Matcher;
import org.hamcrest.Matchers;
import org.hamcrest.TypeSafeMatcher;

import java.util.Calendar;
import java.util.List;
import java.util.concurrent.atomic.AtomicReference;

import comp3350.team7.scheduleapp.R;
import comp3350.team7.scheduleapp.ToastMatcher;
import comp3350.team7.scheduleapp.application.UserClient;
import comp3350.team7.scheduleapp.logic.EventController;
import comp3350.team7.scheduleapp.logic.exceptions.EventControllerException;
import comp3350.team7.scheduleapp.objects.Event;

import static androidx.test.espresso.Espresso.onData;
import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.clearText;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.action.ViewActions.closeSoftKeyboard;
import static androidx.test.espresso.action.ViewActions.swipeDown;
import static androidx.test.espresso.action.ViewActions.swipeLeft;
import static androidx.test.espresso.action.ViewActions.swipeRight;
import static androidx.test.espresso.action.ViewActions.swipeUp;
import static androidx.test.espresso.action.ViewActions.typeText;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.intent.matcher.IntentMatchers.hasComponent;
import static androidx.test.espresso.intent.matcher.IntentMatchers.toPackage;
import static androidx.test.espresso.matcher.RootMatchers.isDialog;
import static androidx.test.espresso.matcher.RootMatchers.withDecorView;
import static androidx.test.espresso.matcher.ViewMatchers.assertThat;
import static androidx.test.espresso.matcher.ViewMatchers.hasDescendant;
import static androidx.test.espresso.matcher.ViewMatchers.isCompletelyDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.withClassName;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.Matchers.allOf;
import static org.hamcrest.Matchers.containsString;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.not;


public class TestHelper {
    private static final String PACKAGE_NAME = "comp3350.team7.scheduleapp";
    private static final String TAG = "TestHelper";
    public static void loginSetup() {
        onView(ViewMatchers.withId(R.id.LoginUsernameInput))
                .check(matches(isCompletelyDisplayed()))
                .perform(clearText(), typeText("ttran"), closeSoftKeyboard());

        onView(withId(R.id.LoginPasswordInput))
                .check(matches(isCompletelyDisplayed()))
                .perform(clearText(), typeText("123456"), closeSoftKeyboard());

        onView(withId(R.id.loginButton))
                .check(matches(isCompletelyDisplayed()))
                .perform(click());

    }
    public static Calendar getCustomizeCalendarInstance(int field,int offset){
        Calendar cal = Calendar.getInstance();
        cal.roll(Calendar.MONTH, true);
        cal.add(field,offset);
        return cal;

    }
    public static void setDate(int datePickerLaunchViewId, Calendar cal) {
        int year, monthOfYear, dayOfMonth;
        year = cal.get(Calendar.YEAR);
        monthOfYear = cal.get(Calendar.MONTH);
        dayOfMonth = cal.get(Calendar.DAY_OF_MONTH);
        onView(withId(datePickerLaunchViewId))
                .check(matches(isCompletelyDisplayed()))
                .perform(click());
        onView(withClassName(Matchers.equalTo(DatePicker.class.getName())))
                .check(matches(isCompletelyDisplayed()))
                .perform(PickerActions.setDate(year, monthOfYear, dayOfMonth));
        onView(withText("OK")).perform(click());
    }

    public static void setTime(int datePickerLaunchViewId, Calendar cal) {
        int hour, min;
        hour = cal.get(Calendar.HOUR_OF_DAY);
        min = cal.get(Calendar.MINUTE);
        onView(withId(datePickerLaunchViewId)).perform(click());
        onView(withClassName(Matchers.equalTo(TimePicker.class.getName()))).perform(PickerActions.setTime(hour, min));
        onView(withText("OK")).perform(click());
    }

    public static void pickAlarmSpinnerItems(int spinnerPosition, ActivityScenarioRule activityRule) throws ArrayIndexOutOfBoundsException {
        Activity activity = TestHelper.getActivity(activityRule);
        String[] min_prior_alarm_array_string = activity.getResources().getStringArray(R.array.min_prior_alarm_array_string);
        int size = min_prior_alarm_array_string.length;
        if (spinnerPosition < size) {
            onView(withId(R.id.reminder)).perform(click());
            onData(is(min_prior_alarm_array_string[spinnerPosition])).perform(click());
        }
        if (spinnerPosition >= size)
            throw new ArrayIndexOutOfBoundsException("Invalid input \"int spinnerPosition\"");
    }

    public static <T extends Activity> void testMatchToast(String toastMessage, ActivityScenarioRule<T> mActivityRule) {
        onView(withText(toastMessage))
                .inRoot(withDecorView(not(getActivity(mActivityRule).getWindow().getDecorView())))
                .check(matches(isDisplayed()));
    }

    public static <T extends Activity> void testMatchToast(String toastMessage) {
        onView(withText(toastMessage))
                .inRoot(new ToastMatcher())
                .check(matches(isDisplayed()));
    }

    public static <T extends Activity> T getActivity(ActivityScenarioRule<T> activityRule) {
        AtomicReference<T> activityRef = new AtomicReference<>();
        activityRule.getScenario().onActivity(activityRef::set);
        return activityRef.get();
    }

    public static void testMatchReceivedIntent(String activity) {
        Intents.intended(allOf(hasComponent(activity), toPackage(PACKAGE_NAME)));
    }

    public static <T> void testMatchReceivedIntentData(String activity, String key, T value) {
        Intents.intended(allOf(hasComponent(activity),
                IntentMatchers.hasExtras(
                        BundleMatchers.hasEntry(equalTo("EVENT_UNIQUE"),
                                BundleMatchers.hasEntry(equalTo(key), equalTo(value)))
                )));
    }

    public static void matchItemAtPositionWithTextOnRecyclerView(int Position, String text) {
        onView(allOf(withId(R.id.recylerview),
                isDisplayed()))
                .perform(RecyclerViewActions.actionOnItemAtPosition(Position, click()))
                .check(matches(hasDescendant(withText(containsString(text)))));
    }

    public static void matchItemWithTextOnRecyclerViewAndPerformClick(String text) {
        ViewInteraction viewInteraction = onView(allOf(withId(R.id.recylerview),
                hasDescendant(withText(containsString(text))),
                isDisplayed()));
        viewInteraction.perform(RecyclerViewActions.scrollTo(hasDescendant(withText(containsString(text)))));
        viewInteraction.perform(RecyclerViewActions.actionOnItem(hasDescendant(withText(containsString(text))),
                click()));
    }


    public static int matchEventListSize(EventController eContrl, int size) throws EventControllerException {
        int eventsSize = -1;
        eventsSize = eContrl.getEventListLength(UserClient.getUserId());
        assertThat(eventsSize, Matchers.equalTo(size));

        return eventsSize;
    }

    public static void cleanDb(EventController eCtrl) {
        try {
            List<Event> list = eCtrl.getEventList(UserClient.getUserId());
            for (int i = 0; i < list.size(); i++) {
                eCtrl.removeEvent(list.get(i));
            }
        } catch (EventControllerException e) {
            e.printStackTrace();
        }

    }

    public static void matchDisplayDialogMessage(String dialogMessage) {
        onView(withText(containsString(dialogMessage)))
                .inRoot(isDialog())
                .check(matches(isDisplayed()));
    }

    public static ViewInteraction swipeRecyclerItemInDir(String dir) {
        ViewAction swipeInDir;
        switch (dir) {
            case "RIGHT":
                swipeInDir = swipeRight();
                break;
            case "LEFT":
                swipeInDir = swipeLeft();
                break;
            case "UP":
                swipeInDir = swipeUp();
                break;
            case "DOWN":
                swipeInDir = swipeDown();
                break;
            default:
                throw new IllegalStateException("Unexpected value: " + dir);
        }

        return onView(allOf(withId(R.id.recylerview),
                hasDescendant(withId(R.id.backGround)),
                isDisplayed()))
                .perform(RecyclerViewActions.actionOnItemAtPosition(0, swipeInDir));
    }

    public static Matcher<View> withRecyclerViewSize(final int size) {
        return new TypeSafeMatcher<View>() {

            @Override
            public boolean matchesSafely(final View view) {
                final int actualListSize = ((RecyclerView) view).getAdapter().getItemCount();
                Log.d(TAG, "RecyclerView actual size " + actualListSize);
                return actualListSize == size;
            }

            @Override
            public void describeTo(final Description description) {
                description.appendText("RecyclerView should have " + size + " items");
            }
        };
    }

}
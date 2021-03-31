package comp3350.team7.scheduleapp.ultils;

/*
 * Created By Thai Tran on 28 March,2021
 *
 */

import android.app.Activity;
import android.widget.DatePicker;
import android.widget.TimePicker;

import androidx.constraintlayout.helper.widget.Layer;
import androidx.test.core.app.ActivityScenario;
import androidx.test.espresso.DataInteraction;
import androidx.test.espresso.contrib.PickerActions;
import androidx.test.espresso.intent.Intents;
import androidx.test.espresso.matcher.ViewMatchers;
import androidx.test.ext.junit.rules.ActivityScenarioRule;

import org.hamcrest.Matchers;

import java.util.Calendar;
import java.util.concurrent.atomic.AtomicReference;

import comp3350.team7.scheduleapp.R;
import comp3350.team7.scheduleapp.ToastMatcher;
import comp3350.team7.scheduleapp.presentation.adapter.RecyclerViewAdapter;

import static androidx.test.espresso.Espresso.onData;
import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.clearText;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.action.ViewActions.closeSoftKeyboard;
import static androidx.test.espresso.action.ViewActions.typeText;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.intent.matcher.IntentMatchers.hasExtra;
import static androidx.test.espresso.intent.matcher.IntentMatchers.toPackage;
import static androidx.test.espresso.matcher.RootMatchers.withDecorView;
import static androidx.test.espresso.matcher.ViewMatchers.isCompletelyDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.withClassName;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withText;
import static androidx.test.espresso.intent.matcher.IntentMatchers.hasComponent;
import static org.hamcrest.Matchers.allOf;
import static org.hamcrest.Matchers.instanceOf;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.not;
import static org.hamcrest.Matchers.startsWith;


public class TestHelper {
    private static final String PACKAGE_NAME = "comp3350.team7.scheduleapp";

    public static void intentSetup() {
        Intents.init();
    }

    public static void intentTeardown() {
        Intents.release();
    }

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
//        cal.roll(Calendar.YEAR, true);
        cal.roll(Calendar.MONTH, true);
//        cal.roll(field, true);
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
        hour = cal.get(Calendar.HOUR);
        min = cal.get(Calendar.MINUTE);
        onView(withId(datePickerLaunchViewId)).perform(click());
        onView(withClassName(Matchers.equalTo(TimePicker.class.getName()))).perform(PickerActions.setTime(hour, min));
        onView(withText("OK")).perform(click());
    }

    public static <T extends Activity> void testMatchToast(String toastMessage,  ActivityScenarioRule<T> mActivityRule) {
        onView(withText(toastMessage))
                .inRoot(withDecorView(not(getActivity(mActivityRule).getWindow().getDecorView())))
                .check(matches(isDisplayed()));
    }
    public static <T extends Activity> void testMatchToast(String toastMessage ){
        onView(withText(toastMessage))
                .inRoot(new ToastMatcher())
                .check(matches(isDisplayed()));
    }
    public static <T extends Activity> T getActivity(ActivityScenarioRule<T> activityRule) {
        AtomicReference<T> activityRef = new AtomicReference<>();
        activityRule.getScenario().onActivity(activityRef::set);
        return activityRef.get();
    }

    public static void testMatchReceivedIntent(String activity){
        Intents.intended(allOf(hasComponent(activity),toPackage(PACKAGE_NAME)));
    }
    public static <T> void testMatchReceivedIntentData(String key, T value){
       Intents.intended(hasExtra(key, value));
    }
    public static DataInteraction onLayerAt(int listPosition) {
        return onData(instanceOf(RecyclerViewAdapter.MyViewHolder.class))
                .inAdapterView(withId(R.id.recylerview))
                .atPosition(listPosition);
    }
}
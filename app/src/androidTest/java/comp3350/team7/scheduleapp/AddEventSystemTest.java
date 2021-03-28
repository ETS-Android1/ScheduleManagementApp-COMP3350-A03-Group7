package comp3350.team7.scheduleapp;

/*
 * Created By Thai Tran on 28 March,2021
 *
 */

import androidx.test.ext.junit.rules.ActivityScenarioRule;
import androidx.test.ext.junit.runners.AndroidJUnit4;


import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import comp3350.team7.scheduleapp.presentation.activity.LoginActivity;
import comp3350.team7.scheduleapp.ultils.TestHelper;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
@RunWith(AndroidJUnit4.class)
public class AddEventSystemTest {

    @Rule
    public ActivityScenarioRule<LoginActivity> activityRule = new ActivityScenarioRule<>(LoginActivity.class);

    @Before
    public void setup() {
        TestHelper.loginSetup();

    }
    @After
    public void teardown(){
        TestHelper.intentTeardown();
    }
    @Test
    public void addValidEvent() {
        onView(withId(R.id.include))
                .perform(click());

    }

    @Test
    public void addEventWithEmptyEventTitle() {

    }
    @Test
    public void addEventWithEmptyEventDescription() {

    }
    @Test
    public void addEventWithEmptyEventStartDate() {

    }
    @Test
    public void addEventWithEmptyEvetStartTime() {

    }
    @Test
    public void addEventWithSettingAlarm5MinutePiorEventStart() {

    }
    @Test
    public void addEventWithSettingAlarm10MinutePiorEventStart() {

    }
    @Test
    public void addEventWithSettingAlarm15MinutePiorEventStart() {

    }
    @Test
    public void addEventWithoutSettingAlarm() {

    }

}

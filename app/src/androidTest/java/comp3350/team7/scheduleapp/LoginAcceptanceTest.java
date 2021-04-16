package comp3350.team7.scheduleapp;

/*
 * Created By Thai Tran on 03 April,2021
 *
 */

import androidx.test.espresso.intent.Intents;
import androidx.test.ext.junit.rules.ActivityScenarioRule;
import androidx.test.ext.junit.runners.AndroidJUnit4;
import androidx.test.filters.LargeTest;

import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import comp3350.team7.scheduleapp.R;
import comp3350.team7.scheduleapp.presentation.activity.LoginActivity;
import comp3350.team7.scheduleapp.presentation.activity.ScrollingActivity;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.action.ViewActions.closeSoftKeyboard;
import static androidx.test.espresso.action.ViewActions.typeText;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.intent.Intents.intended;
import static androidx.test.espresso.intent.matcher.IntentMatchers.hasComponent;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.withId;


/*
 * Created By Thai Tran on 03 April,2021
 *
 */

@RunWith(AndroidJUnit4.class)
public class LoginAcceptanceTest {
    private String usernameInput;
    private String passwordInput;

    @Rule
    public ActivityScenarioRule<LoginActivity> activityRule = new ActivityScenarioRule<>(LoginActivity.class);

    @Before
    public void setup(){
        usernameInput = "ajoson";
        passwordInput = "12345678";
    }


    @Test
    public void accountLogin(){
        Intents.init();

        System.out.println("Starting the Login Acceptance Test");
        //App starts on Login Screen

        //input login details
        onView(withId(R.id.LoginUsernameInput)).perform(typeText(usernameInput), closeSoftKeyboard());
        onView(withId(R.id.LoginPasswordInput)).perform(typeText(passwordInput), closeSoftKeyboard());

        System.out.println("Clicking Login Button");
        onView(withId(R.id.loginButton)).perform(click());

        intended(hasComponent(ScrollingActivity.class.getName()));
        Intents.release();

        System.out.println("Login Acceptance Test finished.\n");
    }

}

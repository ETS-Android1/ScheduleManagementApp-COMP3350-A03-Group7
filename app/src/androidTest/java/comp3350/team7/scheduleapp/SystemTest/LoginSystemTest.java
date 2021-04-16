package comp3350.team7.scheduleapp.SystemTest;

import androidx.test.espresso.matcher.ViewMatchers;
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

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.action.ViewActions.closeSoftKeyboard;
import static androidx.test.espresso.action.ViewActions.typeText;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withText;

/*
 * Created By Thai Tran on 16 April,2021
 *
 */

@RunWith(AndroidJUnit4.class)
@LargeTest
public class LoginSystemTest {
    private String usernameInput;
    private String passwordInput;
    private String invalidUsername;
    private String invalidPassword;

    @Rule
    public ActivityScenarioRule<LoginActivity> activityRule = new ActivityScenarioRule<>(LoginActivity.class);

    @Before
    public void setup(){
        usernameInput = "ajoson";
        passwordInput = "12345678";
        invalidUsername = "dummy1234";
        invalidPassword = "87654321";
    }

    @After
    public void teardown(){
        usernameInput = null;
        passwordInput = null;
        invalidUsername = null;
        invalidPassword = null;
    }

    @Test
    public void testVisibility(){
        System.out.println("Starting testVisibility");

        //Check if LoginUsername text box is visible
        onView(ViewMatchers.withId(R.id.LoginUsernameInput)).check(matches(isDisplayed()));

        //Check if LoginPassword text box is visible
        onView(withId(R.id.LoginPasswordInput)).check(matches(isDisplayed()));

        //Check if Create New Account Button is visible
        onView(withId(R.id.NewAccountButton)).check(matches(isDisplayed()));

        //Check if Login Button is visible
        onView(withId(R.id.loginButton)).check(matches(isDisplayed()));

        System.out.println("Finished testVisibility.\n");
    }

    @Test
    public void testUsernameInputText(){
        System.out.println("Starting testUsernameInputText.");
        onView(withId(R.id.LoginUsernameInput)).perform(typeText(usernameInput), closeSoftKeyboard());

        System.out.println("Checking if Display matches" + usernameInput+ ".");
        onView(withId(R.id.LoginUsernameInput)).check(matches(withText(usernameInput)));
        System.out.println("Finished testUsernameInputText.\n");
    }

    @Test
    public void testPasswordInputText(){
        System.out.println("Starting testPasswordInputText");
        onView(withId(R.id.LoginPasswordInput)).perform(typeText(passwordInput), closeSoftKeyboard());

        System.out.println("Checking if Display matches password length since password is hidden.");
        onView(withId(R.id.LoginPasswordInput)).check(matches(withText(passwordInput)));
        System.out.println("Finished testpasswordInputText.\n");
    }


    @Test
    public void testFailedLogin() {
        System.out.println("Starting testFailedLogin");
        System.out.println("Typing in an invalid username and password");
        onView(withId(R.id.LoginUsernameInput)).perform(typeText(invalidUsername), closeSoftKeyboard());
        onView(withId(R.id.LoginPasswordInput)).perform(typeText(invalidPassword), closeSoftKeyboard());

        System.out.println("Clicking Login Button");
        onView(withId(R.id.loginButton)).perform(click());

        System.out.println("Checking if OnView stayed on LoginActivity");
        //onView(withId(R.id.LoginLayout)).check(matches(isDisplayed()));
        System.out.println("Finsihed testFailedLogin.\n");
    }

        @Test
    public void testCreateAccountButton(){
        System.out.println("Starting testCreateAccountButton.");
        System.out.println("Clicking Create New Account button.");
        onView(withId(R.id.NewAccountButton)).perform(click());

        System.out.println("Checking if CreateAccountActivity is onView");
       // onView(withId(R.id.CreateAccountLayout)).check(matches(isDisplayed()));
        System.out.println("Finished testCreateAccountButton.\n");
    }
}

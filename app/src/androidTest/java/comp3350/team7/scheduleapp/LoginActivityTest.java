package comp3350.team7.scheduleapp;

import androidx.test.ext.junit.rules.ActivityScenarioRule;
import androidx.test.ext.junit.runners.AndroidJUnit4;

import org.junit.After;
import org.junit.runner.RunWith;
import org.junit.Rule;
import org.junit.Test;
import org.junit.Before;

import comp3350.team7.scheduleapp.presentation.activity.LoginActivity;
import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.*;
import static androidx.test.espresso.assertion.ViewAssertions.*;
import static androidx.test.espresso.matcher.ViewMatchers.*;

@RunWith(AndroidJUnit4.class)
public class LoginActivityTest {
    private String usernameInput;
    private String passwordInput;
    private String invalidUsername;
    private String invalidPassword;

    @Rule
    public ActivityScenarioRule<LoginActivity> activityRule = new ActivityScenarioRule<>(LoginActivity.class);

    @Before
    public void setup(){
        usernameInput = "josona1234";
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
    public void visibilityTest(){
        System.out.println("Starting visibilityTest.");

        //check if Login Username textbox is visible
        onView(withId(R.id.LoginUsernameInput)).check(matches(isDisplayed()));

        //check if login password textbox is visible
        onView(withId(R.id.LoginPasswordInput)).check(matches(isDisplayed()));

        //check if login button is visible
        onView(withId(R.id.loginButton)).check(matches(isDisplayed()));

        //check if Create New Account button is visible
        onView(withId(R.id.NewAccountButton)).check(matches(isDisplayed()));
        System.out.println("Finished visibility Test");
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
    public void testSuccessfulLogin(){
        System.out.println("Starting testSuccessfulLogin");
        System.out.println("Typing in an invalid username and password");
        onView(withId(R.id.LoginUsernameInput)).perform(typeText(usernameInput), closeSoftKeyboard());
        onView(withId(R.id.LoginPasswordInput)).perform(typeText(passwordInput), closeSoftKeyboard());

        System.out.println("Clicking Login Button");
        onView(withId(R.id.loginButton)).perform(click());

        onView(withId(R.id.ScrollingLayout)).check(matches(isDisplayed()));
        System.out.println("Finished testSuccessfulLogin.\n");
    }

    @Test
    public void testFailedLogin(){
        System.out.println("Starting testFailedLogin");
        System.out.println("Typing in an invalid username and password");
        onView(withId(R.id.LoginUsernameInput)).perform(typeText(invalidUsername), closeSoftKeyboard());
        onView(withId(R.id.LoginPasswordInput)).perform(typeText(invalidPassword), closeSoftKeyboard());

        System.out.println("Clicking Login Button");
        onView(withId(R.id.loginButton)).perform(click());

        System.out.println("Checking if OnView stayed on LoginActivity");
        onView(withId(R.id.LoginLayout)).check(matches(isDisplayed()));
        System.out.println("Finsihed testFailedLogin.\n");
    }

    @Test
    public void testCreateAccountButton(){
        System.out.println("Starting testCreateAccountButton.");
        System.out.println("Clicking Create New Account button.");
        onView(withId(R.id.NewAccountButton)).perform(click());

        System.out.println("Checking if CreateAccountActivity is onView");
        onView(withId(R.id.CreateAccountLayout)).check(matches(isDisplayed()));
        System.out.println("Finished testCreateAccountButton.\n");
    }
}

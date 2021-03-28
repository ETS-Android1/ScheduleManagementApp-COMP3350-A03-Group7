package comp3350.team7.scheduleapp;


import androidx.test.ext.junit.rules.ActivityScenarioRule;
import androidx.test.ext.junit.runners.AndroidJUnit4;

import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import comp3350.team7.scheduleapp.logic.exceptions.UserDBException;
import comp3350.team7.scheduleapp.presentation.activity.CreateAccountActivity;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.action.ViewActions.closeSoftKeyboard;
import static androidx.test.espresso.action.ViewActions.typeText;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withText;

@RunWith(AndroidJUnit4.class)
public class CreateAccountActivityTest {
    @Rule
    public ActivityScenarioRule<CreateAccountActivity> activityRule = new ActivityScenarioRule<>(CreateAccountActivity.class);
    private String firstNameInput;
    private String lastNameInput;
    private String usernameInput;
    private String passwordInput;
    private String confirmPWInput;

    @Before
    public void setup() {
        firstNameInput = "john";
        lastNameInput = "doe";
        usernameInput = "123dummy";
        passwordInput = "12345678";
        confirmPWInput = "12345678";

    }

    @After
    public void teardown() {

        firstNameInput = null;
        lastNameInput = null;
        usernameInput = null;
        passwordInput = null;
        confirmPWInput = null;
    }

    @Test
    public void visibilityTest() {
        System.out.println("Starting visibilityTest.");

        //check if Firstname textbox is visible
        onView(withId(R.id.Firstname)).check(matches(isDisplayed()));

        //check if Lastname textbox is visible
        onView(withId(R.id.Lastname)).check(matches(isDisplayed()));

        //check if Username textbox is visible
        onView(withId(R.id.Username)).check(matches(isDisplayed()));

        //check if password textbox is visible
        onView(withId(R.id.newPassword)).check(matches(isDisplayed()));

        //check if confirm password textbox is visible
        onView(withId(R.id.confirmPassword)).check(matches(isDisplayed()));

        //check if Create Account button is visible
        onView(withId(R.id.Create_Account)).check(matches(isDisplayed()));
        System.out.println("Finished visibility Test");
    }

    @Test
    public void testFirstNameInput() {
        System.out.println("Starting testFirstNameInput.");
        onView(withId(R.id.Firstname)).perform(typeText(firstNameInput), closeSoftKeyboard());

        System.out.println("Checking if Firstname textbox Display matches " + firstNameInput + ".");
        onView(withId(R.id.Firstname)).check(matches(withText(firstNameInput)));
        System.out.println("Finished testFirstNameInput.\n");
    }

    @Test
    public void testLastNameInput() {
        System.out.println("Starting testLastNameInput.");
        onView(withId(R.id.Lastname)).perform(typeText(lastNameInput), closeSoftKeyboard());

        System.out.println("Checking if Lastname textbox Display matches " + lastNameInput + ".");
        onView(withId(R.id.Lastname)).check(matches(withText(lastNameInput)));
        System.out.println("Finished testLastNameInput.\n");
    }

    @Test
    public void testUsernameInput() {
        System.out.println("Starting testUsernameInput.");
        onView(withId(R.id.Username)).perform(typeText(usernameInput), closeSoftKeyboard());

        System.out.println("Checking if Username textbox Display matches " + usernameInput + ".");
        onView(withId(R.id.Username)).check(matches(withText(usernameInput)));
        System.out.println("Finished testUsernameInput.\n");
    }

    @Test
    public void testPasswordInput() {
        System.out.println("Starting testPassword.");
        onView(withId(R.id.newPassword)).perform(typeText(passwordInput), closeSoftKeyboard());

        System.out.println("Checking if password textbox entry matches " + passwordInput + ".");
        onView(withId(R.id.newPassword)).check(matches(withText(passwordInput)));
        System.out.println("Finished testPasswordInput.\n");
    }

    @Test
    public void testConfirmPasswordInput() {
        System.out.println("Starting testConfirmPasswordInput.");
        onView(withId(R.id.confirmPassword)).perform(typeText(confirmPWInput), closeSoftKeyboard());

        System.out.println("Checking if Confirm password textbox entry matches " + confirmPWInput + ".");
        onView(withId(R.id.confirmPassword)).check(matches(withText(confirmPWInput)));
        System.out.println("Finished testConfirmPasswordInput.\n");
    }

    @Test
    public void testCreateAccountButtonWithAllEmptyFields() {
        System.out.println("Starting testCreateAccountWithAllEmptyFields.");

        //Press Create Account button
        onView(withId(R.id.Create_Account)).perform(click());

        //pressing the button with empty fields should send a toast makeText display
        //onView(withId(R.id.CreateAccountLayout)).check(matches(isDisplayed()));
        //figure out how to test for toast display using ActivityScenarioRule

        System.out.println("Finished testCreateAccountWithAllEmptyFields.\n");
    }

    @Test
    public void testCreateAccountButtonWithSomeEmptyFields() {
        System.out.println("Starting testCreateAccountWithSomeEmptyFields.");

        //Fill in some fields
        onView(withId(R.id.Firstname)).perform(typeText(firstNameInput), closeSoftKeyboard());
        onView(withId(R.id.Username)).perform(typeText(usernameInput), closeSoftKeyboard());
        onView(withId(R.id.newPassword)).perform(typeText(passwordInput), closeSoftKeyboard());
        onView(withId(R.id.confirmPassword)).perform(typeText(confirmPWInput), closeSoftKeyboard());

        //Press Create Account button
        onView(withId(R.id.Create_Account)).perform(click());

        //pressing the button with empty fields should send a toast makeText display
        //onView(withId(R.id.CreateAccountLayout)).check(matches(isDisplayed()));
        //figure out how to test for toast display using ActivityScenarioRule

        System.out.println("Finished testCreateAccountWithSomeEmptyFields.\n");
    }

    @Test
    public void testCreateAccountButtonWithMismatchedPassword() {
        String notMatchingPassword = "18273645";

        System.out.println("Starting testCreateAccountWithMismatchedPassword.");

        //Fill in some fields
        onView(withId(R.id.Firstname)).perform(typeText(firstNameInput), closeSoftKeyboard());
        onView(withId(R.id.Lastname)).perform(typeText(lastNameInput), closeSoftKeyboard());
        onView(withId(R.id.Username)).perform(typeText(usernameInput), closeSoftKeyboard());
        onView(withId(R.id.newPassword)).perform(typeText(passwordInput), closeSoftKeyboard());
        onView(withId(R.id.confirmPassword)).perform(typeText(notMatchingPassword), closeSoftKeyboard());

        //Press Create Account button
        onView(withId(R.id.Create_Account)).perform(click());

        //pressing the button with confirmPassword =/= password should display a toast message stating that "Must match the password entered."
        //and stay on CreateAccountActivity
        //onView(withId(R.id.CreateAccountLayout)).check(matches(isDisplayed()));
        //figure out how to test for toast display using ActivityScenarioRule

        System.out.println("Finished testCreateAccountWithMismatchedPassword.\n");
    }

    @Test
    public void testCreateAccountButtonSuccess() throws UserDBException {
        System.out.println("Starting testCreateAccountSuccess.");

        //Fill in some fields
        onView(withId(R.id.Firstname)).perform(typeText(firstNameInput), closeSoftKeyboard());
        onView(withId(R.id.Lastname)).perform(typeText(lastNameInput), closeSoftKeyboard());
        onView(withId(R.id.Username)).perform(typeText(usernameInput), closeSoftKeyboard());
        onView(withId(R.id.newPassword)).perform(typeText(passwordInput), closeSoftKeyboard());
        onView(withId(R.id.confirmPassword)).perform(typeText(confirmPWInput), closeSoftKeyboard());

        //Press Create Account button
        onView(withId(R.id.Create_Account)).perform(click());
        //I don't know why its returning an "Username already taken" toast when it shouldn't.
        //Pressing CreateAccount button with all correct inputs should open up ScrollingActivity
        //onView(withId(R.id.ScrollingLayout)).check(matches(isDisplayed()));
        //figure out how to test for toast display using ActivityScenarioRule

        System.out.println("This should load up ScrollingActivity");


        System.out.println("Finished testCreateAccountSuccess.\n");
    }


    @Test
    public void testCreateNotUniqueAccount() {
        String notUniqueUsername = "josona123";
        System.out.println("Starting testCreateNotUniqueAccount.");

        //Fill in some fields
        onView(withId(R.id.Firstname)).perform(typeText(firstNameInput), closeSoftKeyboard());
        onView(withId(R.id.Lastname)).perform(typeText(lastNameInput), closeSoftKeyboard());
        onView(withId(R.id.Username)).perform(typeText(notUniqueUsername), closeSoftKeyboard());
        onView(withId(R.id.newPassword)).perform(typeText(passwordInput), closeSoftKeyboard());
        onView(withId(R.id.confirmPassword)).perform(typeText(confirmPWInput), closeSoftKeyboard());

        //Press Create Account button
        onView(withId(R.id.Create_Account)).perform(click());

        //Pressing CreateAccount button with a not unique username should display a toast message stating that "Username is taken"
        //and stay on CreateAccountActivity
        //onView(withId(R.id.CreateAccountLayout)).check(matches(isDisplayed()));
        //figure out how to test for toast display using ActivityScenarioRule

        System.out.println("Finished testCreateNotUniqueAccount.\n");
    }
}

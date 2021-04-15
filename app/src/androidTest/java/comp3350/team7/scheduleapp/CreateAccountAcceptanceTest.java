package comp3350.team7.scheduleapp;

/*
 * Created By Thai Tran on 05 April,2021
 *
 */

import androidx.test.ext.junit.rules.ActivityScenarioRule;
import androidx.test.ext.junit.runners.AndroidJUnit4;

import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import comp3350.team7.scheduleapp.Utils.TestUtils;
import comp3350.team7.scheduleapp.Utils.UserInfo.FakeUserInfo;
import comp3350.team7.scheduleapp.application.DbServiceProvider;
import comp3350.team7.scheduleapp.objects.User;
import comp3350.team7.scheduleapp.persistence.UserPersistenceInterface;
import comp3350.team7.scheduleapp.presentation.activity.LoginActivity;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.action.ViewActions.closeSoftKeyboard;
import static androidx.test.espresso.action.ViewActions.typeText;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static org.junit.Assert.assertNotEquals;


@RunWith(AndroidJUnit4.class)
public class CreateAccountAcceptanceTest {
    private UserPersistenceInterface db;

    @Rule
    public ActivityScenarioRule<LoginActivity> activityRule = new ActivityScenarioRule<>(LoginActivity.class);

    @Before
    public void setup(){
        db = DbServiceProvider.getInstance().getUserPersistence();
        //User removeTestUser = new User(FakeUserInfo.firstname, FakeUserInfo.lastname, FakeUserInfo.username,FakeUserInfo.password);
        //db.deleteUser(removeTestUser);
    }

    @After
    public void teardown(){
        TestUtils.clean();
    }

    @Test
    public void createNewUser(){
        User removeTestUser = new User(FakeUserInfo.firstname, FakeUserInfo.lastname, FakeUserInfo.username,FakeUserInfo.password);
        db.deleteUser(removeTestUser);

        onView(withId(R.id.NewAccountButton)).perform((click()));

        //At CreateAccount Screen
        onView(withId(R.id.Firstname)).perform(typeText(FakeUserInfo.firstname), closeSoftKeyboard());
        onView(withId(R.id.Lastname)).perform(typeText(FakeUserInfo.lastname), closeSoftKeyboard());
        onView(withId(R.id.Username)).perform(typeText(FakeUserInfo.username), closeSoftKeyboard());
        onView(withId(R.id.newPassword)).perform(typeText(FakeUserInfo.password), closeSoftKeyboard());
        onView(withId(R.id.confirmPassword)).perform(typeText(FakeUserInfo.confirmPW), closeSoftKeyboard());

        //Press create account button
        onView(withId(R.id.Create_Account)).perform(click());

        //Check if New account is in the Database
        assertNotEquals(db.getUser(FakeUserInfo.username, FakeUserInfo.password), false);
    }
}

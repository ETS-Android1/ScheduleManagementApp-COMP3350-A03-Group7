package comp3350.team7.scheduleapp.ultils;

/*
 * Created By Thai Tran on 28 March,2021
 *
 */

import android.content.Intent;

import androidx.test.espresso.intent.Intents;
import androidx.test.espresso.matcher.ViewMatchers;
import androidx.test.ext.junit.runners.AndroidJUnit4;

import org.junit.After;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.runner.RunWith;

import comp3350.team7.scheduleapp.R;
import comp3350.team7.scheduleapp.presentation.activity.LoginActivity;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.clearText;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.action.ViewActions.closeSoftKeyboard;
import static androidx.test.espresso.action.ViewActions.typeText;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
/*
 * Created By Thai Tran on 28 March,2021
 *
 */

public class TestHelper {
    public static void intentSetup() {
        Intents.init();
    }
    public static void intentTeardown() {
        Intents.release();
    }

    public static void loginSetup(){
        onView(ViewMatchers.withId(R.id.LoginUsernameInput))
                .perform(clearText(), typeText("ttran"),closeSoftKeyboard());

        onView(withId(R.id.LoginPasswordInput))
                .perform(clearText(), typeText("123456"),closeSoftKeyboard());

        onView(withId(R.id.loginButton))
                .perform(click());

    }

}

package comp3350.team7.scheduleapp;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;

import comp3350.team7.scheduleapp.SystemTest.AddEventSystemTest;
import comp3350.team7.scheduleapp.SystemTest.CreateAccountAcceptanceTest;
import comp3350.team7.scheduleapp.SystemTest.CreateAccountSystemTest;
import comp3350.team7.scheduleapp.SystemTest.EditEventSystemTest;
import comp3350.team7.scheduleapp.SystemTest.LoginAcceptanceTest;
import comp3350.team7.scheduleapp.SystemTest.LoginSystemTest;
import comp3350.team7.scheduleapp.SystemTest.RemoveUndoEventSystemTest;
import comp3350.team7.scheduleapp.SystemTest.ViewScheduleOnDateSystemTest;

/*
 * Created By Thai Tran on 16 April,2021
 *
 */

/*
 * Created By Thai Tran on 16 April,2021
 *
 */

/*
 * Created By Thai Tran on 12 April,2021
 *
 */
@RunWith(Suite.class)
@Suite.SuiteClasses({
        AddEventSystemTest.class,
        EditEventSystemTest.class,
        ViewScheduleOnDateSystemTest.class,
        RemoveUndoEventSystemTest.class,
        CreateAccountSystemTest.class,
        CreateAccountAcceptanceTest.class,
        LoginSystemTest.class,
        LoginAcceptanceTest.class
})
public class AllAcceptanceTests {
}

package comp3350.team7.scheduleapp;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;

/*
 * Created By Thai Tran on 12 April,2021
 *
 */
@RunWith(Suite.class)
@Suite.SuiteClasses({
        /* 
        ViewScheduleOnDateSystemTest.class,*/
        AddEventSystemTest.class,
        AlarmServiceSystemTest.class,
        EditEventSystemTest.class,
        RemoveUndoEventSystemTest.class,
        CreateAccountSystemTest.class,
        LoginSystemTest.class,
        LoginAcceptanceTest.class
})
public class AllAcceptanceTests {
}

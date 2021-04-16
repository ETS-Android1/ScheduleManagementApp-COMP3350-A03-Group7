package comp3350.team7.scheduleapp;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;

import comp3350.team7.scheduleapp.logic.EventControllerIntergrationTest;
import comp3350.team7.scheduleapp.logic.IntegrationTest.UserDBManagerIT;

@RunWith(Suite.class)
@Suite.SuiteClasses({
        EventControllerIntergrationTest.class,
        UserDBManagerIT.class
})

public class AllIntegrationTests {
}

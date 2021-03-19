package comp3350.team7.scheduleapp;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;

import comp3350.team7.scheduleapp.logic.EventControllerIntergrationTest;

@RunWith(Suite.class)
@Suite.SuiteClasses({
        EventControllerIntergrationTest.class
})

public class AllIntegration {
}
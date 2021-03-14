package comp3350.team7.scheduleapp;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;

import comp3350.team7.scheduleapp.logic.EventControllerTest;
import comp3350.team7.scheduleapp.logic.EventValidatorTest;
import comp3350.team7.scheduleapp.objects.EventTest;
import comp3350.team7.scheduleapp.objects.UserTest;
import comp3350.team7.scheduleapp.persistence.EventDbStubTest;

@RunWith(Suite.class)
@Suite.SuiteClasses({
        EventControllerTest.class,
        EventDbStubTest.class,
        EventTest.class,
        EventValidatorTest.class,
        UserTest.class
        UserValidatorTest.class
})

public class AllTests {

}

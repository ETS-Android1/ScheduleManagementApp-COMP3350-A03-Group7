package comp3350.team7.scheduleapp;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;

@RunWith(Suite.class)
@Suite.SuiteClasses({
        EventControllerTest.class,
        EventDbStubTest.class,
        EventTest.class,
        EventValidatorTest.class,
        UserTest.class
})

public class AllTests {

}

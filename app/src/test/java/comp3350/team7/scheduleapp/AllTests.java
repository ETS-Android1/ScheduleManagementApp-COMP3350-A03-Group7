package comp3350.team7.scheduleapp;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
<<<<<<< HEAD
import comp3350.team7.scheduleapp.logic.EventControllerTest;
import comp3350.team7.scheduleapp.logic.EventValidatorTest;
import comp3350.team7.scheduleapp.logic.UserValidatorTest;
import comp3350.team7.scheduleapp.objects.EventTest;
import comp3350.team7.scheduleapp.objects.UserTest;
import comp3350.team7.scheduleapp.persistence.EventDbStubTest;
=======
>>>>>>> master

@RunWith(Suite.class)
@Suite.SuiteClasses({
        EventControllerTest.class,
        EventDbStubTest.class,
        EventTest.class,
        EventValidatorTest.class,
<<<<<<< HEAD
        UserTest.class,
        UserValidatorTest.class
=======
        UserTest.class
>>>>>>> master
})

public class AllTests {

<<<<<<< HEAD
}
=======
}
>>>>>>> master

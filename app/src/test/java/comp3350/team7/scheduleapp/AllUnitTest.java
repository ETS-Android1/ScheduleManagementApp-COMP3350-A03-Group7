package comp3350.team7.scheduleapp;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;

import comp3350.team7.scheduleapp.logic.EventControllerTest;
import comp3350.team7.scheduleapp.logic.EventValidatorTest;
import comp3350.team7.scheduleapp.logic.UserDBManagerTest;
import comp3350.team7.scheduleapp.logic.UserValidatorTest;
import comp3350.team7.scheduleapp.objects.EventTest;
import comp3350.team7.scheduleapp.objects.UserTest;
import comp3350.team7.scheduleapp.persistence.EventDbStubTest;
import comp3350.team7.scheduleapp.persistence.UserPersistenceIT;

/*
 * Created By Thai Tran on 12 April,2021
 *
 */
@RunWith(Suite.class)
@Suite.SuiteClasses({
        UserDBManagerTest.class,
        UserValidatorTest.class,
        EventValidatorTest.class,
        EventControllerTest.class,
        UserTest.class,
        EventTest.class,
        UserPersistenceIT.class,
        EventDbStubTest.class
        }
)
public class AllUnitTest {
}

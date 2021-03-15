package comp3350.team7.scheduleapp.logic;

import org.junit.*;
import static org.junit.Assert.*;

import comp3350.team7.scheduleapp.persistence.stubs.UserPersistenceStub;

/*
 * Created By Thai Tran on 12 March,2021
 *
 */

public class UserValidatorTest {
    public static UserValidator validatorTester;
    @Before
    public void setup(){
        validatorTester = new UserValidator(new UserPersistenceStub());
    }

    @After
    public void teardown(){
        validatorTester = null;
    }

    @Test
    public void validateInput_Is_True(){
        String firstname = "john";
        String lastname = "doe";
        String username = "johndoe123";
        String password = "TestMe";
        String confirmPassword = "TestMe";

        assertTrue("Expecting no empty User Input", validatorTester.validateInput(firstname, lastname, username, password, confirmPassword));
    }

    @Test
    public void validateInput_Is_False(){
        String firstname = "john";
        String lastname = "doe";
        String username = "";
        String password = "TestMe";
        String confirmPassword = "TestMe";

        assertFalse("Expecting an empty User input", validatorTester.validateInput(firstname, lastname, username, password, confirmPassword));
    }

    @Test
    public void validateConfirmPassword_Is_True(){
        String password = "testing123";
        String confirmPassword = "testing123";

        assertTrue("Expecting that password and confirm password matches", validatorTester.validateConfirmPassword(password,confirmPassword));
    }

    @Test
    public void validateConfirmPassword_Is_False(){
        String password = "ConfirmMustMatch";
        String confirmPassword = "IDontMatch";

        assertFalse("Expecting Password =/= Confirm Password", validatorTester.validateConfirmPassword(password,confirmPassword));
    }

    @Test
    public void isUniqueID_Is_True(){
        String uniqueID = "janedoe123";

        assertTrue("Expecting janedoe123 to be a Unique ID", validatorTester.isUniqueID(uniqueID));
    }

    @Test
    public void isUniqueID_Is_False(){
        String notUniqueID1 = "josona";

        assertFalse("Expecting josona not to be a Unique ID", validatorTester.isUniqueID(notUniqueID1));
    }

    @Test
    public void validateLogin_Is_Not_Null(){
        String username = "josona";
        String password = "aaron1234";

        assertNotNull("Expecting a User object returned by validateLogin", validatorTester.validateLogin(username, password));
    }

    @Test
    public void validateLogin_Is_Null(){
        String username = "josona";
        String password = "jackrussel";

        assertNull("Expecting a null object returned by validateLogin", validatorTester.validateLogin(username, password));
    }
}

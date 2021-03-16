package comp3350.team7.scheduleapp.logic;

import org.junit.*;
import static org.junit.Assert.*;

import comp3350.team7.scheduleapp.logic.UserValidator;
import comp3350.team7.scheduleapp.persistence.UserPersistenceStub;

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

        System.out.println("Starting validateInput_Is_True Test.");
        assertTrue("Expecting no empty User Input", validatorTester.validateInput(firstname, lastname, username, password, confirmPassword));
        System.out.println("Finished validateInput_Is_True Test.");
    }

    @Test
    public void validateInput_Is_False(){
        String firstname = "john";
        String lastname = "doe";
        String username = "";
        String password = "TestMe";
        String confirmPassword = "TestMe";

        
        System.out.println("Starting validateInput_Is_False Test.");
        assertFalse("Expecting an empty User input", validatorTester.validateInput(firstname, lastname, username, password, confirmPassword));        
        System.out.println("Finished validateInput_Is_False Test.");
    }

    @Test
    public void validateConfirmPassword_Is_True(){
        String password = "testing123";
        String confirmPassword = "testing123";

        System.out.println("Starting validateInput_Is_True Test.");
        assertTrue("Expecting that password and confirm password matches", validatorTester.validateConfirmPassword(password,confirmPassword));
        System.out.println("Finished validateInput_Is_True Test.");
    }

    @Test
    public void validateConfirmPassword_Is_False(){
        String password = "ConfirmMustMatch";
        String confirmPassword = "IDontMatch";

        System.out.println("Starting validateConfirmPassword_Is_False Test.");
        assertFalse("Expecting Password =/= Confirm Password", validatorTester.validateConfirmPassword(password,confirmPassword));
        System.out.println("Finished validateConfirmPassword_Is_False Test.");
    }

    @Test
    public void isUniqueID_Is_True(){
        String uniqueID = "janedoe123";

        System.out.println("Starting isUniqueID_Is_True Test.");
        assertTrue("Expecting janedoe123 to be a Unique ID", validatorTester.isUniqueID(uniqueID));
        System.out.println("Finished isUniqueID_Is_True Test.");
    }

    @Test
    public void isUniqueID_Is_False(){
        String notUniqueID1 = "josona";

        System.out.println("Starting isUniqueID_Is_False Test.");
        assertFalse("Expecting josona not to be a Unique ID", validatorTester.isUniqueID(notUniqueID1));
        System.out.println("Finished isUniqueID_Is_False Test.");
    }

    @Test
    public void validateLogin_Is_Not_Null(){
        String username = "josona";
        String password = "aaron1234";

        System.out.println("Starting validateLogin_Is_Not_Null Test.");
        assertNotNull("Expecting a User object returned by validateLogin", validatorTester.validateLogin(username, password));
        System.out.println("Starting validateLogin_Is_Not_Null Test.");
    }

    @Test
    public void validateLogin_Is_Null(){
        String username = "josona";
        String password = "jackrussel";

        System.out.println("Starting validateLogin_Is_Null Test.");
        assertNull("Expecting a null object returned by validateLogin", validatorTester.validateLogin(username, password));
        System.out.println("Finished validateLogin_Is_Null Test.");
    }

    @Test
    public void passwordLengthCheck_is_True(){
        String password = "ThisIsTrue";

        System.out.println("Starting passwordLengthCheck_is_True Test.");
        assertTrue("Expecting passwordLengthCheck to return true.", validatorTester.passwordLengthCheck(password));
        System.out.println("Finished passwordLengthCheck_is_True Test.");
    }

    @Test
    public void passwordLengthCheck_is_False(){
        String password1 = "PasswordIsTooLong";
        String password2 = "12345"

        System.out.println("Starting passwordLengthCheck_is_False Test.");
        assertFalse("Expecting false for password = 'PasswordIsTooLong'.", validatorTester.passwordLengthCheck(password));
        assertFalse("Expecting false for password = '12345'.", validatorTester.passwordLengthCheck(password));
        System.out.println("Finished passwordLengthCheck_is_False Test.");
    }
}

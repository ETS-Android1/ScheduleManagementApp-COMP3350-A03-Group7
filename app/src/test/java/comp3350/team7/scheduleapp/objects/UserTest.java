package comp3350.team7.scheduleapp.objects;

import org.junit.Test;

import comp3350.team7.scheduleapp.objects.User;
import static org.junit.Assert.*;

/*
 * Created By Thai Tran on 12 March,2021
 *
 */

public class UserTest {
    public static User testUser;

    @Before
    public void setup(){
        testUser = new User("John", "Doe", "johndoe123", "qwertyasdf");
    }

    @After
    public void teardown(){
        testUser = null;
    }

    @Test
    public void setFirstNameTest() {
        String newFirstName = "Jane";

        System.out.println("\nStarting setFirstNameTest");
        testUser.setFirstName(newFirstName);
        assertEquals("Expecting new first name to be Jane", newFirstName, testUser.getFirstName());
        System.out.println("Finished setFirstNameTest");
    }

    @Test
    public void setLastNameTest() {
        String newLastName = "Otter";
        
        System.out.println("\nStarting setLastNameTest");
        testUser.setLastName(newLastName);
        assertEquals("Expecting new last name to be Otter", newLastName, testUser.getLastName());
        System.out.println("Finished setLastNameTest");

    }

    @Test
    public void setUserIDTest() {
        String newUserID = "janeotter1";
        
        System.out.println("\nStarting setUserIDTest");
        testUser.setUserId(newUserID);
        assertEquals("Expecting new userID to be janeotter1", newUserID, testUser.getUserID());
        System.out.println("Finished setUserIDNameTest");

    }

    @Test
    public void setPasswordTest() {
        String newPassword = "weirdpassword";
        
        System.out.println("\nStarting setPasswordTest");
        testUser.setPassword(newPassword);
        assertEquals("Expecting new password to be weirdpassword", newPassword, testUser.getPassword());
        System.out.println("Finished setPasswordTest");
    }
}

package comp3350.team7.scheduleapp.objects;

import org.junit.Test;

import comp3350.team7.scheduleapp.objects.User;
import static org.junit.Assert.*;

/*
 * Created By Thai Tran on 12 March,2021
 *
 */

public class UserTest {
    @Test
    public void testUser1() {
        User user;
        System.out.println("\nStarting testUser");
        user = new User("Antony", "Anuraj", "antonyanuraj", "qwertyasdf");
        assertNotNull(user);
        assertTrue("Antony".equals(user.getFirstName()));
        assertTrue("Anuraj".equals(user.getLastName()));
        assertTrue("antonyanuraj".equals(user.getUserId()));
        assertTrue("qwertyasdf".equals(user.getPassword()));
        System.out.println("\nEnding testUser");
    }
}

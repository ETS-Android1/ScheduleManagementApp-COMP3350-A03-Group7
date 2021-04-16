package comp3350.team7.scheduleapp.persistence;

/*
 * Created By Thai Tran on 04 April,2021
 *
 */

import org.junit.Test;

import java.util.List;

import comp3350.team7.scheduleapp.objects.User;
import comp3350.team7.scheduleapp.persistence.stubs.UserPersistenceStub;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

/*
 * Created By Thai Tran on 04 April,2021
 *
 */

public class UserPersistenceIT {

    @Test
    public void testGetUserDB(){
        System.out.println("Starting testGetUserDB");
        UserPersistenceInterface dbStub = new UserPersistenceStub();
        assertTrue(dbStub.getUserDB() != null);

        System.out.println("Finished testGetUserDB.\n");
    }

    @Test
    public void testGetUser(){
        System.out.println("Starting testGetUser");

        UserPersistenceInterface dbStub = new UserPersistenceStub();

        System.out.println("Checking User Persistence for user ajoson");

        assertTrue("ajoson should be in the database",dbStub.getUser("ajoson","123456"));

        System.out.println("Finished TestGetUser.\n");
    }

    @Test
    public void testAddUser(){
        System.out.println("Starting testAddUser");
        UserPersistenceInterface dbStub = new UserPersistenceStub();
        List<User> userDB = dbStub.getUserDB();


        System.out.println("Starting Database size is " +userDB.size());
        System.out.println("Adding John to the user database");
        dbStub.addUser("John","Doe","test1234","12345678");

        System.out.println("Checking if John was added to the UserPersistence");
        assertEquals("userDatabase should have 5 Users", 5, userDB.size());

        System.out.println("Finished testAddUser");
    }

    @Test
    public void testDeleteUser(){
        System.out.println("Starting testDeleteUser");
        UserPersistenceInterface dbStub = new UserPersistenceStub();
        User deleteAaron = new User("Aaron", "Joson","ajoson", "123456");
        System.out.println("Checking if Aaron is in the UserPersistence");
        assertTrue(dbStub.getUser(deleteAaron.getUserId(), deleteAaron.getPassword()));

        System.out.println("Deleting Aaron from UserPersistence");
        dbStub.deleteUser(deleteAaron);

        System.out.println("Verifying if Aaron was deleted from the UserPersistence");
        assertTrue(dbStub.getUser(deleteAaron.getUserId(),deleteAaron.getPassword()));
    }
}

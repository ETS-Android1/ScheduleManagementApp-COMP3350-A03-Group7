package comp3350.team7.scheduleapp.logic.IntegrationTest;


import android.os.UserManager;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.File;
import java.io.IOException;

import comp3350.team7.scheduleapp.Helper.TestHelper;
import comp3350.team7.scheduleapp.logic.UserDBManager;
import comp3350.team7.scheduleapp.persistence.UserPersistenceInterface;
import comp3350.team7.scheduleapp.persistence.hsqldb.UserPersistenceHSQLDB;

import static org.junit.Assert.assertEquals;

/*
 * Created By Thai Tran on 31 March,2021
 *
 */

public class UserDBManagerIT {
    private File tempDatabase;
    private UserDBManager userDBManager;
    @Before
    public void setup() throws IOException{
        tempDatabase = TestHelper.cloneDb();
        UserPersistenceInterface userDB = new UserPersistenceHSQLDB(tempDatabase.getAbsolutePath().replace(".script",""));
        userDBManager = new UserDBManager(userDB);
    }

    @Test
    public void testLogin(){
        //Fetch Aaron Joson as login test
        assertEquals(UserDBManager.SUCCESS, userDBManager.login("ajoson", "12345678"));
    }

    @Test
    public void testRegister(){
        userDBManager.register("Mike","Hawk", "mikehawk123", "12345678");
        assertEquals(UserDBManager.SUCCESS, userDBManager.login("mikehawk123","12345678"));
    }

    @After
    public void tearDown(){
        //start fresh per test
        tempDatabase.delete();
    }
}

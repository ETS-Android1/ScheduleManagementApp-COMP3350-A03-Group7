package comp3350.team7.scheduleapp.logic;


import org.junit.Before;
import org.junit.Test;

import comp3350.team7.scheduleapp.objects.User;
import comp3350.team7.scheduleapp.persistence.UserPersistenceInterface;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;


public class UserDBManagerTest {
    UserPersistenceInterface userDB;
    private UserDBManager dbManager;
    private User testUser;

    @Before
    public void setup(){
        userDB = mock(UserPersistenceInterface.class);
        dbManager = new UserDBManager(userDB);
        testUser = new User("mike","hawk","mikehawk123","12345678");
    }

    @Test
    public void testLoginSuccess(){
        when(userDB.getUser("ajoson")).thenReturn(userDB.getUser("ajoson"));
        assertEquals(dbManager.login("ajoson", "123456"), UserDBManager.SUCCESS);
        verify(userDB).getUser("ajoson");
    }

    @Test
    public void testLoginFail(){
        when(userDB.getUser("mikehawk123>")).thenReturn(userDB.getUser("mikehawk123"));
        assertEquals(dbManager.login("mikehawk123", "12345678"), UserDBManager.DB_FAIL);
        verify(userDB).getUser("mikehawk123");

        //invalid input
        assertEquals(dbManager.login("",""), UserDBManager.INPUT_FAILURE);
    }

    @Test
    public void testRegisterSuccess(){
        when(userDB.addUser(testUser)).thenReturn(testUser);
        assertEquals(dbManager.register(testUser.getFirstName(),
                testUser.getLastName(),
                testUser.getUserId(),
                testUser.getPassword()), UserDBManager.SUCCESS);

        verify(userDB).addUser(testUser);
    }

    @Test
    public void testRegisterFail(){
        when(userDB.addUser(testUser)).thenReturn(null);
        assertEquals(dbManager.register(testUser.getFirstName(),
                testUser.getLastName(),
                testUser.getUserId(),
                testUser.getPassword()), UserDBManager.DB_FAIL);

        assertEquals(dbManager.register("who","dis","dude","123"),UserDBManager.INPUT_FAILURE);
    }
}

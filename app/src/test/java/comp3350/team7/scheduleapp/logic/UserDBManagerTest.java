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

    @Before
    public void setup(){
        userDB = mock(UserPersistenceInterface.class);
        dbManager = new UserDBManager(userDB);
    }

    @Test
    public void testLoginSuccess(){
        when(userDB.getUser("ajoson", "123456")).thenReturn(true);
        assertEquals(dbManager.login("ajoson", "123456"), UserDBManager.SUCCESS);
        verify(userDB).getUser("ajoson", "123456");
    }

    @Test
    public void testLoginFail(){
        when(userDB.getUser("mikehawk123", "12345678")).thenReturn(false);
        assertEquals(dbManager.login("mikehawk123", "12345678"), UserDBManager.DB_FAIL);
        verify(userDB).getUser("mikehawk123", "12345678");

        //invalid input
        assertEquals(dbManager.login("",""), UserDBManager.INPUT_FAILURE);
    }

    @Test
    public void testRegisterSuccess(){
        when(userDB.addUser("mike", "hawk", "mikehawk123", "12345678")).thenReturn(true);
        assertEquals(dbManager.register("mike","hawk","mikehawk123","12345678"), UserDBManager.SUCCESS);
        verify(userDB).addUser("mike","hawk","mikehawk123","12345678");
    }

    @Test
    public void testRegisterFail(){
        when(userDB.addUser("mike", "hawk", "mikehawk123", "12345678")).thenReturn(false);
        assertEquals(dbManager.register("mike", "hawk", "mikehawk123", "12345678"), UserDBManager.DB_FAIL);
        verify(userDB).addUser("mike", "hawk", "mikehawk123", "12345678");

        assertEquals(dbManager.register("who","dis","dude","123"), UserDBManager.INPUT_FAILURE);
    }
}

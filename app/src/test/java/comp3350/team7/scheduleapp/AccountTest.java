package comp3350.team7.scheduleapp;

import org.junit.Test;
import comp3350.team7.scheduleapp.presentation.Account;

import static org.junit.Assert.*;

public class AccountTest {

    public void changePAC() {
        Account newUser = new Account("johndoe","iamjohn1");
        String newPass = "imstilljohn";
        try {
            newUser.changePassword(newPass);
        } catch (Exception e) {
            e.printStackTrace();
        }
        assertEquals(newPass, newUser.getPAC);
    }
}

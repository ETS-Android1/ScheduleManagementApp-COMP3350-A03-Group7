package comp3350.team7.scheduleapp.logic;

/*
 * Created By Thai Tran on 10 March,2021
 *
 */

import comp3350.team7.scheduleapp.Application.Services;
import comp3350.team7.scheduleapp.objects.User;
import comp3350.team7.scheduleapp.persistence.UserPersistence;

public class UserValidator {
    private static UserPersistence userDB;

    public UserValidator(){
        userDB = Services.getUserPersistence();
    }

    public static boolean validateInput(String firstname, String lastname, String userID, String password, String confirmPassword){
        boolean validInput = false;

        if((firstname.trim().length() > 0) && (lastname.trim().length() > 0) && (userID.trim().length() > 0) && (password.trim().length() > 0) && (confirmPassword.trim().length() > 0)){
            validInput = true;
        }

        return validInput;
    }

    public static boolean validateConfirmPassword(String password, String confirmPassword){
        boolean isMatching = false;

        if(password.equals(confirmPassword)){
            isMatching = true;
        }

        return isMatching;
    }

    public static boolean isUniqueID(String userID){
        boolean uniqueUserID = false;
        User userInDB = userDB.getUser(userID);

        if(userInDB == null){
            uniqueUserID = true;
        }

        return uniqueUserID;
    }

    public static User validateLogin(String userID, String password){
        User user = userDB.getUser(userID);

        if(user.getPassword() != password){
            user = null;
        }

        return user;
    }
}

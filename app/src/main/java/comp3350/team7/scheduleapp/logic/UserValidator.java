package comp3350.team7.scheduleapp.logic;


import android.util.Log;

import comp3350.team7.scheduleapp.logic.exceptions.DbErrorException;
import comp3350.team7.scheduleapp.logic.exceptions.InvalidUserException;
import comp3350.team7.scheduleapp.logic.exceptions.UserDBException;
import comp3350.team7.scheduleapp.objects.User;
import comp3350.team7.scheduleapp.persistence.UserPersistenceInterface;

public class UserValidator {
    private static final String TAG = "UserValidator";
    private static UserPersistenceInterface userDB;
    private static UserValidator validatorInstance;

    //DIP
    private UserValidator(UserPersistenceInterface dbStub) {
        userDB = dbStub;
    }

    public static UserValidator getValidatorInstance(UserPersistenceInterface dbStub) {
        if (validatorInstance == null) {
            validatorInstance = new UserValidator(dbStub);
        }
        return validatorInstance;
    }

    public static boolean validateInput(String firstname, String lastname, String userID, String password, String confirmPassword) {
        boolean validInput = false;

        if ((firstname.trim().length() > 0) && (lastname.trim().length() > 0) && (userID.trim().length() > 0) && (password.trim().length() > 0) && (confirmPassword.trim().length() > 0)) {
            validInput = true;
        }

        return validInput;
    }

    public static boolean validateConfirmPassword(String password, String confirmPassword) {
        boolean isMatching = false;

        if (password.equals(confirmPassword)) {
            isMatching = true;
        }

        return isMatching;
    }

    public static boolean isUniqueID(String userID) {
        boolean uniqueUserID = false;
        try {
            User userInDB = userDB.getUser(userID);
            if (userInDB == null) {
                uniqueUserID = true;
            }
        } catch (UserDBException err) {
            Log.d(TAG, err.getMessage() + ", Cause by: " + err.getCause());
        }

        return uniqueUserID;
    }

    public static User validateLogin(String userID, String password)  {
        try {
            User user = userDB.getUser(userID);
            if (! user.getPassword().equals(password)) {
                user = null;
            }
            return user;
        } catch (UserDBException err) {
            Log.d(TAG, err.getMessage());
        }
        return null;
    }

    public static boolean passwordLengthCheck(String p) {
        boolean correctLength = false;
        if ((p.length() >= 8) && (p.length() <= 16)) {
            correctLength = true;
        }

        return correctLength;
    }

    public static boolean userIDLengthCheck(String userID) {
        boolean correctLength = false;
        if ((userID.length() >= 8) && (userID.length() <= 16)) {
            correctLength = true;
        }
        return correctLength;
    }

}

package comp3350.team7.scheduleapp.logic;

<<<<<<< HEAD

import android.util.Log;

import comp3350.team7.scheduleapp.logic.exceptions.DbErrorException;
import comp3350.team7.scheduleapp.logic.exceptions.InvalidUserException;
import comp3350.team7.scheduleapp.logic.exceptions.UserDBException;
=======
import comp3350.team7.scheduleapp.Application.Services;
>>>>>>> parent of 2ab9576... Get thing done
import comp3350.team7.scheduleapp.objects.User;
<<<<<<< HEAD
import comp3350.team7.scheduleapp.persistence.UserPersistence;
=======
>>>>>>> 552662351bb8ec39d73b945d576956e74e883795
import comp3350.team7.scheduleapp.persistence.UserPersistenceInterface;

public class UserValidator {
    private static final String TAG = "UserValidator";
    private static UserPersistenceInterface userDB;
    private static UserValidator validatorInstance;

    //DIP
    public UserValidator(UserPersistenceInterface dbStub) {
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

    public static boolean isUniqueID(String userID, String password) {
        boolean uniqueUserID = false;
        boolean userInDB = userDB.getUser(userID, password);
        if (!userInDB) {
            uniqueUserID = true;
        }

        return uniqueUserID;
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

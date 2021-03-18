package comp3350.team7.scheduleapp.logic;


/*
 * Created By Thai Tran on 10 March,2021
 *
 */

import comp3350.team7.scheduleapp.objects.User;
import comp3350.team7.scheduleapp.persistence.UserPersistenceInterface;

public class UserValidator {
    private static UserPersistenceInterface userDB;
    private static UserValidator validatorInstance;

    //DIP
    public UserValidator(UserPersistenceInterface dbStub){
        userDB = dbStub;
    }

    public static UserValidator getValidatorInstance(UserPersistenceInterface dbStub){
        if(validatorInstance == null){
            validatorInstance = new UserValidator(dbStub);
        }
        return validatorInstance;
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

    public static boolean passwordLengthCheck(String p){
        boolean correctLength = false;
        if ((p.length() >= 8) && (p.length() <= 16)){
            correctLength = true;
        }

        return correctLength;
    }

    public static boolean userIDLengthCheck(String userID){
        boolean correctLength = false;
        if ((userID.length() >= 8) && (userID.length() <= 16)){
            correctLength = true;
        }
        return correctLength;
    }

}

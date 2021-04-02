package comp3350.team7.scheduleapp.logic;

/*
 * Created By Thai Tran on 31 March,2021
 *
 */

import comp3350.team7.scheduleapp.application.DbServiceProvider;
import comp3350.team7.scheduleapp.logic.exceptions.UserDBException;
import comp3350.team7.scheduleapp.objects.User;
import comp3350.team7.scheduleapp.persistence.UserPersistenceInterface;

public class UserDBManager {

    public static final int SUCCESS = 1;
    public static final int DB_FAIL = 0;
    public static final int INPUT_FAILURE = -1;
    private static UserPersistenceInterface db;

    public UserDBManager(){
        db = DbServiceProvider.getInstance().getUserPersistence();
    }

    public UserDBManager(UserPersistenceInterface db){
        this.db = db;
    }


    public static int register(String firstname, String lastname, String username, String password) {
        int returnVal = SUCCESS;

        if((username.length() <=16 && username.length() >= 8) && (password.length() <= 16 && password.length() >=8)){
            if(db.addUser(new User(firstname, lastname, username, password)) == null){
                returnVal = DB_FAIL;
            }
            else { returnVal = INPUT_FAILURE;}
        }
        return  returnVal;
    }

    public static int login(String username, String password){
        int returnVal = SUCCESS;
        User fromDB;
        if(username.length() > 0 && password.length() > 0){
            fromDB = db.getUser(username);
            if(fromDB == null){
                if(fromDB.getPassword() != password) {
                    returnVal = DB_FAIL;
                }
            }
        }
        else{
            returnVal = INPUT_FAILURE;
        }

        return returnVal;
    }

}

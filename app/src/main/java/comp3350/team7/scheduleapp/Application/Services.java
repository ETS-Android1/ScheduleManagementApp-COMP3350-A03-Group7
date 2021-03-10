package comp3350.team7.scheduleapp.Application;

/*
 * Created By Thai Tran on 10 March,2021
 *
 */

import comp3350.team7.scheduleapp.persistence.UserPersistence;
import comp3350.team7.scheduleapp.persistence.hsqldb.UserPersistanceHSQLDB;

public class Services {

    private static UserPersistence userDatabase = null;

    public static synchronized UserPersistence getUserPersistence(){

        if(userDatabase == null){
            userDatabase = new UserPersistanceHSQLDB(Main.getDBPathName());
        }

        return userDatabase;
    }
}

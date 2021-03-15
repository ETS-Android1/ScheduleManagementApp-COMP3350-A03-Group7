package comp3350.team7.scheduleapp.Application;

/*
 * Created By Thai Tran on 10 March,2021
 *
 */

import comp3350.team7.scheduleapp.persistence.UserPersistenceInterface;
import comp3350.team7.scheduleapp.persistence.hsqldb.UserPersistanceHSQLDB;
import comp3350.team7.scheduleapp.persistence.hsqldb.EventPersistanceInterface;
import comp3350.team7.scheduleapp.persistence.hsqldb.EventPersistanceHSQLDB;

/*
Purpose: To put a lock on the calls towards the methods and turn it into a Queue on who gets the database access
*/

public class Services {

    private static UserPersistenceInterface userDatabase = null;
    //private static EventPersistenceInterface eventDatabase = null;


    public static synchronized UserPersistenceInterface getUserPersistence(){

        if(userDatabase == null){
            userDatabase = new UserPersistanceHSQLDB(Main.getDBPathName());
        }

        return userDatabase;
    }
/*
    public static synchronized EventPersistenceInterface getEventPersistence(){

        if(eventDatabase == null){
            eventDatabase = new EventPersistanceHSQLDB(Main.getDBPathName());
        }

        return eventDatabase;
    }
  */  
}

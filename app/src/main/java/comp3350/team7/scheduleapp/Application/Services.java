package comp3350.team7.scheduleapp.application;


import comp3350.team7.scheduleapp.persistence.UserPersistenceInterface;
import comp3350.team7.scheduleapp.persistence.hsqldb.UserPersistenceHSQLDB;
import comp3350.team7.scheduleapp.persistence.EventPersistenceInterface;
import comp3350.team7.scheduleapp.persistence.hsqldb.EventPersistenceHSQLDB;

/*
Purpose: To put a lock on the calls towards the methods and turn it into a Queue on who gets the database access
*/

public class Services {

    private static UserPersistenceInterface userDatabase = null;
    //private static EventPersistenceInterface eventDatabase = null;


    public static synchronized UserPersistenceInterface getUserPersistence(){

        if(userDatabase == null){
            userDatabase = new UserPersistanceHSQLDB(comp3350.team7.scheduleapp.Application.Main.getDBPathName());
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

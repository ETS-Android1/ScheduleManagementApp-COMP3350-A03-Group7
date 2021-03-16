package comp3350.team7.scheduleapp.application;


import comp3350.team7.scheduleapp.persistence.UserInterface;
import comp3350.team7.scheduleapp.persistence.hsqldb.UserPersistenceHSQLDB;
import comp3350.team7.scheduleapp.persistence.EventPersistenceInterface;

/*
Purpose: To put a lock on the calls towards the methods and turn it into a Queue on who gets the database access
*/

public class Services {

    private static UserInterface userDatabase = null;
    private static EventPersistenceInterface eventDatabase = null;
    private static ScheduledEventPersistenceInterface scheduleDatabase  = null;


    public static synchronized UserInterface getUserPersistence(){

        if(userDatabase == null){
            userDatabase = new UserPersistenceHSQLDB(comp3350.team7.scheduleapp.Application.Main.getDBPathName());
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

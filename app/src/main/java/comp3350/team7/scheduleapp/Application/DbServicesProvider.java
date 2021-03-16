package comp3350.team7.scheduleapp.application;


import comp3350.team7.scheduleapp.persistence.EventInterface;
import comp3350.team7.scheduleapp.persistence.ScheduledEventInterface;
import comp3350.team7.scheduleapp.persistence.UserInterface;
import comp3350.team7.scheduleapp.persistence.hsqldb.EventPersistenceHSQLDB;
import comp3350.team7.scheduleapp.persistence.hsqldb.UserPersistenceHSQLDB;
import comp3350.team7.scheduleapp.persistence.hsqldb.ScheduledEventsPersistenceHSQLDB;

/*
Purpose: To put a lock on the calls towards the methods and turn it into a Queue on who gets the database access
*/

public class DbServicesProvider {

    private static UserInterface userDatabase = null;
    private static EventInterface eventDatabase = null;
    private static ScheduledEventInterface scheduleDatabase  = null;
    
    public static synchronized UserInterface getUserPersistence(){

        if(userDatabase == null){
            userDatabase = new UserPersistenceHSQLDB(comp3350.team7.scheduleapp.Application.Main.getDBPathName());
        }

        return userDatabase;
    }
    public static synchronized EventInterface getEventPersistence(){

        if(eventDatabase == null){
            eventDatabase = new EventPersistenceHSQLDB(comp3350.team7.scheduleapp.Application.Main.getDBPathName());
        }

        return eventDatabase;
    }
    public static synchronized ScheduledEventInterface getSchedulePersistence(){

        if(scheduleDatabase == null) {
            scheduleDatabase = new ScheduledEventsPersistenceHSQLDB(comp3350.team7.scheduleapp.Application.Main.getDBPathName());
        }
        return scheduleDatabase;
    }

    /* TODO: 2021-03-16
     * This only a set up for one kind of database, need dependency injection 
     */

}

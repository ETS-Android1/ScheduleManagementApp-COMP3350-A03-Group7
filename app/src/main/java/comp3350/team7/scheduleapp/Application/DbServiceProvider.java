package comp3350.team7.scheduleapp.application;


import android.util.Log;

import comp3350.team7.scheduleapp.persistence.EventPersistenceInterface;
import comp3350.team7.scheduleapp.persistence.SchedulePersistenceInterface;
import comp3350.team7.scheduleapp.persistence.UserPersistenceInterface;
import comp3350.team7.scheduleapp.persistence.hsqldb.EventPersistenceHSQLDB;
import comp3350.team7.scheduleapp.persistence.hsqldb.ScheduledEventsPersistenceHSQLDB;
import comp3350.team7.scheduleapp.persistence.hsqldb.UserPersistenceHSQLDB;
import comp3350.team7.scheduleapp.persistence.stubs.EventPersistenceStub;
import comp3350.team7.scheduleapp.persistence.stubs.SchedulePersistenceStub;
import comp3350.team7.scheduleapp.persistence.stubs.UserPersistenceStub;

/*
Purpose: To put a lock on the calls towards the methods and turn it into a Queue on who gets the database access
*/

public class DbServiceProvider {
    private static final String TAG = "DbServiceProvider";
    private static UserPersistenceInterface userDatabase = null;
    private static EventPersistenceInterface eventDatabase = null;
    private static SchedulePersistenceInterface scheduleDatabase = null;


    public static void deployDatabse(String config) {
        if (userDatabase != null && eventDatabase != null && scheduleDatabase != null) {
            if (config.equals("production")) {
                if (userDatabase == null && eventDatabase == null && scheduleDatabase == null) {
                    userDatabase = new UserPersistenceHSQLDB(comp3350.team7.scheduleapp.Application.Main.getDBPathName());
                    eventDatabase = new EventPersistenceHSQLDB(comp3350.team7.scheduleapp.Application.Main.getDBPathName());
                    scheduleDatabase = new ScheduledEventsPersistenceHSQLDB(comp3350.team7.scheduleapp.Application.Main.getDBPathName());
                }else{
                    Log.d(TAG,String.format("%s", "deploy mode: "+ config + ", Database fail to deploy, call DbServiceProvider.reset() first"));
                }
            } else if (config.equals("debug") || config.equals("development")) {
                if (userDatabase == null && eventDatabase == null && scheduleDatabase == null) {
                    userDatabase = new UserPersistenceStub();
                    eventDatabase = new EventPersistenceStub();
                    scheduleDatabase = new SchedulePersistenceStub();
                }else{
                    Log.d(TAG,String.format("%s", "deploy mode: "+ config + ", Database fail to deploy, call DbServiceProvider.reset() first"));
                }}
            }
        }
    }

    public static synchronized UserPersistenceInterface getUserPersistence() {
        return userDatabase;
    }

    public static synchronized EventPersistenceInterface getEventPersistence() {
        return eventDatabase;
    }

    public static synchronized SchedulePersistenceInterface getSchedulePersistence() {
        return scheduleDatabase;
    }

    public static synchronized void clean() {
        eventDatabase = null;
        scheduleDatabase = null;
        userDatabase = null;
    }
}

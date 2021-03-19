package comp3350.team7.scheduleapp.application;


import android.util.Log;

import comp3350.team7.scheduleapp.persistence.EventPersistenceInterface;
import comp3350.team7.scheduleapp.persistence.UserPersistenceInterface;
import comp3350.team7.scheduleapp.persistence.hsqldb.EventPersistenceHSQLDB;
import comp3350.team7.scheduleapp.persistence.hsqldb.UserPersistenceHSQLDB;
import comp3350.team7.scheduleapp.application.Main;

import comp3350.team7.scheduleapp.persistence.stubs.EventPersistenceStub;
import comp3350.team7.scheduleapp.persistence.stubs.UserPersistenceStub;
/*
Purpose: To put a lock on the calls towards the methods and turn it into a Queue on who gets the database access
*/

public class DbServiceProvider {
    private static final String TAG = "DbServiceProvider";
    private static UserPersistenceInterface userDatabase = null;
    private static EventPersistenceInterface eventDatabase = null;
    //private static SchedulePersistenceInterface scheduleDatabase = null;
    private static String current_deploy_mode = null;
    private static DbServiceProvider instance =null;


    private DbServiceProvider(){

    }
    public static synchronized DbServiceProvider getInstance(){
        if(instance == null){
            instance = new DbServiceProvider();
            instance.deployDatabase(Main.getDeployMode());
        }
        return instance;
    }


    private synchronized void deployDatabase(String config) {
        /* reset all database instances to null first, before change the deploy mode */
        if(!config.equals(current_deploy_mode)){
            reset();
        }

        if (userDatabase == null && eventDatabase == null ) {
            if (config.equals("production")) {
                userDatabase = new UserPersistenceHSQLDB(Main.getDBPathName());
                eventDatabase = new EventPersistenceHSQLDB(Main.getDBPathName());
                //scheduleDatabase = new ScheduledEventsPersistenceHSQLDB(Main.getDBPathName());

            } else if (config.equals("debug") || config.equals("development")) {
                userDatabase = new UserPersistenceStub();
                eventDatabase = new EventPersistenceStub();
                //scheduleDatabase = new SchedulePersistenceStub();

            }
            /* Since database only need 1 instance globally. look for dubug log and make sure we only get 1 of this log message */
            Log.d(TAG, String.format("%s", "deploy mode: " + config));
        }

    }
    public synchronized UserPersistenceInterface getUserPersistence() {
        return userDatabase;
    }

    public synchronized EventPersistenceInterface getEventPersistence() {
        return eventDatabase;
    }

   /* public synchronized SchedulePersistenceInterface getSchedulePersistence() {
        return scheduleDatabase;
    }*/

    public synchronized void reset() {
        eventDatabase = null;
        //scheduleDatabase = null;
        userDatabase = null;
    }
}

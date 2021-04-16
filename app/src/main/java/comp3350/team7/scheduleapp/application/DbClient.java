package comp3350.team7.scheduleapp.application;


import android.util.Log;

import comp3350.team7.scheduleapp.persistence.EventPersistenceInterface;
import comp3350.team7.scheduleapp.persistence.UserPersistenceInterface;
import comp3350.team7.scheduleapp.persistence.hsqldb.EventPersistenceHSQLDB;
import comp3350.team7.scheduleapp.persistence.hsqldb.UserPersistenceHSQLDB;
import comp3350.team7.scheduleapp.persistence.stubs.EventPersistenceStub;
import comp3350.team7.scheduleapp.persistence.stubs.UserPersistenceStub;
/*
Purpose: To put a lock on the calls towards the methods and turn it into a Queue on who gets the database access
*/

public class DbClient {
    private static final String TAG = "DbClient";
    private static final String current_deploy_mode = null;
    private static final String scriptName = "schedule";
    private static DbClient instance = null;
    private static String userDBName;
    private static UserPersistenceInterface userDatabase = null;
    private static EventPersistenceInterface eventDatabase = null;

    private DbClient() {

    }

    public static synchronized DbClient getInstance() {
        if (instance == null) {
            instance = new DbClient();
            instance.deployDatabase(Config.getDeployMode());
        }
        return instance;
    }

    public static String getDBPathName() {
        return userDBName;
    }

    public synchronized UserPersistenceInterface getUserPersistence() {
        return userDatabase;
    }

    public synchronized EventPersistenceInterface getEventPersistence() {
        return eventDatabase;
    }

    public static void setDBPathName(final String pathName) {
        userDBName = pathName;
    }

    public static String getScriptName() {
        return scriptName;
    }

    private synchronized void deployDatabase(String config) {
        /* reset all database instances to null first, before change the deploy mode */
        if (!config.equals(current_deploy_mode)) {
            reset();
        }

        if (userDatabase == null && eventDatabase == null) {
            if (config.equals("production")) {
                userDatabase = new UserPersistenceHSQLDB(getDBPathName());
                eventDatabase = new EventPersistenceHSQLDB(getDBPathName());

            } else if (config.equals("debug") || config.equals("development")) {
                userDatabase = new UserPersistenceStub();
                eventDatabase = new EventPersistenceStub();

            }
            /* Since database only need 1 instance globally. look for dubug log and make sure we only get 1 of this log message */
            Log.d(TAG, String.format("%s", "deploy mode: " + config));
        }

    }

    public synchronized void reset() {
        eventDatabase = null;
        //scheduleDatabase = null;
        userDatabase = null;
    }
}

package comp3350.team7.scheduleapp.Application;

/*
 * Created By Thai Tran on 10 March,2021
 *
 */

import android.util.Log;

public class Main {
    private static final String TAG = "Main";
    private static String userDBName = "schedule";
    private static String mode = "production";
    public static void Main(String[] args){
        Log.d(TAG,"Booting up Schedule Management Application");
        //run app
    }

    public static void setDBPathName(final String pathName){
        try {
            Class.forName("org.hsqldb.jdbcDriver").newInstance();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
           e.printStackTrace();
        } catch (ClassNotFoundException e){
           e.printStackTrace();
        }
        userDBName = pathName;
    }

    public static String getDBPathName(){
        return userDBName;
    }

    public static String getDeployMode(){
        return mode;
    }
}

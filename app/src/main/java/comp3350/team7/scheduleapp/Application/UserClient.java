package comp3350.team7.scheduleapp.application;

/*
 * Created By Thai Tran on 18 March,2021
 *
 */


import android.util.Log;

import comp3350.team7.scheduleapp.objects.User;

/*Singleton UserClient extends Application which is a base class for maintaining the application global state  */
public class UserClient{
    private static final String TAG = "UserClient"; 
    private static String userId;
    public static void setUserId(String newuserId){
        userId= newuserId ;
    }
    public static String getUserId(){
        Log.d(TAG,"Current user: " + userId);
        return userId;
    }
    
}
package comp3350.team7.scheduleapp.application;

/*
 * Created By Thai Tran on 18 March,2021
 *
 */


import comp3350.team7.scheduleapp.objects.User;

/*Singleton UserClient extends Application which is a base class for maintaining the application global state  */
public class UserClient{
    private static User user = null;
    public static User getUser(){
        return user;
    }
    public static void setUser(User newUser){
       user= newUser;
    }
}
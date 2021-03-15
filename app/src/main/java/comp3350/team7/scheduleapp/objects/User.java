package comp3350.team7.scheduleapp.objects;

import java.lang.*;
import comp3350.team7.scheduleapp.logic.UserValidator;

public class User {

    private String userId;
    private String password;
    private String firstName;
    private String lastName;
    //private Schedule my_schedule;

    public User(String firstName, String lastName, String userId, String password) {
        this.userId = userId;
        this.password = password;
        this.firstName = firstName;
        this.lastName = lastName;
        //this.my_schedule = null;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        try {
            UserValidator.passwordLengthCheck(password);
            this.password = password;
        }
        catch(Exception e){
            //print out error
            System.out.println(e);
        }
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }
    /*
    public Schedule getMy_schedule() {
        return my_schedule;
    }

    public void setMy_schedule(Schedule my_schedule) {
        this.my_schedule = my_schedule;
    }
    */
}

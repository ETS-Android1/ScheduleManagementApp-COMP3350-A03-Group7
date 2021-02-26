package comp3350.team7.scheduleapp.objects;
import java.lang.*;

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
            passwordLengthCheck(password);
            this.password = password;
        }
        catch(Exception e){
            //print out error
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

    public static void passwordLengthCheck(String p) throws Exception{
        if ((p.length() < 8)|| (p.length() > 16)){
            throw new Exception("Your password needs to be 8-16 characters");
        }
    }

    public void checkIfUsernameExists(String user_id) {
        if (checkInUserData(user_id)) {
            System.out.println("Username already exists, please try another username!");
        }
    }

    // this method would return true if username already exists in the database.
    public Boolean checkInUserData(String u) {
        Boolean answer = false;
        // going to be a search ability to go thought the data structure to find if the username exists in the database.
        return answer;
    }
}

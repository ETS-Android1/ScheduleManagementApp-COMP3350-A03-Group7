package comp3350.team7.scheduleapp.presentation;
import java.lang.*;

public class Account {
    private String username;
    private String password;
    private String firstName;
    private String lastName;

    public Account(String user, String pass, String name, String surname){
        username = user;
        password = pass;
        firstName = name;
        lastName = surname;
    }

    protected String getPAC(){
        return password;
    }

    protected String getUser(){
        return username;
    }

    protected String getFirstName(){return firstName;}

    protected String getLastName(){return lastName;}

    static boolean confirmPassword(String inputPAC1, String inputPAC2){
        return inputPAC1.equals(inputPAC2);
    }
    protected void changePassword(String newPass) throws Exception {
        try {
            passwordValidity(password, newPass);
            password = newPass;
        }
        catch(Exception e){
            System.out.println(e);
        }
    }

    protected void login(String user, String pac) throws Exception{
        if(username.equals(user) && password.equals(pac)){
            System.out.println("Successfully logged in");
        }
        else if(!username.equals(user) && password.equals(pac)){
            throw new Exception("Invalid username.");
        }
        else if(username.equals(user) && !password.equals(pac)){
            throw new Exception ("Incorrect password.");
        }
    }//end login

    static void passwordValidity(String currentPW, String newPass) throws Exception {
        if((newPass.length() < 8)|| (newPass.length() > 16)){
            throw new ArithmeticException("New password must contain 8-16 characters.");
        }
        else if(currentPW.equals(newPass)){
            throw new Exception("New password must not be the old password.");
        }//end else if new password is not the same password
    }

    /*
    protected Event getEvent(){
    }
     */
}

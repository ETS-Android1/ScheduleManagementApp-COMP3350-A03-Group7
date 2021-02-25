package comp3350.team7.scheduleapp.presentation.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.*;


import comp3350.team7.scheduleapp.R;
import comp3350.team7.scheduleapp.objects.User;

import static android.widget.Toast.*;

public class Login extends AppCompatActivity{
    static Button newUser;
    static Button login;
    static EditText ClientID, ClientPassword;
    static String userID;
    static String userPAC; //Personal access code aka password
    static User dummyAccount = new User("John", "Doe", "testing123", "123testing");

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        getView();
    }

    protected void getView(){
        ClientID = (EditText) findViewById(R.id.LoginUsernameInput);
        ClientPassword = (EditText) findViewById(R.id.LoginPasswordInput);

    }

    public void getData(){
        userID = ClientID.getText().toString();
        userPAC = ClientPassword.getText().toString();

        //check if required fields are empty
        if(TextUtils.isEmpty(userID)){
            ClientID.setError("Username is required.");
        }
        if(TextUtils.isEmpty(userPAC)){
            ClientPassword.setError("Password is required.");
        }
    }

    boolean loginCheck(String username, String password){
        if(dummyAccount.getPassword().equals(password) && dummyAccount.getUserId().equals(username)){
            makeText(Login.this, "Login Success", LENGTH_SHORT).show();
            return true;
        }
        else if(!dummyAccount.getUserId().equals(username) && dummyAccount.getPassword().equals(userPAC)){
            makeText(Login.this, "Invalid username", LENGTH_SHORT).show();
            return false;
        }
        else if(dummyAccount.getUserId().equals(username) && !dummyAccount.getPassword().equals(userPAC)) {
            Toast.makeText(Login.this, "Incorrect password", LENGTH_SHORT).show();
            return false;
        }
        else {
            Toast.makeText(Login.this, "Enter Required fields", LENGTH_SHORT).show();
            return false;
        }
    }

    void launchUserHomePage() {
        Bundle bundle = new Bundle();
        bundle.putString("WELCOME", "Welcome to user home page activity");
        Intent createEvent = new Intent(Login.this, ScrollingActivity.class);
        createEvent.putExtra("BUNDLE", bundle);
        startActivityForResult(createEvent, 200);
    }//end launchUserHomePage

    public void goToCreateAccount(View v){
        Intent goToCreateAccount = new Intent(this, CreateAccount.class);
        startActivity(goToCreateAccount);
    }

    public void logOn(View v) {
        getData();
        if (loginCheck(userID, userPAC)) {
            launchUserHomePage();
        }
    }
}
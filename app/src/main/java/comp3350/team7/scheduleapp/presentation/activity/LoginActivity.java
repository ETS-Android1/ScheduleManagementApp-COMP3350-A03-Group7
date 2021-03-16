package comp3350.team7.scheduleapp.presentation.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.*;


import comp3350.team7.scheduleapp.R;
import comp3350.team7.scheduleapp.logic.UserValidator;
import comp3350.team7.scheduleapp.Application.Services;

import comp3350.team7.scheduleapp.persistence.UserInterface;
import comp3350.team7.scheduleapp.persistence.hsqldb.UserPersistenceHSQLDB;
/*
import comp3350.team7.scheduleapp.persistence.stubs.UserPersistenceStub;
*/

import static android.widget.Toast.*;

public class LoginActivity extends AppCompatActivity{
    static EditText ClientID, ClientPassword;
    static String userID;
    static String userPAC; //Personal access code aka password
    private UserValidator validator;
    private UserInterface userDB;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);


        userDB = new UserPersistenceHSQLDB(Services.getUserPersistence());
        validator = new UserValidator(userDB);
        getView();
    }

    protected void getView(){
        ClientID = (EditText) findViewById(R.id.LoginUsernameInput);
        ClientPassword = (EditText) findViewById(R.id.LoginPasswordInput);

    }

    public void getData(){
        userID = ClientID.getText().toString();
        userPAC = ClientPassword.getText().toString();
    }


    void launchUserHomePage() {
        Bundle bundle = new Bundle();
        bundle.putString("WELCOME", "Welcome to user home page activity");
        Intent createEvent = new Intent(LoginActivity.this, ScrollingActivity.class);
        createEvent.putExtra("BUNDLE", bundle);
        startActivityForResult(createEvent, 200);
    }//end launchUserHomePage

    public void goToCreateAccount(View v){
        Intent goToCreateAccount = new Intent(this, CreateAccountActivity.class);
        startActivity(goToCreateAccount);
    }

    ppublic void logOn(View v) {
        getData();

        if(validator.validLoginInput()){
            if (validator.validateLogin(userID,userPAC) != null) {
                launchUserHomePage();
            } else{
                Toast.makeText(LoginActivity.this, "Invalid username/password.", LENGTH_SHORT).show();
            }
        } else{
            Toast.makeText(LoginActivity.this, "Please enter required fields.", LENGTH_SHORT).show();
        }
    }
}

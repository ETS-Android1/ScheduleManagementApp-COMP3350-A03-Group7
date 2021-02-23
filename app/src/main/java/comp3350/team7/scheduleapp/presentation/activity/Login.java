package comp3350.team7.scheduleapp.presentation.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.*;


import comp3350.team7.scheduleapp.R;
import comp3350.team7.scheduleapp.objects.User;

import static android.widget.Toast.*;

/*
 * Created By Thai Tran on 23 February,2021
 *
 */

/*
 * Created By Thai Tran on 23 February,2021
 *
 */

/*
 * Created By Thai Tran on 23 February,2021
 *
 */

public class Login extends AppCompatActivity implements View.OnClickListener{
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
        onClickListenerHelper();
    }

    protected void getView(){
        newUser = (Button) findViewById(R.id.Create_Account);
        login = (Button) findViewById(R.id.loginButton);

    }
    protected void onClickListenerHelper(){
        newUser.setOnClickListener(this);
        login.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.Create_Account:
                Intent goToCreateAccount = new Intent(this, CreateAccount.class);
                startActivity(goToCreateAccount);
                break;
            case R.id.loginButton:
                getData();
                if(loginCheck(userID,userPAC)) {
                    launchUserHomePage();
                }//if successful, go to user homepage, else do nothing
                break;
        }//end switch
    }//end onclick

    void getData(){
        userID = ClientID.getText().toString();
        userPAC = ClientPassword.getText().toString();
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
}
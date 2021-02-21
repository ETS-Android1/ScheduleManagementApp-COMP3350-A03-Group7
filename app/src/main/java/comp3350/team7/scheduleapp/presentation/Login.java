package comp3350.team7.scheduleapp.presentation;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.*;


import comp3350.team7.scheduleapp.R;

public class Login extends AppCompatActivity implements View.OnClickListener{
    static Button newUser;
    static Button login;


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
                launchUserHomePage();
                break;
        }//end switch
    }//end onclick

    void launchUserHomePage() {
        Bundle bundle = new Bundle();
        bundle.putString("WELCOME", "Welcome to user home page activity");
        Intent createEvent = new Intent(Login.this, ScrollingActivity.class);
        createEvent.putExtra("BUNDLE", bundle);
        startActivityForResult(createEvent, 200);

    }//end launchUserHomePage
}
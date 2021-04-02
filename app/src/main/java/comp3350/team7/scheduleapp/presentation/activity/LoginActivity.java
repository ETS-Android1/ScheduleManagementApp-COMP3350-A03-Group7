package comp3350.team7.scheduleapp.presentation.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import comp3350.team7.scheduleapp.R;
import comp3350.team7.scheduleapp.application.DbClient;
import comp3350.team7.scheduleapp.application.UserClient;
import comp3350.team7.scheduleapp.application.ultil.DbHelper;
import comp3350.team7.scheduleapp.logic.UserValidator;
import comp3350.team7.scheduleapp.persistence.UserPersistenceInterface;

import static android.widget.Toast.LENGTH_SHORT;

public class LoginActivity extends AppCompatActivity{
    static EditText ClientID, ClientPassword;
    static String userID;
    static String userPAC; //Personal access code aka password
    private static UserPersistenceInterface userDB;
    private static UserValidator validator;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        DbHelper.copyDatabaseToDevice(this);
        userDB = DbClient
                .getInstance()
                .getUserPersistence();

        validator = UserValidator.getValidatorInstance(userDB);          //line 30+31 is pretty much validator = new UserValidator(DbServicesProvicer.getUserPersistence());
                                                        //broken up for clarity.
        getView();
    }

    protected void getView() {
        ClientID = findViewById(R.id.LoginUsernameInput);
        ClientPassword = findViewById(R.id.LoginPasswordInput);

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

    public void logOn(View v) {
        getData();

        if (UserValidator.validateLogin(userID, userPAC) != null) {
            UserClient.setUserId(userID);
            launchUserHomePage();
        } else {
            Toast.makeText(LoginActivity.this, "Incorrect Username/Password", LENGTH_SHORT).show();
        }
    }
}

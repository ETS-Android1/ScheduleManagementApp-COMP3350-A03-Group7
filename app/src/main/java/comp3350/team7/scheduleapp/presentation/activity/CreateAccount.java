package comp3350.team7.scheduleapp.presentation.activity;
//C:\Users\FatCave\Desktop\Bailey's\School\COMP3350\Team-7\app\src\main\res\layout
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.*;

import comp3350.team7.scheduleapp.R;
import comp3350.team7.scheduleapp.objects.User;

public class CreateAccount extends AppCompatActivity {
    static protected User newUser;

    static Button createAccount;
    static EditText firstNameInput;
    static EditText lastNameInput;
    static EditText usernameInput;
    static EditText passwordInput;
    static EditText confirmPasswordInput;
    static String firstname;
    static String lastname;
    static String username;
    static String password;
    static String confirmPassword;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_account);

        getView();
        getData();
        onClickListenerHelper();
    }



    private void onClickListenerHelper() {
        createAccount.setOnClickListener(this);
    }

    protected void getView(){
        firstNameInput = (EditText) findViewById(R.id.Firstname);
        lastNameInput = (EditText) findViewById(R.id.Lastname);
        usernameInput = (EditText) findViewById(R.id.Username);
        passwordInput = (EditText) findViewById(R.id.newPassword);
        confirmPasswordInput = (EditText) findViewById(R.id.confirmPassword);

        createAccount = (Button) findViewById(R.id.Create_Account);
    }
    
    public void getData(){
        firstname = firstNameInput.getText().toString();
        lastname = lastNameInput.getText().toString();
        username = usernameInput.getText().toString();
        password = passwordInput.getText().toString();
        confirmPassword = confirmPasswordInput.getText().toString();

        //check if required fields are empty
        if(TextUtils.isEmpty(firstname)){
            firstNameInput.setError("Enter your first name.");
        }
        if(TextUtils.isEmpty(lastname)){
            lastNameInput.setError("Enter your first name.");
        }
        if(TextUtils.isEmpty(username)){
            usernameInput.setError("Enter your first name.");
        }
        if(TextUtils.isEmpty(password)){
            passwordInput.setError("Enter your first name.");
        }
        if(TextUtils.isEmpty(confirmPassword)){
            confirmPasswordInput.setError("Enter your first name.");
        }
    }

    @Override
    public void onClick(View v) {
        if (password.equals(confirmPassword)) {
            newUser = new User(firstname, lastname, username, password);
            makeText(CreateAccount.this, "your account has been successfully created.", LENGTH_SHORT).show();
            launchUserHomePage();
        }
        else{
            confirmPasswordInput.setError("Must match the password entered.");
        }
    }
    
    void launchUserHomePage(){
        Bundle bundle = new Bundle();
        bundle.putString("WELCOME","Welcome to user home page activity");
        Intent createEvent = new Intent(CreateAccount.this, ScrollingActivity.class);
        createEvent.putExtra("BUNDLE",bundle);
        startActivityForResult(createEvent,200);

    }

}
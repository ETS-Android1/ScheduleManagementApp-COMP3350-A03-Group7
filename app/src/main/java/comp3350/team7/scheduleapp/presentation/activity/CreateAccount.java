package comp3350.team7.scheduleapp.presentation.activity;
//C:\Users\FatCave\Desktop\Bailey's\School\COMP3350\Team-7\app\src\main\res\layout
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.*;

import comp3350.team7.scheduleapp.R;
import comp3350.team7.scheduleapp.objects.User;

/*
 * Created By Thai Tran on 23 February,2021
 *
 */

public class CreateAccount extends AppCompatActivity {
    static protected User newUser;

    static Button createAccount;
    static EditText firstNameInput;
    static EditText lastNameInput;
    static EditText usernameInput;
    static EditText passwordInput;
    static EditText confirmPasswordInput;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_account);

        getView();
//        onClickListenerHelper();
    }



//    private void onClickListenerHelper() {
//        createAccount.setOnClickListener(this);
//    }

    protected void getView(){
        firstNameInput = (EditText) findViewById(R.id.Firstname);
        lastNameInput = (EditText) findViewById(R.id.Lastname);
        usernameInput = (EditText) findViewById(R.id.Username);
        passwordInput = (EditText) findViewById(R.id.newPassword);
        confirmPasswordInput = (EditText) findViewById(R.id.confirmPassword);

        createAccount = (Button) findViewById(R.id.Create_Account);
    }

//    @Override
//    public void onClick(View v) {
//        String firstname;
//        String lastname;
//        String username;
//        String password;
//        String confirmPassword;
//
//        firstname = firstNameInput.getText().toString();
//        lastname = lastNameInput.getText().toString();
//        username = usernameInput.getText().toString();
//        password = passwordInput.getText().toString();
//        confirmPassword = confirmPasswordInput.getText().toString();
//
//
//        if(firstname != null && lastname!=null && username!=null && password!=null && password.equals(confirmPassword)) {
//            newUser = new User(firstname,lastname,username,password);
//            makeText(CreateAccount.this, "your account has been successfully created", LENGTH_SHORT).show();
//            launchUserHomePage();
//        }
//
//        else{
//            if(firstname == null){
//                makeText(CreateAccount.this, "Enter your first name", LENGTH_SHORT).show();
//            }
//            if(lastname == null){
//                makeText(CreateAccount.this, "Enter your last name", LENGTH_SHORT).show();
//            }
//            if(username == null){
//                makeText(CreateAccount.this, "Enter a username", LENGTH_SHORT).show();
//            }
//            if((password == null) && (confirmPassword != null)){
//                makeText(CreateAccount.this, "Enter a password", LENGTH_SHORT).show();
//            }
//            else if((password != null) && (confirmPassword == null)){
//                makeText(CreateAccount.this, "Reenter your password", LENGTH_SHORT).show();
//            }
//            else if(password.equals(confirmPassword) == false){
//                makeText(CreateAccount.this, "Passwords does not match", LENGTH_LONG).show();
//            }
//        }
//    }
    void launchUserHomePage(){
        Bundle bundle = new Bundle();
        bundle.putString("WELCOME","Welcome to user home page activity");
        Intent createEvent = new Intent(CreateAccount.this, ScrollingActivity.class);
        createEvent.putExtra("BUNDLE",bundle);
        startActivityForResult(createEvent,200);

    }

}
package comp3350.team7.scheduleapp.presentation.activity;
//C:\Users\FatCave\Desktop\Bailey's\School\COMP3350\Team-7\app\src\main\res\layout
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.*;


import comp3350.team7.scheduleapp.Application.DbServicesProvider;
import comp3350.team7.scheduleapp.R;
import comp3350.team7.scheduleapp.application.DbServiceProvider;
import comp3350.team7.scheduleapp.application.UserClient;
import comp3350.team7.scheduleapp.logic.UserValidator;
import comp3350.team7.scheduleapp.objects.User;
import comp3350.team7.scheduleapp.persistence.UserPersistenceInterface;
import comp3350.team7.scheduleapp.persistence.UserPersistenceStub;

import static android.widget.Toast.*;

public class CreateAccountActivity extends AppCompatActivity {
    static protected User newUser;
    static UserValidator validator;
    static UserPersistenceInterface userDB;


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

        userDB = DbServiceProvider
                .getInstance()
                .getUserPersistence(); //can be replaced with  = new UserPersistenceStub() for testing
        validator = UserValidator.getValidatorInstance(userDB);
        getView();
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
    }


    void launchUserHomePage(){
        Bundle bundle = new Bundle();
        bundle.putString("WELCOME","Welcome to user home page activity");
        Intent createEvent = new Intent(CreateAccountActivity.this, ScrollingActivity.class);
        createEvent.putExtra("BUNDLE",bundle);
        startActivityForResult(createEvent,200);

    }

    public void createOnClick(View v) {
        getData();
        boolean validInput = validator.validateInput(firstname, lastname, username, password, confirmPassword);

        if(validInput){ //check if all the fields arent empty
            if(validator.userIDLengthCheck(username) && validator.passwordLengthCheck(password)){

                if (validator.validateConfirmPassword(password, confirmPassword)) {

                    if(validator.isUniqueID(username)) {

                        newUser = new User(firstname, lastname, username, password);
                        UserClient.setUser(newUser);
                        userDB.addUser(newUser);
                        makeText(CreateAccountActivity.this, "Account has been successfully created.", LENGTH_SHORT).show();
                        launchUserHomePage();

                    }else{ makeText(CreateAccountActivity.this, "Username is already taken.", LENGTH_SHORT).show(); }

                }else{ confirmPasswordInput.setError("Must match the password entered."); }
            }//end if password and userIDLengthCheck

            else{ //Give the user a Useful message
                if(validator.userIDLengthCheck(username) == false){
                    makeText(CreateAccountActivity.this, "UserID must be 8-16 characters.", LENGTH_SHORT).show();
                }
                if(validator.passwordLengthCheck(password) == false){
                    makeText(CreateAccountActivity.this, "Password must be 8-16 characters.", LENGTH_SHORT).show();
                }

            }
        }//end if validInput

        else{ makeText(CreateAccountActivity.this, "Please Enter all required fields.", LENGTH_SHORT).show();}

    } //end createOnclick

}

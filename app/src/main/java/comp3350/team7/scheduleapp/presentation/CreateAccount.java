package comp3350.team7.scheduleapp.presentation;
//C:\Users\FatCave\Desktop\Bailey's\School\COMP3350\Team-7\app\src\main\res\layout
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.*;

import comp3350.team7.scheduleapp.R;

import static android.widget.Toast.*;

public class CreateAccount extends AppCompatActivity {
    protected String firstname, lastname, username, password, confirmPassword;

    Button createAccount;
    EditText firstNameInput;
    EditText lastNameInput;
    EditText usernameInput;
    EditText passwordInput;
    EditText confirmPasswordInput;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_account);

        firstNameInput = (EditText) findViewById(R.id.Firstname);
        lastNameInput = (EditText) findViewById(R.id.Lastname);
        usernameInput = (EditText) findViewById(R.id.Username);
        passwordInput = (EditText) findViewById(R.id.newPassword);
        confirmPasswordInput = (EditText) findViewById(R.id.confirmPassword);

        createAccount = (Button) findViewById(R.id.Create_Account);
        createAccount.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                firstname = firstNameInput.getText().toString();
                lastname = lastNameInput.getText().toString();
                username = usernameInput.getText().toString();
                password = passwordInput.getText().toString();
                confirmPassword = confirmPasswordInput.getText().toString();

                if(firstname != null && lastname!=null && username!=null && password.equals(confirmPassword)) {
                    newUser = new User(firstname,lastname,username,password);
                }
                else{
                    if(firstname == null){
                        makeText(CreateAccount.this, "Enter your first name", LENGTH_SHORT).show();
                    }
                    if(lastname == null){
                        makeText(CreateAccount.this, "Enter your last name", LENGTH_SHORT).show();
                    }
                    if(username == null){
                        makeText(CreateAccount.this, "Enter a username", LENGTH_SHORT).show();
                    }
                    if((password == null) && (confirmPassword != null)){
                        makeText(CreateAccount.this, "Enter a password", LENGTH_SHORT).show();
                    }
                    else if((password != null) && (confirmPassword == null)){
                        makeText(CreateAccount.this, "Reenter your password", LENGTH_SHORT).show();
                    }
                    else if(password.equals(confirmPassword) == false){
                        makeText(CreateAccount.this, "Passwords does not match", LENGTH_LONG).show();
                    }
                }
            }
        });
    }

}
package comp3350.team7.scheduleapp.presentation;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Toast;

import static android.widget.Toast.*;

public class CreateAccount extends AppCompatActivity {
    private Button createAccount;
    private String firstname, lastname, username, password, confirmPassword;

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

        createAccount = (Button) findViewById(R.id.CreateAccount);
        createAccount.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                firstname = firstNameInput.getText().toString();
                lastname = lastNameInput.getText().toString();
                username = usernameInput.getText().toString();
                password = passwordInput.getText().toString();
                confirmpassword = confirmPasswordInput.getText().toString();

                if(firstname != null && lastname!=null && username!=null && password.equals(confirmPassword)) {
                    //does something
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
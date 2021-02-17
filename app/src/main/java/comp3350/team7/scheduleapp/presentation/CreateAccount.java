package comp3350.team7.scheduleapp.presentation;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;

public class CreateAccount extends AppCompatActivity {
    private Button createAccount;
    private String firstname, lastname, username, password, confirmpassword;

    EditText firstNameInput;
    EditText lastNameInput;
    EditText usernameInput;
    EditText passwordInput;
    EditText confirmpasswordInput;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_account);

        firstNameInput = (EditText) findViewById(R.id.Firstname);
        lastNameInput = (EditText) findViewById(R.id.Lastname);
        usernameInput = (EditText) findViewById(R.id.Username);
        passwordInput = (EditText) findViewById(R.id.newPassword);
        confirmpasswordInput = (EditText) findViewById(R.id.confirmPassword);

        createAccount = (Button) findViewById(R.id.CreateAccount);
        createAccount.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                firstname = firstNameInput.getText().toString();
                lastname = lastNameInput.getText().toString();
                username = usernameInput.getText().toString();
                password = passwordInput.getText().toString();
                confirmpassword = confirmpasswordInput.getText().toString();
            }
        });
    }

}
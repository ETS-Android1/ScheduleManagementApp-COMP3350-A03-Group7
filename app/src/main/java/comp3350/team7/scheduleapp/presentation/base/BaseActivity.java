package comp3350.team7.scheduleapp.presentation.base;

/*
 * Created By Thai Tran on 18 March,2021
 *
 */


import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import comp3350.team7.scheduleapp.presentation.fragment.ErrorDialog;

public class BaseActivity extends AppCompatActivity {

    private static final String DIALOG_TAG= "DISPLAY_ERROR";
    public void onError(String message){
        if(message!= null){
            ErrorDialog dialog = new ErrorDialog(message);
            dialog.show(getSupportFragmentManager(),DIALOG_TAG);
        }
    }


}

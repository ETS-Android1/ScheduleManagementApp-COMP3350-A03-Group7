package comp3350.team7.scheduleapp.logic.exceptions;

/*
 * Created By Thai Tran on 22 February,2021
 *
 */

import android.os.Build;

import androidx.annotation.RequiresApi;

public class RandomException extends Exception{
    public RandomException() {
        super();
    }

    public RandomException(String message) {
        super(message);
    }

    public RandomException(String message, Throwable cause) {
        super(message, cause);
    }

    public RandomException(Throwable cause) {
        super(cause);
    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    protected RandomException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}

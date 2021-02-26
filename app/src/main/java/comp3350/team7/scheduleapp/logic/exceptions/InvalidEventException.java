package comp3350.team7.scheduleapp.logic.exceptions;

/*
 * Created By Thai Tran on 23 February,2021
 *
 */

import android.os.Build;

import androidx.annotation.RequiresApi;

public class InvalidEventException extends Exception {
    public InvalidEventException() {
        super();
    }

    public InvalidEventException(String message) {
        super(message);
    }

    public InvalidEventException(String message, Throwable cause) {
        super(message, cause);
    }

    public InvalidEventException(Throwable cause) {
        super(cause);
    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    protected InvalidEventException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}

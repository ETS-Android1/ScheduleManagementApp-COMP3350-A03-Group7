package comp3350.team7.scheduleapp.logic.exceptions;

/*
 * Created By Thai Tran on 23 February,2021
 *
 */

import android.os.Build;

import androidx.annotation.RequiresApi;

public class InvalidEventException extends Exception {
    public static String invalidEventMessage = "Not a valid Event";

    public InvalidEventException() {
        super();
        System.out.println(invalidEventMessage);
    }

    public InvalidEventException(String message) {
        super(message);
        System.out.println(invalidEventMessage);
    }

    public InvalidEventException(String message, Throwable cause) {
        super(message, cause);
        System.out.println(invalidEventMessage);
    }

    public InvalidEventException(Throwable cause) {
        super(cause);
        System.out.println(invalidEventMessage);
    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    protected InvalidEventException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
        System.out.println(invalidEventMessage);
    }
}

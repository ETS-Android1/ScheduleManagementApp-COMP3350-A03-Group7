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
        System.out.println(invalidEventMessage);
        super();
    }

    public InvalidEventException(String message) {
        System.out.println(invalidEventMessage);
        super(message);
    }

    public InvalidEventException(String message, Throwable cause) {
        System.out.println(invalidEventMessage);
        super(message, cause);
    }

    public InvalidEventException(Throwable cause) {
        System.out.println(invalidEventMessage);
        super(cause);
    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    protected InvalidEventException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        System.out.println(invalidEventMessage);
        super(message, cause, enableSuppression, writableStackTrace);
    }
}

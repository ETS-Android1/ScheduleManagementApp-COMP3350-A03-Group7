package comp3350.team7.scheduleapp.logic.exceptions;

/*
 * Created By Thai Tran on 23 February,2021
 *
 */

import android.os.Build;

import androidx.annotation.RequiresApi;

import comp3350.team7.scheduleapp.logic.exceptions.base.BaseException;

public class InvalidEventException extends BaseException {
    public InvalidEventException(String message) {
        super(message);
    }

    public InvalidEventException(String message, Throwable cause) {
        super(message, cause);
    }
}

package comp3350.team7.scheduleapp.logic.exceptions;
/*
 * Created By Thai Tran on 22 February,2021
 *
 */
import android.os.Build;
import androidx.annotation.RequiresApi;

import comp3350.team7.scheduleapp.logic.exceptions.base.BaseException;

public class RandomException extends BaseException {
    public RandomException(String message) {
        super(message);
    }

    public RandomException(String message, Throwable cause) {
        super(message, cause);
    }
}

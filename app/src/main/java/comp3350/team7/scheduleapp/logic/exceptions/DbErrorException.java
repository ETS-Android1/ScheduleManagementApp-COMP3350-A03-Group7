package comp3350.team7.scheduleapp.logic.exceptions;

/*
 * Created By Thai Tran on 22 February,2021
 *
 */

import android.os.Build;

import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;

import comp3350.team7.scheduleapp.logic.exceptions.base.BaseException;

/*
 * Created By Thai Tran on 23 February,2021
 *
 */

public class DbErrorException extends BaseException {

    public DbErrorException(String message) {
        super(message);
    }

    public DbErrorException(String message, Throwable cause) {
        super(message, cause);
    }
}

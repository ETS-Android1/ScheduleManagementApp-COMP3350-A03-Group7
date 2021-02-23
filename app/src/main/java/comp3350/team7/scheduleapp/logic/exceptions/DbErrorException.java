package comp3350.team7.scheduleapp.logic.exceptions;

/*
 * Created By Thai Tran on 22 February,2021
 *
 */

import android.os.Build;

import androidx.annotation.RequiresApi;

/*
 * Created By Thai Tran on 23 February,2021
 *
 */

public class DbErrorException extends Exception{

    public DbErrorException() {
        super();
    }

    public DbErrorException(String message) {
        super(message);
    }

    public DbErrorException(String message, Throwable cause) {
        super(message, cause);
    }

    public DbErrorException(Throwable cause) {
        super(cause);
    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    public DbErrorException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}

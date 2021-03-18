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
    public static String dbMessage = "Item isn't in the database";

    public DbErrorException() {
        System.out.println(dbMessage);
        super();
    }

    public DbErrorException(String message) {
        System.out.println(dbMessage);
        super(message);
    }

    public DbErrorException(String message, Throwable cause) {
        System.out.println(dbMessage);
        super(message, cause);
    }

    public DbErrorException(Throwable cause) {
        System.out.println(dbMessage);
        super(cause);
    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    public DbErrorException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        System.out.println(dbMessage);
        super(message, cause, enableSuppression, writableStackTrace);
    }
}

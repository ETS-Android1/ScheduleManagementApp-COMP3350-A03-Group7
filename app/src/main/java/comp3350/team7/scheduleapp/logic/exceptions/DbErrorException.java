package comp3350.team7.scheduleapp.logic.exceptions;

/*
 * Created By Thai Tran on 22 February,2021
 *
 */

import android.os.Build;

import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;

/*
 * Created By Thai Tran on 23 February,2021
 *
 */

public class DbErrorException extends Exception{
    public static String dbMessage = "Item isn't in the database";

    @Nullable
    @Override
    public String getMessage() {
        return super.getMessage();
    }

    public DbErrorException() {
        super();
        System.out.println(dbMessage);

    }

    public DbErrorException(String message) {
        super(message);
        System.out.println(dbMessage);
    }

    public DbErrorException(String message, Throwable cause) {
        super(message, cause);
        System.out.println(dbMessage);
    }

    public DbErrorException(Throwable cause) {
        super(cause);
        System.out.println(dbMessage);
    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    public DbErrorException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
        System.out.println(dbMessage);
    }
}

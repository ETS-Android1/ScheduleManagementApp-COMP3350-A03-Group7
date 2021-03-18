package comp3350.team7.scheduleapp.logic.exceptions;

/*
 * Created By Thai Tran on 18 March,2021
 *
 */

import androidx.annotation.Nullable;

public class ScheduleControllerException extends Exception {
    public ScheduleControllerException(String message) {
        super(message);
    }
    public ScheduleControllerException(String message, Throwable cause) {
        super(message, cause);
    }
    @Nullable
    @Override
    public String getMessage() {
        return super.getMessage();
    }

    @Nullable
    @Override
    public synchronized Throwable getCause() {
        return super.getCause();
    }


}

package comp3350.team7.scheduleapp.logic.exceptions;

/*
 * Created By Thai Tran on 18 March,2021
 *
 */


import comp3350.team7.scheduleapp.logic.exceptions.base.BaseException;

public class ScheduleControllerException extends BaseException {
    public ScheduleControllerException(String message) {
        super(message);
    }

    public ScheduleControllerException(String message, Throwable cause) {
        super(message, cause);
    }

}

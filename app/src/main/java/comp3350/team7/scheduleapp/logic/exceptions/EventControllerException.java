package comp3350.team7.scheduleapp.logic.exceptions;

/*
 * Created By Thai Tran on 18 March,2021
 *
 */


import comp3350.team7.scheduleapp.logic.exceptions.base.BaseException;

public class EventControllerException extends BaseException {
    public EventControllerException(String message) {
        super(message);
    }

    public EventControllerException(String message, Throwable cause) {
        super(message, cause);
    }

}

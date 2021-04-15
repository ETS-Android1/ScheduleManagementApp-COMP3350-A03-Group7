package comp3350.team7.scheduleapp.logic.exceptions;

/*
 * Created By Thai Tran on 18 March,2021
 *
 */

import comp3350.team7.scheduleapp.logic.exceptions.base.BaseException;

public class InvalidUserException extends BaseException {
    public InvalidUserException(String message) {
        super(message);
    }

    public InvalidUserException(String message, Throwable cause) {
        super(message, cause);
    }

}

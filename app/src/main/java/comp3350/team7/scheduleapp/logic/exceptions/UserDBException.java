package comp3350.team7.scheduleapp.logic.exceptions;

import comp3350.team7.scheduleapp.logic.exceptions.base.BaseException;

public class UserDBException extends BaseException {

    public UserDBException(String message) {
        super(message);
    }

    public UserDBException(String message, Throwable cause) {
        super(message, cause);
    }
}

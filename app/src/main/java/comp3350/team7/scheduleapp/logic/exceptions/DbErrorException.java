package comp3350.team7.scheduleapp.logic.exceptions;

/*
 * Created By Thai Tran on 22 February,2021
 *
 */


import comp3350.team7.scheduleapp.logic.exceptions.base.BaseException;

public class DbErrorException extends BaseException {
    public DbErrorException(String message) {
        super(message);
    }

    public DbErrorException(String message, Throwable cause) {
        super(message, cause);
    }
}

package comp3350.team7.scheduleapp.logic.exceptions.base;

/*
 * Created By Thai Tran on 18 March,2021
 *
 */

public class BaseException extends Exception{
    public BaseException(String message) {
        super(message);
    }

    public BaseException(String message, Throwable cause) {
        super(message, cause);

}

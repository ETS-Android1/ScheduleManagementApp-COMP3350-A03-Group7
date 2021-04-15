package comp3350.team7.scheduleapp.logic.exceptions;

/*
 * Created By Thai Tran on 01 April,2021
 *
 */

/*
 * Created By Thai Tran on 08 March,2021
 *
 */

public class DBException extends RuntimeException {
    public DBException(final Exception cause) {
        super(cause);
        System.out.println("Item is not valid.");
    }
}

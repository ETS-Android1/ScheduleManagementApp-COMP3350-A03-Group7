package comp3350.team7.scheduleapp.persistence.hsqldb;

/*
 * Created By Thai Tran on 08 March,2021
 *
 */

public class UserDBException extends RuntimeException{
    public UserDBException(final Exception cause){
        super(cause);
    }
}

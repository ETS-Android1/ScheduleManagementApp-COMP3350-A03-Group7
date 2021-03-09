package comp3350.team7.scheduleapp.persistence.hsqldb;

/*
 * Created By Thai Tran on 08 March,2021
 *
 */

public class DBException extends RuntimeException{
    public DBException(final Exception cause){
        super(cause);
    }
}

package comp3350.team7.scheduleapp.persistence.hsqldb;

public class UserDBException extends RuntimeException{
    public UserDBException(final Exception cause){
        super(cause);
    }

    public UserDBException(String message ,final Exception cause){
        super(message, cause);
    }
}

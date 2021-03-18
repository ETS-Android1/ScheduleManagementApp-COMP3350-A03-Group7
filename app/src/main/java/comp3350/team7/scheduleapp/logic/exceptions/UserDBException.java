package comp3350.team7.scheduleapp.persistence.hsqldb;

public class UserDBException extends RuntimeException{
    public static userDBMessage = "Invalid User.";

    public UserDBException(final Exception cause){
        super(cause);
    }

    public UserDBException(String message ,final Exception cause){
        System.out.println(userDBMessage);
        super(message, cause);
    }
}

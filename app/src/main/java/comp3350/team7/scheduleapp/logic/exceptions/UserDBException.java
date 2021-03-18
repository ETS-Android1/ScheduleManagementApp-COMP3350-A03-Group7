package comp3350.team7.scheduleapp.logic.exceptions;

public class UserDBException extends RuntimeException{
    public static String userDBMessage = "Invalid User";

    public UserDBException(final Exception cause){
        super(cause);
    }

    public UserDBException(String message ,final Exception cause){
        super(message, cause);
        System.out.println(userDBMessage);
    }
}

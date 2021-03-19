package comp3350.team7.scheduleapp.objects;

public abstract class AbstractUser {
    public abstract String getUserId();

    public abstract void setUserId(String userId);

    public abstract String getPassword();

    public abstract void setPassword(String password);

    public abstract String getFirstName();

    public abstract void setFirstName(String firstName);

    public abstract String getLastName();

    public abstract void setLastName(String lastName);

    public abstract void checkIfUsernameExists(String user_id);

    // this method would return true if username already exists in the database.
    public abstract Boolean checkInUserData(String u);
}

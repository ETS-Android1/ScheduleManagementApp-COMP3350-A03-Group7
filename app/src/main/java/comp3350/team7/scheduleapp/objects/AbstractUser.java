package comp3350.team7.scheduleapp.objects;

public abstract class AbstractUser {
    public String getUserId();

    public void setUserId(String userId);

    public String getPassword();

    public void setPassword(String password);

    public String getFirstName();

    public void setFirstName(String firstName);

    public String getLastName();

    public void setLastName(String lastName);


    public void checkIfUsernameExists(String user_id);

    // this method would return true if username already exists in the database.
    public Boolean checkInUserData(String u);
}

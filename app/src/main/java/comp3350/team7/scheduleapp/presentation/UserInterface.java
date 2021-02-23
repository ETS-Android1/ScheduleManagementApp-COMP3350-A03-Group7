package comp3350.team7.scheduleapp.presentation;

public interface UserInterface {
    public String getUserId();

    public void setUserId(String userId);

    public String getPassword();

    public void setPassword(String password);

    public String getFirstName();

    public void setFirstName(String firstName);

    public String getLastName();

    public void setLastName(String lastName);

    /*
    public Schedule getMy_schedule();

    public void setMy_schedule(Schedule my_schedule);
    */

    public void checkIfUsernameExists(String user_id);

    // this method would return true if username already exists in the database.
    public Boolean checkInUserData(String u);
}


public class User {

    private String userId;
    private String password;
    private String firstName;
    private String lastName;
    //private Schedule my_schedule;

    public User(String userId, String password, String firstName, String lastName) {
        this.userId = userId;
        this.password = password;
        this.firstName = firstName;
        this.lastName = lastName;
        //this.my_schedule = null;
    }

    protected String getUserId() {
        return userId;
    }

    protected void setUserId(String userId) {
        this.userId = userId;
    }

    protected String getPassword() {
        return password;
    }

    protected void setPassword(String password) {
        this.password = password;
    }

    protected String getFirstName() {
        return firstName;
    }

    protected void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    protected String getLastName() {
        return lastName;
    }

    protected void setLastName(String lastName) {
        this.lastName = lastName;
    }

    /*
    protected Schedule getMy_schedule() {
        return my_schedule;
    }

    protected void setMy_schedule(Schedule my_schedule) {
        this.my_schedule = my_schedule;
    }
    */

    protected void passwordLengthCheck(String p) throws Exception{
        if ((p.length() < 8)|| (p.length() > 16)){
            throw new Exception("Your password needs to be 8-16 characters");
        }
    }
}

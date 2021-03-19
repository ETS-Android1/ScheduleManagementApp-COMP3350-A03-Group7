package comp3350.team7.scheduleapp.objects;

import comp3350.team7.scheduleapp.logic.UserValidator;

public class User {

    private String userId;
    private String password;
    private String firstName;
    private String lastName;

    public User(String firstName, String lastName, String userId, String password) {
        this.userId = userId;
        this.password = password;
        this.firstName = firstName;
        this.lastName = lastName;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getPassword() {
        return password;
    }


    public void setPassword(String password) {

        if (UserValidator.passwordLengthCheck(password)) {
            this.password = password;
        } else {
            System.out.println("Passwords must be 8 to 16 characters");
        }

    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }


    @Override
    public String toString() {
        return "User{" +
                "userId='" + userId + '\'' +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                '}';

    }
}

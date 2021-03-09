package comp3350.team7.scheduleapp.persistence;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import comp3350.team7.scheduleapp.logic.exceptions.DbErrorException;
import comp3350.team7.scheduleapp.objects.User;

public class UserPersistenceStub implements UserPersistence {
    private List<User> userDB;

    public UserPersistenceStub(){
        this.userDB = new ArrayList<>();

        userDB.add(new User("Aaron", "Joson","josona", "aaron1234"));
        userDB.add(new User("Anthony", "Anuraj","anuraja", "anthony1234"));
        userDB.add(new User("Thai", "Tran","tranttt", "thai1234"));
        userDB.add(new User("Taylor", "Roy","royt34", "taylor1234"));

    }

    public UserPersistenceStub(List<User> userDB){
        this.userDB = userDB;
    }

    @Override
    public List<User> getUserDB(){
        return Collections.unmodifiableList(userDB);
    }

    public User validLogin(String username, String password){
        boolean result = false;
        User user = null;
        for(int i = 0; i < userDB.size() && !result; i++){
            user = userDB.get(i);
            if(user.getUserId().equals(username) && user.getPassword().equals(password)){
                return user;
            }
        }

        return null;
    }

    public User addUser(User newUser) throws DbErrorException {
        boolean existingUser = false;
        User dbEntry;

        for(int i = 0; i< userDB.size() && !existingUser; i++){
            dbEntry = userDB.get(i);

            if(dbEntry.getUserId().equals(newUser.getUserId())){
                existingUser = true;
            }
        }

        if(existingUser) {
            userDB.add(newUser);
        }
        else{
            throw new DbErrorException("Username is taken.");
        }
        return newUser;

    }

    public User updateUser(User currentUser){
        int userIndex;

        userIndex = userDB.indexOf(currentUser);
        if(userIndex >= 0){
            userDB.set(userIndex, currentUser);
        }

        return currentUser;
    }

    public void deleteUser(User currentUser){
        int userIndex;

        userIndex = userDB.indexOf(currentUser);
        if(userIndex >= 0 ){
            userDB.remove(userIndex);
        }
    }
}

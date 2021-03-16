package comp3350.team7.scheduleapp.persistence.stubs;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import comp3350.team7.scheduleapp.objects.User;
import comp3350.team7.scheduleapp.persistence.UserInterface;

/*
 * Created By Thai Tran on 14 March,2021
 *
 */

public class UserPersistenceStub implements UserInterface{
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

    @Override
    public User addUser(User newUser){
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
        return newUser;

    }

    @Override
    public User getUser(String username){
        User userCheck;
        User userExists = null;
        boolean userFound = false;

        for(int index = 0; index < userDB.size() && userFound; index++){
            userCheck = userDB.get(index);

            if(userCheck.getUserId().equals(username)){
                userFound = true;
                userExists = userCheck;
            }
        }

        return userExists;
    }

    @Override
    public void deleteUser(User currentUser){
        int userIndex;

        userIndex = userDB.indexOf(currentUser);
        if(userIndex >= 0 ){
            userDB.remove(userIndex);
        }
    }
}

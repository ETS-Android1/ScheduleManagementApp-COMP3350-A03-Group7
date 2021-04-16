package comp3350.team7.scheduleapp.persistence.stubs;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import comp3350.team7.scheduleapp.objects.User;
import comp3350.team7.scheduleapp.persistence.UserPersistenceInterface;


public class UserPersistenceStub implements UserPersistenceInterface {
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
    public boolean addUser(User newUser){
        boolean returnVal = false;
        boolean existingUser = false;
        User dbEntry;

        for(int i = 0; i< userDB.size() && !existingUser; i++){
            dbEntry = userDB.get(i);

            if(dbEntry.getUserId().equals(newUser.getUserId())){
                existingUser = true;
            }
        }

        if(!existingUser) {
            userDB.add(newUser);
            returnVal = true;
        }
        return returnVal;

    }

    @Override
    public boolean addUser(String username, String password, String firstname, String lastname){
        boolean returnVal = false;
        boolean existingUser = false;
        User dbEntry;

        for(int i = 0; i < userDB.size() && !existingUser; i++){
            dbEntry = userDB.get(i);

            if(dbEntry.getUserId().equals(username)){
                existingUser = true;
            }
        }

        if(!existingUser){
            userDB.add(new User(firstname, lastname, username, password));
            returnVal = true;
        }

        return returnVal;
    }

    @Override
    public boolean getUser(String username, String password){
        User userCheck;
        boolean userFound = false;

        for(int index = 0; index < userDB.size() && !userFound; index++){
            userCheck = userDB.get(index);

            if(userCheck.getUserId().equals(username) && userCheck.getPassword().equals(password)){
                userFound = true;
            }
        }

        return userFound;
    }

    @Override
    public void deleteUser(User currentUser){
        int userIndex;

        userIndex = userDB.indexOf(currentUser);
        if(userIndex >= 0 ){
            userDB.remove(userIndex);
        }
    }

    @Override
    public void deleteUser(String username) {
        for (User user:userDB) {
           if(user.getUserId().equals(username))
               userDB.remove(user);
        }
    }
}

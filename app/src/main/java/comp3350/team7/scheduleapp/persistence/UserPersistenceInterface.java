package comp3350.team7.scheduleapp.persistence;

/*
 * Created By Thai Tran on 04 March,2021
 *
 */

import java.util.List;

import comp3350.team7.scheduleapp.logic.exceptions.UserDBException;
import comp3350.team7.scheduleapp.objects.User;
import comp3350.team7.scheduleapp.logic.exceptions.DbErrorException;

public interface UserPersistenceInterface {

   List<User> getUserDB();

   boolean addUser(User newUser);

   boolean addUser(String username, String password, String firstname, String lastname);

   boolean getUser(String username, String password);

   void deleteUser(User user);

}

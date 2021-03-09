package comp3350.team7.scheduleapp.persistence;

/*
 * Created By Thai Tran on 04 March,2021
 *
 */

import java.util.List;

import comp3350.team7.scheduleapp.logic.exceptions.DbErrorException;
import comp3350.team7.scheduleapp.objects.User;

public interface UserPersistence {
   List<User> getUserDB();
   boolean validLogin(String username, String password);

   User addUser(User newUser) throws DbErrorException;

   User updateUser(User user);

   void deleteUser(User user);

}

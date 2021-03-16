package comp3350.team7.scheduleapp.persistence;

/*
 * Created By Thai Tran on 04 March,2021
 *
 */

import java.util.List;

import comp3350.team7.scheduleapp.logic.exceptions.DbErrorException;
import comp3350.team7.scheduleapp.objects.User;

public interface UserInterface {
   List<User> getUserDB();

   User addUser(User newUser);

   User getUser(String username);

   void deleteUser(User user);

}

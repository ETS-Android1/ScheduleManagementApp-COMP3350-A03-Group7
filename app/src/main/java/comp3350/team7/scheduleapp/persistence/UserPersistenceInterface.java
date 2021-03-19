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

   List<User> getUserDB() throws  UserDBException;

   User addUser(User newUser) throws UserDBException;

   User getUser(String username) throws  UserDBException;

   void deleteUser(User user) throws  UserDBException;

}

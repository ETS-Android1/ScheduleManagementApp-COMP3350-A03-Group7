package comp3350.team7.scheduleapp.ultils;

/*
 * Created By Thai Tran on 31 March,2021
 *
 */

import comp3350.team7.scheduleapp.application.DbClient;
import comp3350.team7.scheduleapp.objects.User;
import comp3350.team7.scheduleapp.persistence.UserPersistenceInterface;
import comp3350.team7.scheduleapp.ultils.UserInfo.FakeUserInfo;

/*
 * Created By Thai Tran on 16 April,2021
 *
 */

public class TestUtils {

    public static void setUp() {
        User u = new User(FakeUserInfo.firstname, FakeUserInfo.lastname, FakeUserInfo.username, FakeUserInfo.password);
        UserPersistenceInterface db = DbClient.getInstance().getUserPersistence();

        db.addUser(u);

    }

    public static void clean() {
        User u = new User(FakeUserInfo.firstname,FakeUserInfo.lastname,FakeUserInfo.username,FakeUserInfo.password);
        UserPersistenceInterface db = DbClient.getInstance().getUserPersistence();
        db.deleteUser(u);
    }

}

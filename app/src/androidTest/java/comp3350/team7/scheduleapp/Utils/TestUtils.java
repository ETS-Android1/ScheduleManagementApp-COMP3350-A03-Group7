package comp3350.team7.scheduleapp.Utils;

/*
 * Created By Thai Tran on 31 March,2021
 *
 */

import comp3350.team7.scheduleapp.Utils.UserInfo.FakeUserInfo;
import comp3350.team7.scheduleapp.application.DbClient;
import comp3350.team7.scheduleapp.objects.User;
import comp3350.team7.scheduleapp.persistence.UserPersistenceInterface;

public class TestUtils {

    public static void setUp(){
        User u = new User(FakeUserInfo.firstname,FakeUserInfo.lastname,FakeUserInfo.username,FakeUserInfo.password);
        UserPersistenceInterface db = DbClient.getInstance().getUserPersistence();

        db.addUser(u);

    }

    public static void clean() {
        User u = new User(FakeUserInfo.firstname,FakeUserInfo.lastname,FakeUserInfo.username,FakeUserInfo.password);
        UserPersistenceInterface db = DbClient.getInstance().getUserPersistence();
        db.deleteUser(u);
    }

}

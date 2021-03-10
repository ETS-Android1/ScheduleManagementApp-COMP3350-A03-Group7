package comp3350.team7.scheduleapp.persistence.hsqldb;

/*
 * Created By Thai Tran on 08 March,2021
 *
 */

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import comp3350.team7.scheduleapp.objects.User;
import comp3350.team7.scheduleapp.persistence.UserPersistence;

public class UserPersistanceHSQLDB implements UserPersistence{

    private final String dbPath;

    public UserPersistanceHSQLDB(final String dbPath){
        this.dbPath = dbPath;
    }

    public Connection connection() throws  SQLException{
        return DriverManager.getConnection("jdbc:hsqldb:file:" + dbPath + ";shutdown=true", "SA", "");
    }

    private User fromResultSet(final ResultSet rs) throws SQLException{
        final String firstName = rs.getString("Firstname");
        final String lastname = rs.getString("Lastname");
        final String username = rs.getString("username");
        final String password = rs.getString("password");

        return new User(firstName,lastname,username,password);
    }


    @Override
    public List<User> getUserDB() {
        final List<User> users = new ArrayList<>();

        try(final Connection c =  connection()){
            final Statement msg = c.createStatement();
            final ResultSet setResult =  msg.executeQuery("SELECT * FROM users");
            while(setResult.next()){
                final User user = fromResultSet(setResult);
                users.add(user);
            }
            setResult.close();
            msg.close();

            return users;
        }catch (final SQLException e){
            throw new DBException(e);
        }
    }
    
    @Override
    public User getUser(String username){
        final User userExists;

        try(final Connection c = connection()){
            final PreparedStatement msg = c.prepareStatement("SELECT * FROM userDB WHERE userID = ?");
            msg.setString(1, username);

            final ResultSet rs = msg.executeQuery();

            userExists = fromResultSet(rs);

            rs.close();
            msg.close();

            return userExists;
        }catch (final SQLException e){
            throw new UserDBException(e);
        }
    }

    @Override
    public User addUser(User newUser) {
        try(final Connection c = connection()){
            final PreparedStatement msg = c.prepareStatement("INSERT INTO userDB VALUES(?,?,?,?)");
            msg.setString(1, newUser.getFirstName());
            msg.setString(2, newUser.getLastName());
            msg.setString(3, newUser.getUserId());
            msg.setString(4, newUser.getPassword());
            msg.executeUpdate();

            return newUser;
        }catch (final SQLException e){
            throw new DBException(e);
        }
    }

    @Override
    public void deleteUser(User user) {
        try(final Connection c = connection()){
            final PreparedStatement msg = c.prepareStatement("DELETE FROM userDB WHERE userID = ?");
            msg.setString(1, user.getUserId());
            msg.executeUpdate();

        }catch (final SQLException e){
            throw new DBException(e);
        }
    }
}

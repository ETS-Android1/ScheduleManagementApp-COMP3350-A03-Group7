package comp3350.team7.scheduleapp.persistence.hsqldb;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import comp3350.team7.scheduleapp.objects.User;
import comp3350.team7.scheduleapp.persistence.UserPersistenceInterface;
import comp3350.team7.scheduleapp.logic.exceptions.DbErrorException;

public class UserPersistenceHSQLDB implements UserPersistenceInterface {

    private final String dbPath;

    public UserPersistenceHSQLDB(final String dbPath){
        this.dbPath = dbPath;
    }

    public Connection connection() throws  SQLException{
        return DriverManager.getConnection("jdbc:hsqldb:file:" + dbPath + ";shutdown=true", "SA", "");
    }

    private User fromResultSet(final ResultSet rs) throws SQLException {
        final String username = rs.getString("userID");
        final String password = rs.getString("password");
        final String firstName = rs.getString("firstName");
        final String lastName = rs.getString("lastName");
        return new User(firstName,lastName,username,password);
    }


    @Override
    public List<User> getUserDB() throws DbErrorException {
        final List<User> users = new ArrayList<>();

        try(final Connection c =  connection()) {
            final Statement msg = c.createStatement();
            final ResultSet rs =  msg.executeQuery("SELECT * FROM Users");
            while(rs.next()) {
                final User user = fromResultSet(rs);
                users.add(user);
            }
            rs.close();
            msg.close();

            return users;
        }catch (final SQLException e){
            throw new DbErrorException(e);
        }
    }

    @Override
    public User getUser(String username) throws DbErrorException {
        final User userExists;

        try(final Connection c = connection()) {
            final PreparedStatement msg = c.prepareStatement("SELECT * FROM Users WHERE userID = ?");
            msg.setString(1, username);

            final ResultSet rs = msg.executeQuery();

            userExists = fromResultSet(rs);

            rs.close();
            msg.close();

            return userExists;
        }catch (final SQLException e){
            throw new DbErrorException(e);
        }
    }

    @Override
    public User addUser(User newUser) throws DbErrorException {
        try(final Connection c = connection()) {
            final PreparedStatement msg = c.prepareStatement("INSERT INTO Users VALUES(?,?,?,?)");
            msg.setString(1, newUser.getUserId());
            msg.setString(2, newUser.getPassword());
            msg.setString(3, newUser.getFirstName());
            msg.setString(4, newUser.getLastName());

            msg.executeUpdate();

            return newUser;
        }catch (final SQLException e){
            throw new DbErrorException("Username is already taken.",e);
        }
    }

    @Override
    public void deleteUser(User user) throws DbErrorException {
        try(final Connection c = connection()){
            final PreparedStatement msg = c.prepareStatement("DELETE FROM Users WHERE userID = ?");
            msg.setString(1, user.getUserId());
            msg.executeUpdate();

        }catch (final SQLException e){
            throw new DbErrorException("Invalid User.",e);
        }
    }
}

package comp3350.team7.scheduleapp.persistence.hsqldb;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Calendar;

import comp3350.team7.scheduleapp.logic.exceptions.DbErrorException;
import comp3350.team7.scheduleapp.objects.Event;
import comp3350.team7.scheduleapp.persistence.EventPersistenceInterface;

public class EventPersistenceHSQLDB implements EventPersistenceInterface {
    private final String dbPath;

    public EventPersistenceHSQLDB(final String dbPath){
        this.dbPath = dbPath;
    }


    public Connection connection() throws  SQLException{
        return DriverManager.getConnection("jdbc:hsqldb:file:" + dbPath + ";shutdown=true", "SA", "");
    }

    private Event fromResultSet(final ResultSet rs) throws SQLException {
        final String userName = rs.getString("userName");
        final int eventID = rs.getInt("eventID");
        final String title = rs.getString("title");
        final String description = rs.getString("description");
        final int startYear = rs.getInt("startYear");
        final int startMonth = rs.getInt("startMonth");
        final int startDay = rs.getInt("startDay");
        final int startHour = rs.getInt("startHour");
        final int startMinute = rs.getInt("startMinute");
        Calendar start = Calendar.getInstance();
        start.set(startYear, startMonth, startDay, startHour, startMinute);
        final int endYear = rs.getInt("endYear");
        final int endMonth = rs.getInt("endMonth");
        final int endDay = rs.getInt("endDay");
        final int endHour = rs.getInt("endHour");
        final int endMinute = rs.getInt("endMinute");
        Calendar end = Calendar.getInstance();
        end.set(endYear, endMonth, endMonth, endDay, endHour, endMinute);
        return new Event(userName,eventID, title, description, start, end);
    }

    @Override
    public Event getEvent(int eventID) {
        final Event eventExists;

        try(final Connection c = connection()) {
            final PreparedStatement msg = c.prepareStatement("SELECT * FROM Event WHERE eventID = ?");
            msg.setInt(1, eventID);

            final ResultSet rs = msg.executeQuery();

            eventExists = fromResultSet(rs);

            rs.close();
            msg.close();

            return eventExists;
        }catch (final SQLException e){
            throw new DBException(e);
        }
    }

    @Override
    public List<Event> getEventList() {
        final List<Event> events = new ArrayList<>();

        try(final Connection c =  connection()) {
            final Statement msg = c.createStatement();
            final ResultSet rs =  msg.executeQuery("SELECT * FROM Events");
            while(rs.next()) {
                final Event event = fromResultSet(rs);
                events.add(event);
            }
            rs.close();
            msg.close();

            return events;
        }catch (final SQLException e){
            throw new DBException(e);
        }
    }

    @Override
    public void addEvent(Event newEvent) {
        try(final Connection c = connection()) {
            final PreparedStatement msg = c.prepareStatement("INSERT INTO Events VALUES(?,?,?,?,?,?)");
            msg.setString(1, newEvent.getTitle());
            msg.setString(2, newEvent.getDescription());

            msg.setInt(3, newEvent.getEventStart().get(Calendar.YEAR));
            msg.setInt(4, newEvent.getEventStart().get(Calendar.MONTH));
            msg.setInt(5, newEvent.getEventStart().get(Calendar.DATE));
            msg.setInt(6, newEvent.getEventStart().get(Calendar.HOUR));
            msg.setInt(7, newEvent.getEventStart().get(Calendar.MINUTE));
            msg.setInt(8, newEvent.getEventEnd().get(Calendar.YEAR));
            msg.setInt(9, newEvent.getEventEnd().get(Calendar.MONTH));
            msg.setInt(10, newEvent.getEventEnd().get(Calendar.DATE));
            msg.setInt(11, newEvent.getEventEnd().get(Calendar.HOUR));
            msg.setInt(12, newEvent.getEventEnd().get(Calendar.MINUTE));

            msg.executeUpdate();

        }catch (final SQLException e){
            throw new DBException(e);
        }
    }

    @Override
    public void removeEvent(Event e) throws DbErrorException {
        try(final Connection c = connection()){
            final PreparedStatement msg = c.prepareStatement("DELETE FROM Events WHERE eventID = ?");
            msg.setInt(1, e.getID());
            msg.executeUpdate();

        }catch (SQLException error){
            throw new DbErrorException("Fail to remove",error);
        }
    }

    @Override
    public void removeEvent(int eventId) throws DbErrorException {
        try(final Connection c = connection()){
            final PreparedStatement msg = c.prepareStatement("DELETE FROM Events WHERE eventID = ?");
            msg.setInt(1, eventId);
            msg.executeUpdate();

        }catch (SQLException error){
            throw new DbErrorException("Fail to remove",error);
        }
    }

    @Override
    public Event updateEvent(Event old, Event fresh) throws DbErrorException {
        try (final Connection connection = connection()) {
            final PreparedStatement statement = connection.prepareStatement("UPDATE tasks SET taskName = ?, deadline = ?, completed = ?, priority = ? WHERE userName = ? AND taskId = ?");
            statement.setString(1,currentTask.getName());
            statement.setDate(2,new java.sql.Date(currentTask.getDeadline().getTime()));
            statement.setBoolean(3,currentTask.getCompleted());
            statement.setInt(4,currentTask.getPriority());
            statement.setString(5,currentTask.getUsername());
            statement.setInt(6,currentTask.getTaskID());
            statement.executeUpdate();
            return true;
        } catch (final SQLException e) {
            throw new PersistenceException(e);
        }
    }

    @Override
    public int getEventListLength() {
        return 0;
    }


}

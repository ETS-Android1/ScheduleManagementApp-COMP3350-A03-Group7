package comp3350.team7.scheduleapp.persistence.hsqldb;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
//import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Calendar;

import comp3350.team7.scheduleapp.objects.Event;
import comp3350.team7.scheduleapp.persistence.SchedulePersistenceInterface;
import comp3350.team7.scheduleapp.persistence.ScheduledEventInterface;


public class ScheduledEventsPersistenceHSQLDB implements SchedulePersistenceInterface {

    private final String dbPath;

    public ScheduledEventsPersistenceHSQLDB(final String dbPath){
        this.dbPath = dbPath;
    }


    public Connection connection() throws  SQLException{
        return DriverManager.getConnection("jdbc:hsqldb:file:" + dbPath + ";shutdown=true", "SA", "");
    }

    private Event fromResultSet(final ResultSet rs) throws SQLException {
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
        return new Event(eventID, title, description, start, end);
    }

    public List<Event> getScheduleForUserOnDate(String username, Calendar date) {
        final List<Event> schedule = new ArrayList<>();
        final int year = date.get(Calendar.YEAR);
        final int month = date.get(Calendar.MONTH);
        final int day = date.get(Calendar.DATE);

        try(final Connection c = connection()) {
            final PreparedStatement msg = c.prepareStatement("SELECT * FROM ScheduledEvents NATURAL JOIN Events WHERE " +
                    "userID = ? AND startYear = ? AND startMONTH = ? AND startDay = ?)");
            msg.setString(1, username);
            msg.setInt(2, year);
            msg.setInt(2, month);
            msg.setInt(2, day);
            final ResultSet rs = msg.executeQuery();

            while(rs.next()) {
                final Event scheduledEvent = fromResultSet(rs);
                schedule.add(scheduledEvent);
            }
            rs.close();
            msg.close();

            return schedule;
        }catch (final SQLException e){
            throw new comp3350.team7.scheduleapp.persistence.hsqldb.UserDBException(e);
        }
    }

}

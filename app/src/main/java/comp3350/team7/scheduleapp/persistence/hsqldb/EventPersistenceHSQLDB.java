package comp3350.team7.scheduleapp.persistence.hsqldb;

import android.util.Log;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.sql.Timestamp;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;
import java.util.Calendar;

import comp3350.team7.scheduleapp.logic.exceptions.DbErrorException;
import comp3350.team7.scheduleapp.objects.Event;
import comp3350.team7.scheduleapp.persistence.EventPersistenceInterface;

public class EventPersistenceHSQLDB implements EventPersistenceInterface {
    private static final String TAG = "EventPersistenceHSQLDB";

    private final String dbPath;

    public EventPersistenceHSQLDB(final String dbPath){
        this.dbPath = dbPath;
    }


    public Connection connection() throws  SQLException{
        return DriverManager.getConnection("jdbc:hsqldb:file:" + dbPath + ";shutdown=true", "SA", "");
    }

    private Event fromResultSet(final ResultSet rs) throws SQLException {
        final String userName = rs.getString("USERID");
        final int eventID = rs.getInt("EVENTID");
        final String title = rs.getString("TITLE");
        final String description = rs.getString("DESCRIPTION");
//        final int startYear = rs.getInt("STARTYEAR");
//        final int startMonth = rs.getInt("STARTMONTH");
//        final int startDay = rs.getInt("STARTDAY");
//        final int startHour = rs.getInt("STARTHOUR");
//        final int startMinute = rs.getInt("STARTMINUTE");
        Date date;
        final long startLong, endLong, alarmLong;
        Calendar start, end=null, alarm = null;


        startLong = rs.getTimestamp("START").getTime();
        start = Calendar.getInstance();
        start.setTimeInMillis(startLong);

        final Timestamp endTimeStamp= rs.getTimestamp("END");
        if(endTimeStamp!=null){
            endLong =endTimeStamp.getTime();
            end = Calendar.getInstance();
            end.setTimeInMillis(endLong);
        }


        final Timestamp alarmTimeStamp = rs.getTimestamp("ALARM");
        if(alarmTimeStamp!=null){
            alarmLong =alarmTimeStamp.getTime();
            alarm = Calendar.getInstance();
            alarm.setTimeInMillis(alarmLong);
            Log.d(TAG,alarm.getTime().toString());
        }






        //start.set(startYear, startMonth, startDay, startHour, startMinute);
//        final int endYear = rs.getInt("ENDYEAR");
//        final int endMonth = rs.getInt("ENDMONTH");
//        final int endDay = rs.getInt("ENDDAY");
//        final int endHour = rs.getInt("ENDHOUR");
//        final int endMinute = rs.getInt("ENDMINUTE");
        //Calendar end = Calendar.getInstance();
        //end.set(endYear, endMonth, endMonth, endDay, endHour, endMinute);
        Event event = new Event(userName,eventID, title, description, start, end,alarm);
        return event;
    }

    @Override
    public Event getEvent(String userid, int eventID) throws DbErrorException{
        Event eventExists = null;
        try(final Connection c = connection()) {
            final PreparedStatement msg = c.prepareStatement("SELECT * FROM EVENTS WHERE EVENTID = ? AND USERID = ?");
            msg.setInt(1, eventID);
            msg.setString(2,userid);

            final ResultSet rs = msg.executeQuery();
            if(rs.next())
                eventExists = fromResultSet(rs);

            rs.close();
            msg.close();

            return eventExists;
        }catch (final SQLException e){
            throw new DbErrorException("Fail to get event from the database",e);
        }
    }

    @Override
    public List<Event> getEventList(String userid) throws DbErrorException{
        final List<Event> events = new ArrayList<>();

        try(final Connection c =  connection()) {
            final PreparedStatement msg = c.prepareStatement("SELECT * FROM EVENTS WHERE USERID =?");
            msg.setString(1,userid);
            final ResultSet rs =  msg.executeQuery();
            while(rs.next()) {
                final Event event = fromResultSet(rs);
                events.add(event);
            }
            rs.close();
            msg.close();

        }catch (final SQLException e){
            throw new DbErrorException("Fail to get event list",e);
        }

        return events;
    }

    @Override
    public void addEvent(Event newEvent) throws DbErrorException {
        try(final Connection c = connection()) {
            final PreparedStatement msg = c.prepareStatement(
                    "INSERT INTO EVENTS VALUES(DEFAULT, ?, ?, ?, ?, ?, ?)");
            msg.setString(1, newEvent.getUserName());
            msg.setString(2, newEvent.getTitle());
            msg.setString(3, newEvent.getDescription());
            msg.setTimestamp(4, new Timestamp(newEvent.getEventStart().getTimeInMillis()));
            /*msg.setInt(4, newEvent.getEventStart().get(Calendar.YEAR));
            msg.setInt(5, newEvent.getEventStart().get(Calendar.MONTH));
            msg.setInt(6, newEvent.getEventStart().get(Calendar.DATE));
            msg.setInt(7, newEvent.getEventStart().get(Calendar.HOUR));
            msg.setInt(8, newEvent.getEventStart().get(Calendar.MINUTE));*/
            if(newEvent.getEventEnd()!=null ) {
                msg.setTimestamp(5,new Timestamp(newEvent.getEventEnd().getTimeInMillis()));
                /*msg.setInt(9, newEvent.getEventEnd().get(Calendar.YEAR));
                msg.setInt(10, newEvent.getEventEnd().get(Calendar.MONTH));
                msg.setInt(11, newEvent.getEventEnd().get(Calendar.DATE));
                msg.setInt(12, newEvent.getEventEnd().get(Calendar.HOUR));
                msg.setInt(13, newEvent.getEventEnd().get(Calendar.MINUTE));*/
            }else{
                msg.setNull(5, Types.NULL);
            }

            Calendar alarm = newEvent.getAlarm();
            if(alarm!=null){
                Log.d(TAG,"alarm stored");
                msg.setTimestamp(6,new Timestamp(alarm.getTimeInMillis()));
            }else{
                msg.setNull(6, Types.NULL);
            }


            msg.executeUpdate();

        }catch (final SQLException e){
            throw new DbErrorException("Fail to add event to the database",e);
        }
    }

//    public void addEventNoEnd(Event newEvent) throws DbErrorException {
//        try(final Connection c = connection()) {
//            final PreparedStatement msg = c.prepareStatement(
//                    "INSERT INTO EVENTS(EVENTID, USERID, TITLE, DESCRIPTION, STARTYEAR, STARTMONTH, STARTDAY, STARTHOUR, " +
//                            "STARTMINUTE, ENDYEAR, ENDMONTH, ENDDAY, ENDHOUR, ENDMINUTE) " +
//                            "VALUES(DEFAULT, ?, ?, ?, ?, ?, ?, ?, ?, NULL, NULL, NULL, NULL, NULL)");
//            msg.setString(1, newEvent.getUserName());
//            msg.setString(2, newEvent.getTitle());
//            msg.setString(3, newEvent.getDescription());
//            msg.setInt(4, newEvent.getEventStart().get(Calendar.YEAR));
//            msg.setInt(5, newEvent.getEventStart().get(Calendar.MONTH));
//            msg.setInt(6, newEvent.getEventStart().get(Calendar.DATE));
//            msg.setInt(7, newEvent.getEventStart().get(Calendar.HOUR));
//            msg.setInt(8, newEvent.getEventStart().get(Calendar.MINUTE));
//
//            msg.executeUpdate();
//
//        }catch (final SQLException e){
//            throw new DbErrorException("Fail to add event to the database",e);
//        }
//    }

    @Override
    public void removeEvent(Event e) throws DbErrorException {
        try(final Connection c = connection()){
            final PreparedStatement msg = c.prepareStatement("DELETE FROM EVENTS WHERE EVENTID = ? AND USERID = ?");
            msg.setInt(1, e.getID());
            msg.setString(2,e.getUserName());
            msg.executeUpdate();

        }catch (SQLException error){
            throw new DbErrorException("Fail to remove event from database",error);
        }
    }

    @Override
    public void removeEvent(String userid,int eventId) throws DbErrorException {
        try(final Connection c = connection()){
            final PreparedStatement msg = c.prepareStatement("DELETE FROM EVENTS WHERE EVENTID = ? AND USERID = ?");
            msg.setInt(1, eventId);
            msg.setString(2,userid);
            msg.executeUpdate();

        }catch (SQLException error){
            throw new DbErrorException("Fail to remove event from database",error);
        }
    }

    @Override
    public void updateEvent(Event fresh) throws DbErrorException {
        try (final Connection connection = connection()) {
            final PreparedStatement statement = connection.prepareStatement(
                    "UPDATE EVENTS SET TITLE = ?, DESCRIPTION = ?, START = ?, END = ?, ALARM = ? WHERE USERID = ? AND EVENTID = ?");
            statement.setString(1,fresh.getTitle());
            statement.setString(2, fresh.getDescription());
            statement.setTimestamp(3, new Timestamp(fresh.getEventStart().getTimeInMillis()));
            if(fresh.getEventEnd()!=null ) {
                statement.setTimestamp(4, new Timestamp(fresh.getEventEnd().getTimeInMillis()));
                /*msg.setInt(9, newEvent.getEventEnd().get(Calendar.YEAR));
                msg.setInt(10, newEvent.getEventEnd().get(Calendar.MONTH));
                msg.setInt(11, newEvent.getEventEnd().get(Calendar.DATE));
                msg.setInt(12, newEvent.getEventEnd().get(Calendar.HOUR));
                msg.setInt(13, newEvent.getEventEnd().get(Calendar.MINUTE));*/
            }else{
                statement.setNull(4, Types.NULL);
            }
            Calendar alarm = fresh.getAlarm();
            if(alarm!=null){
                Log.d(TAG,"alarm stored");
                statement.setTimestamp(5,new Timestamp(alarm.getTimeInMillis()));

            }else{
                statement.setNull(5, Types.NULL);
            }

            /*statement.setInt(3, fresh.getEventStart().get(Calendar.YEAR));
            statement.setInt(4, fresh.getEventStart().get(Calendar.MONTH));
            statement.setInt(5, fresh.getEventStart().get(Calendar.DATE));
            statement.setInt(6, fresh.getEventStart().get(Calendar.HOUR));
            statement.setInt(7, fresh.getEventStart().get(Calendar.MINUTE));*/
           /* statement.setInt(8, fresh.getEventEnd().get(Calendar.YEAR));
            statement.setInt(9, fresh.getEventEnd().get(Calendar.MONTH));
            statement.setInt(10, fresh.getEventEnd().get(Calendar.DATE));
            statement.setInt(11, fresh.getEventEnd().get(Calendar.HOUR));
            statement.setInt(12, fresh.getEventEnd().get(Calendar.MINUTE));*/
            statement.setString(6, fresh.getUserName());
            statement.setInt(7, fresh.getID());
            statement.executeUpdate();
        } catch (final SQLException e) {
            throw new DbErrorException("Failed to update event", e);
        }
    }

    @Override
    public int getEventListLength(String userId) throws DbErrorException {
        List<Event> events =getEventList(userId);
        if(events!=null){
            return events.size();
        }

        return 0;
    }

    public List<Event> getScheduleForUserOnDate(String username, Calendar date) throws DbErrorException {
        final List<Event> schedule = new ArrayList<>();
       /* final int year = date.get(Calendar.YEAR);
        final int month = date.get(Calendar.MONTH);
        final int day = date.get(Calendar.DATE);*/

        try(final Connection c = connection()) {
            final PreparedStatement msg = c.prepareStatement(
                    "SELECT * FROM EVENTS WHERE USERID = ? AND CAST(START AS DATE) = ?");

            msg.setString(1, username);
            msg.setDate(2, new Date(date.getTimeInMillis()));
          /*  msg.setInt(2, year);
            msg.setInt(2, month);
            msg.setInt(2, day);*/
            final ResultSet rs = msg.executeQuery();

            while(rs.next()) {
                final Event scheduledEvent = fromResultSet(rs);
                schedule.add(scheduledEvent);
            }
            rs.close();
            msg.close();

            return schedule;
        }catch (final SQLException e){
            throw new DbErrorException("Can not retrieve schedule on date: " + date.getTime().toString() , e);
        }
    }

}

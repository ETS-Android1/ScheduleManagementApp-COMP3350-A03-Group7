package comp3350.team7.scheduleapp.persistence;

import java.util.Calendar;
import java.util.List;

import comp3350.team7.scheduleapp.logic.exceptions.DbErrorException;
import comp3350.team7.scheduleapp.objects.Event;


/*
 * Created By Thai Tran on 22 February,2021
 *
 */
public interface EventPersistenceInterface {
    List<Event> getEventList(String userName) throws DbErrorException;
    Event getEvent(String userName, int eventID) throws DbErrorException;
    void addEvent(Event e) throws DbErrorException;
    //void addEventNoEnd(Event newEvent) throws DbErrorException;
    void removeEvent(Event e) throws DbErrorException;
    void removeEvent(String username, int eventId) throws DbErrorException;
    void updateEvent(Event fresh) throws DbErrorException;
    List<Event> getScheduleForUserOnDate(String username, Calendar date) throws DbErrorException;

    int getEventListLength(String userid) throws DbErrorException;
}

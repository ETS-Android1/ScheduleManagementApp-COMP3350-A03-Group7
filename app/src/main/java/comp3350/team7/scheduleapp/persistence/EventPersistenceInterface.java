package comp3350.team7.scheduleapp.persistence;

import java.util.List;

import comp3350.team7.scheduleapp.logic.exceptions.DbErrorException;
import comp3350.team7.scheduleapp.objects.Event;


/*
 * Created By Thai Tran on 22 February,2021
 *
 */
public interface EventPersistenceInterface {
    List<Event> getEventList() throws DbErrorException;
    Event getEvent(int eventID) throws DbErrorException;
    void addEvent(Event e) throws DbErrorException;
    void removeEvent(Event e) throws DbErrorException;
    void removeEvent(int eventId) throws DbErrorException;
    Event updateEvent(Event old, Event fresh) throws DbErrorException;

    int getEventListLength();
}

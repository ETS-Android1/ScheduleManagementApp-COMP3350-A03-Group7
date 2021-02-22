package comp3350.team7.scheduleapp.persistence;

import java.util.List;

import comp3350.team7.scheduleapp.presentation.Event;


/*
 * Created By Thai Tran on 22 February,2021
 *
 */
public interface EventDB {
    List<Event> getEventList();
    List<Event> addEvent(Event e);
    List<Event> removeEvent(Event e) throws DbErrorException;
    List<Event> updateEvent(Event e) throws DbErrorException;
}

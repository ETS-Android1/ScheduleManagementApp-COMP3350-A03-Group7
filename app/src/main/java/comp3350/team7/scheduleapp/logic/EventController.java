package comp3350.team7.scheduleapp.logic;

import android.os.Build;
import android.util.Log;

import androidx.annotation.RequiresApi;

import java.util.Calendar;
import java.util.List;

import comp3350.team7.scheduleapp.application.DbServiceProvider;
import comp3350.team7.scheduleapp.logic.comparators.AbstractComparator;
import comp3350.team7.scheduleapp.logic.comparators.EventStartAscendingComparator;
import comp3350.team7.scheduleapp.logic.exceptions.DbErrorException;
import comp3350.team7.scheduleapp.logic.exceptions.InvalidEventException;
import comp3350.team7.scheduleapp.objects.User;
import comp3350.team7.scheduleapp.persistence.EventPersistenceInterface;
import comp3350.team7.scheduleapp.objects.Event;

/*
 * Created By Thai Tran on 22 February,2021
 *
 */
public class EventController {
    EventPersistenceInterface eventPersistence;
    AbstractComparator sortingStrategy;
    List<Event> events;

    public EventController() {
        eventPersistence = DbServiceProvider
                .getInstance()
                .getEventPersistence();
        events = null;
        // default way of sorting
        sortingStrategy = new EventStartAscendingComparator();
    }
    public EventController(EventPersistenceInterface eventPersistence){
        this.eventPersistence = eventPersistence;
        sortingStrategy = new EventStartAscendingComparator();
        events= null;
    }
    public EventController(EventPersistenceInterface eventPersistence, String username){
        this.eventPersistence = eventPersistence;
        sortingStrategy = new EventStartAscendingComparator();
        events= null;
    }

    public EventController(String userName){
        eventPersistence = DbServiceProvider
                .getInstance()
                .getEventPersistence();
        eventPersistence.getEvents(userName);
    }


    // part of strategy pattern, inject AbstractComparator
    public void setSortStrategy(AbstractComparator newSortStrategy){
        this.sortingStrategy = newSortStrategy;
    }

    public Event CreateEvent(String eventName, String description, Calendar calStart){
        Event newEvent = new Event(eventName,description,calStart);
        eventPersistence.addEvent(newEvent);
        return newEvent;
    }

    public Event CreateEvent(String eventName, String description, Calendar calStart, Calendar calEnd){
        Event newEvent = new Event(eventName,description,calStart,calEnd);
        eventPersistence.addEvent(newEvent);
        return newEvent;
    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    public List<Event> getEventList(){
        List<Event> eventList = eventPersistence.getEventList();
        eventList.sort(sortingStrategy);
        return eventList;
    }

    // Someone use our api to create an invalid event, let them catch it
    @RequiresApi(api = Build.VERSION_CODES.N)
    public List<Event> addEvent(Event e) throws InvalidEventException {
        if (EventValidator.valid(e)){
            return eventPersistence.addEvent(e);

        }

        // return this for testing
        return getEventList();
    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    public List<Event> removeEvent(Event e) throws InvalidEventException{
        try {
            if(EventValidator.valid(e)){
                eventPersistence.removeEvent(e);
            }

        }catch(DbErrorException err){
            // need do more than just this
            Log.e("DbErrorException", err.getMessage());
            err.printStackTrace();
        }
        // return this for testing
        return getEventList();
    }
    @RequiresApi(api = Build.VERSION_CODES.N)
    public List<Event> removeEvent(int position) throws DbErrorException{
        eventPersistence.removeEvent(position);
        // return this for testing
        return getEventList();
    }


    @RequiresApi(api = Build.VERSION_CODES.N)
    public List<Event> updateEvent(Event old,Event fresh) throws InvalidEventException{
        try {
            if(EventValidator.valid(old)){
                eventPersistence.updateEvent(old,fresh);
            }

        }catch(DbErrorException err){
            // need do more than just this
            Log.e("DbErrorException", err.getMessage());
            err.printStackTrace();
        }
        // return this for testing
        return getEventList();
    }

}

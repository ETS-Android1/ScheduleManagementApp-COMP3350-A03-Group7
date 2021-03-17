package comp3350.team7.scheduleapp.logic;

import android.os.Build;
import android.util.Log;

import androidx.annotation.RequiresApi;

import java.util.Calendar;
import java.util.Comparator;
import java.util.List;

import comp3350.team7.scheduleapp.application.DbServiceProvider;
import comp3350.team7.scheduleapp.logic.comparators.EventEndAscendingComparator;
import comp3350.team7.scheduleapp.logic.comparators.EventEndDescendingComparator;
import comp3350.team7.scheduleapp.logic.comparators.EventStartAscendingComparator;
import comp3350.team7.scheduleapp.logic.comparators.EventStartDescendingComparator;
import comp3350.team7.scheduleapp.logic.exceptions.DbErrorException;
import comp3350.team7.scheduleapp.logic.exceptions.InvalidEventException;
import comp3350.team7.scheduleapp.persistence.stubs.EventPersistenceStub;
import comp3350.team7.scheduleapp.objects.Event;

/*
 * Created By Thai Tran on 22 February,2021
 *
 */
public class EventController {
    EventPersistenceStub eventStub;
    Comparator<Event> wayOfsort;


    public EventController() {
        DbServiceProvider.deployDatabse("development");
        this.eventStub = DbServiceProvider.get;
        // default way of sorting
        wayOfsort = new EventStartAscendingComparator();
    }

    public void setWayOfsort(SORTNAME sortname){
        switch(sortname)
        {
            case TIME_START_DESCENDING:
                this.wayOfsort = new EventStartDescendingComparator();
                break;
            case TIME_START_ASCENDING:
                this.wayOfsort = new EventStartAscendingComparator();
                break;
            case TIME_END_DESCENDING:
                this.wayOfsort = new EventEndDescendingComparator();
                break;
            case TIME_END_ASCENDING:
                this.wayOfsort = new EventEndAscendingComparator();
                break;

        }
    }
    public Event CreateEvent(String eventName, String description, Calendar calStart){
        Event newEvent = new Event(eventName,description,calStart);
        eventStub.addEvent(newEvent);
        return newEvent;
    }

    public Event CreateEvent(String eventName, String description, Calendar calStart, Calendar calEnd){
        Event newEvent = new Event(eventName,description,calStart,calEnd);
        eventStub.addEvent(newEvent);
        return newEvent;
    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    public List<Event> getEventList(){
        List<Event> eventList = eventStub.getEventList();
        eventList.sort(wayOfsort);
        return eventList;
    }

    // Someone use our api to create an invalid event, let them catch it
    @RequiresApi(api = Build.VERSION_CODES.N)
    public List<Event> addEvent(Event e) throws InvalidEventException {
        if (EventValidator.valid(e)){
            return eventStub.addEvent(e);

        }

        // return this for testing
        return getEventList();
    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    public List<Event> removeEvent(Event e) throws InvalidEventException{
        try {
            if(EventValidator.valid(e)){
                eventStub.removeEvent(e);
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
        eventStub.removeEvent(position);
        // return this for testing
        return getEventList();
    }


    @RequiresApi(api = Build.VERSION_CODES.N)
    public List<Event> updateEvent(Event old,Event fresh) throws InvalidEventException{
        try {
            if(EventValidator.valid(old)){
                eventStub.updateEvent(old,fresh);
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

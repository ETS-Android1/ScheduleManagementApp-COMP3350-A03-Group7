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
import comp3350.team7.scheduleapp.logic.exceptions.EventControllerException;
import comp3350.team7.scheduleapp.logic.exceptions.InvalidEventException;
import comp3350.team7.scheduleapp.objects.User;
import comp3350.team7.scheduleapp.persistence.EventPersistenceInterface;
import comp3350.team7.scheduleapp.objects.Event;

/*
 * Created By Thai Tran on 22 February,2021
 *
 */
public class EventController {
    private static final String TAG = "EventController";
    EventPersistenceInterface eventPersistence;
    AbstractComparator sortingStrategy;
    String userName;
    List<Event> events;

    public EventController(String userName) {
        eventPersistence = DbServiceProvider
                .getInstance()
                .getEventPersistence();

        this.userName = userName;
        // default way of sorting
        sortingStrategy = new EventStartAscendingComparator();
    }

    /* dependency inject */
    public EventController(EventPersistenceInterface eventPersistence, String username){
        this.eventPersistence = eventPersistence;
        sortingStrategy = new EventStartAscendingComparator();
        events= null;
    }


    // part of strategy pattern, inject AbstractComparator
    public void setSortStrategy(AbstractComparator newSortStrategy){
        this.sortingStrategy = newSortStrategy;
    }

    public Event CreateEvent(String userName,int id,  String eventName, String description, Calendar calStart) throws EventControllerException {
        Event newEvent = new Event(userName,id, eventName,description,calStart);
        try {
            eventPersistence.addEvent(newEvent);
        }catch (DbErrorException error) {
            Log.e(TAG,error.getMessage() + "Cause by" + error.getCause());
            throw new EventControllerException("Something went wrong,fail to create event");
        }
        return newEvent;
    }

    public Event CreateEvent(String userName, int id, String eventName, String description, Calendar calStart, Calendar calEnd) throws EventControllerException {
        Event newEvent = new Event(userName, id, eventName, description, calStart, calEnd);
        try {
            eventPersistence.addEvent(newEvent);
        } catch (DbErrorException error) {
            Log.e(TAG, error.getMessage() + "Cause by" + error.getCause());
            throw new EventControllerException("Something went wrong,fail to create event");
        }
        return newEvent;
    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    public List<Event> getEventList() throws EventControllerException {
        List<Event> eventList =null;
        try{
            eventList = eventPersistence.getEventList(this.userName);
            eventList.sort(sortingStrategy);
        }catch (DbErrorException e){
            Log.e(TAG,e.getMessage() + "\n Cause by "+ e.getCause());
            throw new EventControllerException("Something went wrong,fail to get event list");
        }

        return eventList;
    }

    // Someone use our api to create an invalid event, let them catch it
    public void addEvent(Event e) throws EventControllerException {
        try {
            EventValidator.valid(e);
            eventPersistence.addEvent(e);
        }catch (InvalidEventException error) {
            Log.e(TAG, error.getMessage() + " ,Cause by : " + error.getCause());
            throw new EventControllerException("Some thing went wrong: "+error.getMessage());
        }catch (DbErrorException dbError){
            Log.e(TAG, dbError.getMessage() + " ,Cause by : " + dbError.getCause());
            throw new EventControllerException("Some thing went wrong: "+dbError.getMessage());
        }

    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    public void removeEvent(Event e) throws EventControllerException {
        try {
            EventValidator.valid(e);
            eventPersistence.removeEvent(e);
        }catch(InvalidEventException err){
            Log.e(TAG, err.getMessage() + " ,Cause by : " + err.getCause());
            throw new EventControllerException("Some thing went wrong: "+ err.getMessage());
        }catch(DbErrorException dbError){
            Log.e(TAG, dbError.getMessage() + " ,Cause by : " + dbError.getCause());
            throw new EventControllerException("Some thing went wrong: "+dbError.getMessage());
        }

    }
    public void removeEvent(String username, int id) throws EventControllerException {
        try{
           eventPersistence.removeEvent(username, id);
        }catch(DbErrorException dbError){
            Log.e(TAG, dbError.getMessage() + " ,Cause by : " + dbError.getCause());
            throw new EventControllerException("Some thing went wrong: "+ dbError.getMessage());
        }
    }


    @RequiresApi(api = Build.VERSION_CODES.N)
    public List<Event> updateEvent(Event old,Event fresh) throws EventControllerException {
        try {
            EventValidator.valid(old);
            eventPersistence.updateEvent(old, fresh);
        }catch(InvalidEventException err){
            Log.e(TAG, err.getMessage() + " ,Cause by : " + err.getCause());
            throw new EventControllerException("Some thing went wrong: "+ err.getMessage());
        }catch(DbErrorException dbError){
            Log.e(TAG, dbError.getMessage() + " ,Cause by : " + dbError.getCause());
            throw new EventControllerException("Some thing went wrong: "+dbError.getMessage());
        }
        return getEventList();
    }

}

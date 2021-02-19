package comp3350.team7.scheduleapp.presentation;

import android.annotation.SuppressLint;
import android.os.Build;

import androidx.annotation.RequiresApi;

import java.time.LocalDateTime;
import java.time.DateTimeException;
import java.time.Duration;

@RequiresApi(api = Build.VERSION_CODES.O)

public class Event implements EventInterface {

    private static int id_count = 0;

    private int event_id;
    private String event_title;
    private String event_description;
    private String event_location;
    private LocalDateTime event_start;
    private LocalDateTime event_end;
    private Duration duration;
    private Event next_ptr;

    public Event() {
        event_id = -1;
        event_title = "unknown";
        event_description = "unknown";
        event_location = "unknown";
        event_start = null;
        event_end = null;
        duration = null;
        next_ptr = null;
    }

    public Event createEvent(String title, String desc, String location, Event next,
                             int s_year, int s_month, int s_day, int s_hour, int s_minute,
                             int e_year, int e_month, int e_day, int e_hour, int e_minute) {
        Event new_event = new Event();
        setID();
        setTitle(title);
        setDescription(desc);
        setLocation(location);
        setStart(s_year, s_month, s_day, s_hour, s_minute);
        setEnd(e_year, e_month, e_day, e_hour, e_minute);
        setDuration();
        setNext(next);
        return new_event;
    }

    public int getID() {
        return event_id;
    }
    public String getTitle() {
        return event_title;
    }
    public String getDescription() {
        return event_description;
    }
    public String getLocation() {
        return event_location;
    }
    public LocalDateTime getStart() {
        return event_start;
    }
    public LocalDateTime getEnd() {
        return event_end;
    }
    public Duration getDuration() { return duration; }
    public Event getNext() { return next_ptr; }

    private void setID() {
        event_id = id_count;
        id_count++;
    }
    public void setTitle(String title) throws ArithmeticException {
        //sets the title of the event to "title"
        //min length 1, max length 30
        //returns true if successful
        if(title.length() <= 0) {
            throw new ArithmeticException("ERROR: Title must have minimum length of 1 character.");
        }
        else if(title.length() > 30) {
            throw new ArithmeticException("ERROR: Title must have maximum length of 30 characters.");
        }
        else {
            event_title = title;
        }
    }
    public void setDescription(String desc) throws ArithmeticException {
        //sets the event description to "desc"
        //max length of 60
        //returns true if successful
        if(desc.length() > 60) {
            throw new ArithmeticException("ERROR: Description must have maximum length of 60 characters.");
        }
        else {
            event_description = desc;
        }
    }
    public void setLocation(String location) throws ArithmeticException {
        //sets the event location to "location"
        //max length of 30
        //returns true if successful
        if(location.length() > 30) {
            throw new ArithmeticException("ERROR: Location must have maximum length of 30 characters.");
        }
        else {
            event_location = location;
        }
    }
    public void setStart(int year, int month, int day, int hour, int minute) throws DateTimeException {
        try {
            event_start = LocalDateTime.of(year, month, day, hour, minute);
        } catch(DateTimeException e) {
            throw new IllegalArgumentException("Incorrect inputs for date and/or time.") ;
        }
    }
    public void setEnd(int year, int month, int day, int hour, int minute) throws DateTimeException {
        try {
            event_start = LocalDateTime.of(year, month, day, hour, minute);
        } catch(DateTimeException e) {
            throw new IllegalArgumentException("Incorrect inputs for date and/or time.") ;
        }
    }
    private void setDuration() {
        duration = Duration.between(event_start, event_end);
    }
    public void setNext(Event next) {
        next_ptr = next;
    }
}

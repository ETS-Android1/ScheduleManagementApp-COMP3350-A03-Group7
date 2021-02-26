package comp3350.team7.scheduleapp.objects;

import java.util.Calendar;

import comp3350.team7.scheduleapp.logic.exceptions.InvalidEventException;
import comp3350.team7.scheduleapp.objects.Event;

public interface EventInterface {

    final int MAX_DESCRIPTION_LENGTH = 240;
    final int MAX_TITLE_LENGTH = 30;

    final int PRIORITY_HIGH = 0;
    final int PRIORITY_MEDIUM = 1;
    final int PRIORITY_LOW = 2;

    public int getID();
    public String getTitle();
    public String getDescription();
//    public String getLocation();
//    public LocalDateTime getStart();
//    public LocalDateTime getEnd();
//    public Event getNext();

    public void setTitle(String title) throws InvalidEventException;
    public void setDescription(String desc) throws InvalidEventException;
    public void setStart(Calendar calendar);
    public void setEnd(Calendar calendar);
//    public void setLocation(String location);
//    public void setNext(Event next);
//    public void setStart(int year, int month, int day, int hour, int minute);
//    public void setEnd(int year, int month, int day, int hour, int minute);
//    public Duration getDuration();
//    public Event createEvent(String title, String desc, String location, Event next,
//                             int s_year, int s_month, int s_day, int s_hour, int s_minute,
//                             int e_year, int e_month, int e_day, int e_hour, int e_minute);
}

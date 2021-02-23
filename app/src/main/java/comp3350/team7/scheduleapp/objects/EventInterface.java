package comp3350.team7.scheduleapp.objects;

import java.util.Calendar;

import comp3350.team7.scheduleapp.objects.Event;

public interface EventInterface {

    public int getID();
    public String getTitle();
    public String getDescription();
    public String getLocation();
//    public LocalDateTime getStart();
//    public LocalDateTime getEnd();
    public Event getNext();

    public void setTitle(String title);
    public void setDescription(String desc);
    public void setStart(Calendar calendar);
    public void setLocation(String location);
    public void setNext(Event next);
//    public void setStart(int year, int month, int day, int hour, int minute);
//    public void setEnd(int year, int month, int day, int hour, int minute);
//    public Duration getDuration();
//    public Event createEvent(String title, String desc, String location, Event next,
//                             int s_year, int s_month, int s_day, int s_hour, int s_minute,
//                             int e_year, int e_month, int e_day, int e_hour, int e_minute);
}

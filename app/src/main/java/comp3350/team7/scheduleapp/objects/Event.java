package comp3350.team7.scheduleapp.objects;

import java.util.Calendar;


//@RequiresApi(api = Build.VERSION_CODES.O)

public class Event extends AbstractEvent {

    private int Event_id;
    private String Event_title;
    private String Event_description;
    private String username;
    private Calendar eventStart;
    private Calendar eventEnd;
    private Calendar alarm;

    public Event(String username, int eid, String title, String description, Calendar eventStart, Calendar eventEnd) {
        setID(eid);
        setTitle(title);
        setDescription(description);
        setStart(eventStart);
        setEnd(eventEnd);
        setUserName(username);
        setAlarm(null);

    }

    public Event(String username, int eid, String title, String description, Calendar eventStart, Calendar eventEnd, Calendar alarm) {
        setID(eid);
        setTitle(title);
        setDescription(description);
        setStart(eventStart);
        setEnd(eventEnd);
        setUserName(username);
        setAlarm(alarm);

    }

    public Event(String username, int eid, String title, String description, Calendar eventStart) {
        setID(eid);
        setTitle(title);
        setDescription(description);
        setStart(eventStart);
        setEnd(null);
        setUserName(username);
        setAlarm(null);

    }

    public Event(String username, String title, String description, Calendar eventStart, Calendar eventEnd) {
        setTitle(title);
        setDescription(description);
        setStart(eventStart);
        setEnd(eventEnd);
        setUserName(username);
        setAlarm(null);
    }

    public Event(String username, String title, String description, Calendar eventStart, Calendar eventEnd, Calendar alarm) {
        setTitle(title);
        setDescription(description);
        setStart(eventStart);
        setEnd(eventEnd);
        setUserName(username);
        setAlarm(alarm);
    }


    public Event(String username, String title, String description, Calendar eventStart) {
        setTitle(title);
        setDescription(description);
        setStart(eventStart);
        setEnd(null);
        setUserName(username);
        setAlarm(null);

    }

    public String getUserName() {
        return this.username;
    }

    public void setUserName(String username) {
        this.username = username;
    }

    public int getID() {
        return this.Event_id;
    }

    public void setID(int eid) {
        this.Event_id = eid;
    }

    public String getTitle() {
        return this.Event_title;
    }

    public void setTitle(String title) {
        this.Event_title = title;
    }

    public String getDescription() {
        return this.Event_description;
    }

    public void setDescription(String desc) {
        this.Event_description = desc;
    }

    public Calendar getEventStart() {
        return this.eventStart;
    }

    public Calendar getEventEnd() {
        return this.eventEnd;
    }

    public Calendar getAlarm() {
        return this.alarm;
    }

    public void setAlarm(Calendar alarm) {
        this.alarm = alarm;
    }

    public void setStart(Calendar calendar) {
        eventStart = calendar;
    }

    public void setEnd(Calendar calendar) {
        this.eventEnd = calendar;
    }

    @Override
    public String toString() {
        return "Event{" +
                "Event_id=" + Event_id +
                ", Event_title='" + Event_title +
                ", Description=" + Event_description +
                ", eventStart=" + eventStart.toString() +
                '}';
    }
}
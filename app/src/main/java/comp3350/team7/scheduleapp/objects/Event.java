package comp3350.team7.scheduleapp.objects;

import android.os.Parcel;
import android.os.Parcelable;

import java.text.SimpleDateFormat;
import java.util.Calendar;


//@RequiresApi(api = Build.VERSION_CODES.O)

public class Event extends AbstractEvent implements Parcelable {

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
    public Event(String username, int eid, String title, String description, Calendar eventStart, Calendar eventEnd,Calendar alarm) {
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
    public Event(String username,String title, String description, Calendar eventStart, Calendar eventEnd) {
        setTitle(title);
        setDescription(description);
        setStart(eventStart);
        setEnd(eventEnd);
        setUserName(username);
        setAlarm(null);
    }
    public Event(String username,String title, String description, Calendar eventStart, Calendar eventEnd,Calendar alarm) {
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


    public String getUserName(){
        return this.username;
    }
    public void setUserName(String username){
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

    public String getEventStartToString() {
        return timeDisplayHelper(this.eventStart);

    }

    public Calendar getEventEnd() {
        return this.eventEnd;
    }

    public Calendar getAlarm(){
        return this.alarm;
    }

    public String getEventEndToString() {
        return timeDisplayHelper(this.eventEnd);
    }

    private String timeDisplayHelper(Calendar calendar) {
        SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");
        String timeFormated = formatter.format(calendar.getTime());
        return timeFormated;

    }

    public void setStart(Calendar calendar) {
        eventStart = calendar;
    }

    public void setEnd(Calendar calendar) {
        eventEnd = calendar;
    }
    public void setAlarm(Calendar alarm){
        this.alarm = alarm;
    }

    @Override
    public String toString() {
        return "Event{" +
                "Event_id=" + Event_id +
                ", Event_title='" + Event_title +
                ", Description=" + Event_description+
                ", eventStart=" + eventStart.toString() +
                '}';
    }






    protected Event(Parcel in) {
        Event_id = in.readInt();
        Event_title = in.readString();
        Event_description = in.readString();
        username = in.readString();
        eventStart = (Calendar) in.readValue(Calendar.class.getClassLoader());
        eventEnd = (Calendar) in.readValue(Calendar.class.getClassLoader());
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(Event_id);
        dest.writeString(Event_title);
        dest.writeString(Event_description);
        dest.writeString(username);
        dest.writeValue(eventStart);
        dest.writeValue(eventEnd);
    }

    @SuppressWarnings("unused")
    public static final Parcelable.Creator<Event> CREATOR = new Parcelable.Creator<Event>() {
        @Override
        public Event createFromParcel(Parcel in) {
            return new Event(in);
        }

        @Override
        public Event[] newArray(int size) {
            return new Event[size];
        }
    };
}
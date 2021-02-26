package comp3350.team7.scheduleapp.objects;

import android.os.Parcel;
import android.os.Parcelable;

import java.text.SimpleDateFormat;
import java.util.Calendar;


//@RequiresApi(api = Build.VERSION_CODES.O)

public class Event implements EventInterface, Parcelable {

    private static int id_count = 0;
    private int Event_id;
    private String Event_title;
    private String Event_location;
    private String Event_description;
    private Calendar eventStart;
    private Calendar eventEnd;

    public Event(String title, String description, Calendar eventStart, Calendar eventEnd) {
        setTitle(title);
        setDescription(description);
        setID();
        setStart(eventStart);
        setEnd(eventEnd);

    }

    public Event(String title, String description, Calendar eventStart) {
        setTitle(title);
        setDescription(description);
        setID();
        setStart(eventStart);
        setEnd(null);

    }

    protected Event(Parcel in) {
        Event_id = in.readInt();
        Event_title = in.readString();
        Event_description = in.readString();
        eventStart = (Calendar) in.readValue(Calendar.class.getClassLoader());
        eventEnd = (Calendar) in.readValue(Calendar.class.getClassLoader());
    }

    public int getID() {
        return Event_id;
    }

    public String getTitle() {
        return Event_title;
    }

    public void setTitle(String title) {
        Event_title = title;
    }


    public String getDescription() {
        return Event_description;
    }

    public void setDescription(String desc) {
        Event_description = desc;
    }


    public Calendar getEventStart() {
        return eventStart;
    }

    public String getEventStartToString() {
        return timeDisplayHelper(eventStart);

    }

    public Calendar getEventEnd() {
        return eventEnd;
    }

    public String getEventEndToString() {
        return timeDisplayHelper(eventEnd);
    }

    private void setID() {
        Event_id = id_count;
        id_count++;
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

    // parceling process
    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(Event_id);
        dest.writeString(Event_title);
        dest.writeString(Event_description);
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
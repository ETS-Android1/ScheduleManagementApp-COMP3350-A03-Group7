package comp3350.team7.scheduleapp.objects;

import android.os.Parcel;
import android.os.Parcelable;

import java.text.SimpleDateFormat;
import java.time.DateTimeException;
import java.util.ArrayList;
import java.util.Calendar;


//@RequiresApi(api = Build.VERSION_CODES.O)

/*
 * Created By Thai Tran on 23 February,2021
 *
 */

public class Event implements EventInterface, Parcelable {

    private static int id_count = 0;

    private int Event_id;
    private String Event_title;
    private String Event_description;
    private String Event_location;
    //    private LocalDateTime Event_start;
//    private LocalDateTime Event_end;
//    private Duration duration;
    private Event next_ptr;


    public Event() {
        Event_id = -1;
        Event_title = "unknown";
        Event_description = "unknown";
        Event_location = "unknown";
//        Event_start = null;
//        Event_end = null;
//        duration = null;
        next_ptr = null;
    }

    public Event createEvent(String title, String desc, String location, Event next,
                             int s_year, int s_month, int s_day, int s_hour, int s_minute,
                             int e_year, int e_month, int e_day, int e_hour, int e_minute) {
        Event new_Event = new Event();
        setID();
        setTitle(title);
        setDescription(desc);
        setLocation(location);
//        setStart(s_year, s_month, s_day, s_hour, s_minute);
//        setEnd(e_year, e_month, e_day, e_hour, e_minute);
//        setDuration();
        setNext(next);
        return new_Event;
    }

    public int getID() {
        return Event_id;
    }

    public String getTitle() {
        return Event_title;
    }

    public String getDescription() {
        return Event_description;
    }

    public String getLocation() {
        return Event_location;
    }

    public String geteventStart(){
        return timeDisplayHelper(eventStart);

    }
    public String geteventEnd(){
       return timeDisplayHelper(eventEnd);
    }

//    public LocalDateTime getStart() {
//        return Event_start;
//    }
//
//    public LocalDateTime getEnd() {
//        return Event_end;
//    }
//
//    public Duration getDuration() {
//        return duration;
//    }

    public Event getNext() {
        return next_ptr;
    }

    private void setID() {
        Event_id = id_count;
        id_count++;
    }

    public void setTitle(String title) throws ArithmeticException {
        //sets the title of the Event to "title"
        //min length 1, max length 30
        //returns true if successful
        if (title.length() <= 0) {
            throw new ArithmeticException("ERROR: Title must have minimum length of 1 character.");
        } else if (title.length() > 30) {
            throw new ArithmeticException("ERROR: Title must have maximum length of 30 characters.");
        } else {
            Event_title = title;
        }
    }

    public void setDescription(String desc) throws ArithmeticException {
        //sets the Event description to "desc"
        //max length of 60
        //returns true if successful
        if (desc.length() > 60) {
            throw new ArithmeticException("ERROR: Description must have maximum length of 60 characters.");
        } else {
            Event_description = desc;
        }
    }

    public void setLocation(String location) throws ArithmeticException {
        //sets the Event location to "location"
        //max length of 30
        //returns true if successful
        if (location.length() > 30) {
            throw new ArithmeticException("ERROR: Location must have maximum length of 30 characters.");
        } else {
            Event_location = location;
        }
    }

//    public void setStart(int year, int month, int day, int hour, int minute) throws DateTimeException {
//        try {
//            Event_start = LocalDateTime.of(year, month, day, hour, minute);
//        } catch (DateTimeException e) {
//            throw new IllegalArgumentException("Incorrect inputs for date and/or time.");
//        }
//    }


//    public void setEnd(int year, int month, int day, int hour, int minute) throws DateTimeException {
//        try {
//            Event_start = LocalDateTime.of(year, month, day, hour, minute);
//        } catch (DateTimeException e) {
//            throw new IllegalArgumentException("Incorrect inputs for date and/or time.");
//        }
//    }
//
//    private void setDuration() {
//        duration = Duration.between(Event_start, Event_end);
//    }

    public void setNext(Event next) {
        next_ptr = next;
    }


    /*** Start Modify or Extend by Thai Tran ***/
    private Calendar eventStart;
    private Calendar eventEnd;
    private ArrayList<Task> taskList;
    private boolean isRepeated;  //  false as default
    //    private Drawable backgroundImg;
    public static final int PRIORITY_HIGH = 0;
    public static final int PRIORITY_MEDIUM = 1;
    public static final int PRIORITY_LOW = 2;

    private int Priority;

    public Event(String title, String description, Calendar eventStart, Calendar eventEnd){
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
    private String timeDisplayHelper(Calendar calendar)
    {
        SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");
        String timeFormated  = formatter.format(calendar.getTime());
        return timeFormated;

    }
    public void setStart(Calendar calendar) throws DateTimeException {
        eventStart = calendar;
    }

    public void setEnd(Calendar calendar) throws DateTimeException {
        eventEnd = calendar;
    }

    /* parceling process */
    protected Event(Parcel in) {
        Event_id = in.readInt();
        Event_title = in.readString();
        Event_description = in.readString();
        Event_location = in.readString();
        next_ptr = (Event) in.readValue(Event.class.getClassLoader());
        eventStart = (Calendar) in.readValue(Calendar.class.getClassLoader());
        eventEnd = (Calendar) in.readValue(Calendar.class.getClassLoader());
        if (in.readByte() == 0x01) {
            taskList = new ArrayList<Task>();
            in.readList(taskList, Task.class.getClassLoader());
        } else {
            taskList = null;
        }
        isRepeated = in.readByte() != 0x00;
        Priority = in.readInt();
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
        dest.writeString(Event_location);
        dest.writeValue(next_ptr);
        dest.writeValue(eventStart);
        dest.writeValue(eventEnd);
        if (taskList == null) {
            dest.writeByte((byte) (0x00));
        } else {
            dest.writeByte((byte) (0x01));
            dest.writeList(taskList);
        }
        dest.writeByte((byte) (isRepeated ? 0x01 : 0x00));
        dest.writeInt(Priority);
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
    /*** End Modify or Extend on 2021-02-19 ***/
}
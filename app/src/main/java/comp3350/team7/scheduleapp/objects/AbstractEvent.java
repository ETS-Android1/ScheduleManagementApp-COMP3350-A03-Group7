package comp3350.team7.scheduleapp.objects;

import java.util.Calendar;

public abstract class AbstractEvent {

    public abstract String getUserName();

    public abstract int getID();

    public abstract void setID(int eid);

    public abstract String getTitle();

    public abstract void setTitle(String title);

    public abstract String getDescription();

    public abstract void setDescription(String desc);


    public abstract void setStart(Calendar calendar);

    public abstract void setEnd(Calendar calendar);
}

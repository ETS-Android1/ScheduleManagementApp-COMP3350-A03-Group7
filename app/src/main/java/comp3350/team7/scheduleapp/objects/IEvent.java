package comp3350.team7.scheduleapp.objects;

import java.util.Calendar;

import comp3350.team7.scheduleapp.logic.exceptions.InvalidEventException;
import comp3350.team7.scheduleapp.objects.Event;

public interface IEvent {

    final int MAX_DESCRIPTION_LENGTH = 240;
    final int MAX_TITLE_LENGTH = 30;

    final int PRIORITY_HIGH = 0;
    final int PRIORITY_MEDIUM = 1;
    final int PRIORITY_LOW = 2;

    public int getID();
    public String getTitle();
    public String getDescription();

    public void setID(int eid);
    public void setTitle(String title) throws InvalidEventException;
    public void setDescription(String desc) throws InvalidEventException;
    public void setStart(Calendar calendar);
    public void setEnd(Calendar calendar);
}

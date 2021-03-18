package comp3350.team7.scheduleapp.objects;

import java.util.Calendar;

import comp3350.team7.scheduleapp.logic.exceptions.InvalidEventException;
import comp3350.team7.scheduleapp.objects.Event;

public abstract class AbstractEvent {

    final int MAX_DESCRIPTION_LENGTH = 240;
    final int MAX_TITLE_LENGTH = 30;

    public String getUserName(){return null};
    public int getID() {
        return 0;
    }

    public String getTitle() {
        return null;
    }

    public String getDescription() {


    }

    public void setID(int eid) {
    }

    public void setTitle(String title) throws InvalidEventException {

    }

    public void setDescription(String desc) throws InvalidEventException {

    }

    public void setStart(Calendar calendar) {

    }

    public void setEnd(Calendar calendar) {

    }
}

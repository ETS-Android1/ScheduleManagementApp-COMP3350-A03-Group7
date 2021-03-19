package comp3350.team7.scheduleapp.logic;

import android.util.Log;

import java.util.Calendar;
import java.util.List;

import comp3350.team7.scheduleapp.application.DbServiceProvider;
import comp3350.team7.scheduleapp.logic.exceptions.DbErrorException;
import comp3350.team7.scheduleapp.logic.exceptions.ScheduleControllerException;
import comp3350.team7.scheduleapp.objects.Event;
import comp3350.team7.scheduleapp.persistence.SchedulePersistenceInterface;

public class ScheduleController {
    private static final String TAG = "ScheduleController";
    private String userName;
    private List<Event> scheduleEvents;
    private SchedulePersistenceInterface scheduleDB;

    public ScheduleController(String username,Calendar date) {
        this.userName = username;
        this.scheduleDB = DbServiceProvider
                .getInstance()
                .getSchedulePersistence();
    }

    public ScheduleController(SchedulePersistenceInterface scheduleDB, String username) {
        this.scheduleDB = scheduleDB;
        this.userName = username;
        scheduleEvents = null;
    }

    public List<Event> getScheduleForDay(Calendar specificDate) throws ScheduleControllerException {
        try{
            scheduleEvents = scheduleDB.getScheduleForUserOnDate(this.userName, specificDate);
        }catch (DbErrorException e){
            Log.e(TAG,e.getMessage());
            throw new ScheduleControllerException(e.getMessage(),e);
        }
        return scheduleEvents;
    }
}
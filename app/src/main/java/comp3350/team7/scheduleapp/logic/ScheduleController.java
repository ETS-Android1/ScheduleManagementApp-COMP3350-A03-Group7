package comp3350.team7.scheduleapp.logic;

import java.util.Calendar;
import java.util.List;

import comp3350.team7.scheduleapp.application.DbServiceProvider;
import comp3350.team7.scheduleapp.objects.Event;
import comp3350.team7.scheduleapp.persistence.SchedulePersistenceInterface;

public class ScheduleController {

    private List<Event> scheduleEvents;
    private SchedulePersistenceInterface scheduleDB;

    public ScheduleController() {
        scheduleEvents = null;
        scheduleDB = DbServiceProvider.getInstance().getSchedulePersistence();
    }

    public List<Event> getScheduleForDay(String username, Calendar specificDate) {
        scheduleEvents = scheduleDB.getScheduleForUserOnDate(username, specificDate);
        if (scheduleEvents.size() == 0) {
            System.out.println("You don't have any events on this day");
        }
        return scheduleEvents;
    }
}
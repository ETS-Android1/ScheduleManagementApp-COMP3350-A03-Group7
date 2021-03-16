package comp3350.team7.scheduleapp.logic;

import java.util.Calendar;
import java.util.List;
import comp3350.team7.scheduleapp.objects.Event;

public class ScheduleController {

    private List<Event> scheduleEvents;
    private static ScheduledEventsInterface scheduleDB;

    public ScheduleController(ScheduledEventsInterface scheduleHSQLDB) {
        scheduleEvents = null;
        scheduleDB = scheduleHSQLDB;
    }

    public List<Event> getScheduleForDay(String username, Calendar specificDate) {
        scheduleEvents = scheduleDB.getScheduleForUserOnDate(username, specificDate);
        if (scheduleEvents.size() == 0) {
            System.out.println("You don't have any events on this day");
        }
        return scheduleEvents;
    }
}
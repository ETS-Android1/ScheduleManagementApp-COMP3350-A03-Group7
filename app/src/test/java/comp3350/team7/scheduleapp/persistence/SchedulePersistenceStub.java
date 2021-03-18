package comp3350.team7.scheduleapp.persistence.stubs;

/*
 * Created By Thai Tran on 16 March,2021
 *
 */

import java.util.Calendar;
import java.util.List;

import comp3350.team7.scheduleapp.objects.Event;
import comp3350.team7.scheduleapp.persistence.SchedulePersistenceInterface;

public class SchedulePersistenceStub implements SchedulePersistenceInterface {
    @Override
    public List<Event> getScheduleForUserOnDate(String username, Calendar date) {
        return null;
    }
    /* TODO: 2021-03-16
     * SchedulePersistenceStub, a fake datbase storing schedule
     */

}

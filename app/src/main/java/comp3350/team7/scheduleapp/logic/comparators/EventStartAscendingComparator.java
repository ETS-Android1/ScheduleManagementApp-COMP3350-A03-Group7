package comp3350.team7.scheduleapp.logic.comparators;

/*
 * Created By Thai Tran on 22 February,2021
 *
 */

import java.util.Comparator;

import comp3350.team7.scheduleapp.objects.Event;

public class EventStartAscendingComparator implements Comparator<Event> {

    @Override
    public int compare(Event e1, Event e2) {
        return e1.getEventStart().compareTo(e2.getEventStart());
    }
}

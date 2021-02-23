package comp3350.team7.scheduleapp.logic.comparators;

/*
 * Created By Thai Tran on 22 February,2021
 *
 */

import java.util.Comparator;

import comp3350.team7.scheduleapp.objects.Event;

public class EventEndDescendingComparator implements Comparator<Event> {


    public int compare(Event e1, Event e2) {
        return e2.geteventEnd().compareTo(e1.geteventEnd());
    }
}
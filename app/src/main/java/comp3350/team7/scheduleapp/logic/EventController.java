package comp3350.team7.scheduleapp.logic;

import java.util.Comparator;

import comp3350.team7.scheduleapp.logic.comparators.EventEndAscendingComparator;
import comp3350.team7.scheduleapp.logic.comparators.EventEndDescendingComparator;
import comp3350.team7.scheduleapp.logic.comparators.EventStartAscendingComparator;
import comp3350.team7.scheduleapp.logic.comparators.EventStartDescendingComparator;
import comp3350.team7.scheduleapp.persistence.EventDbStub;
import comp3350.team7.scheduleapp.presentation.Event;

/*
 * Created By Thai Tran on 22 February,2021
 *
 */
public class EventController {
    EventDbStub eventStub;
    Comparator<Event> wayOfsort;


    public EventController() {
        this.eventStub = new EventDbStub(20);
        // default way of sorting
        wayOfsort = new EventStartAscendingComparator();
    }

    public void setWayOfsort(SORTNAME sortname){
        switch(sortname)
        {
            case TIME_START_DESCENDING:
                this.wayOfsort = new EventStartDescendingComparator();
                break;
            case TIME_START_ASCENDING:
                this.wayOfsort = new EventStartAscendingComparator();
                break;
            case TIME_END_DESCENDING:
                this.wayOfsort = new EventEndDescendingComparator();
                break;
            case TIME_END_ASCENDING:
                this.wayOfsort = new EventEndAscendingComparator();
                break;

        }
    }
}

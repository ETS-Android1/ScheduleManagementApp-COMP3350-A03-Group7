package comp3350.team7.scheduleapp.objects;

import org.junit.Test;
import org.junit.Before;
import org.junit.After;

import java.util.Calendar;

import static org.junit.Assert.*;
import comp3350.team7.scheduleapp.objects.Event;

/*
 * Created By Thai Tran on 12 March,2021
 *
 */

public class EventTest {

    private Event testEvent;

    @Before
    public void setup() {
        Calendar start = Calendar.getInstance();
        start.set(2021, 2, 5, 16, 30);
        Calendar end = Calendar.getInstance();
        start.set(2021, 2, 5, 16, 30);
        testEvent = new Event("username","Test Event", "This is a test event", start, end);
    }
    @After
    public void teardown() { testEvent = null; }

    @Test
    public void testAcceptableTitle() {
        testEvent.setTitle("New Title");
        assertEquals("Both title should 'New Title'", "New Title", testEvent.getTitle());
    }

    @Test
    public void testAcceptableDescription() {
        testEvent.setDescription("Event description");
        assertEquals("Event description should change to 'Event Description'",
                "Event description", testEvent.getDescription());
    }

}

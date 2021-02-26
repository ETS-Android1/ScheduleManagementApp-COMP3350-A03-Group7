package comp3350.team7.scheduleapp;

import org.junit.Test;
import org.junit.Before;
import org.junit.After;

import java.util.Calendar;

import static org.junit.Assert.*;
import comp3350.team7.scheduleapp.objects.Event;

public class EventTest {

    private Event testEvent;

    @Before
    public void setup() {
        Calendar start = Calendar.getInstance();
        start.set(2021, 2, 5, 16, 30);
        Calendar end = Calendar.getInstance();
        start.set(2021, 2, 5, 16, 30);
        testEvent = new Event("Test Event", "This is a test event", start, end);
    }
    @After
    public void teardown() { testEvent = null; }

    @Test
    public void testAcceptableTitle() {
        testEvent.setTitle("New Title");
        assertEquals("Both title should 'New Title'", "New Title", testEvent.getTitle());
    }
    @Test
    public void testTooLongTitle() {
        assertThrows("This should fail as title too long", ArithmeticException.class,
                () -> testEvent.setTitle("This title is way too long you need to change it to something shorter"));
    }
    @Test
    public void testTooShortTitle() {
        assertThrows("This should fail as title too short", ArithmeticException.class,
                () -> testEvent.setTitle(""));
    }
    @Test
    public void testAcceptableDescription() {
        testEvent.setDescription("Event description");
        assertEquals("Event description should change to 'Event Description'",
                "Event description", testEvent.getDescription());
    }
    @Test
    public void testTooLongDescription() {
        assertThrows("This should fail as title too long", ArithmeticException.class,
                () -> testEvent.setDescription("Description Description Description Description Description Description " +
                        "Description Description Description Description Description Description Description Description " +
                        "Description Description Description Description Description Description Description Description "));
    }
}

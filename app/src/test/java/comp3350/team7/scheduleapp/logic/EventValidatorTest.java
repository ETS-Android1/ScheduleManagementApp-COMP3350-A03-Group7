package comp3350.team7.scheduleapp.logic;

import org.junit.Test;
import org.junit.Before;
import org.junit.After;

import java.util.Calendar;

import static comp3350.team7.scheduleapp.logic.EventValidator.*;
import static org.junit.Assert.*;
import comp3350.team7.scheduleapp.logic.exceptions.InvalidEventException;
import comp3350.team7.scheduleapp.objects.Event;

public class EventValidatorTest {

    public Void testEvent;
    boolean worked;

    @Before
    public void setup() {
        Calendar start = Calendar.getInstance();
        start.set(2022, 1, 1, 0, 0);
        Calendar end = Calendar.getInstance();
        end.set(2023, 1, 1, 0, 0);
        testEvent = new Event("Test Event", "testEvent","This is a test event", start, end);
        worked = false;
    }

    @After
    public void teardown() { testEvent = null; }

    @Test
    public void testInvalidEvent() {
        testEvent = null;
        assertThrows("Null event is invalid", InvalidEventException.class, () -> validate(testEvent));
    }

    @Test
    public void testValidEventTitle() {
        worked = false;
        try {
            validateEventName(testEvent.getTitle());
            worked = true;
        }
        catch (InvalidEventException i) {
            System.out.println("Event title is not valid");
        }
        assertTrue("The event title is valid (whitespace + alphanumeric chars)", worked);
    }

    @Test
    public void testInvalidEventTitle() {
        testEvent.setTitle("+ =+ - -_a122sas +sas");
        assertThrows("The event title is invalid (whitespace + alphanumeric chars)", InvalidEventException.class, () -> validateEventName(testEvent.getTitle()));
    }

    @Test
    public void testValidEventStartAfterNow() throws InvalidEventException {
        worked = false;
        try {
            validateEventStartTime(testEvent.getEventStart(), Calendar.getInstance());
            worked = true;
        }
        catch (InvalidEventException i) {
            System.out.println("event tried to start before the current time");
        }
        assertTrue("Valid event starts after current time", worked);
    }

    @Test
    public void testInvalidEventStartBeforeNow() {
        Calendar badDate = Calendar.getInstance();
        badDate.set(2021, 1, 1, 0, 0);
        testEvent.setStart(badDate);
        assertThrows("Invalid event starts before current time", InvalidEventException.class, () -> validateEventStartTime(testEvent.getEventStart(), Calendar.getInstance()));
    }

    @Test
    public void testInvalidEventEndBeforeStart() {
        Calendar badDate = Calendar.getInstance();
        badDate.set(2023, 1, 1, 0, 0);
        testEvent.setStart(badDate);
        assertThrows("Invalid event ends before start time", InvalidEventException.class,() -> validateEventStartAndEndTime(testEvent.getEventEnd(), testEvent.getEventStart(), Calendar.getInstance()));
    }

    @Test
    public void testInvalidEventEndBeforeNow() {
        Calendar badDate = Calendar.getInstance();
        badDate.set(2021, 1, 1, 0, 0);
        testEvent.setEnd(badDate);
        assertThrows("Invalid event ends before now", InvalidEventException.class, () -> validateEventStartAndEndTime(testEvent.getEventEnd(), testEvent.getEventStart(), Calendar.getInstance()));
    }
}

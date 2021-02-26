package comp3350.team7.scheduleapp;

import org.junit.Test;
import org.junit.Before;
import org.junit.After;

import java.util.Calendar;

import static comp3350.team7.scheduleapp.logic.EventValidator.*;
import static org.junit.Assert.*;
import comp3350.team7.scheduleapp.logic.exceptions.InvalidEventException;
import comp3350.team7.scheduleapp.objects.Event;

public class EventValidatorTest {

    public Event testEvent;

    @Before
    public void setup() {
        Calendar start = Calendar.getInstance();
        start.set(2022, 1, 1, 0, 0);
        Calendar end = Calendar.getInstance();
        end.set(2023, 1, 1, 0, 0);
        testEvent = new Event("Test Event", "This is a test event", start, end);
    }
    @After
    public void teardown() { testEvent = null; }

    @Test
    public void testValidEvent() throws InvalidEventException {
        assertTrue("Event is valid", valid(testEvent));
    }
    @Test
    public void testInvalidEvent() {
        testEvent = null;
        assertThrows("Null event is invalid", InvalidEventException.class, () -> valid(testEvent));
    }
    @Test
    public void testValidEventTitle() {
        assertTrue("The event title is valid (whitespace + alphanumeric chars)",
                validateEventName(testEvent.getTitle()));
    }
    @Test
    public void testInvalidEventTitle() {
        testEvent.setTitle("+ =+ - -_a122sas +sas");
        assertFalse("The event title is invalid (whitespace + alphanumeric chars)",
                validateEventName(testEvent.getTitle()));
    }
    @Test
    public void testValidEventStartAfterNow() {
        assertTrue("Valid event starts after current time",
                validateEventStartTime(testEvent.getEventStart(), Calendar.getInstance()));
    }
    @Test
    public void testInvalidEventStartBeforeNow() {
        Calendar badDate = Calendar.getInstance();
        badDate.set(2021, 1, 1, 0, 0);
        testEvent.setStart(badDate);
        assertFalse("Invalid event starts before current time",
                validateEventStartTime(testEvent.getEventStart(), Calendar.getInstance()));
    }
    @Test
    public void testValidEndTimeEndAfterStart() {
        assertTrue("Valid event starts before current time",
                validateEventStartTime(testEvent.getEventStart(), Calendar.getInstance()));
    }
    @Test
    public void testInvalidEventEndBeforeStart() {
        Calendar badDate = Calendar.getInstance();
        badDate.set(2023, 1, 1, 0, 0);
        testEvent.setStart(badDate);
        assertFalse("Invalid event ends before start time",
                validateEventEndTime(testEvent.getEventEnd(), testEvent.getEventStart(),
                        Calendar.getInstance()));
    }
    @Test
    public void testInvalidEventEndBeforeNow() {
        Calendar badDate = Calendar.getInstance();
        badDate.set(2021, 1, 1, 0, 0);
        testEvent.setEnd(badDate);
        assertFalse("Invalid event ends before now",
                validateEventEndTime(testEvent.getEventEnd(), testEvent.getEventStart(),
                        Calendar.getInstance()));
    }
}

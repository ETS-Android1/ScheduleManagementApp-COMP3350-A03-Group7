package comp3350.team7.scheduleapp.logic;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.util.Calendar;

import comp3350.team7.scheduleapp.Helper.TestHelper;
import comp3350.team7.scheduleapp.logic.exceptions.InvalidEventException;
import comp3350.team7.scheduleapp.objects.Event;

import static comp3350.team7.scheduleapp.logic.EventValidator.validate;
import static comp3350.team7.scheduleapp.logic.EventValidator.validateAlarm;
import static comp3350.team7.scheduleapp.logic.EventValidator.validateEventDescription;
import static comp3350.team7.scheduleapp.logic.EventValidator.validateEventName;
import static comp3350.team7.scheduleapp.logic.EventValidator.validateEventStartAndEndTime;
import static comp3350.team7.scheduleapp.logic.EventValidator.validateEventStartTime;
import static org.junit.Assert.assertThrows;
import static org.junit.Assert.assertTrue;

public class EventValidatorTest {

    public Event testEvent;
    boolean worked;
    Calendar now;
    @Before
    public void setup() {
        now = Calendar.getInstance();
        Calendar start = TestHelper.getCustomizeCalendarInstance(Calendar.DAY_OF_MONTH, 2);
        Calendar end = TestHelper.getCustomizeCalendarInstance(Calendar.DAY_OF_MONTH, 5);
        testEvent = new Event("Test Event", "testEvent", "This is a test event", start, end);
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
    public void testInvalidEventTitleTooLong() {
        testEvent.setTitle(String.format(" %60s", "too long title"));
        assertThrows(InvalidEventException.class, () -> validateEventName(testEvent.getTitle()));
    }


    @Test
    public void testValidEventDiscriptionLength() {
        testEvent.setDescription(String.format(" %120s", "too long description"));
        assertThrows(InvalidEventException.class, () -> validateEventDescription(testEvent.getDescription()));
    }

    @Test
    public void testValidEventStartAfterNow() throws InvalidEventException {
        worked = false;
        try {
            validateEventStartTime(testEvent.getEventStart(), Calendar.getInstance());
            worked = true;
        } catch (InvalidEventException i) {
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
        assertThrows("Invalid event ends before start time", InvalidEventException.class, () -> validateEventStartAndEndTime(testEvent.getEventEnd(), testEvent.getEventStart(), Calendar.getInstance()));
    }

    @Test
    public void testInvalidEventEndBeforeNow() {
        Calendar badDate = Calendar.getInstance();
        badDate.set(2021, 1, 1, 0, 0);
        testEvent.setEnd(badDate);
        assertThrows("Invalid event ends before now", InvalidEventException.class, () -> validateEventStartAndEndTime(testEvent.getEventEnd(), testEvent.getEventStart(), Calendar.getInstance()));
    }

    @Test
    public void testValidEventAlarm() throws InvalidEventException {
        Calendar alarm = TestHelper.getCustomizeCalendarInstance(Calendar.DAY_OF_MONTH, 1);

        testEvent.setAlarm(alarm);
        EventValidator.validateAlarm(testEvent.getEventStart(), now, testEvent.getAlarm());
    }

    @Test
    public void testInValidEventAlarm() {
        Calendar alarm = TestHelper.getCustomizeCalendarInstance(Calendar.DAY_OF_MONTH, 3);
        testEvent.setAlarm(alarm);
        assertThrows("Invalid event alarm, alarm should be before event start and after now ",
                InvalidEventException.class,
                () -> validateAlarm(testEvent.getEventStart(), now, testEvent.getAlarm()));
    }
}

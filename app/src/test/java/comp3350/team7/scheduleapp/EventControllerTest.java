package comp3350.team7.scheduleapp;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.Calendar;

import comp3350.team7.scheduleapp.logic.EventController;
import comp3350.team7.scheduleapp.logic.exceptions.DbErrorException;
import comp3350.team7.scheduleapp.logic.exceptions.InvalidEventException;
import comp3350.team7.scheduleapp.objects.Event;

import static org.junit.Assert.*;

public class EventControllerTest {

    private EventController eventController;
    private Calendar startTestDate;
    private Calendar endTestDate;
    private Event testEvent;
    private Event finalTestEvent;

    @Before
    public void setup() {
        eventController = new EventController();
        startTestDate = Calendar.getInstance();
        endTestDate = Calendar.getInstance();
        endTestDate.add(Calendar.DATE, 5);
        testEvent = new Event("sample event", "the description", startTestDate);
        finalTestEvent = new Event("fresh sample event", "this is the fresh test description", startTestDate);
    }

    @After
    public void teardown() {
        eventController = null;
        startTestDate = null;
        endTestDate = null;
    }

    @Test
    public void testCreateEvent() {
        System.out.println("\nStarting testCreateEvent");
        assertNotNull(eventController.CreateEvent("first test", "this is the first test description", startTestDate));
        assertNotNull(eventController.CreateEvent("second test","this is the second test description", startTestDate, endTestDate));
        assertNotNull(eventController.getEventList());
        System.out.println("\nFinish testCreateEvent");
    }

    @Test
    public void testAddEvent() throws InvalidEventException {
        System.out.println("\nStarting testAddEvent");
        eventController.addEvent(testEvent);
        System.out.println("\nFinish testAddEvent");
    }

    @Test
    public void testRemoveEvent() throws InvalidEventException {
        System.out.println("\nStarting testRemoveEvent");
        eventController.addEvent(testEvent);
        assertNotNull(eventController.removeEvent(testEvent));
        System.out.println("\nFinish testRemoveEvent");
    }

    @Test
    public void testRemoveByIndex() throws DbErrorException {
        System.out.println("\nStarting testRemoveByIndex");
        eventController.removeEvent(1);
        assertNotNull(eventController.getEventList());
        System.out.println("\nFinish testRemoveByIndex");
    }

    @Test
    public void testRemoveInvalidEvent() {
        System.out.println("\nStarting testRemoveInvalidEvent");
        Event nullEvent = null;
        assertThrows(InvalidEventException.class,()-> {
            eventController.removeEvent(nullEvent);
        });
        System.out.println("\nFinish testRemoveInvalidEvent");
    }

    @Test
    public void testRemoveEventInvalidPosition() {
        System.out.println("\nStarting testRemoveEventInvalidPosition");
        assertThrows(IndexOutOfBoundsException.class,()-> {
            eventController.removeEvent(30);
        });
        System.out.println("\nFinish testRemoveEventInvalidPosition");
    }

    @Test
    public void testUpdateEvent() throws InvalidEventException {
        System.out.println("\nStarting testUpdateEvent");
        eventController.addEvent(testEvent);
        assertNotNull(eventController.updateEvent(testEvent,finalTestEvent));
        System.out.println("\nFinishing testUpdateEvent");
    }

}

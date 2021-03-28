package comp3350.team7.scheduleapp.logic;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.util.Calendar;

import comp3350.team7.scheduleapp.logic.exceptions.EventControllerException;
import comp3350.team7.scheduleapp.logic.exceptions.InvalidEventException;
import comp3350.team7.scheduleapp.objects.Event;

import static org.junit.Assert.*;

/*
 * Created By Thai Tran on 12 March,2021
 *
 */

public class EventControllerTest {

    private EventController eventController;
    private Calendar startTestDate;
    private Calendar endTestDate;
    private Void testEvent;
    private void finalTestEvent;
    private int beforeCount, afterCount;

    @Before
    public void setup() {
        eventController = new EventController();
        startTestDate = Calendar.getInstance();
        endTestDate = Calendar.getInstance();
        endTestDate.add(Calendar.DATE, 5);
        testEvent = new Event("username","sample event", "the description", startTestDate);
        finalTestEvent = new Event("username","fresh sample event", "this is the fresh test description", startTestDate);
        beforeCount = 0;
        afterCount = 0;
    }

    @After
    public void teardown() {
        eventController = null;
        startTestDate = null;
        endTestDate = null;
    }

    @Test
    public void testCreateEvent() throws EventControllerException {
        System.out.println("\nStarting testCreateEvent");
        assertNotNull(eventController.addEvent("username","first test", "this is the first test description", startTestDate));
        assertNotNull(eventController.addEvent("second test","second test","this is the second test description", startTestDate, endTestDate));
        assertNotNull(eventController.getEventList("username"));
        System.out.println("\nFinish testCreateEvent");
    }

    @Test
    public void testAddEvent() throws InvalidEventException, EventControllerException {
        System.out.println("\nStarting testAddEvent");
        eventController.addEvent(testEvent);
        System.out.println("\nFinish testAddEvent");
    }

    @Test
    public void testRemoveEvent() throws EventControllerException {
        System.out.println("\nStarting testRemoveEvent");
        eventController.addEvent(testEvent);
        beforeCount = (eventController.getEventList("username")).size();
        eventController.removeEvent(testEvent);
        afterCount = (eventController.getEventList("username")).size();
        assertNotEquals(beforeCount, afterCount);
        System.out.println("\nFinish testRemoveEvent");
    }

    @Test
    public void testRemoveByIndex() throws EventControllerException {
        System.out.println("\nStarting testRemoveByIndex");
        eventController.removeEvent("username",1);
        assertNotNull(eventController.getEventList("username"));
        System.out.println("\nFinish testRemoveByIndex");
    }

    @Test
    public void testRemoveInvalidEvent() {
        System.out.println("\nStarting testRemoveInvalidEvent");
        Void nullEvent = null;
        assertThrows(EventControllerException.class,()-> {
            eventController.removeEvent(nullEvent);
        });
        System.out.println("\nFinish testRemoveInvalidEvent");
    }

    @Test
    public void testRemoveEventInvalidPosition() {
        System.out.println("\nStarting testRemoveEventInvalidPosition");
        assertThrows(EventControllerException.class,()-> {
            eventController.removeEvent("username",30);
        });
        System.out.println("\nFinish testRemoveEventInvalidPosition");
    }

    @Test
    public void testUpdateEvent() throws EventControllerException {
        System.out.println("\nStarting testUpdateEvent");
        eventController.addEvent(testEvent);
        beforeCount = (eventController.getEventList("username")).size();
        eventController.updateEvent(testEvent,finalTestEvent);
        afterCount = (eventController.getEventList("username")).size();
        assertEquals(beforeCount, afterCount);
        System.out.println("\nFinishing testUpdateEvent");
    }

}

package comp3350.team7.scheduleapp;

import org.junit.Before;
import org.junit.Test;

import java.util.Calendar;

import comp3350.team7.scheduleapp.logic.exceptions.DbErrorException;
import comp3350.team7.scheduleapp.objects.Event;
import comp3350.team7.scheduleapp.persistence.EventDbStub;
import static org.junit.Assert.*;

public class EventDbStubTest {

    private EventDbStub eventDbStub;
    private Event event;
    private Event freshEvent;
    private Calendar calendar;

    @Before
    public void createTestingEvent() {
        eventDbStub = new EventDbStub(2);
        calendar = Calendar.getInstance();
        event = new Event("newEvent","this is just a test", calendar);
        freshEvent = new Event("freshEvent","this is the fresh event that we be used in replacing",calendar);
    }

    @Test
    public void testEventDbStub() {
        System.out.println("\nStarting testEventDbStub");
        assertNotNull(eventDbStub);
        assertNotNull(eventDbStub.getEventList());
        assertTrue(2 == eventDbStub.getEventListLength());
        System.out.println("\nFinished testEventDbStub");
    }
    
    @Test
    public void testAddEvent() {
        System.out.println("\nStarting testAddEvent");
        eventDbStub.addEvent(event);
        assertTrue(3 == eventDbStub.getEventListLength());
        System.out.println("\nFinished testAddEvent");
    }

    @Test
    public void testRemoveEventByIndex() throws DbErrorException {
        System.out.println("\nStarting testRemoveEventByIndex");
        assertTrue(2 == eventDbStub.getEventListLength());
        eventDbStub.removeEvent((eventDbStub.getEventListLength()-1));
        assertTrue(1 == eventDbStub.getEventListLength());
        System.out.println("\nFinished testRemoveEventByIndex");
    }

    @Test
    public void testRemoveEventByEvent() throws DbErrorException {
        System.out.println("\nStarting testRemoveEvent");
        eventDbStub.addEvent(event);
        assertTrue(3 == eventDbStub.getEventListLength());
        eventDbStub.removeEvent(event);
        assertTrue(2 == eventDbStub.getEventListLength());
        System.out.println("\nFinished testRemoveEvent");
    }

    @Test
    public void testUpdateEvent() throws DbErrorException {
        System.out.println("\nStarting testUpdateEvent");
        eventDbStub.addEvent(event);
        assertTrue(3 == eventDbStub.getEventListLength());
        assertTrue(event.equals((eventDbStub.getEventList()).get(2)));
        eventDbStub.updateEvent(event,freshEvent);
        assertTrue(freshEvent.equals((eventDbStub.getEventList()).get(2)));
        System.out.println("\nFinished testUpdateEvent");
    }
}

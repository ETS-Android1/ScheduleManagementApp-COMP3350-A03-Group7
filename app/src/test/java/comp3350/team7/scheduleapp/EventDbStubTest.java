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
    private Calendar calendar;

    @Before
    public void createTestingEvent() {
        calendar = Calendar.getInstance();
        event = new Event("newEvent","this is just a test", calendar);
    }

    @Test
    public void testEventDbStub() {
        System.out.println("\nStarting testEventDbStub");
        eventDbStub = new EventDbStub(2);
        assertNotNull(eventDbStub);
        assertNotNull(eventDbStub.getEventList());
        assertTrue(2 == eventDbStub.getEventListLength());
        System.out.println("\nFinished testEventDbStub");
    }
    @Test
    public void testAddEvent() {
        System.out.println("\nStarting testAddEvent");
        eventDbStub = new EventDbStub(1);
        eventDbStub.addEvent(event);
        assertTrue(2 == eventDbStub.getEventListLength());
        System.out.println("\nFinished testAddEvent");
    }

//    @Test
//    public void testRemoveEventByIndex() {
//        System.out.println("\nStarting testRemoveEventByIndex");
//        eventDbStub = new EventDbStub(2);
//        System.out.println("\nFinished testRemoveEventByIndex");
//    }

    @Test
    public void testRemoveEvent() throws DbErrorException {
        System.out.println("\nStarting testRemoveEvent");
        eventDbStub = new EventDbStub(2);
        assertTrue(2 == eventDbStub.getEventListLength());
        eventDbStub.removeEvent((eventDbStub.getEventListLength()-1));
        assertTrue(1 == eventDbStub.getEventListLength());
        System.out.println("\nFinished testRemoveEvent");
    }

//    @Test
//    public void testUpdateEvent() {
//        System.out.println("\nStarting testUpdateEvent");
//        System.out.println("\nFinished testUpdateEvent");
//    }
}

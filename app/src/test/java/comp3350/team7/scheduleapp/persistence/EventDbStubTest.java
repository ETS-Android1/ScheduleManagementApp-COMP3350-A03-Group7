package comp3350.team7.scheduleapp.persistence;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.util.Calendar;

import comp3350.team7.scheduleapp.persistence.stubs.EventPersistenceStub;
import comp3350.team7.scheduleapp.logic.exceptions.DbErrorException;
import comp3350.team7.scheduleapp.objects.Event;
import comp3350.team7.scheduleapp.persistence.stubs.EventPersistenceStub;
import static org.junit.Assert.*;

/*
 * Created By Thai Tran on 12 March,2021
 *
 */
public class EventDbStubTest {

    private EventPersistenceStub eventDbStub;
    private Event event;
    private Event freshEvent;
    private Calendar calendar;

    @Before
    public void createTestingEvent() {
        eventDbStub = new EventPersistenceStub();
        calendar = Calendar.getInstance();
        event = new Event("testuser","newEvent","this is just a test", calendar);
        freshEvent = new Event("testuser","freshEvent","this is the fresh event that we be used in replacing",calendar);
    }

    @After
    public void teardown() {
        eventDbStub = null;
        calendar = null;
        event = null;
        freshEvent = null;
    }

    @Test
    public void testEventDbStub() {
        System.out.println("\nStarting testEventDbStub");
        assertNotNull(eventDbStub);
        assertNotNull(eventDbStub.getEventList("testuser"));
        assertTrue(2 == eventDbStub.getEventListLength("testuser"));
        System.out.println("\nFinished testEventDbStub");
    }

    @Test
    public void testAddEvent() {
        System.out.println("\nStarting testAddEvent");
        eventDbStub.addEvent(event);
        assertTrue(3 == eventDbStub.getEventListLength("testuser"));
        System.out.println("\nFinished testAddEvent");
    }

    @Test
    public void testRemoveEventByIndex() throws DbErrorException {
        System.out.println("\nStarting testRemoveEventByIndex");
        assertTrue(2 == eventDbStub.getEventListLength("testuser"));
        eventDbStub.removeEvent("testuser", (eventDbStub.getEventListLength("testuser")-1));
        assertTrue(1 == eventDbStub.getEventListLength("testuser"));
        System.out.println("\nFinished testRemoveEventByIndex");
    }

    @Test
    public void testRemoveEventFromNonExistingPosition() {
        System.out.println("\nStarting testRemoveEventFromNonExistingPosition");
        assertThrows(DbErrorException.class,()-> eventDbStub.removeEvent("testuser",-1));
        System.out.println("\nFinished testRemoveEventFromNonExistingPosition");
    }

    @Test
    public void testRemoveFromEmptyEventLIst() {
        System.out.println("\nStarting testRemoveEventFromNonExistingPosition");
        EventPersistenceStub eventDbStub1 = new EventPersistenceStub();
        assertThrows(IndexOutOfBoundsException.class,()-> {
            eventDbStub1.removeEvent("testuser",1);
        });
        System.out.println("\nFinished testRemoveEventFromNonExistingPosition");
    }

    @Test
    public void testRemoveEventByEvent() throws DbErrorException {
        System.out.println("\nStarting testRemoveEvent");
        eventDbStub.addEvent(event);
        assertTrue(3 == eventDbStub.getEventListLength("testuser"));
        eventDbStub.removeEvent(event);
        assertTrue(2 == eventDbStub.getEventListLength("testuser"));
        System.out.println("\nFinished testRemoveEvent");
    }

    @Test
    public void testRemoveEventNotInList() {
        System.out.println("\nStarting testRemoveEventNotInList");
        assertThrows(DbErrorException.class,()-> {
            eventDbStub.removeEvent(event);
        });
        System.out.println("\nFinished testRemoveEventNotInList");
    }

    @Test
    public void testUpdateEvent() throws DbErrorException {
        System.out.println("\nStarting testUpdateEvent");
        eventDbStub.addEvent(event);
        assertTrue(3 == eventDbStub.getEventListLength("testuser"));
        assertTrue(event.equals((eventDbStub.getEventList("testuser")).get(2)));
        eventDbStub.updateEvent(event,freshEvent);
        assertTrue(freshEvent.equals((eventDbStub.getEventList("testuser")).get(2)));
        System.out.println("\nFinished testUpdateEvent");
    }
}

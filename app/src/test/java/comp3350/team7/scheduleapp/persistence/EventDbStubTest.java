package comp3350.team7.scheduleapp.persistence;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.util.Calendar;

import comp3350.team7.scheduleapp.logic.exceptions.DbErrorException;
import comp3350.team7.scheduleapp.objects.Event;
import comp3350.team7.scheduleapp.persistence.stubs.EventPersistenceStub;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertThrows;
import static org.junit.Assert.assertTrue;

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
        System.out.println("\nFinished testEventDbStub");
    }

    @Test
    public void testAddEvent() {
        System.out.println("\nStarting testAddEvent");
        eventDbStub.addEvent(event);
        assertTrue( (eventDbStub.getEventList("testuser")).contains(event));
        System.out.println("\nFinished testAddEvent");
    }

    @Test
    public void testRemoveEventByIndex() throws DbErrorException {
        System.out.println("\nStarting testRemoveEventByIndex");
        eventDbStub.addEvent(event);
        assertTrue( (eventDbStub.getEventList("testuser")).contains(event));
        eventDbStub.removeEvent("testuser", (eventDbStub.getEventList("testuser")).indexOf(event));
        assertFalse( (eventDbStub.getEventList("testuser")).contains(event));
        System.out.println("\nFinished testRemoveEventByIndex");
    }

    @Test
    public void testRemoveEventFromNonExistingPosition() {
        System.out.println("\nStarting testRemoveEventFromNonExistingPosition");
        assertThrows(DbErrorException.class,()-> eventDbStub.removeEvent("testuser",-1));
        System.out.println("\nFinished testRemoveEventFromNonExistingPosition");
    }

    @Test
    public void testRemoveEventByEvent() throws DbErrorException {
        System.out.println("\nStarting testRemoveEvent");
        eventDbStub.addEvent(event);
        assertTrue( (eventDbStub.getEventList("testuser")).contains(event));
        eventDbStub.removeEvent(event);
        assertFalse(eventDbStub.getEventList("testuser").contains(event));
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
//        assertTrue(3 == eventDbStub.getEventListLength("testuser"));
        assertTrue((eventDbStub.getEventList("testuser")).contains(event));
        eventDbStub.updateEvent(freshEvent);
        assertTrue((eventDbStub.getEventList("testuser")).contains(freshEvent));
        System.out.println("\nFinished testUpdateEvent");
    }
}

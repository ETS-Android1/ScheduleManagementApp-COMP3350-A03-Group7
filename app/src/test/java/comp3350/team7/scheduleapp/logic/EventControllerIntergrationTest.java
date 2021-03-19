package comp3350.team7.scheduleapp.logic;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.File;
import java.io.IOException;
import java.util.Calendar;
import java.util.List;

import comp3350.team7.scheduleapp.Helper.TestHelper;
import comp3350.team7.scheduleapp.logic.exceptions.EventControllerException;
import comp3350.team7.scheduleapp.objects.Event;
import comp3350.team7.scheduleapp.persistence.EventPersistenceInterface;
import comp3350.team7.scheduleapp.persistence.hsqldb.EventPersistenceHSQLDB;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.assertFalse;
/*
 * Created By Thai Tran on 18 March,2021
 *
 */

public class EventControllerIntergrationTest {
    private File tempDb;
    private EventController eventController;
    private EventPersistenceInterface eventPersistence;
    private Calendar testStartDate, testEndDate;
    @Before
    public void setUp() throws IOException {
        this.tempDb = TestHelper.cloneDb();
        this.eventPersistence = new EventPersistenceHSQLDB(this.tempDb.getAbsolutePath().replace(".script", ""));
        eventController = new EventController(eventPersistence, "username");
        testStartDate = Calendar.getInstance();
        testStartDate.set(2021,8,9);
        testEndDate = Calendar.getInstance();
        testEndDate.set(2021,8,10);
    }

    @Test
    public void testAddEvent() throws EventControllerException {
        System.out.println("Starting testAddEvent");
        final Event eventForAddEvent = eventController.CreateEvent("username","testAddEvent", "this is a test event", testStartDate);
        eventController.addEvent(eventForAddEvent);
        System.out.println("Finished testAddEvent");
    }

    @Test
    public void testGetEventList() throws EventControllerException {
        System.out.println("Starting testGetEventList");
        final List<Event> testEventList;
        testEventList = eventController.getEventList("username");
        assertNotNull(testEventList);
        System.out.println("Finished testGetEventList");
    }

    @Test
    public void testCreateEvent() throws EventControllerException {
        System.out.println("Starting testCreateEvent");
        final Event createdEvent;
        final Event createdEvent1;
        createdEvent = eventController.CreateEvent("username","testCreateEvent", "this is a test event", testStartDate);
        assertNotNull(createdEvent);
        createdEvent1 = eventController.CreateEvent("username","secondCreateEvent", "this is another test event", testStartDate, testEndDate);
        assertNotNull(createdEvent1);
        System.out.println("Finished testCreateEvent");
    }

    @Test
    public void testRemoveEvent() throws EventControllerException {
        System.out.println("Starting testRemoveEvent");
        final Event createdEvent3;
        createdEvent3 = eventController.CreateEvent("username","testRemoveEvent", "this is a test event", testStartDate);
        eventController.addEvent(createdEvent3);
        final List<Event> testEventList1 = eventController.getEventList("username");
        final int sizeOfList = testEventList1.size();
        eventController.removeEvent(createdEvent3);
        assertEquals(sizeOfList-1,(eventController.getEventList("username")).size());
        System.out.println("Finished testRemoveEvent");
    }

    @Test
    public void testUpdateEvent(){
        System.out.println("Starting testUpdateEvent");

        System.out.println("Finished testUpdateEvent");
    }

    @Test
    public void testSortingEvent(){
        System.out.println("Starting testSortingEvent");

        System.out.println("Finished testSortingEvent");
    }

    @After
    public void tearDown(){
        this.tempDb.delete();
    }
}


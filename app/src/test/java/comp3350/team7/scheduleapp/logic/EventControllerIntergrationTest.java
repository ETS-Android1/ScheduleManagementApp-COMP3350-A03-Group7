package comp3350.team7.scheduleapp.logic;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.File;
import java.io.IOException;
import java.util.Calendar;
import java.util.List;

import comp3350.team7.scheduleapp.Helper.TestHelper;
import comp3350.team7.scheduleapp.logic.comparators.EventEndAscendingComparator;
import comp3350.team7.scheduleapp.logic.comparators.EventEndDescendingComparator;
import comp3350.team7.scheduleapp.logic.comparators.EventStartAscendingComparator;
import comp3350.team7.scheduleapp.logic.comparators.EventStartDescendingComparator;
import comp3350.team7.scheduleapp.logic.exceptions.EventControllerException;
import comp3350.team7.scheduleapp.logic.exceptions.UserDBException;
import comp3350.team7.scheduleapp.objects.Event;
import comp3350.team7.scheduleapp.objects.User;
import comp3350.team7.scheduleapp.persistence.EventPersistenceInterface;
import comp3350.team7.scheduleapp.persistence.UserPersistenceInterface;
import comp3350.team7.scheduleapp.persistence.hsqldb.EventPersistenceHSQLDB;
import comp3350.team7.scheduleapp.persistence.hsqldb.UserPersistenceHSQLDB;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertSame;
import static org.junit.Assert.assertTrue;
/*
 * Created By Thai Tran on 18 March,2021
 *
 */

public class EventControllerIntergrationTest {
    private File tempDb;
    private EventController eventController;
    private UserPersistenceInterface userPersistenceInterface;
    private EventPersistenceInterface eventPersistence;
    private Calendar testStartDate, testEndDate, testAlarmDate;
    private int countBefore, countAfter;

    @Before
    public void setUp() throws IOException, UserDBException {
        this.tempDb = TestHelper.cloneDb();
        String dbPath = this.tempDb.getAbsolutePath().replace(".script", "");
        this.eventPersistence = new EventPersistenceHSQLDB(dbPath);
        this.userPersistenceInterface = new UserPersistenceHSQLDB(dbPath);
        eventController = new EventController(eventPersistence);
        userPersistenceInterface.addUser(new User("thai", "tran", "username", "testing"));
        testStartDate = TestHelper.getCustomizeCalendarInstance(Calendar.HOUR_OF_DAY, 5);
        testEndDate = TestHelper.getCustomizeCalendarInstance(Calendar.HOUR_OF_DAY, 6);
        testAlarmDate = TestHelper.getCustomizeCalendarInstance(Calendar.HOUR_OF_DAY, 4);
        countBefore = 0;
        countAfter = 0;
    }

    @Test
    public void buildEvent() throws EventControllerException {
        final Event eventForAddEvent = eventController.buildEvent("username", "testAddEvent", "this is a test event", testStartDate);
        assertNotNull("Should Return Valid Event", eventForAddEvent);
        assertEquals(eventForAddEvent.getTitle(), "testAddEvent");
        assertEquals(eventForAddEvent.getUserName(), "username");
        assertEquals(eventForAddEvent.getDescription(), "this is a test event");
        assertSame(eventForAddEvent.getEventStart(), testStartDate);
        assertNull(eventForAddEvent.getAlarm());
        assertNull(eventForAddEvent.getEventEnd());
    }

    @Test
    public void buildEventWithEndTime() throws EventControllerException {
        final Event eventForAddEvent = eventController.buildEvent("username", "testAddEvent", "this is a test event", testStartDate, testEndDate);
        assertNotNull("Should Return Valid Event", eventForAddEvent);
        assertEquals(eventForAddEvent.getTitle(), "testAddEvent");
        assertEquals(eventForAddEvent.getUserName(), "username");
        assertEquals(eventForAddEvent.getDescription(), "this is a test event");
        assertSame(eventForAddEvent.getEventStart(), testStartDate);
        assertSame(eventForAddEvent.getEventEnd(), testEndDate);
        assertNull(eventForAddEvent.getAlarm());
    }

    @Test
    public void buildEventWithAlarmTime() throws EventControllerException {
        final Event eventForAddEvent = eventController.buildEvent("username", "testAddEvent", "this is a test event", testStartDate, testEndDate, testAlarmDate);
        assertNotNull("Should Return Valid Event", eventForAddEvent);
        assertEquals(eventForAddEvent.getTitle(), "testAddEvent");
        assertEquals(eventForAddEvent.getUserName(), "username");
        assertEquals(eventForAddEvent.getDescription(), "this is a test event");
        assertNotNull(eventForAddEvent.getEventEnd());
        assertSame(eventForAddEvent.getEventStart(), testStartDate);
        assertSame(eventForAddEvent.getEventEnd(), testEndDate);
        assertSame(eventForAddEvent.getAlarm(), testAlarmDate);

    }

    @Test
    public void testGetEvent() throws EventControllerException {
        System.out.println("Starting testAddEvent");
        final Event eventForAddEvent = eventController.buildEvent("username", "testAddEvent", "this is a test event", testStartDate);
        eventController.addEvent(eventForAddEvent);
        Event retrievedEvent = eventController.getEvent("username", eventForAddEvent.getID());
        assertEquals(retrievedEvent.getID(), eventForAddEvent.getID());
        System.out.println("Finished testAddEvent");
    }

    @Test
    public void testAddEvent() throws EventControllerException {
        final Event eventForAddEvent = eventController.buildEvent("username", "testAddEvent", "this is a test event", testStartDate);
        eventController.addEvent(eventForAddEvent);
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
        createdEvent = eventController.buildEvent("username", "testCreateEvent", "this is a test event", testStartDate);
        assertNotNull(createdEvent);
        createdEvent1 = eventController.buildEvent("username", "secondCreateEvent", "this is another test event", testStartDate, testEndDate);
        assertNotNull(createdEvent1);
        System.out.println("Finished testCreateEvent");
    }

    @Test
    public void testRemoveEvent() throws EventControllerException {
        System.out.println("Starting testRemoveEvent");
        final Event createdEvent3;
        createdEvent3 = eventController.buildEvent("username", "testRemoveEvent", "this is a test event", testStartDate);
        eventController.addEvent(createdEvent3);
        final List<Event> testEventList1 = eventController.getEventList("username");
        final int sizeOfList = testEventList1.size();
        eventController.removeEvent(createdEvent3);
        assertEquals(sizeOfList - 1, (eventController.getEventList("username")).size());
        System.out.println("Finished testRemoveEvent");
    }

    @Test
    public void testRemoveEventWithId() throws EventControllerException {
        System.out.println("Starting testRemoveEventWithId");
        final Event createdEvent3;
        createdEvent3 = eventController.buildEvent("username", "testRemoveEvent", "this is a test event", testStartDate);
        eventController.addEvent(createdEvent3);
        final List<Event> testEventList1 = eventController.getEventList("username");
        final int sizeOfList = testEventList1.size();
        eventController.removeEvent("username", createdEvent3.getID());
        assertEquals(sizeOfList - 1, (eventController.getEventList("username")).size());
        System.out.println("Finished testRemoveEventWithid");
    }

    @Test
    public void testGetEventListLength() throws EventControllerException {
        System.out.println("Starting testGetEventListLength");
        final Event createdEvent1, createdEvent2, createdEvent3;
        createdEvent1 = eventController.buildEvent("username", "testRemoveEvent1", "this is a test event", testStartDate);
        createdEvent2 = eventController.buildEvent("username", "testRemoveEvent2", "this is a test event", testStartDate);
        createdEvent3 = eventController.buildEvent("username", "testRemoveEvent3", "this is a test event", testStartDate);
        eventController.addEvent(createdEvent1);
        eventController.addEvent(createdEvent2);
        eventController.addEvent(createdEvent3);
        final List<Event> testEventList1 = eventController.getEventList("username");
        final int sizeOfList = eventController.getEventListLength("username");
        assertEquals(sizeOfList, testEventList1.size());
        System.out.println("Finished testGetEventListLength");

    }

    @Test
    public void testGetScheduleForUserOnDate() throws EventControllerException {
        System.out.println("Starting testGetScheduleForUserOnDate");
        Calendar day1, day2, day3;
        List<Event> listofSchedule1, listofSchedule2, listofSchedule3;
        day1 = TestHelper.getCustomizeCalendarInstance(Calendar.DAY_OF_MONTH, 5);
        day2 = TestHelper.getCustomizeCalendarInstance(Calendar.DAY_OF_MONTH, 10);
        day3 = TestHelper.getCustomizeCalendarInstance(Calendar.DAY_OF_MONTH, 15);

        final Event createdEvent1, createdEvent2, createdEvent3;
        createdEvent1 = eventController.buildEvent("username", "testRemoveEvent1", "this is a test event", day1);
        createdEvent2 = eventController.buildEvent("username", "testRemoveEvent2", "this is a test event", day2);
        createdEvent3 = eventController.buildEvent("username", "testRemoveEvent3", "this is a test event", day3);
        eventController.addEvent(createdEvent1);
        eventController.addEvent(createdEvent2);
        eventController.addEvent(createdEvent3);
        listofSchedule1 = eventController.getScheduleForUserOnDate("username", day1);
        listofSchedule2 = eventController.getScheduleForUserOnDate("username", day2);
        listofSchedule3 = eventController.getScheduleForUserOnDate("username", day3);
        assertEquals(1, listofSchedule1.size());
        assertEquals(1, listofSchedule2.size());
        assertEquals(1, listofSchedule3.size());
        assertNotNull(listofSchedule1.get(0));
        assertNotNull(listofSchedule2.get(0));
        assertNotNull(listofSchedule3.get(0));
        assertEquals(listofSchedule1.get(0).getTitle(), "testRemoveEvent1");
        assertEquals(listofSchedule2.get(0).getTitle(), "testRemoveEvent2");
        assertEquals(listofSchedule3.get(0).getTitle(), "testRemoveEvent3");


        System.out.println("Finished testGetScheduleForUserOnDate");
    }


    @Test
    public void testUpdateEvent() throws EventControllerException {

        System.out.println("Starting testUpdateEvent");

        final Event createdEvent4;
        final Event createdEvent5;
        List<Event> eventList;

        assertNotNull(createdEvent4 = eventController.buildEvent("username", "UpdateEvent1", "this is a testing update", testStartDate));
        assertNotNull(createdEvent5 = eventController.buildEvent("username", "UpdateEvent2", "this is test for update", testStartDate));

        eventController.addEvent(createdEvent4);
        eventList = eventController.getEventList("username");
        countBefore = eventList.size();
        eventController.updateEvent(createdEvent4, createdEvent5);
        countAfter = (eventController.getEventList("username")).size();
        assertEquals(countBefore, countAfter);

        System.out.println("Finished testUpdateEvent");
    }

    @Test
    public void testSortingEventStart() throws EventControllerException {

        System.out.println("Starting testSortingEvent");
        List<Event> eventList;
        Calendar day1, day2, day3, day4, day5, day6, day7, day8, day9;
        Event createdEvent1, createdEvent2, createdEvent3, createdEvent4, createdEvent5, createdEvent6, createdEvent7, createdEvent8, createdEvent9;
        day1 = TestHelper.getCustomizeCalendarInstance(Calendar.DAY_OF_MONTH, 1);
        day2 = TestHelper.getCustomizeCalendarInstance(Calendar.DAY_OF_MONTH, 2);
        day3 = TestHelper.getCustomizeCalendarInstance(Calendar.DAY_OF_MONTH, 3);
        day4 = TestHelper.getCustomizeCalendarInstance(Calendar.DAY_OF_MONTH, 4);
        day5 = TestHelper.getCustomizeCalendarInstance(Calendar.DAY_OF_MONTH, 5);
        day6 = TestHelper.getCustomizeCalendarInstance(Calendar.DAY_OF_MONTH, 6);
        day7 = TestHelper.getCustomizeCalendarInstance(Calendar.DAY_OF_MONTH, 7);
        day8 = TestHelper.getCustomizeCalendarInstance(Calendar.DAY_OF_MONTH, 8);
        day9 = TestHelper.getCustomizeCalendarInstance(Calendar.DAY_OF_MONTH, 9);

        createdEvent2 = eventController.buildEvent("username", "testRemoveEvent2", "this is a test event", day2);
        createdEvent3 = eventController.buildEvent("username", "testRemoveEvent3", "this is a test event", day3);
        createdEvent7 = eventController.buildEvent("username", "testRemoveEvent7", "this is a test event", day7);
        createdEvent8 = eventController.buildEvent("username", "testRemoveEvent8", "this is a test event", day8);
        createdEvent1 = eventController.buildEvent("username", "testRemoveEvent1", "this is a test event", day1);
        createdEvent4 = eventController.buildEvent("username", "testRemoveEvent4", "this is a test event", day4);
        createdEvent5 = eventController.buildEvent("username", "testRemoveEvent5", "this is a test event", day5);
        createdEvent6 = eventController.buildEvent("username", "testRemoveEvent6", "this is a test event", day6);
        createdEvent9 = eventController.buildEvent("username", "testRemoveEvent9", "this is a test event", day9);

        eventController.addEvent(createdEvent5);
        eventController.addEvent(createdEvent6);
        eventController.addEvent(createdEvent7);
        eventController.addEvent(createdEvent8);
        eventController.addEvent(createdEvent9);
        eventController.addEvent(createdEvent1);
        eventController.addEvent(createdEvent2);
        eventController.addEvent(createdEvent3);
        eventController.addEvent(createdEvent4);

        // default sort in asc order
        eventList = eventController.getEventList("username");
        assertTrue(TestHelper.isSorted(eventList, new EventStartAscendingComparator(), eventList.size()));
        assertEquals(eventList.get(0).getTitle(), "testRemoveEvent1");
        assertEquals(eventList.get(8).getTitle(), "testRemoveEvent9");
        // test sort in desc order
        eventController.setSortStrategy(new EventStartDescendingComparator());
        eventList = eventController.getEventList("username");
        assertTrue(TestHelper.isSorted(eventList, new EventStartDescendingComparator(), eventList.size()));
        assertEquals(eventList.get(0).getTitle(), "testRemoveEvent9");
        assertEquals(eventList.get(8).getTitle(), "testRemoveEvent1");
        System.out.println("Finished testSortingEvent");
    }

    @Test
    public void testSortingEventEnd() throws EventControllerException {
        System.out.println("Starting testSortingEvent");
        List<Event> eventList;
        Calendar day1, day2, day3, day4, day5, day6, day7, day8, day9, day10;
        Event createdEvent1, createdEvent2, createdEvent3, createdEvent4, createdEvent5, createdEvent6, createdEvent7, createdEvent8, createdEvent9;
        day1 = TestHelper.getCustomizeCalendarInstance(Calendar.DAY_OF_MONTH, 1);
        day2 = TestHelper.getCustomizeCalendarInstance(Calendar.DAY_OF_MONTH, 2);
        day3 = TestHelper.getCustomizeCalendarInstance(Calendar.DAY_OF_MONTH, 3);
        day4 = TestHelper.getCustomizeCalendarInstance(Calendar.DAY_OF_MONTH, 4);
        day5 = TestHelper.getCustomizeCalendarInstance(Calendar.DAY_OF_MONTH, 5);
        day6 = TestHelper.getCustomizeCalendarInstance(Calendar.DAY_OF_MONTH, 6);
        day7 = TestHelper.getCustomizeCalendarInstance(Calendar.DAY_OF_MONTH, 7);
        day8 = TestHelper.getCustomizeCalendarInstance(Calendar.DAY_OF_MONTH, 8);
        day9 = TestHelper.getCustomizeCalendarInstance(Calendar.DAY_OF_MONTH, 9);
        day10 = TestHelper.getCustomizeCalendarInstance(Calendar.DAY_OF_MONTH, 10);


        createdEvent2 = eventController.buildEvent("username", "testRemoveEvent2", "this is a test event", day2, day3);
        createdEvent3 = eventController.buildEvent("username", "testRemoveEvent3", "this is a test event", day3, day4);
        createdEvent7 = eventController.buildEvent("username", "testRemoveEvent7", "this is a test event", day7, day8);
        createdEvent8 = eventController.buildEvent("username", "testRemoveEvent8", "this is a test event", day8, day9);
        createdEvent1 = eventController.buildEvent("username", "testRemoveEvent1", "this is a test event", day1, day2);
        createdEvent4 = eventController.buildEvent("username", "testRemoveEvent4", "this is a test event", day4, day5);
        createdEvent5 = eventController.buildEvent("username", "testRemoveEvent5", "this is a test event", day5, day6);
        createdEvent6 = eventController.buildEvent("username", "testRemoveEvent6", "this is a test event", day6, day7);
        createdEvent9 = eventController.buildEvent("username", "testRemoveEvent9", "this is a test event", day9, day10);

        eventController.addEvent(createdEvent5);
        eventController.addEvent(createdEvent6);
        eventController.addEvent(createdEvent7);
        eventController.addEvent(createdEvent8);
        eventController.addEvent(createdEvent9);
        eventController.addEvent(createdEvent1);
        eventController.addEvent(createdEvent2);
        eventController.addEvent(createdEvent3);
        eventController.addEvent(createdEvent4);

        // default sort in asc order
        eventController.setSortStrategy(new EventEndAscendingComparator());
        eventList = eventController.getEventList("username");
        assertTrue(TestHelper.isSorted(eventList, new EventEndAscendingComparator(), eventList.size()));
        assertEquals(eventList.get(0).getTitle(), "testRemoveEvent1");
        assertEquals(eventList.get(8).getTitle(), "testRemoveEvent9");

        // test sort in desc order
        eventController.setSortStrategy(new EventEndDescendingComparator());
        eventList = eventController.getEventList("username");
        assertTrue(TestHelper.isSorted(eventList, new EventStartDescendingComparator(), eventList.size()));
        assertEquals(eventList.get(0).getTitle(), "testRemoveEvent9");
        assertEquals(eventList.get(8).getTitle(), "testRemoveEvent1");

        System.out.println("Finished testSortingEvent");
    }

    @After
    public void tearDown() {
        this.tempDb.delete();
    }
}


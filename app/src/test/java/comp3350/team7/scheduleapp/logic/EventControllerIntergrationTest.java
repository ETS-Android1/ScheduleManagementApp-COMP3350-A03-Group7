package comp3350.team7.scheduleapp.logic;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.File;
import java.io.IOException;

import comp3350.team7.scheduleapp.Helper.TestHelper;
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
    @Before
    public void setUp() throws IOException {
        this.tempDb = TestHelper.cloneDb();
        this.eventPersistence = new EventPersistenceHSQLDB(this.tempDb.getAbsolutePath().replace(".script", ""));

        eventController = new EventController(eventPersistence, "username");
    }

    @Test
    public void testAddEvent(){

    }

    @Test
    public void testGetEventList(){

    }

    @Test
    public void testCreateEvent(){

    }

    @Test
    public void testRemoveEvent(){

    }

    @Test
    public void testUpdateEvent(){

    }

    @Test
    public void testSortingEvent(){

    }

    @After
    public void tearDown(){
        this.tempDb.delete();
    }
}


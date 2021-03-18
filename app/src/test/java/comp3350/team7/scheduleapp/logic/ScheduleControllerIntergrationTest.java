package comp3350.team7.scheduleapp.logic;

/*
 * Created By Thai Tran on 18 March,2021
 *
 */

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.File;
import java.io.IOException;

import comp3350.team7.scheduleapp.Helper.TestHelper;
import comp3350.team7.scheduleapp.persistence.SchedulePersistenceInterface;
import comp3350.team7.scheduleapp.persistence.hsqldb.ScheduledEventsPersistenceHSQLDB;

public class ScheduleControllerIntergrationTest {
    private File tempDb;
    private SchedulePersistenceInterface schedulePersistence;
    private ScheduleController scheduleController;
    @Before
    public void setUp() throws IOException {
        this.tempDb = TestHelper.cloneDb();
        this.schedulePersistence = new ScheduledEventsPersistenceHSQLDB(this.tempDb.getAbsolutePath().replace(".script", ""));

        this.scheduleController = new ScheduleController(this.schedulePersistence, "username");
    }

    @Test
    public void testGetScheduleForDay(){

    }

    @After
    public void tearDown(){
        this.tempDb.delete();
    }
}

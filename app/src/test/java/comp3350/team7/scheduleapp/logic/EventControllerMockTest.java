package comp3350.team7.scheduleapp.logic;

/*
 * Created By Thai Tran on 18 March,2021
 *
 */
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.mockito.Mockito.times;
import org.junit.Before;
import org.junit.Test;

import comp3350.team7.scheduleapp.persistence.EventPersistenceInterface;

public class EventControllerMockTest {
    private EventController eventController;
    private EventPersistenceInterface eventPersistence;

    @Before
    public void setUp(){
        eventPersistence = mock(EventPersistenceInterface.class);
    }


    @Test
    public testSortingEvent(){

    }

}

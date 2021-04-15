package comp3350.team7.scheduleapp.logic;

/*
 * Created By Thai Tran on 06 April,2021
 *
 */

import org.junit.Before;
import org.junit.Test;

import java.util.Calendar;

import static org.junit.Assert.assertEquals;

public class TimeControllerTest {

    final String DATE_FORMAT = "dd-MM-yyyy";
    final String TIME_FORMAT = "HH:mm";
    Calendar calendar;

    @Before
    public void setup() {
        calendar = Calendar.getInstance();
        calendar.set(2021, 9, 8, 6, 5);
    }

    @Test
    public void minToMillisecondsTest() {
        assertEquals(TimeController.minToMilliseconds(5), 5 * 6000);
    }

    @Test
    public void millisecondsToMinTest() {
        assertEquals(TimeController.millisecondsToMin(5 * 6000), 5);
    }

    @Test
    public void dateFormatHelperTest() {
        assertEquals("08-10-2021", TimeController.dateFormatHelper(calendar));
    }

    @Test
    public void timeFormatHelperTest() {
        assertEquals("06:05", TimeController.timeFormatHelper(calendar));
    }

    @Test
    public void dateTimeFormatHelperTest() {
        assertEquals("08-10-2021\n06:05", TimeController.dateTimeFormatHelper(calendar));

    }


}


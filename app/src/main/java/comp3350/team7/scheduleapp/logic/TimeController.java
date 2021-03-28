package comp3350.team7.scheduleapp.logic;

/*
 * Created By Thai Tran on 28 March,2021
 *
 */

import java.util.Calendar;
import java.util.Date;

public class TimeController {
    private static final long OffSetInMills = 6000;
    public static long minToMilliseconds(int min){
        return min * OffSetInMills;
    }
    public static int millisecondsToMin(long millis){
        return (int) (millis/ OffSetInMills);
    }

}

package comp3350.team7.scheduleapp.logic;

/*
 * Created By Thai Tran on 28 March,2021
 *
 */

import android.util.Log;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;

public class TimeController {
    private static final String TAG = "TimeController";
    private static final long OffSetInMills = 6000;
    private static final String DATE_FORMAT = "dd-MM-yyyy";
    private static final String TIME_FORMAT = "HH:mm";
    private static final Locale location =  Locale.getDefault();
    public static long minToMilliseconds(int min){
        return min * OffSetInMills;
    }
    public static int millisecondsToMin(long millis){
        return (int) (millis/ OffSetInMills);
    }

    public static String dateFormatHelper(Calendar cal) {
        SimpleDateFormat formatter = new SimpleDateFormat(DATE_FORMAT, location);
        Log.d(TAG, cal.getTimeZone().getDisplayName());
        return formatter.format(cal.getTime());
    }

    public static String timeFormatHelper(Calendar cal) {
        SimpleDateFormat formatter = new SimpleDateFormat(TIME_FORMAT, location);
        Log.d(TAG, cal.getTimeZone().getDisplayName());
        return formatter.format(cal.getTime());
    }

    public static String dateTimeFormatHelper(Calendar cal) {
        SimpleDateFormat formatter = new SimpleDateFormat(DATE_FORMAT + '\n' + TIME_FORMAT, location);
        Log.d(TAG, cal.getTimeZone().getDisplayName());
        return formatter.format(cal.getTime());

    }
}

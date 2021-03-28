package comp3350.team7.scheduleapp.logic;

/*
 * Created By Thai Tran on 28 March,2021
 *
 */

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;

import comp3350.team7.scheduleapp.R;
import comp3350.team7.scheduleapp.logic.AlarmService.AlarmReceiver;

public class AlarmController {
    private static final int ALARM_BROADCAST_REQUEST_CODE =0 ;

    public static long getAlarmTriggerAtMilliSecondFromMin(long millisStart, int minPiorAlarm) {
        long minPiorAlarmAtMillis = minPiorAlarm * 60000L;
        //Log.d(TAG,minPiorAlarmAtMillis +" | " + (startCalendar.getTimeInMillis() - minPiorAlarmAtMillis) );
        return millisStart - minPiorAlarmAtMillis;
    }
    public static int getAlarmTriggerAtMinFromMillis(long millisStart,long millisPiorAlarm){
        return (int) (millisStart - millisPiorAlarm) / 60000;
    }

    public static void setAlarmService(Context context, String alertContent,long triggerAtMillis) {
        AlarmManager alarmManager = (AlarmManager) context.getSystemService(Context.ALARM_SERVICE);
        Intent intent = new Intent(context, AlarmReceiver.class);

        intent.putExtra("ALERT_TITLE", "Event Reminder");
        intent.putExtra("ALERT_CONTENT", alertContent);
        PendingIntent pendingIntent = PendingIntent.getBroadcast(context, ALARM_BROADCAST_REQUEST_CODE, intent, 0);
        alarmManager.set(AlarmManager.RTC_WAKEUP, triggerAtMillis, pendingIntent);

    }
}

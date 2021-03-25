package comp3350.team7.scheduleapp.logic.AlarmService;

/*
 * Created By Thai Tran on 23 March,2021
 *
 */

import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.ContentResolver;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.util.Log;

import androidx.annotation.RequiresApi;
import androidx.core.app.NotificationCompat;

import comp3350.team7.scheduleapp.R;
import comp3350.team7.scheduleapp.presentation.activity.ScrollingActivity;


public class AlarmBoardcast extends BroadcastReceiver {
    private static final String TAG = "AlarmBoardcast";
    private final String channelID = "ALARM";

    @RequiresApi(api = Build.VERSION_CODES.M)
    @Override
    public void onReceive(Context context, Intent intent) {
        Log.d(TAG,"OnReceive called");
        String title = intent.getStringExtra("ALERT_TITLE");
        String content = intent.getStringExtra("ALERT_CONTENT");
        createNotification(context,R.drawable.ic_alarms,title,content);
    }

    private void createNotification(Context context, int iconRes, String title, String content){
        NotificationCompat.Builder builder =
                new NotificationCompat.Builder(context, channelID)
                        .setSmallIcon(iconRes)
                        .setContentTitle(title)
                        .setAutoCancel(true)
                        .setContentText(content).setSound(Uri.parse(ContentResolver.SCHEME_ANDROID_RESOURCE
                        + "://" + context.getPackageName() + "/raw/notify"));


        // Add as notification
        NotificationManager manager = (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);
        manager.notify(0, builder.build());

    }

}

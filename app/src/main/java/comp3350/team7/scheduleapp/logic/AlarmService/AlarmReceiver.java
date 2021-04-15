package comp3350.team7.scheduleapp.logic.AlarmService;

/*
 * Created By Thai Tran on 23 March,2021
 *
 */

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;

import comp3350.team7.scheduleapp.R;
import comp3350.team7.scheduleapp.presentation.activity.AlarmActivity;


public class AlarmReceiver extends BroadcastReceiver {
    private static final String TAG = "AlarmReceiver";
    @Override
    public void onReceive(Context context, Intent intent)
    {
        Log.d(TAG,"onReceive called");
        //Toast.makeText(context, context.getString(R.string.Alertnotifty) + intent.getStringExtra("title") , Toast.LENGTH_LONG).show();
        String title = intent.getStringExtra("ALERT_TITLE");
        String content = intent.getStringExtra("ALERT_CONTENT");
        Intent x = new Intent(context, AlarmActivity.class);
        x.putExtra("ALERT_TITLE", title);
        x.putExtra("ALERT_CONTENT", content);
        x.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        context.startActivity(x);
    }
}
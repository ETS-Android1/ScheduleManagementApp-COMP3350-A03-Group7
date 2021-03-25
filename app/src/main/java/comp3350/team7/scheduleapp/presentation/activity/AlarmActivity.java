package comp3350.team7.scheduleapp.presentation.activity;

/*
 * Created By Thai Tran on 23 March,2021
 *
 */

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;

import comp3350.team7.scheduleapp.R;
import comp3350.team7.scheduleapp.presentation.base.BaseActivity;

public class AlarmActivity extends BaseActivity {

        MediaPlayer mp;
        int reso = R.raw.chec;
        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_alert);

            mp=MediaPlayer.create(getApplicationContext(),reso);
            mp.start();
            AlertDialog.Builder builder = new AlertDialog.Builder(this);
            String title = getIntent().getExtras().getString("ALERT_TITLE");
            String content = getIntent().getExtras().getString("ALERT_CONTENT");

            builder.setTitle(title)
                    .setIcon(R.drawable.alarm_on)
                    .setMessage(content)
                    .setCancelable(true)
                    .setPositiveButton("Snooze",
                    new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int id) {
                            dialog.cancel();
                            AlarmActivity.this.finish();
                        }
                    });
            AlertDialog alert = builder.create();
            alert.show();
        }
        @Override
        public void onDestroy() {
            super.onDestroy();
            mp.release();

        }

}

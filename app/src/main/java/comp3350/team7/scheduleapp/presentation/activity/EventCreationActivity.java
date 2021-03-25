package comp3350.team7.scheduleapp.presentation.activity;

import android.app.AlarmManager;
import android.app.DatePickerDialog;
import android.app.Dialog;
import android.app.PendingIntent;
import android.app.TimePickerDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.res.Resources;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.TimePicker;

import androidx.appcompat.app.AlertDialog;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;

import comp3350.team7.scheduleapp.R;
import comp3350.team7.scheduleapp.application.DbServiceProvider;
import comp3350.team7.scheduleapp.application.UserClient;
import comp3350.team7.scheduleapp.logic.AlarmService.AlarmBoardcast;
import comp3350.team7.scheduleapp.logic.AlarmService.AlarmReceiver;
import comp3350.team7.scheduleapp.logic.EventController;
import comp3350.team7.scheduleapp.logic.exceptions.EventControllerException;
import comp3350.team7.scheduleapp.objects.Event;
import comp3350.team7.scheduleapp.persistence.EventPersistenceInterface;
import comp3350.team7.scheduleapp.presentation.base.BaseActivity;

/*
 * Created By Thai Tran on 23 February,2021
 *
 */

public class EventCreationActivity extends BaseActivity {
    private static final String TAG = "EventCreationActivity";
    DatePickerDialog datePicker;
    TimePickerDialog timePicker;
    EditText datePickerText;
    EditText eventNameText;
    EditText timePickerText;
    Button saveButton;
    Button alarmSettingButton;
    TextView reminder_info;

    Calendar calendar;
    Calendar ourCalendar;
    EventController eventController;
    Switch sw;
    int minPiorAlarm =5;
    String alarm_info;
    Resources res;

    final int ALARM_BROADCAST_REQUEST_CODE = 0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.event_creation_activity);

        init();
        getView();
        onClickListenerHelper();

    }

    private void init(){
        ourCalendar = Calendar.getInstance();
        EventPersistenceInterface eventPersistence = DbServiceProvider.getInstance().getEventPersistence();
        eventController = new EventController(eventPersistence);
        res = getResources();
        reminder_info = findViewById( R.id.reminder_info);
        setAlarmInfo();
    }

    private void getView(){
        eventNameText = (EditText) findViewById(R.id.event_name_text);
        datePickerText = (EditText) findViewById(R.id.date_picker_text2);
        timePickerText = (EditText) findViewById(R.id.time_picker_text);
        saveButton = (Button) findViewById(R.id.save_event_button);
        sw = findViewById(R.id.switch1);
        alarmSettingButton = (Button) findViewById( R.id.reminder);
    }

    private void onClickListenerHelper(){
        // date picker input listener
        datePickerText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                calendar = Calendar.getInstance();
                int day = calendar.get(Calendar.DAY_OF_MONTH);
                int month = calendar.get(Calendar.MONTH);
                int year = calendar.get(Calendar.YEAR);
                datePicker = new DatePickerDialog(EventCreationActivity.this, new DatePickerDialog.OnDateSetListener() {

                    @Override
                    public void onDateSet(DatePicker view, int yearOfDecade, int monthOfYear, int dayOfMonth) {


                        ourCalendar.set(yearOfDecade, monthOfYear, dayOfMonth);
                        datePickerText.setText(String.format("%2d/%2d/%2d", dayOfMonth, month + 1, year));
                    }
                }, year, month, day);
                datePicker.show();
            }
        });

        // time picker input listener
        timePickerText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                calendar = Calendar.getInstance();
                int hour = calendar.get(Calendar.HOUR_OF_DAY);
                int minute = calendar.get(Calendar.MINUTE);

                timePicker = new TimePickerDialog(EventCreationActivity.this, new TimePickerDialog.OnTimeSetListener() {
                    @Override
                    public void onTimeSet(TimePicker view, int hourOfDay, int minuteOfSecond) {
                        ourCalendar.set(Calendar.HOUR_OF_DAY, hourOfDay);
                        ourCalendar.set(Calendar.MINUTE, minuteOfSecond);
                        ourCalendar.set(Calendar.SECOND,0);
                        timePickerText.setText(String.format("%2d:%2d", hourOfDay, minuteOfSecond));
                    }


                }, hour, minute, true);
                timePicker.show();
            }
        });

        alarmSettingButton.setOnClickListener(new View.OnClickListener() {
            final String[] tm = new String[]
                    {"5 minutes"
                    , "10 minutes"
                    , "15 minutes"};
            @Override
            public void onClick(View v) {
               new AlertDialog.Builder(EventCreationActivity.this)
                       .setTitle("Pick a time prior event start")
                       .setIcon(R.drawable.alarm_add)
                       .setItems(tm, new DialogInterface.OnClickListener() {
                           @Override
                           public void onClick(DialogInterface dialog, int which) {
                                 minPiorAlarm = (which+1)*5;
                                 setAlarmInfo();
                           }
                       })
                       .create()
                       .show();
            }
        });

        sw.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked){
                    Log.d(TAG,"Alarm option enable");
                }else{
                    Log.d(TAG,"Alarm option disable");
                }
            }
        });

        // Save button listener
        saveButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                Event newEvent = null;
                try {
                    newEvent = eventController.buildEvent(UserClient.getUserId(),eventNameText.getText().toString(), "description", ourCalendar);
                    eventController.addEvent(newEvent);
                    Log.d(TAG, "Saved");
                } catch (EventControllerException e) {
                    Log.d(TAG,"Caused by: " + e.getCause());
                    onError(e.getMessage());
                }

                if(newEvent!=null && sw.isChecked()){
                    AlarmManager alarmManager = (AlarmManager)getSystemService(Context.ALARM_SERVICE);
                    Intent intent = new Intent(EventCreationActivity.this, AlarmReceiver.class);

                    int minPiorAlarmAtMillis = minPiorAlarm*60000;
                    String alertContent = String.format(getString(R.string.alarm_content), minPiorAlarm, newEvent.getTitle());
                    long triggerAtMillis = newEvent.getEventStart().getTimeInMillis() - minPiorAlarmAtMillis;
                    intent.putExtra("ALERT_TITLE", "Event Reminder");
                    intent.putExtra("ALERT_CONTENT", alertContent);
                    PendingIntent pendingIntent = PendingIntent.getBroadcast(EventCreationActivity.this, ALARM_BROADCAST_REQUEST_CODE, intent, 0);
                    alarmManager.set(AlarmManager.RTC_WAKEUP,triggerAtMillis,pendingIntent);
                }
                if(newEvent!=null)
                    returnResult();
            }
        });
    }

    private void setAlarmInfo(){
        alarm_info = String.format(String.valueOf(res.getString(R.string.alarm_reminder_before_2)), minPiorAlarm);
        reminder_info.setText(alarm_info);
    }
    private void returnResult() {
        Intent i = new Intent(EventCreationActivity.this,ScrollingActivity.class);
       // i.putExtra("RETURN_DATA", returnEvent);
        setResult(RESULT_OK, i);
        finish();
    }


}
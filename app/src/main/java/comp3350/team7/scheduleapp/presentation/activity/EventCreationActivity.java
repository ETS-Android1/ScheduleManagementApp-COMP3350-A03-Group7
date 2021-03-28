package comp3350.team7.scheduleapp.presentation.activity;

import android.app.AlarmManager;
import android.app.DatePickerDialog;
import android.app.PendingIntent;
import android.app.TimePickerDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.res.Resources;
import android.os.Build;
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

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.widget.SwitchCompat;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import comp3350.team7.scheduleapp.R;
import comp3350.team7.scheduleapp.application.DbServiceProvider;
import comp3350.team7.scheduleapp.application.UserClient;
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
    final int ALARM_BROADCAST_REQUEST_CODE = 0;
    DatePickerDialog datePicker;
    TimePickerDialog timePicker;
    EditText datePickerText;
    EditText eventNameText;
    EditText timePickerText;
    EditText description;
    Button saveButton;
    Button alarmSettingButton;
    Button setImage;
    TextView reminder_info;
    Calendar tempCalendar, startCalendar, alarmCalendar;
    EventController eventController;
    SwitchCompat sw;
    int minPiorAlarm = 5;  // default
    Resources res;
    Event onReturnEditEvent;
    Object alarm;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.event_creation_activity);
        getView();
        init();
        onClickListenerHelper();

    }

    private void init() {
        EventPersistenceInterface eventPersistence = DbServiceProvider.getInstance().getEventPersistence();
        eventController = new EventController(eventPersistence);
        res = getResources();
        Bundle bundle = getOnReturnEditEventId();
        try {
            onReturnEditEvent = eventController.getEvent(bundle.getString("EVENT_USER_ID"),bundle.getInt("EVENT_ID"));
        } catch (EventControllerException e) {
            Log.d(TAG, "Caused by : " + e.getCause());
            onError(e.getMessage());
        }
        if (onReturnEditEvent != null) {
            Log.d(TAG, onReturnEditEvent.toString());
            startCalendar = onReturnEditEvent.getEventStart();
            alarmCalendar = onReturnEditEvent.getAlarm();
            setView(onReturnEditEvent);
        }
        else{
            startCalendar = Calendar.getInstance();
            Log.d(TAG,"startCalandar new instance");
        }

        setAlarmInfo();


    }

    private void getView() {
        eventNameText = (EditText) findViewById(R.id.event_name_text);
        datePickerText = (EditText) findViewById(R.id.date_picker_text);
        timePickerText = (EditText) findViewById(R.id.time_picker_text);
        description = (EditText) findViewById(R.id.description);
        //setImage = (Button) findViewById(R.id.setImage);
        saveButton = (Button) findViewById(R.id.save_event_button);
        sw = findViewById(R.id.switch1);
        alarmSettingButton = (Button) findViewById(R.id.reminder);
        reminder_info = findViewById(R.id.reminder_info);

    }

    private void setView(Event event) {
        eventNameText.setText(event.getTitle());
        description.setText(event.getDescription());
        SimpleDateFormat formatter = new SimpleDateFormat("HH:mm");
        String timeString = formatter.format(new Date(event.getEventStart().getTimeInMillis()));
        SimpleDateFormat dateformatter = new SimpleDateFormat("dd/MM/yyyy");
        String dateString = dateformatter.format(new Date(event.getEventStart().getTimeInMillis()));
        timePickerText.setText(timeString);
        datePickerText.setText(dateString);
        Calendar alarm  = event.getAlarm();
        if (alarm != null) {
            sw.setChecked(true);
            Log.d(TAG, "switch change state");
            minPiorAlarm= getAlarmTriggerAtMinFromMillis(event.getEventStart().getTimeInMillis(), alarm.getTimeInMillis());
        }


    }

    private void onClickListenerHelper() {
        // date picker input listener
        datePickerText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                tempCalendar = Calendar.getInstance();
                int day = tempCalendar.get(Calendar.DAY_OF_MONTH);
                int month = tempCalendar.get(Calendar.MONTH);
                int year = tempCalendar.get(Calendar.YEAR);
                datePicker = new DatePickerDialog(EventCreationActivity.this, new DatePickerDialog.OnDateSetListener() {

                    @Override
                    public void onDateSet(DatePicker view, int yearOfDecade, int monthOfYear, int dayOfMonth) {


                        startCalendar.set(yearOfDecade, monthOfYear, dayOfMonth);
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
                tempCalendar = Calendar.getInstance();
                int hour = tempCalendar.get(Calendar.HOUR_OF_DAY);
                int minute = tempCalendar.get(Calendar.MINUTE);

                timePicker = new TimePickerDialog(EventCreationActivity.this, new TimePickerDialog.OnTimeSetListener() {
                    @Override
                    public void onTimeSet(TimePicker view, int hourOfDay, int minuteOfSecond) {
                        startCalendar.set(Calendar.HOUR_OF_DAY, hourOfDay);
                        startCalendar.set(Calendar.MINUTE, minuteOfSecond);
                        startCalendar.set(Calendar.SECOND, 0);
                        timePickerText.setText(String.format("%2d:%2d", hourOfDay, minuteOfSecond));
                    }


                }, hour, minute, true);
                timePicker.show();
            }
        });

        // alarm setting
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
                                minPiorAlarm = (which + 1) * 5;
                                setAlarmInfo();
                            }
                        })
                        .create()
                        .show();
            }
        });

        // switch
        sw.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    Log.d(TAG, "Alarm option enable");
                } else {
                    Log.d(TAG, "Alarm option disable");
                }
            }
        });

        // Save button listener
        saveButton.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.N)
            @Override
            public void onClick(View v) {
                String eventTitle = eventNameText.getText().toString();
                String eventDescription = description.getText().toString();
                long triggerAtMillis = getAlarmTriggerAtMilliSecondFromMin(startCalendar.getTimeInMillis(),minPiorAlarm);
                alarmCalendar = Calendar.getInstance();
                alarmCalendar.setTimeInMillis(triggerAtMillis);
                Log.d(TAG, alarmCalendar.getTime().toString());

                Event newEvent = null;
                try {
                    if (sw.isChecked()) {
                        newEvent = eventController.buildEvent(UserClient.getUserId(), eventTitle, eventDescription, startCalendar, null, alarmCalendar);
                        setAlarmService(eventTitle, triggerAtMillis);
                    }
                    else
                        newEvent = eventController.buildEvent(UserClient.getUserId(), eventTitle, eventDescription, startCalendar, null);
                } catch (EventControllerException e) {
                    Log.d(TAG, "Caused by " + e.getCause());
                    onError(e.getMessage());
                }

                if(newEvent!=null){
                    if (onReturnEditEvent != null) {
                        try {
                            onReturnEditEvent= eventController.updateEvent(onReturnEditEvent, newEvent);
                            // clean up
                            onReturnEditEvent = null;
                            returnResult();
                            Log.d(TAG, "Event Update Successfully and Saved");

                        } catch (EventControllerException e) {
                            e.printStackTrace();
                        }
                    } else {
                        try {
                            eventController.addEvent(newEvent);
                            returnResult();
                            Log.d(TAG, "Event Created and Saved");
                        } catch (EventControllerException e) {
                            Log.d(TAG, "Caused by: " + e.getCause());
                            onError(e.getMessage());
                        }
                    }

                }


            }
        });
    }

    //    @RequiresApi(api = Build.VERSION_CODES.N)
//    private void updateEvent(Event onReturnEditEvent, Event fresh){
//        try {
//            eventController.updateEvent(onReturnEditEvent,fresh);
//        } catch (EventControllerException e) {
//            onReturnEditEvent =null;
//            onError(e.getMessage());
//            Log.d(TAG,"Caused by: " + e.getCause());
//            e.printStackTrace();
//        }
//    }
    private long getAlarmTriggerAtMilliSecondFromMin(long millisStart, int minPiorAlarm) {
        long minPiorAlarmAtMillis = minPiorAlarm * 60000L;
        //Log.d(TAG,minPiorAlarmAtMillis +" | " + (startCalendar.getTimeInMillis() - minPiorAlarmAtMillis) );
        return millisStart - minPiorAlarmAtMillis;
    }
    private int getAlarmTriggerAtMinFromMillis(long millisStart,long millisPiorAlarm){
        return (int) (millisStart - millisPiorAlarm) / 60000;
    }

    private void setAlarmService(String eventName, long triggerAtMillis) {
        AlarmManager alarmManager = (AlarmManager) getSystemService(Context.ALARM_SERVICE);
        Intent intent = new Intent(EventCreationActivity.this, AlarmReceiver.class);
        String alertContent = String.format(getString(R.string.alarm_content), minPiorAlarm, eventName);
        intent.putExtra("ALERT_TITLE", "Event Reminder");
        intent.putExtra("ALERT_CONTENT", alertContent);
        PendingIntent pendingIntent = PendingIntent.getBroadcast(EventCreationActivity.this, ALARM_BROADCAST_REQUEST_CODE, intent, 0);
        alarmManager.set(AlarmManager.RTC_WAKEUP, triggerAtMillis, pendingIntent);

    }

//    private void updateOnReturnEditEvent(Event e, String EventTitle, String description, Calendar start) {
//        e.setTitle(EventTitle);
//        e.setDescription(description);
//        e.setStart(start);
//    }

    private void setAlarmInfo() {
        String alarm_info = String.format(String.valueOf(res.getString(R.string.alarm_reminder_before_2)), minPiorAlarm);
        reminder_info.setText(alarm_info);
    }

    private void returnResult() {
        Intent i = new Intent(EventCreationActivity.this, ScrollingActivity.class);
        // i.putExtra("RETURN_DATA", returnEvent);
        setResult(RESULT_OK, i);
        finish();
    }

    private Bundle getOnReturnEditEventId() {
        return getIntent().getBundleExtra("EVENT_UNIQUE");
    }





}
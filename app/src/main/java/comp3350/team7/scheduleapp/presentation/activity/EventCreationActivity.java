package comp3350.team7.scheduleapp.presentation.activity;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
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
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.widget.SwitchCompat;

import java.util.Calendar;

import comp3350.team7.scheduleapp.R;
import comp3350.team7.scheduleapp.application.DbClient;
import comp3350.team7.scheduleapp.application.UserClient;
import comp3350.team7.scheduleapp.application.ultil.DbHelper;
import comp3350.team7.scheduleapp.logic.AlarmController;
import comp3350.team7.scheduleapp.logic.EventController;
import comp3350.team7.scheduleapp.logic.TimeController;
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
    EditText datePickerText, eventNameText, timePickerText, description;
    Button saveButton, alarmSettingButton;
    TextView reminder_info;
    SwitchCompat sw;

    int minPiorAlarm = 5;  // default
    int[] minPiorAlarmArray;
    Calendar tempCalendar, startCalendar, alarmCalendar;
    EventController eventController;
    Resources res;
    Event onReturnEditEvent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.event_creation_activity);
        getView();
        init();
        onClickListenerHelper();

    }

    private void init() {
        if (DbClient.getDBPathName() == null) {
            DbHelper.copyDatabaseToDevice(this);
        }
        EventPersistenceInterface eventPersistence = DbClient.getInstance().getEventPersistence(); // get event database
        eventController = new EventController(eventPersistence); // inject db to event controller
        res = getResources(); // get application resource
        Bundle bundle = getBundleFromOnReturnEditEventId(); // get bundle for event returns from edit action
        onReturnEditEvent = getEventFromOnReturnEditEvent(bundle); // get event returns from edit action
        if (onReturnEditEvent != null) {
            startCalendar = onReturnEditEvent.getEventStart();
            alarmCalendar = onReturnEditEvent.getAlarm();
            setView(onReturnEditEvent); // set view according for the return event
            Log.d(TAG, "Finish Init For Edit Event");
        } else {
            startCalendar = Calendar.getInstance();
            Log.d(TAG,"Finish Init For New Event");
        }

        setAlarmInfo(minPiorAlarm);


    }

    public Event getEventFromOnReturnEditEvent(Bundle bundle){
        Event retEvent = null;
        if(bundle!=null){
            String userId = bundle.getString("EVENT_USER_ID");
            int eventId = bundle.getInt("EVENT_ID");
            retEvent = getEvent(userId, eventId);
        }
        return retEvent;


    }
    public Event getEvent(String userId, int eventId){
        Event retEvent = null;
        try {
            retEvent = eventController.getEvent(userId,eventId);
        } catch (EventControllerException e) {
            Log.d(TAG, "Caused by : " + e.getCause());
            onError(e.getMessage());
        }
        return retEvent;
    }

    private void getView() {
        eventNameText = findViewById(R.id.event_name_text);
        datePickerText = findViewById(R.id.date_picker_text);
        timePickerText = findViewById(R.id.time_picker_text);
        description = findViewById(R.id.description);
        //setImage = (Button) findViewById(R.id.setImage);
        saveButton = findViewById(R.id.save_event_button);
        sw = findViewById(R.id.switch1);
        alarmSettingButton = findViewById(R.id.reminder);
        reminder_info = findViewById(R.id.reminder_info);

    }

    private void setView(Event event) {
        eventNameText.setText(event.getTitle());
        description.setText(event.getDescription());
        String timeString = TimeController.timeFormatHelper(event.getEventStart());
        String dateString = TimeController.dateFormatHelper(event.getEventStart());
        timePickerText.setText(timeString);
        datePickerText.setText(dateString);
        Calendar alarm = event.getAlarm();
        if (alarm != null) {
            sw.setChecked(true);
            Log.d(TAG, "switch change state");
            minPiorAlarm = AlarmController.getAlarmTriggerAtMinFromMillis(event.getEventStart().getTimeInMillis(), alarm.getTimeInMillis());
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
                        String dateFormat = TimeController.dateFormatHelper(startCalendar);
                        datePickerText.setText(dateFormat);
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
                        String timeFormat = TimeController.timeFormatHelper(startCalendar);
                        timePickerText.setText(timeFormat);
                    }

                }, hour, minute, true);
                timePicker.show();
            }
        });

        // alarm setting
        alarmSettingButton.setOnClickListener(new View.OnClickListener() {
            final String[] tm = res.getStringArray(R.array.min_prior_alarm_array_string);
            @Override
            public void onClick(View v) {
                new AlertDialog.Builder(EventCreationActivity.this)
                        .setTitle("Pick a time prior event start")
                        .setIcon(R.drawable.alarm_add)
                        .setItems(tm, new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                minPiorAlarm = res.getIntArray(R.array.min_prior_alarm_array_int)[which];
                                setAlarmInfo(minPiorAlarm);
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
                String eventTitle = eventNameText.getText().toString().trim();
                String eventDescription = description.getText().toString().trim();
                long triggerAtMillis = AlarmController.getAlarmTriggerAtMilliSecondFromMin(startCalendar.getTimeInMillis(), minPiorAlarm);
                alarmCalendar = Calendar.getInstance();
                alarmCalendar.setTimeInMillis(triggerAtMillis);
                Log.d(TAG, alarmCalendar.getTime().toString());

                Event newEvent = null;
                try {
                    if (sw.isChecked()) {
                        newEvent = eventController.buildEvent(UserClient.getUserId(), eventTitle, eventDescription, startCalendar, null, alarmCalendar);
                        AlarmController.setAlarmService(getApplicationContext(), eventTitle, triggerAtMillis);
                    } else
                        newEvent = eventController.buildEvent(UserClient.getUserId(), eventTitle, eventDescription, startCalendar, null);
                } catch (EventControllerException e) {
                    Log.d(TAG, "Caused by " + e.getCause());
                    onError(e.getMessage());
                }

                if (newEvent != null) {
                    if (onReturnEditEvent != null) {
                        try {
                            onReturnEditEvent = eventController.updateEvent(onReturnEditEvent, newEvent); // edited event
                            onReturnEditEvent = null;  // clean up
                            returnResult();
                            Log.d(TAG, "Event Update Successfully and Saved");
                            Toast.makeText(EventCreationActivity.this, "Event Update Successfully", Toast.LENGTH_LONG).show();

                        } catch (EventControllerException e) {
                            e.printStackTrace();
                        }
                    } else {
                        try {
                            eventController.addEvent(newEvent); // created event
                            returnResult();
                            Log.d(TAG, "Event Created Successfully and Saved");

                            Toast.makeText(EventCreationActivity.this, "Event Created Successfully", Toast.LENGTH_LONG).show();
                        } catch (EventControllerException e) {
                            Log.d(TAG, "Caused by: " + e.getCause());
                            onError(e.getMessage());
                        }
                    }

                }

            }
        });
    }

    private void setAlarmInfo(int minPiorAlarm) {
        String alarm_info = String.format(String.valueOf(res.getString(R.string.alarm_reminder_before_2)), minPiorAlarm);
        reminder_info.setText(alarm_info);
    }


    private void returnResult() {

        Intent i = new Intent(EventCreationActivity.this, ScrollingActivity.class);
        // i.putExtra("RETURN_DATA", returnEvent);
//        setResult(RESULT_OK, i);
        this.startActivity(i);
    }

    private Bundle getBundleFromOnReturnEditEventId() {
        return getIntent().getBundleExtra("EVENT_UNIQUE");
    }

}
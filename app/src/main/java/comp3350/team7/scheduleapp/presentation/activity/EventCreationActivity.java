package comp3350.team7.scheduleapp.presentation.activity;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TimePicker;

import java.util.Calendar;

import comp3350.team7.scheduleapp.R;
import comp3350.team7.scheduleapp.application.DbServiceProvider;
import comp3350.team7.scheduleapp.application.UserClient;
import comp3350.team7.scheduleapp.logic.EventController;
import comp3350.team7.scheduleapp.logic.EventValidator;
import comp3350.team7.scheduleapp.logic.exceptions.DbErrorException;
import comp3350.team7.scheduleapp.logic.exceptions.EventControllerException;
import comp3350.team7.scheduleapp.logic.exceptions.InvalidEventException;
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
    Calendar calendar;
    Calendar ourCalendar;
    EventController eventController;
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
    }

    private void getView(){
        eventNameText = (EditText) findViewById(R.id.event_name_text);
        datePickerText = (EditText) findViewById(R.id.date_picker_text);
        timePickerText = (EditText) findViewById(R.id.time_picker_text);
        saveButton = (Button) findViewById(R.id.save_event_button);
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
                        datePickerText.setText(String.format("%d/%d/%d", dayOfMonth, month + 1, year));

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
                        timePickerText.setText(String.format("%d:%d", hourOfDay, minuteOfSecond));
                    }


                }, hour, minute, true);
                timePicker.show();
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
                    returnResult();
                    Log.d(TAG, "Saved");
                } catch (EventControllerException e) {
                    Log.d(TAG,"Caused by: " + e.getCause());
                    onError(e.getMessage());
                    e.printStackTrace();
                }
            }
        });
    }

    private void returnResult() {
        Intent i = new Intent(EventCreationActivity.this,ScrollingActivity.class);
       // i.putExtra("RETURN_DATA", returnEvent);
        setResult(RESULT_OK, i);
        finish();
    }


}
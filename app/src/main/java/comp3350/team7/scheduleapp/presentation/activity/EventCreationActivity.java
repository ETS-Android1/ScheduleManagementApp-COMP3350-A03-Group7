package comp3350.team7.scheduleapp.presentation.activity;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.Intent;
import android.nfc.Tag;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TimePicker;

import androidx.appcompat.app.AppCompatActivity;

import java.util.Calendar;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import comp3350.team7.scheduleapp.R;
import comp3350.team7.scheduleapp.logic.EventValidator;
import comp3350.team7.scheduleapp.logic.exceptions.InvalidEventException;
import comp3350.team7.scheduleapp.logic.logTag.TAG;
import comp3350.team7.scheduleapp.objects.Event;
import comp3350.team7.scheduleapp.presentation.fragment.InvalidInputDialogFragment;

/*
 * Created By Thai Tran on 23 February,2021
 *
 */

public class EventCreationActivity extends AppCompatActivity {
    DatePickerDialog datePicker;
    TimePickerDialog timePicker;
    EditText datePickerText;
    EditText eventNameText;
    EditText timePickerText;
    Button saveButton;
    Calendar calendar;
    Calendar ourCalendar;
    boolean isDateValid = false;
    boolean isTimeValid = false;
    boolean isEventNameValid = false;
    boolean isDateSetOnSameDay= false;
    final int maxTitleLength = 60;
    final int maxDescriptionLength =120;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.event_creation_activity);

        eventNameText = (EditText) findViewById(R.id.event_name_text);

        datePickerText = (EditText) findViewById(R.id.date_picker_text);
        timePickerText = (EditText) findViewById(R.id.time_picker_text);
        saveButton = (Button) findViewById(R.id.save_event_button);

        ourCalendar = Calendar.getInstance();

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

                        if (yearOfDecade >= year && monthOfYear >= month && dayOfMonth >= day) {
                            if(yearOfDecade == year && monthOfYear == month && dayOfMonth ==day)
                                isDateSetOnSameDay= true;
                            ourCalendar.set(year, month + 1, dayOfMonth);
                            datePickerText.setText(String.format("%d/%d/%d", dayOfMonth, month + 1, year));
                            isDateValid = true;
                        } else {
                            InvalidInputDialogFragment invalid_date = new InvalidInputDialogFragment("Invalid Date Input");
                            invalid_date.show(getSupportFragmentManager(), "date input");
                        }
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
                        if(isDateValid){
                            if (!isDateSetOnSameDay || hourOfDay >= hour && minuteOfSecond >= minute) {
                                ourCalendar.set(Calendar.HOUR_OF_DAY, hourOfDay);
                                ourCalendar.set(Calendar.MINUTE, minute);
                                timePickerText.setText(String.format("%d:%d", hourOfDay, minuteOfSecond));
                                isTimeValid = true;
                            }
                            else{
                                InvalidInputDialogFragment invalid_time = new InvalidInputDialogFragment("Invalid Time Input\nplease set " +
                                        "a valid future time");
                                invalid_time.show(getSupportFragmentManager(), "time input");
                                timePickerText.setText("");
                            }
                        }else {
                            InvalidInputDialogFragment setDateFirst = new InvalidInputDialogFragment("Please Set Date First");
                            setDateFirst.show(getSupportFragmentManager(),"set date first");
                        }

                    }


                }, hour, minute, true);
                timePicker.show();
            }
        });

        // Save button listener
        saveButton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                if (isValidateBeforeSave()){

                    returnResult();
                    Log.d(TAG.CreateEventActivity.toString(),"Clicked");
                }
            }
        });
    }

    private boolean isValidateBeforeSave() {
        String eventTitle = eventNameText.getText().toString();
        int titleLength = eventTitle.length();
        if (!EventValidator.validateEventName(eventTitle)) {
            InvalidInputDialogFragment invalidEventName = new InvalidInputDialogFragment("Invalid Event Name" +
                    "\nOnly accept any combination of Word character,number and white space");
            invalidEventName.show(getSupportFragmentManager(), "event name");
        } else if(titleLength <4 || titleLength > maxTitleLength ) {
            InvalidInputDialogFragment invalidEventName = new InvalidInputDialogFragment("Invalid Event Name" +
                    "\nshould have at least 5 characters and no more than 60 characters");
            invalidEventName.show(getSupportFragmentManager(), "title length");
        }else{
            isEventNameValid = true;
        }
        return isDateValid && isTimeValid && isEventNameValid;
    }


    private void returnResult() {
        Event newUserEvent = new Event(eventNameText.getText().toString(), "description", ourCalendar);
        Intent i = new Intent(EventCreationActivity.this,ScrollingActivity.class);
        i.putExtra("RETURN_DATA", newUserEvent);


        setResult(RESULT_OK, i);
        finish();
    }
}
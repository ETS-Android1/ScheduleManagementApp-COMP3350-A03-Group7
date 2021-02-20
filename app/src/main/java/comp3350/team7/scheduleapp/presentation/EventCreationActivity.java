package comp3350.team7.scheduleapp.presentation;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.KeyEvent;
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

/*
 * Created By Thai Tran on 20/02/21
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
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.event_creation_activity);

        eventNameText = (EditText) findViewById(R.id.event_name_text);
        datePickerText = (EditText) findViewById(R.id.date_picker_text);
        timePickerText = (EditText) findViewById(R.id.time_picker_text);
        saveButton = (Button) findViewById(R.id.save_event_button);

        ourCalendar = Calendar.getInstance();

        // Event name input listener
//        eventNameText.setOnKeyListener(new View.OnKeyListener() {
//            public boolean onKey(View v, int keyCode, KeyEvent event) {
//                if ((event.getAction() == KeyEvent.ACTION_DOWN) &&
//                        (keyCode == KeyEvent.KEYCODE_ENTER)) {
//                    // Perform action on key press
//                    Pattern pattern = Pattern.compile("(\\w)+");
//                    Matcher matcher = pattern.matcher(eventNameText.getText().toString());
//                    if (!matcher.matches()) {
//                        InvalidInputDialogFragment invalidEventName = new InvalidInputDialogFragment("Invalid Event Name ");
//                        invalidEventName.show(getSupportFragmentManager(), "event name");
//                    } else {
//                        isEventNameValid = true;
//                    }
//                    return true;
//                }
//                return false;
//            }
//        });

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
                    System.out.println("Clicked");
                }
            }
        });
    }

    private boolean isValidateBeforeSave() {
        Pattern pattern = Pattern.compile("(\\w)+(\\s)*");
        Matcher matcher = pattern.matcher(eventNameText.getText().toString());
        if (!matcher.matches()) {
            InvalidInputDialogFragment invalidEventName = new InvalidInputDialogFragment("Invalid Event Name ");
            invalidEventName.show(getSupportFragmentManager(), "event name");
        } else {
            isEventNameValid = true;
        }
        return isDateValid && isTimeValid && isEventNameValid;
    }


    private void returnResult() {
        Event newUserEvent = new Event(eventNameText.getText().toString(), "description", ourCalendar, null);
        Intent i = new Intent();
        i.putExtra("RETURN_DATA", newUserEvent);


        setResult(RESULT_OK, i);
        finish();
    }
}
package comp3350.team7.scheduleapp.presentation;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TimePicker;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import java.util.Calendar;

import comp3350.team7.scheduleapp.R;

public class EventCreationActivity extends AppCompatActivity {
    DatePickerDialog datePicker;
    TimePickerDialog timePicker;
    EditText datePickerText;
    EditText eventNameText;
    EditText timePickerText;
    Calendar calendar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.event_creation_activity);
        Bundle bundle;
        if ((bundle = getIntent().getBundleExtra("BUNDLE")) != null) {
            String welcome = bundle.getString("WELCOME");
            eventNameText = (EditText) findViewById(R.id.event_name_text);
            eventNameText.setText(welcome);
            System.out.println("AAA");
        }else{
            System.out.println("BBB");
        }
        datePickerText= (EditText) findViewById(R.id.date_picker_text);
        datePickerText.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.N)
            @Override
            public void onClick(View v) {
                calendar = Calendar.getInstance();
                int day = calendar.get(Calendar.DAY_OF_MONTH);
                int month = calendar.get(Calendar.MONTH);
                int year = calendar.get(Calendar.YEAR);
                datePicker = new DatePickerDialog(EventCreationActivity.this, new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                        datePickerText.setText(dayOfMonth + "/" + (month+1)+"/"+year);


                    }
                },year,month,day);
                datePicker.show();
            }
        });

        timePickerText =(EditText) findViewById(R.id.time_picker_text);
        timePickerText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                calendar =Calendar.getInstance();
                int hour = calendar.get(Calendar.HOUR_OF_DAY);
                int minute = calendar.get(Calendar.MINUTE);

                timePicker = new TimePickerDialog(EventCreationActivity.this, new TimePickerDialog.OnTimeSetListener() {
                    @Override
                    public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
                       timePickerText.setText(hourOfDay+":"+minute);
                    }


                },hour,minute,true);
                timePicker.show();
            }
        });

    }
}
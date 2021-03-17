package comp3350.team7.scheduleapp.presentation.activity;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import android.app.DatePickerDialog;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;
import java.util.Calendar;
import comp3350.team7.scheduleapp.R;

/*
 * Created By Thai Tran on 16 March,2021
 *
 */

public class ScheduleViewActivity extends AppCompatActivity {

    Button viewSchedule;
    TextView selectedDate;
    ImageButton calender;
    private int mDay, mMonth, mYear;
    Calendar searchDate;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_schedule_view);

        calender = findViewById(R.id.datePicker);
        selectedDate = findViewById(R.id.seeSelectedDate);
        viewSchedule = findViewById(R.id.seeSchedule);

        calender.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.N)
            @Override
            public void onClick(View v) {
                final Calendar calendar=Calendar.getInstance();
                mDay = calendar.get(Calendar.DATE);
                mMonth = calendar.get(Calendar.MONTH);
                mYear = calendar.get(Calendar.YEAR);
                DatePickerDialog datePickerDialog=new DatePickerDialog(ScheduleView.this,
                        android.R.style.Theme_DeviceDefault_Dialog, new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker datePicker, int year, int month, int day) {
                        selectedDate.setText(day + "-" + month + "-" + year);
                    }
                },mYear,mMonth,mDay);
                datePickerDialog.show();
            }
        });
    }

    public void showPerDaySchedule(View v) {
        String selectedDate;
        boolean selectedDateEmpty = this.selectedDate.getText().toString().isEmpty();
        if (selectedDateEmpty){
            Toast.makeText(getApplicationContext(), "Please select a date", Toast.LENGTH_SHORT).show();
        }
        else {
            try {
                selectedDate = this.selectedDate.getText().toString().trim();
                Toast.makeText(getApplicationContext(),selectedDate, Toast.LENGTH_SHORT).show();
                searchDate.set(mYear, mMonth, mDay);
            }
            catch (Exception e) {
                Toast.makeText(getApplicationContext(), "Invalid fields", Toast.LENGTH_LONG).show();
            }
        }
    }
}

package comp3350.team7.scheduleapp.presentation.activity;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.RequiresApi;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.SimpleItemAnimator;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Objects;

import comp3350.team7.scheduleapp.R;
import comp3350.team7.scheduleapp.application.DbServiceProvider;
import comp3350.team7.scheduleapp.application.UserClient;
import comp3350.team7.scheduleapp.logic.EventController;
import comp3350.team7.scheduleapp.logic.comparators.EventStartAscendingComparator;
import comp3350.team7.scheduleapp.logic.comparators.EventStartDescendingComparator;
import comp3350.team7.scheduleapp.logic.exceptions.EventControllerException;
import comp3350.team7.scheduleapp.objects.Event;
import comp3350.team7.scheduleapp.persistence.EventPersistenceInterface;
import comp3350.team7.scheduleapp.presentation.UiHelper.ItemOffsetDecoration;
import comp3350.team7.scheduleapp.presentation.UiHelper.RecyclerViewOnItemtouchHelper;
import comp3350.team7.scheduleapp.presentation.adapter.RecyclerViewAdapter;
import comp3350.team7.scheduleapp.presentation.base.BaseActivity;



/*
 * Created By Thai Tran on 20/02/21
 *
 */

public class ScrollingActivity extends BaseActivity {
    private static final String TAG = "ScrollingActivity";
    private final static int REQUEST_CODE = 100;
    RecyclerView recyclerView;
    View scrollingLayout;
    View fba;
    RecyclerViewAdapter adapter;
    List<Event> eventList;
    EventController eventController;
    Button sortAsc,sortDesc;
    TextView datePickerText;
    DatePickerDialog datePicker;
    Calendar ourCalendar;
    @RequiresApi(api = Build.VERSION_CODES.N)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_scrolling);
        // Setting new Controller
        init();
        getView();
        onClickListenerHelper();
        initRecyclerView();
    }
    @RequiresApi(api = Build.VERSION_CODES.N)
    @Override
    protected void onResume() {
        super.onResume();
        Log.d(TAG,"onResume called, updated view");
        eventList= getEventList(eventController);
        UpdateView(adapter, eventList);
    }

    private void getView() {
        recyclerView = findViewById(R.id.recylerview);
        scrollingLayout = (View) findViewById(R.id.ScrollingLayout);
        fba = findViewById(R.id.include);
        sortAsc = findViewById(R.id.sortAsc);
        sortDesc = findViewById( R.id.sortDes);
        datePickerText = findViewById(R.id.date_picker_text_1);
    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    private void init() {
        EventPersistenceInterface eventPersistence  = DbServiceProvider.getInstance().getEventPersistence();
        eventController = new EventController(eventPersistence);
        eventList = getEventList(eventController);

        ourCalendar = Calendar.getInstance();


    }
    @RequiresApi(api = Build.VERSION_CODES.N)
    private List<Event> getEventList(EventController eventController){
        List<Event> list = new ArrayList<>();
        try{
            list = eventController.getEventList(UserClient.getUserId());
        }catch (EventControllerException err){
            Log.e(TAG,"Cause by: " + err.getCause());
            err.printStackTrace();
            onError(err.getMessage());
        }
        return list;
    }


    private void onClickListenerHelper() {
        fba.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                launchEditItent();
            }
        });

        // sort ascending
        sortAsc.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.N)
            @Override
            public void onClick(View v) {
                eventController.setSortStrategy(new EventStartAscendingComparator());
                List<Event> list= null;
                list = getEventList(eventController);
                UpdateView(adapter, list);
            }
        });

        // sort descending
        sortDesc.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.N)
            @Override
            public void onClick(View v) {
                eventController.setSortStrategy(new EventStartDescendingComparator());
                List<Event> list= null;
                list = getEventList(eventController);
                UpdateView(adapter, list);
            }
        });

        // pick date to see schedule on that date
        datePickerText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Calendar calendar = Calendar.getInstance();
                int day = calendar.get(Calendar.DAY_OF_MONTH);
                int month = calendar.get(Calendar.MONTH);
                int year = calendar.get(Calendar.YEAR);
                datePicker = new DatePickerDialog(ScrollingActivity.this, new DatePickerDialog.OnDateSetListener() {

                    @RequiresApi(api = Build.VERSION_CODES.N)
                    @Override
                    public void onDateSet(DatePicker view, int yearOfDecade, int monthOfYear, int dayOfMonth) {

                        ourCalendar.set(yearOfDecade, monthOfYear, dayOfMonth);
                        try{
                            List<Event> list;
                            list = eventController.getScheduleForUserOnDate(UserClient.getUserId(), ourCalendar);
                            //Log.d(TAG,list.get(0).getEventEnd().getTime().toString());
                            if(list!=null)
                                UpdateView(adapter,list);
                            if (list.size() ==0)
                                Toast.makeText(v.getContext(), "No event found on date " + ourCalendar.getTime().toString(), Toast.LENGTH_SHORT).show();

                        }catch(EventControllerException err){
                           Log.e(TAG,"Cause by: " + err.getCause());
                           onError(err.getMessage());
                        }
                        datePickerText.setText(String.format("%d/%d/%d", dayOfMonth, monthOfYear + 1, yearOfDecade));

                        }
                }, year, month, day);
                datePicker.show();
            }
        });
    }



    @RequiresApi(api = Build.VERSION_CODES.N)
    private void initRecyclerView() {
        adapter = new RecyclerViewAdapter(this);
        ItemOffsetDecoration itemDecoration = new ItemOffsetDecoration(this, R.dimen.item_offset);
        recyclerView.addItemDecoration(itemDecoration);
        ((SimpleItemAnimator) Objects.requireNonNull(recyclerView.getItemAnimator())).setSupportsChangeAnimations(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(adapter);

        // Setup onItemTouchHandler

        ItemTouchHelper.Callback callback = new RecyclerViewOnItemtouchHelper(adapter, scrollingLayout);
        ItemTouchHelper helper = new ItemTouchHelper(callback);
        helper.attachToRecyclerView(recyclerView);

    }

    private void launchEditItent() {
        Bundle bundle = new Bundle();
        bundle.putString("WELCOME", "Welcome to a new activity");
        Intent createEvent = new Intent(ScrollingActivity.this, EventCreationActivity.class);
        createEvent.putExtra("BUNDLE", bundle);
        startActivityForResult(createEvent, REQUEST_CODE);

    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (resultCode == RESULT_OK) {
            if (requestCode == REQUEST_CODE && data != null) {
                Log.d(TAG,"Got back from CreateEvent Activity");
                eventList =getEventList(eventController);
                UpdateView(adapter,eventList);

            }
        }
    }


    @RequiresApi(api = Build.VERSION_CODES.N)
    private void UpdateView(RecyclerViewAdapter a, List<Event> events) {
        a.setList(events);
        recyclerView.setAdapter(a); // redraw
        Log.d(TAG,"Updated View");
    }


}

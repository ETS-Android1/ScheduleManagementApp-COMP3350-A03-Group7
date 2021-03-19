package comp3350.team7.scheduleapp.presentation.activity;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.SimpleItemAnimator;

import java.util.List;

import comp3350.team7.scheduleapp.R;
import comp3350.team7.scheduleapp.logic.EventController;
import comp3350.team7.scheduleapp.logic.exceptions.InvalidEventException;
import comp3350.team7.scheduleapp.logic.logTag.TAG;
import comp3350.team7.scheduleapp.objects.Event;
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


    private void getView() {
        recyclerView = findViewById(R.id.recylerview);
        scrollingLayout = (View) findViewById(R.id.ScrollingLayout);
        fba = findViewById(R.id.include);
    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    private void init() {
        eventController = new EventController();
        eventList = eventController.getEventList();


    }

    private void onClickListenerHelper() {
        fba.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                launchEditItent();
            }
        });
    }


    @RequiresApi(api = Build.VERSION_CODES.N)
    private void initRecyclerView() {
        adapter = new RecyclerViewAdapter(this, eventController);
        ItemOffsetDecoration itemDecoration = new ItemOffsetDecoration(this, R.dimen.item_offset);
        recyclerView.addItemDecoration(itemDecoration);
        ((SimpleItemAnimator)recyclerView.getItemAnimator()).setSupportsChangeAnimations(false);
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
                Log.d(TAG,"Got result back from CreateEvent");
                Event returnEvent = data.getParcelableExtra("RETURN_DATA");
                addEventAndUpdateView(adapter, returnEvent);
                Toast.makeText(ScrollingActivity.this, "Event Object Received :" + returnEvent.getTitle()
                        , Toast.LENGTH_LONG).show();

            }
        }
    }


    @RequiresApi(api = Build.VERSION_CODES.N)
    private void addEventAndUpdateView(RecyclerViewAdapter a, Event e) {
        try {
            eventController.addEvent(e);
        } catch (InvalidEventException err) {
            Log.e(TAG,err.getMessage());
            err.printStackTrace();
        }
        // force redraw
        a.setList(eventController.getEventList());

        recyclerView.setAdapter(a);
    }
}

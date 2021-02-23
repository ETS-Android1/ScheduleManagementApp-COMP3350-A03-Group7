package comp3350.team7.scheduleapp.presentation.activity;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.View;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import comp3350.team7.scheduleapp.R;
import comp3350.team7.scheduleapp.objects.Event;
import comp3350.team7.scheduleapp.objects.RecyclerViewItem;
import comp3350.team7.scheduleapp.presentation.UiHelper.ItemOffsetDecoration;
import comp3350.team7.scheduleapp.presentation.adapter.RecyclerViewAdapter;
import comp3350.team7.scheduleapp.presentation.UiHelper.RecyclerViewOnItemtouchHelper;



/*
 * Created By Thai Tran on 20/02/21
 *
 */

public class ScrollingActivity extends AppCompatActivity {

    private final static int REQUEST_CODE = 100;

    /* View */
    RecyclerView recyclerView;
    View scrollingLayout;
    View fba;

    RecyclerViewAdapter adapter;
    List<Event> eventList;
    ArrayList<RecyclerViewItem> list;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_scrolling);

        getView();
        onClickListenerHelper();
        initRecyclerView();
    }

    private void onClickListenerHelper() {
        fba.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                launchEditItent();
            }
        });
    }

    private void getView() {
        recyclerView = findViewById(R.id.recylerview);
        scrollingLayout = (View) findViewById(R.id.ScrollingLayout);
        fba = findViewById(R.id.include);
    }

    private void initRecyclerView() {
//        list = new ArrayList<>();
//        list.add(new RecyclerViewItem("Appointment #1","4:30", R.drawable.ic_launcher_background,false));
//        list.add(new RecyclerViewItem("Appointment #2","4:30", R.drawable.ic_launcher_background,false));
//        list.add(new RecyclerViewItem("Appointment #3", "4:30",R.drawable.ic_launcher_background,false));
//        list.add(new RecyclerViewItem("Appointment #4", "4:30",R.drawable.ic_launcher_background,false));
//        list.add(new RecyclerViewItem("Appointment #5","4:30", R.drawable.ic_launcher_background,false));
//        list.add(new RecyclerViewItem("Appointment #6","4:30", R.drawable.ic_launcher_background,false));
//        list.add(new RecyclerViewItem("Appointment #6","4:30", R.drawable.ic_launcher_background,false));
//        list.add(new RecyclerViewItem("Appointment #6","4:30", R.drawable.ic_launcher_background,false));
//        list.add(new RecyclerViewItem("Appointment #6","4:30", R.drawable.ic_launcher_background,false));
//        list.add(new RecyclerViewItem("Appointment #6","4:30", R.drawable.ic_launcher_background,false));
//        list.add(new RecyclerViewItem("Appointment #6","4:30", R.drawable.ic_launcher_background,false));
//        list.add(new RecyclerViewItem("Appointment #6","4:30", R.drawable.ic_launcher_background,false));
//        list.add(new RecyclerViewItem("Appointment #6","4:30", R.drawable.ic_launcher_background,false));
//        list.add(new RecyclerViewItem("Appointment #6","4:30", R.drawable.ic_launcher_background,false));
//        list.add(new RecyclerViewItem("Appointment #6","4:30", R.drawable.ic_launcher_background,false));
//        list.add(new RecyclerViewItem("Appointment #6","4:30", R.drawable.ic_launcher_background,false));
//        list.add(new RecyclerViewItem("Appointment #6","4:30", R.drawable.ic_launcher_background,false));
//        list.add(new RecyclerViewItem("Appointment #6","4:30", R.drawable.ic_launcher_background,false));
//        list.add(new RecyclerViewItem("Appointment #6","4:30", R.drawable.ic_launcher_background,false));
//        list.add(new RecyclerViewItem("Appointment #6","4:30", R.drawable.ic_launcher_background,false));
//        list.add(new RecyclerViewItem("Appointment #6","4:30", R.drawable.ic_launcher_background,false));
        intitEventList();
        adapter = new RecyclerViewAdapter(this, (ArrayList<Event>) eventList);
        ItemOffsetDecoration itemDecoration = new ItemOffsetDecoration(this, R.dimen.item_offset);
        recyclerView.addItemDecoration(itemDecoration);
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
                Event returnEvent = data.getParcelableExtra("RETURN_DATA");
                addEvent(adapter,returnEvent);
                Toast.makeText(ScrollingActivity.this, "Event Object Received :" + returnEvent.getTitle()
                        , Toast.LENGTH_LONG).show();

            }
        }
    }

    private void intitEventList() {
        eventList = new ArrayList<Event>();
    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    private void addEvent(RecyclerViewAdapter a, Event e) {
        a.addAscending(e);
    }
}

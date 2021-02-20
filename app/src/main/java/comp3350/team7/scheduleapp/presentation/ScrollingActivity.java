package comp3350.team7.scheduleapp.presentation;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Adapter;
import android.widget.Toast;

import java.util.Comparator;
import java.util.LinkedList;
import java.util.ArrayList;
import java.util.List;

import comp3350.team7.scheduleapp.R;
import comp3350.team7.scheduleapp.objects.RecyclerViewItem;



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


//        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar_layout);
//        setSupportActionBar(toolbar);
//        CollapsingToolbarLayout toolBarLayout = (CollapsingToolbarLayout) findViewById(R.id.toolbar_layout);
//        toolBarLayout.setTitle(getTitle());

//        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
//        fab.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
//                        .setAction("Action", null).show();
//            }
//        });


        getView();
        onClickListenerHelper();
        initRecyclerView();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_scrolling, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
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

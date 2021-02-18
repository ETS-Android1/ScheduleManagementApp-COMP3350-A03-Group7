package comp3350.team7.scheduleapp.presentation;

import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.View;
import android.view.Menu;
import android.view.MenuItem;

import java.util.ArrayList;

import comp3350.team7.scheduleapp.R;
import comp3350.team7.scheduleapp.objects.RecyclerViewItem;

public class ScrollingActivity extends AppCompatActivity {
    RecyclerView recyclerView;
    ArrayList<RecyclerViewItem> list;
    RecyclerViewAdapter adapter;
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
        View fba = findViewById(R.id.include);
        fba.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                launchEditItent();
            }
        });
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

    private void initRecyclerView(){
        recyclerView = findViewById(R.id.recylerview);
        list = new ArrayList<>();
        list.add(new RecyclerViewItem("Appointment #1","4:30", R.drawable.ic_launcher_background,false));
        list.add(new RecyclerViewItem("Appointment #2","4:30", R.drawable.ic_launcher_background,false));
        list.add(new RecyclerViewItem("Appointment #3", "4:30",R.drawable.ic_launcher_background,false));
        list.add(new RecyclerViewItem("Appointment #4", "4:30",R.drawable.ic_launcher_background,false));
        list.add(new RecyclerViewItem("Appointment #5","4:30", R.drawable.ic_launcher_background,false));
        list.add(new RecyclerViewItem("Appointment #6","4:30", R.drawable.ic_launcher_background,false));
        list.add(new RecyclerViewItem("Appointment #6","4:30", R.drawable.ic_launcher_background,false));
        list.add(new RecyclerViewItem("Appointment #6","4:30", R.drawable.ic_launcher_background,false));
        list.add(new RecyclerViewItem("Appointment #6","4:30", R.drawable.ic_launcher_background,false));
        list.add(new RecyclerViewItem("Appointment #6","4:30", R.drawable.ic_launcher_background,false));
        list.add(new RecyclerViewItem("Appointment #6","4:30", R.drawable.ic_launcher_background,false));
        list.add(new RecyclerViewItem("Appointment #6","4:30", R.drawable.ic_launcher_background,false));
        list.add(new RecyclerViewItem("Appointment #6","4:30", R.drawable.ic_launcher_background,false));
        list.add(new RecyclerViewItem("Appointment #6","4:30", R.drawable.ic_launcher_background,false));
        list.add(new RecyclerViewItem("Appointment #6","4:30", R.drawable.ic_launcher_background,false));
        list.add(new RecyclerViewItem("Appointment #6","4:30", R.drawable.ic_launcher_background,false));
        list.add(new RecyclerViewItem("Appointment #6","4:30", R.drawable.ic_launcher_background,false));
        list.add(new RecyclerViewItem("Appointment #6","4:30", R.drawable.ic_launcher_background,false));
        list.add(new RecyclerViewItem("Appointment #6","4:30", R.drawable.ic_launcher_background,false));
        list.add(new RecyclerViewItem("Appointment #6","4:30", R.drawable.ic_launcher_background,false));
        list.add(new RecyclerViewItem("Appointment #6","4:30", R.drawable.ic_launcher_background,false));

        adapter = new RecyclerViewAdapter(this,list);
        ItemOffsetDecoration itemDecoration = new ItemOffsetDecoration(this, R.dimen.item_offset);
        recyclerView.addItemDecoration(itemDecoration);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(adapter);

        // Setup onItemTouchHandler
        ItemTouchHelper.Callback callback = new RecyclerViewOnItemtouchHelper(adapter,(View) findViewById(R.id.ScrollingLayout));
        ItemTouchHelper helper = new ItemTouchHelper(callback);
        helper.attachToRecyclerView(recyclerView);

    }

    private void launchEditItent(){
        Bundle bundle = new Bundle();
        bundle.putString("WELCOME","Welcome to a new activity");
        Intent createEvent = new Intent(ScrollingActivity.this, EventCreationActivity.class);
        createEvent.putExtra("BUNDLE",bundle);
        startActivityForResult(createEvent,100);

    }
}

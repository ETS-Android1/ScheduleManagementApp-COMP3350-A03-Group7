package comp3350.team7.scheduleapp.presentation;

import android.os.Bundle;

import com.google.android.material.appbar.CollapsingToolbarLayout;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.View;
import android.view.Menu;
import android.view.MenuItem;

import java.util.ArrayList;
import java.util.List;

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
//        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
//        setSupportActionBar(toolbar);
//        CollapsingToolbarLayout toolBarLayout = (CollapsingToolbarLayout) findViewById(R.id.toolbar_layout);
//        toolBarLayout.setTitle(getTitle());
//
//        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
//        fab.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
//                        .setAction("Action", null).show();
//            }
//        });

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

    public void initRecyclerView(){
        recyclerView = findViewById(R.id.recylerview);
        list = new ArrayList<>();
        for
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

    }
}

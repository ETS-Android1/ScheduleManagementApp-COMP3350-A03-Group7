package comp3350.team7.scheduleapp.presentation.adapter;

import android.content.Context;
import android.os.Build;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.RequiresApi;
import androidx.recyclerview.widget.RecyclerView;

import java.util.Collections;
import java.util.List;

import comp3350.team7.scheduleapp.R;
import comp3350.team7.scheduleapp.logic.EventController;
import comp3350.team7.scheduleapp.logic.exceptions.DbErrorException;
import comp3350.team7.scheduleapp.logic.exceptions.InvalidEventException;
import comp3350.team7.scheduleapp.logic.logTag.TAG;
import comp3350.team7.scheduleapp.objects.Event;

/*
 * Created By Thai Tran on 23 February,2021
 *
 */

public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private EventController eventController;
    private Context context;
    private List<Event> list;
    @RequiresApi(api = Build.VERSION_CODES.N)
    public RecyclerViewAdapter(Context context, EventController eventController) {
        this.context = context;
        this.eventController = eventController;
        this.list = eventController.getEventList();
    }


    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v;
        v = LayoutInflater.from(parent.getContext()).inflate(R.layout.view_holder, parent, false);
        return new MyViewHolder(v);
    }

    // bind view holder with a given position in RecyclerView
    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, final int position) {
        Event entity = list.get(position);
        if (holder instanceof MyViewHolder) {
            ((MyViewHolder) holder).textView3.setText(entity.getTitle());
            ((MyViewHolder) holder).textView4.setText(entity.getEventStartToString());
        }
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public Event getItem(int adaptivePos) {
        return this.list.get(adaptivePos);
    }

    public void setList(List<Event> e){
        this.list = e;
    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    public void remove(int position)  {
//        Event deletedItem = list.remove(position);
//        // NOTE: don't call notifyDataSetChanged(
//        //       It will cancel any animation
        try {
            eventController.removeEvent(position);
        } catch (DbErrorException e) {
            // always catch and log
            // we know where the bom init
            Log.e(TAG.RecyclerViewAdapter.toString(),"Err: in remove "+ e.getMessage());
            e.printStackTrace();
        }
        notifyItemRemoved(position);
    }

    public void swap(int firstPosition, int secondPosition) {
        Collections.swap(list, firstPosition, secondPosition);
        notifyItemMoved(firstPosition, secondPosition);
    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    public void undo(Event deletedItem, int position) {
        list.add(position, deletedItem);
        // notify item added by position
        try {
            eventController.addEvent(deletedItem);
        } catch (InvalidEventException e) {
            // always catch and log
            // we know where the bom init
            Log.e(TAG.RecyclerViewAdapter.toString(),"Err: in Undo "+ e.getMessage());
            e.printStackTrace();
        }
        notifyItemInserted(position);
    }

public class MyViewHolder extends RecyclerView.ViewHolder {
    public TextView textView3;
    public TextView textView4;
    public View holder, foreGround, backGround;

    public MyViewHolder(View itemView) {
        super(itemView);
        textView3 = itemView.findViewById(R.id.textView3);
        textView4 = itemView.findViewById(R.id.textView4);
        foreGround = itemView.findViewById(R.id.foreGround);
        backGround = itemView.findViewById(R.id.backGround);
        holder = itemView.findViewById(R.id.holder);
    }

}
}

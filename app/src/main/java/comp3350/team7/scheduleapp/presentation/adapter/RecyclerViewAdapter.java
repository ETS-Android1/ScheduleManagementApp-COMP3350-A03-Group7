package comp3350.team7.scheduleapp.presentation.adapter;

import android.content.Context;
import android.os.Build;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.RequiresApi;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

import comp3350.team7.scheduleapp.R;
import comp3350.team7.scheduleapp.objects.Event;

/*
 * Created By Thai Tran on 23 February,2021
 *
 */

public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private ArrayList<Event> list;

    private Context context;

    public class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView textView3;
        public TextView textView4;
        public View holder,foreGround, backGround;

        public MyViewHolder(View itemView) {
            super(itemView);
            textView3 = itemView.findViewById(R.id.textView3);
            textView4 = itemView.findViewById(R.id.textView4);
            foreGround =itemView.findViewById(R.id.foreGround);
            backGround =itemView.findViewById(R.id.backGround);
            holder = itemView.findViewById(R.id.holder);
        }

    }
    public RecyclerViewAdapter(Context context, ArrayList<Event> eventList) {
        this.list = eventList;
        this.context = context;
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
            ((MyViewHolder) holder).textView4.setText(entity.geteventStart());
        }
    }

    @Override
    public int getItemCount() {
        return list.size();
    }
    public Event getItem(int adaptivePos){
        return this.list.get(adaptivePos);
    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    public void addAscending(Event e){
        if (list != null) {
            list.add(e);
            list.sort(new Comparator<Event>() {
                @Override
                public int compare(Event e, Event r) {
                    return e.geteventStart().compareTo(r.geteventStart());
                }
            });
        }
        notifyDataSetChanged();
    }


    public void remove(int position) {
        list.remove(position);
        // NOTE: don't call notifyDataSetChanged(
        //       It will cancel any animation
        notifyItemRemoved(position);
    }

    public void swap(int firstPosition, int secondPosition) {
        Collections.swap(list, firstPosition, secondPosition);
        notifyItemMoved(firstPosition, secondPosition);
    }

    public void undo(Event item, int position) {
        list.add(position, item);
        // notify item added by position
        notifyItemInserted(position);
    }
}

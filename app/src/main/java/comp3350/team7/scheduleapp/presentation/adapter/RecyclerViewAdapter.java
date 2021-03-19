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
import java.util.HashSet;
import java.util.List;

import comp3350.team7.scheduleapp.R;
import comp3350.team7.scheduleapp.application.UserClient;
import comp3350.team7.scheduleapp.logic.EventController;
import comp3350.team7.scheduleapp.logic.exceptions.EventControllerException;
import comp3350.team7.scheduleapp.objects.Event;

/*
 * Created By Thai Tran on 23 February,2021
 *
 */

public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.MyViewHolder> {
    private static final String TAG = "RecyclerViewAdapter";
    private EventController eventController;
    private Context context;
    private List<Event> list;
    private HashSet<Integer> expandViewHolderPositionSet;

    @RequiresApi(api = Build.VERSION_CODES.N)
    public RecyclerViewAdapter(Context context, EventController eventController) {
        this.context = context;
        this.eventController = eventController;
        /* TODO: 2021-03-15
         *  inject eventController
         */
        try{
            this.list = eventController.getEventList(UserClient.getUserId());
        }catch (EventControllerException err) {
            Log.e(TAG,"Developer attention, internal error" );
            Log.d(TAG,"Cause: " + err.getCause());
            err.printStackTrace();
        } {

        }
        this.expandViewHolderPositionSet = new HashSet<>();

    }


    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v;
        v = LayoutInflater.from(parent.getContext()).inflate(R.layout.view_holder_event, parent, false);
        return new MyViewHolder(v);
    }


    // bind view holder with a given position in RecyclerView
    @Override
    public void onBindViewHolder(MyViewHolder holder, final int position) {
        Event event = list.get(position);
        holder.bind(position);
        if (holder instanceof MyViewHolder) {
            holder.textView3.setText(event.getTitle());
            holder.textView4.setText(event.getEventStartToString());
            holder.description.setText(event.getDescription());
            /* TODO: 2021-03-15
             * implement remind time in event class
             *
             */
            /*((MyViewHolder) holder).remindMe.setText(event.getRemindTime()); */

        }
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (isViewHolderExpanded(position)) {
                    addExpandViewHolderPostion(position);
                } else {
                    removeExpandViewHolderPosition(position);
                }
                notifyItemChanged(position);
            }
        });
    }

    private boolean isViewHolderExpanded(int position) {
        return expandViewHolderPositionSet.contains(position);
    }

    private void addExpandViewHolderPostion(int position) {
        expandViewHolderPositionSet.add(position);
    }

    private void removeExpandViewHolderPosition(int position) {
        expandViewHolderPositionSet.remove(position);
    }

    @Override
    public int getItemViewType(int position) {
        return position % 2;
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public Event getItem(int adaptivePos) {
        return this.list.get(adaptivePos);
    }

    public void setList(List<Event> e) {
        this.list = e;
    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    public void remove(int position) {
//        Event deletedItem = list.remove(position);
//        // NOTE: don't call notifyDataSetChanged(
//        //       It will cancel any animation
        Event event = list.get(position);
        try {
            eventController.removeEvent(event);
        } catch (EventControllerException e) {
            // always catch and log
            // we know where the bom init
            Log.e(TAG, "Err: in remove " + e.getMessage());
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
        } catch (EventControllerException e) {
            // always catch and log
            // we know where the bom init
            Log.e(TAG, "Err: in Undo " + e.getMessage());
            e.printStackTrace();
        }
        notifyItemInserted(position);
    }

    public void click(Event clickedEvent, int position) {

    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView textView3;
        public TextView textView4;
        public TextView description;
        public TextView remindMe;
        public View holder, foreGround, backGround, expandView;

        public MyViewHolder(View itemView) {
            super(itemView);
            textView3 = itemView.findViewById(R.id.textView3);
            textView4 = itemView.findViewById(R.id.textView4);
            foreGround = itemView.findViewById(R.id.foreGround);
            backGround = itemView.findViewById(R.id.backGround);
            holder = itemView.findViewById(R.id.holder);
            expandView = itemView.findViewById(R.id.expand_event_view);
            description = itemView.findViewById(R.id.event_description);
            remindMe = itemView.findViewById(R.id.remind_me);

        }

        private void bind(int position) {
            expandView.setVisibility(isViewHolderExpanded(position) ? View.VISIBLE : View.GONE);

        }

    }
}

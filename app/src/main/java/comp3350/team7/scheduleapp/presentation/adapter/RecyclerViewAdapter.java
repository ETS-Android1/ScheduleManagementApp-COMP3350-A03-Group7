package comp3350.team7.scheduleapp.presentation.adapter;

import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.RequiresApi;
import androidx.recyclerview.widget.RecyclerView;

import java.util.Calendar;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;

import comp3350.team7.scheduleapp.R;
import comp3350.team7.scheduleapp.application.DbClient;
import comp3350.team7.scheduleapp.application.UserClient;
import comp3350.team7.scheduleapp.logic.EventController;
import comp3350.team7.scheduleapp.logic.TimeController;
import comp3350.team7.scheduleapp.logic.exceptions.EventControllerException;
import comp3350.team7.scheduleapp.objects.Event;
import comp3350.team7.scheduleapp.persistence.EventPersistenceInterface;
import comp3350.team7.scheduleapp.presentation.activity.EventCreationActivity;

/*
 * Created By Thai Tran on 23 February,2021
 *
 */

public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.MyViewHolder> {
    private static final String TAG = "RecyclerViewAdapter";
    private static HashSet<Integer> expandViewHolderPositionSet;
    private final Context context;
    private EventController eventController;
    private List<Event> list;

    @RequiresApi(api = Build.VERSION_CODES.N)
    public RecyclerViewAdapter(Context context) {
        this.context = context;
        init();

    }

    private static boolean isViewHolderExpanded(int position) {
        return expandViewHolderPositionSet.contains(position);
    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    private void init() {
        EventPersistenceInterface eventPersistence = DbClient.getInstance().getEventPersistence();
        eventController = new EventController(eventPersistence);
        setAdapterList(getEventListFromController(eventController));
        expandViewHolderPositionSet = new HashSet<>();
    }

    private void setAdapterList(List<Event> list) {
        this.list = list;
    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    private List<Event> getEventListFromController(EventController eventController) {
        List<Event> list = null;
        try {
            list = eventController.getEventList(UserClient.getUserId());
        } catch (EventControllerException err) {
            Log.e(TAG, "Developer attention, internal error");
            Log.d(TAG, "Caused by: " + err.getCause());
            err.printStackTrace();
        }
        return list;

    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v;
        v = LayoutInflater.from(parent.getContext()).inflate(R.layout.view_holder_event, parent, false);
        //MyViewHolder holder = new MyViewHolder(v, new )
        MyViewHolder holder = new MyViewHolder(v, new MyViewHolder.MyClickListener() {
            @Override
            public void onEdit(int position) {
                Log.d(TAG, "onEdit clicked");
                Event onEditEvent = list.get(position);
                Intent intent = new Intent(context, EventCreationActivity.class);
                Bundle bundle = new Bundle();
                bundle.putString("EVENT_USER_ID", onEditEvent.getUserName());
                bundle.putInt("EVENT_ID", onEditEvent.getID());
                intent.putExtra("EVENT_UNIQUE", bundle);
                //intent.putExtra("EVENT_ID", onEditEvent.getID());
                //intent.putExtra("EVENT_USER_ID", onEditEvent.getUserName());
                //Bundle bundle = new Bundle();
                //bundle.putParcelable("EVENT_DATA", onEditEvent);
                //context.startActivity(intent,bundle);
                context.startActivity(intent);
            }

            @Override
            public void onClick(int position) {
                if (isViewHolderExpanded(position)) {
                    removeExpandViewHolderPosition(position);
                } else {
                    addExpandViewHolderPostion(position);
                }
                Toast.makeText(v.getContext(), "event clicked", Toast.LENGTH_SHORT).show();
                notifyDataSetChanged();
            }
//            @Override
//            public void onDelete(int position) {
//                Log.d(TAG,"onDelete clicked");
//            }
        });
        return holder;

    }

    // bind view holder with a given position in RecyclerView
    @Override
    public void onBindViewHolder(MyViewHolder holder, final int position) {
        Event event = list.get(position);
        holder.bind(position);
        holder.textView3.setText(event.getTitle());
        holder.textView4.setText(TimeController.dateTimeFormatHelper(event.getEventStart()));
        holder.description.setText(event.getDescription());
        Calendar alarm = event.getAlarm();
        if (alarm != null) {
            String dateString = TimeController.dateTimeFormatHelper(alarm);
            holder.remindMe.setText(dateString);
        } else {
            holder.remindMe.setText("No Alarm Set");
        }
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
            setAdapterList(getEventListFromController(eventController));
            notifyItemRemoved(position);
            Log.d(TAG, "Swipped Left, Event removed");
        } catch (EventControllerException e) {
            // always catch and log
            // we know where the bom init


            Log.e(TAG, "Error: in remove." + e.getMessage() + "\nCaused by: " + e.getCause());
            e.printStackTrace();
        }
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
            setAdapterList(getEventListFromController(eventController));
            notifyItemInserted(position);
            Log.d(TAG, "Undo removed Event");
        } catch (EventControllerException e) {
            // always catch and log
            // we know where the bom init
            Log.e(TAG, "Error: in Undo." + e.getMessage() + "\nCaused by: " + e.getCause());

            e.printStackTrace();
        }
    }


    public static class MyViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        public TextView textView3;
        public TextView textView4;
        public TextView description;
        public TextView remindMe;
        public ImageView alarm_ic, edit_ic;
        public View holder, foreGround, backGround, expandView;
        MyClickListener listener;

        public MyViewHolder(View itemView, MyClickListener listener) {
            super(itemView);
            this.listener = listener;
            textView3 = itemView.findViewById(R.id.textView3);
            textView4 = itemView.findViewById(R.id.textView4);
            foreGround = itemView.findViewById(R.id.foreGround);
            backGround = itemView.findViewById(R.id.backGround);
            holder = itemView.findViewById(R.id.holder);
            expandView = itemView.findViewById(R.id.expand_event_view);
            description = itemView.findViewById(R.id.event_description);
            remindMe = itemView.findViewById(R.id.remind_me);
            alarm_ic = itemView.findViewById(R.id.alarm_ic);
            edit_ic = itemView.findViewById(R.id.edit_ic);

            alarm_ic.setOnClickListener(this);
            edit_ic.setOnClickListener(this);
            itemView.setOnClickListener(this);
        }

        private void bind(int position) {
            expandView.setVisibility(isViewHolderExpanded(position) ? View.VISIBLE : View.GONE);
        }

        @Override
        public void onClick(View v) {
            switch (v.getId()) {
                case R.id.edit_ic:
                    listener.onEdit(this.getLayoutPosition());
                    break;
                default:
                    listener.onClick(this.getLayoutPosition());
                    break;


            }
        }

        public interface MyClickListener {
            void onEdit(int position);

            void onClick(int position);
        }


    }


}

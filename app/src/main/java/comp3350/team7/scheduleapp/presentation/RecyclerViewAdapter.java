package comp3350.team7.scheduleapp.presentation;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import comp3350.team7.scheduleapp.R;
import comp3350.team7.scheduleapp.objects.RecyclerViewItem;

public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    ArrayList<RecyclerViewItem> list;
    Context context;

    public RecyclerViewAdapter(Context context, ArrayList<RecyclerViewItem> eventList) {
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
        RecyclerViewItem entity = list.get(position);
        if (holder instanceof MyViewHolder) {
            ((MyViewHolder) holder).textView3.setText(entity.getTitle());
            ((MyViewHolder) holder).textView4.setText(entity.getTime());
//            ((MyViewHolder) holder).imageView.setImageDrawable(context.getResources().getDrawable(entity.getImage()));
        }
    }

    @Override
    public int getItemCount() {
        return list.size();
    }
    public void remove(int position) {
        list.remove(position);
        notifyItemRemoved(position);
    }

    public void swap(int firstPosition, int secondPosition) {
        Collections.swap(list, firstPosition, secondPosition);
        notifyItemMoved(firstPosition, secondPosition);
    }
    public class MyViewHolder extends RecyclerView.ViewHolder {
        TextView textView3;
        TextView textView4;


        public MyViewHolder(View itemView) {
            super(itemView);
            textView3 = itemView.findViewById(R.id.textView3);
            textView4 = itemView.findViewById(R.id.textView4);
//            imageView = itemView.findViewById(R.id.imageView);
//            container = itemView.findViewById(R.id.container);
        }
    }
}

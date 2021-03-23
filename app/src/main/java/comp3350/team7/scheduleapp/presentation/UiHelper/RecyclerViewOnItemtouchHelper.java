package comp3350.team7.scheduleapp.presentation.UiHelper;

import android.graphics.Canvas;
import android.graphics.Color;
import android.os.Build;
import android.util.Log;
import android.view.View;

import androidx.annotation.RequiresApi;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.snackbar.Snackbar;

import comp3350.team7.scheduleapp.objects.Event;
import comp3350.team7.scheduleapp.presentation.adapter.RecyclerViewAdapter;

/*
 * Created By Thai Tran on 22 February,2021
 *
 */

public class RecyclerViewOnItemtouchHelper extends ItemTouchHelper.SimpleCallback {
    private static final String TAG = "RecyclerViewOnItemtouch";
    private final RecyclerViewAdapter Adapter;
    private final View view;

    public RecyclerViewOnItemtouchHelper(RecyclerViewAdapter Adapter, View view) {
        super(0, ItemTouchHelper.LEFT);
        this.Adapter = Adapter;
        this.view = view;
    }

    @Override
    public void clearView(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder) {
        final View foregroundView = ((RecyclerViewAdapter.MyViewHolder) viewHolder).foreGround;
        getDefaultUIUtil().clearView(foregroundView);
    }

//    @Override
//    public void onChildDraw(Canvas c, RecyclerView recyclerView,
//                            RecyclerView.ViewHolder viewHolder, float dX, float dY,
//                            int actionState, boolean isCurrentlyActive) {
//        final View foregroundView = ((RecyclerViewAdapter.MyViewHolder) viewHolder).foreGround;
//
//        getDefaultUIUtil().onDraw(c, recyclerView, foregroundView, dX, dY,
//                actionState, isCurrentlyActive);
//    }

    @Override
    public int convertToAbsoluteDirection(int flags, int layoutDirection) {
        return super.convertToAbsoluteDirection(flags, layoutDirection);
    }

    @Override
    public boolean onMove(RecyclerView recyclerView, RecyclerView.ViewHolder dragged, RecyclerView.ViewHolder target) {
        Adapter.swap(dragged.getAdapterPosition(), target.getAdapterPosition());
        return true;
    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    @Override
    public void onSwiped(RecyclerView.ViewHolder swipped, int direction) {
        // backup of removed item for undo purpose
        final int viewHolderPosition = swipped.getAdapterPosition();
        final Event deletedItem = Adapter.getItem(viewHolderPosition);

        switch (direction) {
            case ItemTouchHelper.LEFT:
                //Remove item
                Adapter.remove(viewHolderPosition);
                Log.d(TAG, "Swipped Left, Event removed");
                break;
        }

        Snackbar snackbar = Snackbar
                .make(view, "Event removed!", Snackbar.LENGTH_LONG);
        snackbar.setAction("UNDO", new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // undo is selected, restore the deleted item
                Adapter.undo(deletedItem, viewHolderPosition);
            }
        });
        snackbar.setActionTextColor(Color.WHITE);
        snackbar.show();
    }


}
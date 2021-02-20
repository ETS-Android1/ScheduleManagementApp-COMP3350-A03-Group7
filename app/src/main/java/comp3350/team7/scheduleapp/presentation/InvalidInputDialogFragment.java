package comp3350.team7.scheduleapp.presentation;

import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.DialogFragment;
import comp3350.team7.scheduleapp.R;

public class InvalidInputDialogFragment extends DialogFragment {
    private String displayMessage;
    public InvalidInputDialogFragment(String message) {
        displayMessage = message;
    }

    public Dialog onCreateDialog(Bundle savedInstanceState) {
        // Use the Builder class for convenient dialog construction
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setMessage(displayMessage)
//                    .setPositiveButton(R.string.fire, new DialogInterface.OnClickListener() {
//                        public void onClick(DialogInterface dialog, int id) {
//                            // FIRE ZE MISSILES!
//                        }
//                    })
                .setNegativeButton(R.string.cancel, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        // User cancelled the dialog
                    }
                })

        ;
        // Create the AlertDialog object and return it
        return builder.create();
    }


}

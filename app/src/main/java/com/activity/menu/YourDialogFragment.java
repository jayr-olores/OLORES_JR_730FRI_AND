package com.activity.menu;

import android.app.Dialog;
import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.DialogFragment;

public class YourDialogFragment extends DialogFragment {

    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(requireActivity());
        builder.setTitle("Your Title")
                .setMessage("Your message goes here.")
                .setPositiveButton("OK", (dialog, id) -> {
                    // Handle OK button
                })
                .setNegativeButton("Cancel", (dialog, id) -> {
                    // Handle Cancel button
                });
        return builder.create();
    }
}


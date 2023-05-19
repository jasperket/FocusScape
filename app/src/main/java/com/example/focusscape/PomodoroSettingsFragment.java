package com.example.focusscape;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatDialogFragment;
import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

import com.google.android.material.dialog.MaterialAlertDialogBuilder;
import com.google.android.material.textfield.TextInputEditText;

public class PomodoroSettingsFragment extends DialogFragment {
    private TextInputEditText editTextWorkMins;
    private TextInputEditText editTextBreakMins;
    private SettingsDialogListener listener;
    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
        MaterialAlertDialogBuilder builder = new MaterialAlertDialogBuilder(getActivity());

        LayoutInflater inflater = getActivity().getLayoutInflater();
        View view = inflater.inflate(R.layout.fragment_pomodoro_settings,null);

        editTextWorkMins = view.findViewById(R.id.etxtWorkMinuteInputLayout);
        editTextBreakMins = view.findViewById(R.id.etxtBreakMinuteInputLayout);

        builder.setView(view)
                .setTitle("Set Duration")
                .setNegativeButton("cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {

                    }
                })
                .setPositiveButton("ok", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        int workMins = Integer.parseInt(editTextWorkMins.getText().toString());
                        long workDurationMillis = workMins * 60000L;

                        int breakMins = Integer.parseInt(editTextBreakMins.getText().toString());
                        long breakDurationMillis = breakMins * 60000L;
                        listener.applyTexts(workDurationMillis,breakDurationMillis);
                    }
                });


        return builder.create();
    }

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);

        try {
            listener = (SettingsDialogListener) context;
        } catch (ClassCastException e) {
            throw new ClassCastException(context.toString() + "must implement SettingsDialogListener");
        }

    }

    public interface SettingsDialogListener {
        void applyTexts(long workStartTime, long breakStartTime);
    }
}
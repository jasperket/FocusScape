package com.example.focusscape;


import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.text.method.ScrollingMovementMethod;
import android.util.Pair;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;

import com.google.android.material.datepicker.CalendarConstraints;
import com.google.android.material.datepicker.DateValidatorPointForward;
import com.google.android.material.datepicker.MaterialDatePicker;
import com.google.android.material.datepicker.MaterialPickerOnPositiveButtonClickListener;
import com.google.android.material.internal.ViewUtils;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.timepicker.MaterialTimePicker;
import com.google.android.material.timepicker.TimeFormat;

import org.w3c.dom.Text;

import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class SetReminder extends AppCompatActivity {

    private TextInputEditText eTxtReminderNotes;
    private TextInputEditText eTxtReminderDate;
    private TextInputEditText eTxtReminderStart;
    private TextInputEditText eTxtReminderEnd;
    private Button btnReminderSet;
    private String dateString;
    private String startString;
    private String endString;
    private Button btnBackFromSetReminder;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_set_reminder);

        endString = "";

        eTxtReminderNotes = findViewById(R.id.eTxtReminderNotes);
        eTxtReminderDate = findViewById(R.id.eTxtReminderDate);
        eTxtReminderStart = findViewById(R.id.eTxtReminderStart);
        eTxtReminderEnd = findViewById(R.id.eTxtReminderEnd);
        btnReminderSet = findViewById(R.id.btnSetReminder);
        btnBackFromSetReminder = findViewById(R.id.btnBackFromSetReminder);

        eTxtReminderDate.setShowSoftInputOnFocus(false);
        eTxtReminderStart.setShowSoftInputOnFocus(false);
        eTxtReminderEnd.setShowSoftInputOnFocus(false);

        btnBackFromSetReminder.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(view.getContext(), MainActivity.class);
                startActivity(intent);
            }
        });
        eTxtReminderDate.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View view, boolean b) {
                if(b)
                    showDatePickerDialog();
            }
        });

        eTxtReminderStart.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View view, boolean b) {
                if(b)
                    showTimePickerDialog(eTxtReminderStart,true);
            }
        });

        eTxtReminderEnd.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View view, boolean b) {
                if(b)
                    showTimePickerDialog(eTxtReminderEnd,false);
            }
        });

        btnReminderSet.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DatabaseHelper databaseHelper = new DatabaseHelper(view.getContext());
                SQLiteDatabase database = databaseHelper.getWritableDatabase();
                String time = startString;
                if(!endString.equals("")) {
                    time = time + " - " + endString;
                }
                String notes = "";
                if (eTxtReminderNotes.length() > 0) {
                    // EditText is not empty
                    notes = String.valueOf(eTxtReminderNotes.getText());
                }

                ContentValues values = new ContentValues();
                values.put("date",dateString);
                values.put("time",time);
                if(!notes.equals("")) {
                    values.put("notes", notes);
                }
                long rowId = database.insert("calendarTable",null,values);
                System.out.println("Success! rowId: " +rowId);
                database.close();
            }
        });
    }



    private void showTimePickerDialog(TextInputEditText eTxt, boolean isStart) {
        MaterialTimePicker.Builder builder = new MaterialTimePicker.Builder();
        builder.setTimeFormat(TimeFormat.CLOCK_12H);
        builder.setTitleText("Reminder Start");

        MaterialTimePicker timePicker = builder.build();
        timePicker.addOnPositiveButtonClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int hour = timePicker.getHour(); // Get the selected hour (0-23)
                int minute = timePicker.getMinute(); // Get the selected minute

                // Convert the hour to 12-hour format
                int hour12 = hour % 12;
                if (hour12 == 0) {
                    hour12 = 12; // Convert 0 to 12 for 12-hour format
                }

                // Determine if the selected time is in AM or PM
                String amPm;
                if (hour < 12) {
                    amPm = "AM";
                } else {
                    amPm = "PM";
                }

                if(isStart) {
                    startString = String.format("%02d:%02d %s",hour12,minute,amPm);
                    eTxtReminderStart.setText(startString);
                } else {
                    endString = String.format("%02d:%02d %s",hour12,minute,amPm);
                    eTxtReminderEnd.setText(endString);
                }
            }
        });
        timePicker.show(getSupportFragmentManager(),"TIME PICKER");
    }

    private void showDatePickerDialog() {
        MaterialDatePicker.Builder<Long> builder = MaterialDatePicker.Builder.datePicker();
        builder.setTitleText("Reminder Date");
        builder.setSelection(MaterialDatePicker.todayInUtcMilliseconds());

        CalendarConstraints.Builder constraintsBuilder = new CalendarConstraints.Builder();
        constraintsBuilder.setValidator(DateValidatorPointForward.now());

        builder.setCalendarConstraints(constraintsBuilder.build());

        MaterialDatePicker<Long> datePicker = builder.build();

        datePicker.addOnPositiveButtonClickListener(new MaterialPickerOnPositiveButtonClickListener<Long>() {
            @Override
            public void onPositiveButtonClick(Long selection) {
                Date reminderDate = new Date(selection);
                SimpleDateFormat dateFormat = new SimpleDateFormat("MM/dd/yyyy");
                dateString = dateFormat.format(reminderDate);
                eTxtReminderDate.setText(dateString);
            }
        });

        datePicker.show(getSupportFragmentManager(), "DATE_PICKER");
    }
    private void hideKeyboard() {
        InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
        imm.hideSoftInputFromWindow(eTxtReminderDate.getWindowToken(), 0);
    }
}
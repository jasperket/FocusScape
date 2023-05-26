package com.example.focusscape.setreminder;


import androidx.appcompat.app.AppCompatActivity;

import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.Toast;

import com.example.focusscape.DatabaseHelper;
import com.example.focusscape.MainActivity;
import com.example.focusscape.R;
import com.google.android.material.datepicker.CalendarConstraints;
import com.google.android.material.datepicker.DateValidatorPointForward;
import com.google.android.material.datepicker.MaterialDatePicker;
import com.google.android.material.datepicker.MaterialPickerOnPositiveButtonClickListener;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.timepicker.MaterialTimePicker;
import com.google.android.material.timepicker.TimeFormat;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

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
    private TextInputEditText eTxtReminderName;
    private int year;
    private int month;
    private int day;
    private int hour;
    private int minute;
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
        eTxtReminderName = findViewById(R.id.eTxtReminderName);

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

                Calendar calendar = Calendar.getInstance();
                calendar.set(Calendar.YEAR, year);
                calendar.set(Calendar.MONTH, month-1);  // Note: Months are zero-based
                calendar.set(Calendar.DAY_OF_MONTH, day);
                calendar.set(Calendar.HOUR_OF_DAY, hour);
                calendar.set(Calendar.MINUTE, minute);
                Date alarmDate = calendar.getTime();

                // Set the alarm using the AlarmHelper class
                String reminderName = eTxtReminderName.getText().toString();
                SetReminderAlarmHelper.setAlarm(view.getContext(), alarmDate,reminderName);

                Toast.makeText(view.getContext(),"Reminder Set!",Toast.LENGTH_SHORT).show();
            }
        });
        createNotificationChannel();
    }

    private void createNotificationChannel() {
        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            CharSequence name = "PomodoroAlarm";
            String description = "Channel for alarm";
            int importance = NotificationManager.IMPORTANCE_HIGH;
            NotificationChannel channel = new NotificationChannel("focusscapealarm",name,importance);
            channel.setDescription(description);

            NotificationManager notificationManager = getSystemService(NotificationManager.class);
            notificationManager.createNotificationChannel(channel);
        }
    }


    private void showTimePickerDialog(TextInputEditText eTxt, boolean isStart) {
        MaterialTimePicker.Builder builder = new MaterialTimePicker.Builder();
        builder.setTimeFormat(TimeFormat.CLOCK_12H);
        builder.setTitleText("Reminder Start");

        MaterialTimePicker timePicker = builder.build();
        timePicker.addOnPositiveButtonClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                hour = timePicker.getHour(); // Get the selected hour (0-23)
                minute = timePicker.getMinute(); // Get the selected minute

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

                Calendar calendar = Calendar.getInstance();
                calendar.setTime(reminderDate);

                year = calendar.get(Calendar.YEAR);
                month = calendar.get(Calendar.MONTH) + 1; // Adding 1, as months are zero-based
                day = calendar.get(Calendar.DAY_OF_MONTH);
            }
        });

        datePicker.show(getSupportFragmentManager(), "DATE_PICKER");
    }
    private void hideKeyboard() {
        InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
        imm.hideSoftInputFromWindow(eTxtReminderDate.getWindowToken(), 0);
    }
}
package com.example.focusscape;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.widget.CalendarView;
import android.widget.TextView;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Calendar;
import java.util.Locale;

public class CalendarAct extends AppCompatActivity {

    private CalendarView calendarView;
    private String currentDate;
    private int pomodoros;
    private TextView txtCalendarDate;
    private TextView txtPomodoroCount;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calendar);

        calendarView = findViewById(R.id.calendarView);
        txtCalendarDate = findViewById(R.id.txtCalendarDate);
        txtPomodoroCount = findViewById(R.id.txtPomodoroCount);

        currentDate = getFormattedDate(calendarView.getDate());
        pomodoros = getPomodoros();
        updateInterface();

        calendarView.setOnDateChangeListener(new CalendarView.OnDateChangeListener() {
            @Override
            public void onSelectedDayChange(@NonNull CalendarView calendarView, int year, int month,
                                            int dayOfMonth) {
                currentDate = getFormattedDate(dayOfMonth,month,year);
                pomodoros = getPomodoros();
                updateInterface();
            }
        });
    }

    private void updateInterface() {
        txtCalendarDate.setText(currentDate);
        txtPomodoroCount.setText("Pomodoro count: " + pomodoros + " pomdoros");
    }


    private int getPomodoros() {
        DatabaseHelper dbHelper = new DatabaseHelper(this);
        SQLiteDatabase database = dbHelper.getWritableDatabase();
        // Specify the table name
        String tableName = "pomodoroTable";
        // Specify the columns to retrieve
        String[] columns = {"pomodoros"};

        // Write the selection criteria
        String selection = "date = ?";  // Check if the "date" column matches the desired date

        // Specify the selection arguments
        String[] selectionArgs = {currentDate};

        // Execute the query
        Cursor cursor = database.query(tableName, columns, selection, selectionArgs, null, null, null);

        int resultPomodoros = 0;

        if (cursor.moveToFirst()) {
            // If the cursor is not empty, retrieve the pomodoros value
            resultPomodoros = cursor.getInt(cursor.getColumnIndex("pomodoros"));
            resultPomodoros++;
        }
        // Close the cursor
        cursor.close();
        database.close();
        return resultPomodoros;
    }

    private String getFormattedDate(long date) {
        Date selectedDate = new Date(date);
        SimpleDateFormat dateFormat = new SimpleDateFormat("MM/dd/yyyy",Locale.getDefault());
        return dateFormat.format(selectedDate);
    }

    private String getFormattedDate(int day, int month, int year) {
        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.YEAR, year);
        calendar.set(Calendar.MONTH, month);
        calendar.set(Calendar.DAY_OF_MONTH, day);

        SimpleDateFormat sdf = new SimpleDateFormat("MM/dd/yyyy", Locale.getDefault());
        return sdf.format(calendar.getTime());
    }


}
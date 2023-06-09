package com.example.focusscape.calendar;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CalendarView;
import android.widget.TextView;

import com.example.focusscape.DatabaseHelper;
import com.example.focusscape.MainActivity;
import com.example.focusscape.R;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Calendar;
import java.util.List;
import java.util.Locale;

public class CalendarAct extends AppCompatActivity implements CalendarViewHolder.DeleteReminderListener {

    private CalendarView calendarView;
    private String currentDate;
    private int pomodoros;
    private TextView txtCalendarDate;
    private TextView txtPomodoroCount;
    private RecyclerView recyclerReminder;
    private CalendarAdapter adapterReminder;
    private List<CalendarItem> calendarItems;
    private DatabaseHelper dbHelper;

    private Button btnBackFromCalendar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calendar);

        calendarView = findViewById(R.id.calendarView);
        txtCalendarDate = findViewById(R.id.txtCalendarDate);
        txtPomodoroCount = findViewById(R.id.txtPomodoroCount);
        btnBackFromCalendar = findViewById(R.id.btnBackFromCalendar);

        dbHelper = new DatabaseHelper(this);

        currentDate = getFormattedDate(calendarView.getDate());
        pomodoros = getPomodoros();
        calendarItems = getDataFromDatabase();

        recyclerReminder = findViewById(R.id.recyclerReminder);
        recyclerReminder.setLayoutManager(new LinearLayoutManager(this));

        adapterReminder = new CalendarAdapter(calendarItems);
        recyclerReminder.setAdapter(adapterReminder);

        updateInterface();
        calendarView.setOnDateChangeListener(new CalendarView.OnDateChangeListener() {
            @Override
            public void onSelectedDayChange(@NonNull CalendarView calendarView, int year, int month,
                                            int dayOfMonth) {
                currentDate = getFormattedDate(dayOfMonth,month,year);
                pomodoros = getPomodoros();
                calendarItems = getDataFromDatabase();
                updateInterface();
                updateRecyclerReminder();
            }
        });

        btnBackFromCalendar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(view.getContext(), MainActivity.class);
                startActivity(intent);
            }
        });
    }


    private void updateRecyclerReminder() {
        // Assuming you have a RecyclerView called 'recyclerView' and an adapter called 'adapter'

        // Deflate the RecyclerView
        adapterReminder.clearData(); // Clear the dataset associated with the adapter

        // Inflate the RecyclerView with new data
        adapterReminder.setData(calendarItems); // Update the dataset associated with the adapter

        // Notify the adapter of the changes
        adapterReminder.notifyDataSetChanged(); // Notify that the entire dataset has changed

    }

    private List<CalendarItem> getDataFromDatabase() {
        List<CalendarItem> data = new ArrayList<>();
        // Example data retrieval using Cursor (replace with your own implementation)
        SQLiteDatabase db = dbHelper.getReadableDatabase();
        String[] projection = {"time", "notes"};
        String selection = "date = ?";
        String[] selectionArgs = {currentDate};
        Cursor cursor = db.query("calendarTable", projection, selection, selectionArgs, null, null, null);

        if (cursor != null && cursor.moveToFirst()) {
            do {
                String time = cursor.getString(cursor.getColumnIndex("time"));
                String notes = cursor.getString(cursor.getColumnIndex("notes"));
                CalendarItem item = new CalendarItem(time, notes);
                data.add(item);
            } while (cursor.moveToNext());
            cursor.close();
        }

        db.close();
        return data;
    }

    private void updateInterface() {
        txtCalendarDate.setText(currentDate);
        txtPomodoroCount.setText("Pomodoro count: " + pomodoros + " pomdoros");
    }


    private int getPomodoros() {
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

    @Override
    public void reminderDelete(String time, String notes) {
        // Get a writable instance of the database
        SQLiteDatabase database = dbHelper.getWritableDatabase();

        // Define the table name and the column names
        String tableName = "calendarTable";
        String timeColumnName = "time";
        String notesColumnName = "notes";

        // Build the selection criteria
        String selection = timeColumnName + " = ? AND " + notesColumnName + " = ?";
        String[] selectionArgs = { time, notes };

        // Delete the row(s) matching the criteria
        int deletedRows = database.delete(tableName, selection, selectionArgs);

        // Check the number of rows deleted
        if (deletedRows > 0) {
            // Row(s) deleted successfully
            System.out.println("Successfully deleted!");
        } else {
            // No rows deleted or an error occurred
            System.out.println("Error");
        }

        // Close the database connection
        database.close();

        calendarItems = getDataFromDatabase();
        updateRecyclerReminder();
    }
}
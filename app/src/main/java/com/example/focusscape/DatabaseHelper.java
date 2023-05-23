package com.example.focusscape;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DatabaseHelper extends SQLiteOpenHelper {
    private static final String DATABASE_NAME = "focusscape.db";
    private static final int DATABASE_VERSION = 1;

    // Table name
    private static final String TABLE_NAME = "calendarTable";
    private static final String TABLE_NAME2 = "pomodoroTable";

    // Column names
    private static final String COLUMN_ID2 = "id";
    private static final String COLUMN_DATE2 = "date";
    private static final String COLUMN_POMODOROS2 = "pomodoros";

    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }
    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        String createTableQuery = "CREATE TABLE IF NOT EXISTS calendarTable " +
                "(id INTEGER PRIMARY KEY AUTOINCREMENT, " +
                "date TEXT NOT NULL, " +
                "time TEXT NOT NULL, " +
                "notes TEXT)";
        sqLiteDatabase.execSQL(createTableQuery);

        String CREATE_TABLE2 = "CREATE TABLE " + TABLE_NAME2 + " (" +
                COLUMN_ID2 + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                COLUMN_DATE2 + " TEXT, " +
                COLUMN_POMODOROS2 + " INTEGER DEFAULT 1)";
        sqLiteDatabase.execSQL(CREATE_TABLE2);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
    }

    public boolean doesDateExist(String date) {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = null;

        try {
            String query = "SELECT * FROM " + TABLE_NAME2 + " WHERE date = ?";
            cursor = db.rawQuery(query, new String[]{date});

            // If cursor has any rows, it means the date exists in the table
            return cursor != null && cursor.getCount() > 0;
        } finally {
            if (cursor != null)
                cursor.close();
            db.close();
        }
    }
}

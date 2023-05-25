package com.example.focusscape.setreminder;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;

import java.util.Calendar;
import java.util.Date;

public class SetReminderAlarmHelper {
    public static void setAlarm(Context context, Date alarmDate, String name) {
        // Create a calendar instance and set it to the desired alarm time
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(alarmDate);

        // Create an intent for the alarm receiver
        Intent intent = new Intent(context, SetReminderAlarmReceiver.class);
        intent.putExtra("reminderName",name);
        PendingIntent pendingIntent = PendingIntent.getBroadcast(context, 0, intent, PendingIntent.FLAG_IMMUTABLE);

        // Get the AlarmManager service
        AlarmManager alarmManager = (AlarmManager) context.getSystemService(Context.ALARM_SERVICE);

        // Set the alarm using AlarmManager
        if (alarmManager != null) {
            alarmManager.set(AlarmManager.RTC_WAKEUP, calendar.getTimeInMillis(), pendingIntent);
        }
    }
}

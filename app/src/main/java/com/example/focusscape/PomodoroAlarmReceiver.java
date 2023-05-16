package com.example.focusscape;

import static androidx.core.content.ContextCompat.getSystemService;
import static androidx.core.content.ContextCompat.startActivity;

import android.Manifest;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.pm.PackageManager;
import android.media.MediaPlayer;
import android.os.Build;

import androidx.core.app.ActivityCompat;
import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;

public class PomodoroAlarmReceiver extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {
        Intent intent2 = new Intent(context, AlarmActivity.class);
        context.startActivity(intent2);

        NotificationCompat.Builder builder = new NotificationCompat.Builder(context, "pomodoroAlarm")
                .setSmallIcon(R.drawable.ic_launcher_background)
                .setContentTitle("Pomodoro Alarm!")
                .setContentText("The session has ended.")
                .setAutoCancel(true)
                .setDefaults(NotificationCompat.DEFAULT_ALL)
                .setPriority(NotificationCompat.PRIORITY_HIGH);

        NotificationManagerCompat notificationManagerCompat = NotificationManagerCompat.from(context);
        notificationManagerCompat.notify(123,builder.build());
    }
}

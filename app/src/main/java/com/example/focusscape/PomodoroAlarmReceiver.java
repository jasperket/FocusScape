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

    private MediaPlayer alarmPlayer;
    @Override
    public void onReceive(Context context, Intent intent) {
//        Intent intent2 = new Intent(context, AlarmActivity.class);
//        intent2.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
//        context.startActivity(intent2);

        alarmPlayer = MediaPlayer.create(context, R.raw.birds);
        alarmPlayer.start();

        NotificationCompat.Builder builder = new NotificationCompat.Builder(context, "pomodoro_alarm")
                .setSmallIcon(R.drawable.ic_launcher_background)
                .setContentTitle("Pomodoro alarm")
                .setContentText("Pomodoro session ended")
                .setAutoCancel(true)
                .setDefaults(NotificationCompat.DEFAULT_ALL)
                .setPriority(NotificationCompat.PRIORITY_HIGH);

        NotificationManagerCompat notificationManagerCompat = NotificationManagerCompat.from(context);
        if (ActivityCompat.checkSelfPermission(context, Manifest.permission.POST_NOTIFICATIONS)
                == PackageManager.PERMISSION_GRANTED
                && Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
            // TODO: Consider calling
            //    ActivityCompat#requestPermissions
            // here to request the missing permissions, and then overriding
            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
            //                                          int[] grantResults)
            // to handle the case where the user grants the permission. See the documentation
            // for ActivityCompat#requestPermissions for more details.
            notificationManagerCompat.notify(123, builder.build());
            return;
        } else if(Build.VERSION.SDK_INT < Build.VERSION_CODES.TIRAMISU) {
            notificationManagerCompat.notify(123, builder.build());
        }


    }


}

package com.example.focusscape;

import android.Manifest;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Build;

import androidx.core.app.ActivityCompat;
import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;

import com.example.focusscape.pomodorotimer.PomodoroTimer;

public class SetReminderAlarmReceiver extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {
        String name = intent.getStringExtra("reminderName");

        Intent intent2 = new Intent(context, SetReminder.class);
        intent2.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        PendingIntent pendingIntent = PendingIntent.getActivity(context,0,intent2,PendingIntent.FLAG_IMMUTABLE);

        NotificationCompat.Builder builder = new NotificationCompat.Builder(context, "focusscapealarm")
                .setSmallIcon(R.drawable.ic_launcher_foreground)
                .setContentTitle("Reminder alarm")
                .setContentText(name)
                .setAutoCancel(true)
                .setDefaults(NotificationCompat.DEFAULT_ALL)
                .setPriority(NotificationCompat.PRIORITY_HIGH)
                .setContentIntent(pendingIntent);

        NotificationManagerCompat notificationManagerCompat = NotificationManagerCompat.from(context);
        if (ActivityCompat.checkSelfPermission(context, Manifest.permission.POST_NOTIFICATIONS)
                == PackageManager.PERMISSION_GRANTED
                && Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
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

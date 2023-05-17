package com.example.focusscape;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;

import android.Manifest;
import android.content.pm.PackageManager;
import android.media.MediaPlayer;
import android.os.Build;
import android.os.Bundle;

public class AlarmActivity extends AppCompatActivity {

    private MediaPlayer alarmPlayer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_alarm);

        NotificationCompat.Builder builder = new NotificationCompat.Builder(this, "pomodoroAlarm")
                .setSmallIcon(R.drawable.ic_launcher_background)
                .setContentTitle("Pomodoro Alarm!")
                .setContentText("The session has ended.")
                .setAutoCancel(true)
                .setDefaults(NotificationCompat.DEFAULT_ALL)
                .setPriority(NotificationCompat.PRIORITY_HIGH);

        NotificationManagerCompat notificationManagerCompat = NotificationManagerCompat.from(this);
        if (ActivityCompat.checkSelfPermission(this, android.Manifest.permission.POST_NOTIFICATIONS)
                != PackageManager.PERMISSION_GRANTED
                && Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
            // TODO: Consider calling
            //    ActivityCompat#requestPermissions
            // here to request the missing permissions, and then overriding
            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
            //                                          int[] grantResults)
            // to handle the case where the user grants the permission. See the documentation
            // for ActivityCompat#requestPermissions for more details.
            final int NOTIF_PERMISSION_REQUEST_CODE = 101;
            ActivityCompat.requestPermissions(this,
                    new String[] { Manifest.permission.POST_NOTIFICATIONS },
                    NOTIF_PERMISSION_REQUEST_CODE);
            return;
        }
        notificationManagerCompat.notify(123, builder.build());

        alarmPlayer = MediaPlayer.create(this, R.raw.birds);
        alarmPlayer.start();
    }
}
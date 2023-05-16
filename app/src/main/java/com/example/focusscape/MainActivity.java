package com.example.focusscape;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {
    Button btnSetReminder;
    Button btnPomodoro;
    Button btnEnvSetup;
    Button btnNoisePlayer;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnSetReminder = findViewById(R.id.btnSetReminderAct);
        btnPomodoro = findViewById(R.id.btnPomodoroAct);
        btnEnvSetup = findViewById(R.id.btnEnvSetupAct);
        btnNoisePlayer = findViewById(R.id.btnNoisePlayer);
        btnPomodoro.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(view.getContext(), PomodoroTimer.class);
                startActivity(intent);
            }
        });

        btnEnvSetup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(view.getContext(), EnvironmentSetup.class);
                startActivity(intent);
            }
        });

        btnSetReminder.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(view.getContext(),SetReminder.class);
                startActivity(intent);
            }
        });

        btnEnvSetup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(view.getContext(), EnvironmentSetup.class);
                startActivity(intent);
            }
        });

        btnSetReminder.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(view.getContext(),SetReminder.class);
                startActivity(intent);
            }
        });

        btnNoisePlayer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(view.getContext(),NoisePlayer.class);
                startActivity(intent);
            }
        });

        if (ContextCompat.checkSelfPermission(this, Manifest.permission.SCHEDULE_EXACT_ALARM)
                != PackageManager.PERMISSION_GRANTED
                && Build.VERSION.SDK_INT >= Build.VERSION_CODES.S) {
            // Permission is already granted
            final int ALARM_PERMISSION_REQUEST_CODE = 100;
            ActivityCompat.requestPermissions(this,
                    new String[] { Manifest.permission.SCHEDULE_EXACT_ALARM },
                    ALARM_PERMISSION_REQUEST_CODE);
        }

        if (ContextCompat.checkSelfPermission(this, Manifest.permission.POST_NOTIFICATIONS) == PackageManager.PERMISSION_GRANTED) {
            // Permission is already granted
        } else {
            // Permission is not granted, you need to request it
            final int NOTIF_PERMISSION_REQUEST_CODE = 101;
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
                ActivityCompat.requestPermissions(this,
                        new String[] { Manifest.permission.POST_NOTIFICATIONS },
                        NOTIF_PERMISSION_REQUEST_CODE);
            }
        }
    }
}
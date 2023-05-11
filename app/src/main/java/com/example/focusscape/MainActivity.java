package com.example.focusscape;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
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
    }
}
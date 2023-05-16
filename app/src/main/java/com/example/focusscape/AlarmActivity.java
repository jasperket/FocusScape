package com.example.focusscape;

import androidx.appcompat.app.AppCompatActivity;

import android.media.MediaPlayer;
import android.os.Bundle;

public class AlarmActivity extends AppCompatActivity {

    private MediaPlayer alarmPlayer

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_alarm);

        alarmPlayer = MediaPlayer.create(this, R.raw.birds);
        alarmPlayer.start();
    }
}
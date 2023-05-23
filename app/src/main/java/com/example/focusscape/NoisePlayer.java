package com.example.focusscape;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.content.Intent;
import android.media.MediaParser;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

public class NoisePlayer extends AppCompatActivity {
    ImageView play;
    FragmentTransaction fragmentTransaction;
    Button btnscene1, btnscene2, btnscene3;
    Scene1Fragment frgscene1;
    Scene2Fragment frgscene2;
    Scene3Fragment frgscene3;

    MediaPlayer player;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_noise_player);
        play = findViewById(R.id.btnPausePlay);
        btnscene1 = findViewById(R.id.btnScene1);
        btnscene2 = findViewById(R.id.btnScene2);
        btnscene3 = findViewById(R.id.btnScene3);

        frgscene1 = new Scene1Fragment();
        frgscene2 = new Scene2Fragment();
        frgscene3 = new Scene3Fragment();
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.framelayout,frgscene1);
        fragmentTransaction.commit();

        btnscene1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                changeScene(frgscene1);
            }
        });

        btnscene2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                changeScene(frgscene2);
            }
        });

        btnscene3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                changeScene(frgscene3);
            }
        });
    }

    private void changeScene(Fragment scene){
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.framelayout,scene);
        fragmentTransaction.commit();
    }

    public void togglePlay(View v){
        if(player == null){
            player = MediaPlayer.create(this, R.raw.rain);
        }
    }
    public void stop (View v){

    }


}
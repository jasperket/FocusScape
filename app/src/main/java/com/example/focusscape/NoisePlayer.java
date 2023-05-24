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

import java.util.ArrayList;

public class NoisePlayer extends AppCompatActivity {
    ImageView play;
    FragmentTransaction fragmentTransaction;
    Button btnscene1, btnscene2, btnscene3;
    Scene1Fragment frgscene1;
    Scene2Fragment frgscene2;
    Scene3Fragment frgscene3;
    MediaPlayer player1, player2, player3, player4, player5, player6, player7,player8, player9;
    ArrayList<MediaPlayer> player;
    boolean isPlaying;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_noise_player);
        play = findViewById(R.id.btnPausePlay);
        btnscene1 = findViewById(R.id.btnScene1);
        btnscene2 = findViewById(R.id.btnScene2);
        btnscene3 = findViewById(R.id.btnScene3);
        isPlaying = false;
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
        if(isPlaying == false){
            if(player1 == null && player2 == null && player3 == null && player4 == null && player5 == null && player6 == null && player7 == null && player8 == null && player9 == null) {
                player1 = MediaPlayer.create(this,R.raw.birds);
                player2 = MediaPlayer.create(this, R.raw.frogs);
                player3 = MediaPlayer.create(this, R.raw.crickets);
                player4 = MediaPlayer.create(this, R.raw.wind);
                player5 = MediaPlayer.create(this, R.raw.rain);
                player6 = MediaPlayer.create(this, R.raw.thunder);
                player7 = MediaPlayer.create(this, R.raw.owl);
                player8 = MediaPlayer.create(this, R.raw.fire);
                player9 = MediaPlayer.create(this, R.raw.leaves);
            }
            player1.setLooping(true);
            player2.setLooping(true);
            player3.setLooping(true);
            player4.setLooping(true);
            player5.setLooping(true);
            player6.setLooping(true);
            player7.setLooping(true);
            player8.setLooping(true);
            player9.setLooping(true);

            player2.start();
            player3.start();
            player4.start();
            player5.start();
            player6.start();
            player7.start();
            player8.start();
            player9.start();
            isPlaying = true;
        }else{
            stopPlayer();
            isPlaying = false;
        }

    }

    public void stop(View v){
        stopPlayer();
    }

    private void stopPlayer(){
        if(player1 != null && player2 != null && player3 != null && player4 != null && player5 != null && player6 != null && player7 != null && player8 != null && player9 != null) {
            player1.release();
            player2.release();
            player3.release();
            player4.release();
            player5.release();
            player6.release();
            player7.release();
            player8.release();
            player9.release();
            player1 = null;
            player2 = null;
            player3 = null;
            player4 = null;
            player5 = null;
            player6 = null;
            player7 = null;
            player8 = null;
            player9 = null;
        }

    }


}
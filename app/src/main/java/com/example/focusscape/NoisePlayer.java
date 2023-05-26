package com.example.focusscape;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.content.Intent;
import android.graphics.drawable.Icon;
import android.media.MediaParser;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.SeekBar;
import android.widget.Toast;

import java.util.ArrayList;

public class NoisePlayer extends AppCompatActivity implements FragmentListener, SeekBar.OnSeekBarChangeListener {
    ImageView play;
    Button btnscene1, btnscene2;
    Scene1Fragment frgscene1;
    Scene2Fragment frgscene2;
    MediaPlayer player1, player2, player3, player4, player5, player6, player7,player8, player9;
    boolean isPlaying;
    SeekBar volume;
    int selectedscene;
    int selected;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_noise_player);
        play = findViewById(R.id.btnPausePlay);
        btnscene1 = findViewById(R.id.btnScene1);
        btnscene2 = findViewById(R.id.btnScene2);
        volume =findViewById(R.id.skVolume);
        volume.setMax(100);
        isPlaying = false;
        selectedscene = 1;
        frgscene1 = new Scene1Fragment();
        frgscene2 = new Scene2Fragment();

        volume.setOnSeekBarChangeListener(this);

        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.framelayout,frgscene1);
        fragmentTransaction.commit();

        btnscene1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                changeScene(frgscene1);
                selectedscene = 1;
            }
        });

        btnscene2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                changeScene(frgscene2);
                selectedscene = 2;
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
            play.setImageIcon(Icon.createWithResource(this, R.drawable.pause_circle));
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

            if(selectedscene == 2 && player1 == null && player2 == null && player3 == null && player4 == null && player5 == null && player6 == null && player7 == null && player8 == null && player9 == null) {
                player1 = MediaPlayer.create(this,R.raw.waves);
                player2 = MediaPlayer.create(this, R.raw.seagull);
                player3 = MediaPlayer.create(this, R.raw.waterwalking);
                player4 = MediaPlayer.create(this, R.raw.wind);
                player5 = MediaPlayer.create(this, R.raw.rain);
                player6 = MediaPlayer.create(this, R.raw.thunder);
                player7 = MediaPlayer.create(this, R.raw.harbor);
                player8 = MediaPlayer.create(this, R.raw.whale);
                player9 = MediaPlayer.create(this, R.raw.fire);
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

            player1.start();
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
            play.setImageIcon(Icon.createWithResource(this, R.drawable.play_circle));
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

    @Override
    public void onDataReceived(int selected) {
        this.selected = selected;
    }

    @Override
    public void onProgressChanged(SeekBar seekBar, int progress, boolean b) {
        float vol = progress / 100f;

        switch (selected) {
            case 1:
            player1.setVolume(vol, vol);
            break;
            case 2:
            player2.setVolume(vol, vol);
            break;
            case 3:
            player3.setVolume(vol, vol);
            break;
            case 4:
            player4.setVolume(vol, vol);
            break;
            case 5:
            player5.setVolume(vol, vol);
            break;
            case 6:
            player6.setVolume(vol, vol);
            break;
            case 7:
            player7.setVolume(vol, vol);
            break;
            case 8:
            player8.setVolume(vol, vol);
            break;
            case 9:
            player9.setVolume(vol, vol);
            break;
        }
    }

    @Override
    public void onStartTrackingTouch(SeekBar seekBar) {

    }

    @Override
    public void onStopTrackingTouch(SeekBar seekBar) {

    }
}
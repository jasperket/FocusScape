package com.example.focusscape;

import android.content.Intent;
import android.graphics.drawable.Icon;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class ScenePreview extends AppCompatActivity implements View.OnClickListener {

    MediaPlayer player;
    boolean isPlaying;
    int itself;
    ImageView bird, frog, cricket, wind, rain, thunder, owl, fire, leaves, wave, seagull, walkingwater, whale, harbor, wind2, rain2, thunder2, fire2, back;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_scenesview);

        bird = findViewById(R.id.ffirst);
        frog = findViewById(R.id.fsecond);
        cricket = findViewById(R.id.fthird);
        wind = findViewById(R.id.ffourth);
        rain = findViewById(R.id.ffifth);
        thunder = findViewById(R.id.fsixth);
        owl = findViewById(R.id.fseventh);
        leaves = findViewById(R.id.feighth);
        fire = findViewById(R.id.fninth);

        wave = findViewById(R.id.bfirst);
        seagull = findViewById(R.id.bsecond);
        walkingwater = findViewById(R.id.bthird);
        wind2 = findViewById(R.id.bfourth);
        rain2 = findViewById(R.id.bfifth);
        thunder2 = findViewById(R.id.bsixth);
        harbor = findViewById(R.id.bseventh);
        whale = findViewById(R.id.beighth);
        fire2 = findViewById(R.id.bninth);

        back = findViewById(R.id.back);


        back.setOnClickListener(this);

        bird.setOnClickListener(this);
        frog.setOnClickListener(this);
        cricket.setOnClickListener(this);
        wind.setOnClickListener(this);
        rain.setOnClickListener(this);
        thunder.setOnClickListener(this);
        owl.setOnClickListener(this);
        leaves.setOnClickListener(this);
        fire.setOnClickListener(this);

        wave.setOnClickListener(this);
        seagull.setOnClickListener(this);
        walkingwater.setOnClickListener(this);
        wind2.setOnClickListener(this);
        rain2.setOnClickListener(this);
        thunder2.setOnClickListener(this);
        harbor.setOnClickListener(this);
        whale.setOnClickListener(this);
        fire2.setOnClickListener(this);

        isPlaying = false;

    }



    private void stopPlayer(){
        if(player != null){
            player.release();
            player = null;
        }

    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.ffirst:
                if(!isPlaying){
                    if(player == null) {
                        player = MediaPlayer.create(this, R.raw.birds);
                    }
                    player.start();
                    itself = 1;
                    isPlaying = true;
                }else if(isPlaying && itself != 1){
                    stopPlayer();
                    if(player == null) {
                        player = MediaPlayer.create(this, R.raw.birds);
                    }
                    player.start();
                    itself = 1;
                    isPlaying = true;
                }else{
                    stopPlayer();
                    isPlaying = false;
                }
                break;
            case R.id.fsecond:
                if(!isPlaying){
                    if(player == null) {
                        player = MediaPlayer.create(this, R.raw.frogs);
                    }
                    player.start();
                    itself = 2;
                    isPlaying = true;
                }else if(isPlaying && itself != 2){
                    stopPlayer();
                    if(player == null) {
                        player = MediaPlayer.create(this, R.raw.frogs);
                    }
                    player.start();
                    itself = 2;
                    isPlaying = true;
                }else{
                    stopPlayer();
                    isPlaying = false;
                }
                break;
            case R.id.fthird:
                if(!isPlaying){
                    if(player == null) {
                        player = MediaPlayer.create(this, R.raw.crickets);
                    }
                    player.start();
                    itself = 3;
                    isPlaying = true;
                }else if(isPlaying && itself != 3){
                    stopPlayer();
                    if(player == null) {
                        player = MediaPlayer.create(this, R.raw.crickets);
                    }
                    player.start();
                    itself = 3;
                    isPlaying = false;
                }else{
                    stopPlayer();
                }
                break;
            case R.id.ffourth:
                if(!isPlaying){
                    if(player == null) {
                        player = MediaPlayer.create(this, R.raw.wind);
                    }
                    player.start();
                    itself = 4;
                    isPlaying = true;
                }else if(isPlaying && itself != 4){
                    stopPlayer();
                    if(player == null) {
                        player = MediaPlayer.create(this, R.raw.wind);
                    }
                    player.start();
                    itself = 4;
                    isPlaying = true;
                }else{
                    stopPlayer();
                    isPlaying = false;
                }

                break;
            case R.id.ffifth:
                if(!isPlaying){
                    if(player == null) {
                        player = MediaPlayer.create(this, R.raw.rain);
                    }
                    player.start();
                    itself = 5;
                    isPlaying = true;
                }else if(isPlaying && itself != 5){
                    stopPlayer();
                    if(player == null) {
                        player = MediaPlayer.create(this, R.raw.rain);
                    }
                    player.start();
                    itself = 5;
                    isPlaying = true;
                }else{
                    stopPlayer();
                    isPlaying = false;
                }

                break;
            case R.id.fsixth:
                if(!isPlaying){
                    if(player == null) {
                        player = MediaPlayer.create(this, R.raw.thunder);
                    }
                    player.start();
                    itself = 6;
                    isPlaying = true;
                }else if(isPlaying && itself != 6){
                    stopPlayer();
                    if(player == null) {
                        player = MediaPlayer.create(this, R.raw.thunder);
                    }
                    player.start();
                    itself = 6;
                    isPlaying = true;
                }else{
                    stopPlayer();
                    isPlaying = false;
                }
                break;
            case R.id.fseventh:
                if(!isPlaying){
                    if(player == null) {
                        player = MediaPlayer.create(this, R.raw.owl);
                    }
                    player.start();
                    itself = 7;
                    isPlaying = true;
                }else if(isPlaying && itself != 7){
                    stopPlayer();
                    if(player == null) {
                        player = MediaPlayer.create(this, R.raw.owl);
                    }
                    player.start();
                    itself = 7;
                    isPlaying = true;
                }else{
                    stopPlayer();
                    isPlaying = false;
                }
                break;
            case R.id.feighth:
                if(!isPlaying){
                    if(player == null) {
                        player = MediaPlayer.create(this, R.raw.fire);
                    }
                    player.start();
                    itself = 8;
                    isPlaying = true;
                }else if(isPlaying && itself != 8){
                    stopPlayer();
                    if(player == null) {
                        player = MediaPlayer.create(this, R.raw.fire);
                    }
                    player.start();
                    itself = 8;
                    isPlaying = true;
                }else{
                    stopPlayer();
                    isPlaying = false;
                }
                break;
            case R.id.fninth:
                if(!isPlaying){
                    if(player == null) {
                        player = MediaPlayer.create(this, R.raw.leaves);
                    }
                    player.start();
                    itself = 9;
                    isPlaying = true;
                }else if(isPlaying && itself != 9){
                    stopPlayer();
                    if(player == null) {
                        player = MediaPlayer.create(this, R.raw.leaves);
                    }
                    player.start();
                    itself = 9;
                    isPlaying = true;
                }else{
                    stopPlayer();
                    isPlaying = false;
                }
                break;
            case R.id.bfirst:
                if(!isPlaying){
                    if(player == null) {
                        player = MediaPlayer.create(this, R.raw.waves);
                    }
                    player.start();
                    itself = 10;
                    isPlaying = true;
                }else if(isPlaying && itself != 10){
                    stopPlayer();
                    if(player == null) {
                        player = MediaPlayer.create(this, R.raw.waves);
                    }
                    player.start();
                    itself = 10;
                    isPlaying = true;
                }else{
                    stopPlayer();
                    isPlaying = false;
                }
                break;
            case R.id.bsecond:
                if(!isPlaying){
                    if(player == null) {
                        player = MediaPlayer.create(this, R.raw.seagull);
                    }
                    player.start();
                    itself = 11;
                    isPlaying = true;
                }else if(isPlaying && itself != 11){
                    stopPlayer();
                    if(player == null) {
                        player = MediaPlayer.create(this, R.raw.seagull);
                    }
                    player.start();
                    itself = 11;
                    isPlaying = true;
                }else{
                    stopPlayer();
                }
                break;
            case R.id.bthird:
                if(!isPlaying){
                    if(player == null) {
                        player = MediaPlayer.create(this, R.raw.waterwalking);
                    }
                    player.start();
                    itself = 12;
                    isPlaying = true;
                }else if(isPlaying && itself != 12){
                    stopPlayer();
                    if(player == null) {
                        player = MediaPlayer.create(this, R.raw.waterwalking);
                    }
                    player.start();
                    itself = 12;
                    isPlaying = true;
                }else{
                    stopPlayer();
                    isPlaying = false;
                }
                break;
            case R.id.bfourth:
                if(!isPlaying){
                    if(player == null) {
                        player = MediaPlayer.create(this, R.raw.wind);
                    }
                    player.start();
                    itself = 13;
                    isPlaying = true;
                }else if(isPlaying && itself != 13){
                    stopPlayer();
                    if(player == null) {
                        player = MediaPlayer.create(this, R.raw.wind);
                    }
                    player.start();
                    itself = 13;
                    isPlaying = true;
                }else{
                    stopPlayer();
                    isPlaying = false;
                }
                break;
            case R.id.bfifth:
                if(!isPlaying){
                    if(player == null) {
                        player = MediaPlayer.create(this, R.raw.rain);
                    }
                    player.start();
                    itself = 14;
                    isPlaying = true;
                }else if(isPlaying && itself != 14){
                    stopPlayer();
                    if(player == null) {
                        player = MediaPlayer.create(this, R.raw.rain);
                    }
                    player.start();
                    itself = 14;
                    isPlaying = true;
                }else{
                    stopPlayer();
                    isPlaying = false;
                }
                break;
            case R.id.bsixth:
                if(!isPlaying){
                    if(player == null) {
                        player = MediaPlayer.create(this, R.raw.thunder);
                    }
                    player.start();
                    itself = 15;
                    isPlaying = true;
                }else if(isPlaying && itself != 15){
                    stopPlayer();
                    if(player == null) {
                        player = MediaPlayer.create(this, R.raw.thunder);
                    }
                    player.start();
                    itself = 15;
                    isPlaying = true;
                }else{
                    stopPlayer();
                    isPlaying = false;
                }
                break;
            case R.id.bseventh:
                if(!isPlaying){
                    if(player == null) {
                        player = MediaPlayer.create(this, R.raw.harbor);
                    }
                    player.start();
                    itself = 16;
                    isPlaying = true;
                }else if(isPlaying && itself != 16){
                    stopPlayer();
                    if(player == null) {
                        player = MediaPlayer.create(this, R.raw.harbor);
                    }
                    player.start();
                    itself = 16;
                    isPlaying = true;
                }else{
                    stopPlayer();
                    isPlaying = false;
                }
                break;
            case R.id.beighth:
                if(!isPlaying){
                    if(player == null) {
                        player = MediaPlayer.create(this, R.raw.whale);
                    }
                    player.start();
                    itself = 17;
                    isPlaying = true;
                }else if(isPlaying && itself != 17){
                    stopPlayer();
                    if(player == null) {
                        player = MediaPlayer.create(this, R.raw.whale);
                    }
                    player.start();
                    itself = 17;
                    isPlaying = true;
                }else{
                    stopPlayer();
                    isPlaying = false;
                }
                break;
            case R.id.bninth:
                if(!isPlaying){
                    if(player == null) {
                        player = MediaPlayer.create(this, R.raw.fire);
                    }
                    player.start();
                    itself = 18;
                    isPlaying = true;
                }else if(isPlaying && itself != 18){
                    stopPlayer();
                    if(player == null) {
                        player = MediaPlayer.create(this, R.raw.fire);
                    }
                    player.start();
                    itself = 18;
                    isPlaying = true;
                }else{
                    stopPlayer();
                    isPlaying = false;
                }
                break;
            case R.id.back:
                stopPlayer();
                Intent intent = new Intent(view.getContext(),NoisePlayer.class);
                startActivity(intent);
                break;
        }
    }
}

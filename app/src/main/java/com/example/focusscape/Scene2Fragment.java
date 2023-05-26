package com.example.focusscape;

import android.content.Intent;
import android.graphics.drawable.Icon;
import android.media.SoundPool;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

public class Scene2Fragment extends Fragment implements View.OnClickListener {
    View view;
    ImageView wave, seagull, walkingwater, wind, rain, thunder, harbor, whale, fire, back;

    int selected;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_scene2, container, false);
        wave = view.findViewById(R.id.first);
        seagull = view.findViewById(R.id.second);
        walkingwater = view.findViewById(R.id.third);
        wind = view.findViewById(R.id.fourth);
        rain = view.findViewById(R.id.fifth);
        thunder = view.findViewById(R.id.sixth);
        harbor = view.findViewById(R.id.seventh);
        whale = view.findViewById(R.id.eighth);
        fire = view.findViewById(R.id.ninth);
        back = view.findViewById(R.id.btnBack);

        rain.setOnClickListener(this);
        wave.setOnClickListener(this);
        fire.setOnClickListener(this);
        seagull.setOnClickListener(this);
        walkingwater.setOnClickListener(this);
        wind.setOnClickListener(this);
        thunder.setOnClickListener(this);
        harbor.setOnClickListener(this);
        whale.setOnClickListener(this);
        back.setOnClickListener(this);

        selected = 1;
        changeSelected(selected);

        return view;
    }


    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.first:
                selected = 1;
                break;
            case R.id.second:
                selected = 2;
                break;
            case R.id.third:
                selected = 3;
                break;
            case R.id.fourth:
                selected = 4;
                break;
            case R.id.fifth:
                selected = 5;
                break;
            case R.id.sixth:
                selected = 6;
                break;
            case R.id.seventh:
                selected = 7;
                break;
            case R.id.eighth:
                selected = 8;
                break;
            case R.id.ninth:
                selected = 9;
                break;
            case R.id.btnBack:
                Intent intent = new Intent(view.getContext(),MainActivity.class);
                startActivity(intent);
                break;
        }
        changeSelected(selected);
    }

    private void changeSelected(int selected){
        rain.setImageIcon(Icon.createWithResource(getActivity(),R.drawable.rain));
        wave.setImageIcon(Icon.createWithResource(getActivity(), R.drawable.wave));
        fire.setImageIcon(Icon.createWithResource(getActivity(),R.drawable.fire));
        seagull.setImageIcon(Icon.createWithResource(getActivity(),R.drawable.seagull));
        walkingwater.setImageIcon(Icon.createWithResource(getActivity(),R.drawable.walkingwater));
        wind.setImageIcon(Icon.createWithResource(getActivity(),R.drawable.wind));
        harbor.setImageIcon(Icon.createWithResource(getActivity(),R.drawable.harbor));
        whale.setImageIcon(Icon.createWithResource(getActivity(),R.drawable.whale));
        thunder.setImageIcon(Icon.createWithResource(getActivity(),R.drawable.thunder));
        switch (selected){
            case 1:
                wave.setImageIcon(Icon.createWithResource(getActivity(),R.drawable.waveselected));
                break;
            case 2:
                seagull.setImageIcon(Icon.createWithResource(getActivity(),R.drawable.seagullselected));
                break;
            case 3:
                walkingwater.setImageIcon(Icon.createWithResource(getActivity(),R.drawable.walkingwater));
                break;
            case 4:
                wind.setImageIcon(Icon.createWithResource(getActivity(),R.drawable.windselected));
                break;
            case 5:
                rain.setImageIcon(Icon.createWithResource(getActivity(),R.drawable.rainselected));
                break;
            case 6:
                thunder.setImageIcon(Icon.createWithResource(getActivity(),R.drawable.thunderselected));
                break;
            case 7:
                harbor.setImageIcon(Icon.createWithResource(getActivity(),R.drawable.harborselected));
                break;
            case 8:
                whale.setImageIcon(Icon.createWithResource(getActivity(),R.drawable.whaleselected));
                break;
            case 9:
                fire.setImageIcon(Icon.createWithResource(getActivity(),R.drawable.fireselected));
                break;
        }
    }

}
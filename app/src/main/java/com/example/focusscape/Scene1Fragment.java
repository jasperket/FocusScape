package com.example.focusscape;

import android.graphics.drawable.Icon;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

public class Scene1Fragment extends Fragment implements View.OnClickListener {
    View view;
    ImageView rain, wave, fire, bird, frog, cricket;

    int selected;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_scene1, container, false);

        rain = view.findViewById(R.id.first);
        wave = view.findViewById(R.id.second);
        fire = view.findViewById(R.id.third);
        bird = view.findViewById(R.id.fourth);
        frog = view.findViewById(R.id.fifth);
        cricket = view.findViewById(R.id.sixth);

        rain.setOnClickListener(this);
        wave.setOnClickListener(this);
        fire.setOnClickListener(this);
        bird.setOnClickListener(this);
        frog.setOnClickListener(this);
        cricket.setOnClickListener(this);

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
        }
        changeSelected(selected);
    }

    private void changeSelected(int selected){
        rain.setImageIcon(Icon.createWithResource(getActivity(),R.drawable.rain));
        wave.setImageIcon(Icon.createWithResource(getActivity(), R.drawable.wave));
        fire.setImageIcon(Icon.createWithResource(getActivity(),R.drawable.fire));
        bird.setImageIcon(Icon.createWithResource(getActivity(),R.drawable.bird));
        frog.setImageIcon(Icon.createWithResource(getActivity(),R.drawable.frogs));
        cricket.setImageIcon(Icon.createWithResource(getActivity(),R.drawable.crickets));
        switch (selected){
            case 1:
                rain.setImageIcon(Icon.createWithResource(getActivity(),R.drawable.rainselected));
                break;
            case 2:
                wave.setImageIcon(Icon.createWithResource(getActivity(),R.drawable.rainselected));
                break;
            case 3:
                fire.setImageIcon(Icon.createWithResource(getActivity(),R.drawable.rainselected));
                break;
            case 4:
                bird.setImageIcon(Icon.createWithResource(getActivity(),R.drawable.rainselected));
                break;
            case 5:
                frog.setImageIcon(Icon.createWithResource(getActivity(),R.drawable.rainselected));
                break;
            case 6:
                cricket.setImageIcon(Icon.createWithResource(getActivity(),R.drawable.rainselected));
                break;
        }
    }
}
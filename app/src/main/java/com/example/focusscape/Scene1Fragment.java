package com.example.focusscape;


import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.Icon;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.os.Bundle;
import androidx.fragment.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.SeekBar;

public class Scene1Fragment extends Fragment implements View.OnClickListener{
    View view;
    ImageView rain, wind, fire, bird, frog, cricket, back, owl, thunder, leaves;
    int selected;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_scene1, container, false);

        bird = view.findViewById(R.id.first);
        frog = view.findViewById(R.id.second);
        cricket = view.findViewById(R.id.third);
        wind = view.findViewById(R.id.fourth);
        rain = view.findViewById(R.id.fifth);
        thunder = view.findViewById(R.id.sixth);
        owl = view.findViewById(R.id.seventh);
        fire = view.findViewById(R.id.eighth);
        leaves = view.findViewById(R.id.ninth);
        back = view.findViewById(R.id.btnBack);

        rain.setOnClickListener(this);
        wind.setOnClickListener(this);
        fire.setOnClickListener(this);
        bird.setOnClickListener(this);
        frog.setOnClickListener(this);
        cricket.setOnClickListener(this);
        thunder.setOnClickListener(this);
        owl.setOnClickListener(this);
        leaves.setOnClickListener(this);
        back.setOnClickListener(this);


        selected = 1;
        changeSelected(selected);
        passSelected(selected);

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
        passSelected(selected);
        changeSelected(selected);
    }

    private void changeSelected(int selected){
        rain.setImageIcon(Icon.createWithResource(getActivity(),R.drawable.rain));
        wind.setImageIcon(Icon.createWithResource(getActivity(), R.drawable.wind));
        fire.setImageIcon(Icon.createWithResource(getActivity(),R.drawable.fire));
        bird.setImageIcon(Icon.createWithResource(getActivity(),R.drawable.bird));
        frog.setImageIcon(Icon.createWithResource(getActivity(),R.drawable.frogs));
        cricket.setImageIcon(Icon.createWithResource(getActivity(),R.drawable.crickets));
        thunder.setImageIcon(Icon.createWithResource(getActivity(),R.drawable.thunder));
        owl.setImageIcon(Icon.createWithResource(getActivity(),R.drawable.owl));
        leaves.setImageIcon(Icon.createWithResource(getActivity(),R.drawable.leaves));
        switch (selected){
            case 1:
                bird.setImageIcon(Icon.createWithResource(getActivity(),R.drawable.birdselected));
                break;
            case 2:
                frog.setImageIcon(Icon.createWithResource(getActivity(),R.drawable.frogselected));
                break;
            case 3:
                cricket.setImageIcon(Icon.createWithResource(getActivity(),R.drawable.cricketsselected));
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
                owl.setImageIcon(Icon.createWithResource(getActivity(),R.drawable.owlselected));
                break;
            case 8:
                fire.setImageIcon(Icon.createWithResource(getActivity(),R.drawable.fireselected));
                break;
            case 9:
                leaves.setImageIcon(Icon.createWithResource(getActivity(),R.drawable.leavesselected));
                break;
        }
    }


    private void passSelected(int selected){
        FragmentListener listener = (FragmentListener) getActivity();
        if(listener != null){
            listener.onDataReceived(selected);
        }
    }

}
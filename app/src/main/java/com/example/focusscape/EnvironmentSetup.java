package com.example.focusscape;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;

import com.example.focusscape.pomodorotimer.PomodoroTimer;
import com.google.android.material.button.MaterialButton;
import com.google.android.material.checkbox.MaterialCheckBox;

import java.util.ArrayList;

public class EnvironmentSetup extends AppCompatActivity {

    MaterialCheckBox phoneAway;
    MaterialCheckBox workRelated;
    MaterialCheckBox rehydrate;
    MaterialCheckBox toDo;
    MaterialButton btnStartWorking;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_environment_setup);

        phoneAway = findViewById(R.id.checkBoxPhoneAway);
        workRelated = findViewById(R.id.checkBoxWorkRelated);
        rehydrate = findViewById(R.id.checkBoxRehydrate);
        toDo = findViewById(R.id.checkBoxToDo);
        btnStartWorking = findViewById(R.id.btnStartWorking);


        ArrayList<CheckBox> checkBoxes = new ArrayList<>();
        checkBoxes.add(phoneAway);
        checkBoxes.add(workRelated);
        checkBoxes.add(rehydrate);
        checkBoxes.add(toDo);

        for(CheckBox c : checkBoxes) {
            c.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if(phoneAway.isChecked() && workRelated.isChecked() && rehydrate.isChecked() &&
                    toDo.isChecked()) {
                        btnStartWorking.setEnabled(true);
                    } else {
                        btnStartWorking.setEnabled(false);
                    }
                }
            });
        }

        btnStartWorking.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(view.getContext(), MainActivity.class);
                startActivity(intent);
            }
        });
    }
}
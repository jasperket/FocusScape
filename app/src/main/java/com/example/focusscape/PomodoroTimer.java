package com.example.focusscape;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlarmManager;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Build;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.progressindicator.CircularProgressIndicator;

import java.util.Locale;

public class PomodoroTimer extends AppCompatActivity implements PomodoroSettingsFragment.SettingsDialogListener {
    private TextView mTextViewCountdown;
    private Button mButtonStartPause;
    private Button mButtonReset;

    private CountDownTimer mCountDownTimer;

    private boolean mTimerRunning;

    private long mStartTimeInMillis;
    private long mWorkTimeInMillis;
    private long mBreakTimeInMillis;
    private boolean mIsWorking;
    private long mTimeLeftInMillis;
    private long mEndTime;

    private AlarmManager mAlarmManager;

    private Intent mAlarmIntent;
    private PendingIntent alarmPendingIntent;

    private CircularProgressIndicator timeCircularIndicator;
    private Button btnPomodoroSettings;
    private TextView txtIsWorking;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pomodoro_timer);

        mTextViewCountdown = findViewById(R.id.textViewCountdown);
        mButtonStartPause = findViewById(R.id.btnStartPause);
        mButtonReset = findViewById(R.id.btnReset);
        txtIsWorking = findViewById(R.id.txtIsWorking);

        timeCircularIndicator = findViewById(R.id.cpiTime);
        btnPomodoroSettings = findViewById(R.id.btnPomodoroTimerSettings);

        btnPomodoroSettings.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openDialog();
            }
        });

        mButtonStartPause.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                mAlarmManager = (AlarmManager) getSystemService(ALARM_SERVICE);
                mAlarmIntent = new Intent(view.getContext(), PomodoroAlarmReceiver.class);
                PendingIntent mAlarmPendingIntent = PendingIntent.getBroadcast(view.getContext(),
                        0, mAlarmIntent, PendingIntent.FLAG_IMMUTABLE);

                if(mTimerRunning) {
                    pauseTimer();
                    mAlarmManager.cancel(mAlarmPendingIntent);
                } else {
                    startTimer();
                    mAlarmManager.setExact(AlarmManager.RTC_WAKEUP,
                            System.currentTimeMillis() + mTimeLeftInMillis,mAlarmPendingIntent);
                }
            }
        });

        mButtonReset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                resetTimer();
            }
        });

        createNotificationChannel();
    }

    private void openDialog() {
        PomodoroSettingsFragment settingsDialog = new PomodoroSettingsFragment();
        settingsDialog.show(getSupportFragmentManager(),"Settings Dialog");
    }

    private void createNotificationChannel() {
        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            CharSequence name = "PomodoroAlarm";
            String description = "Channel for alarm";
            int importance = NotificationManager.IMPORTANCE_HIGH;
            NotificationChannel channel = new NotificationChannel("focusscapealarm",name,importance);
            channel.setDescription(description);

            NotificationManager notificationManager = getSystemService(NotificationManager.class);
            notificationManager.createNotificationChannel(channel);
        }
    }

    private void setTime(long milliseconds) {
        mStartTimeInMillis = milliseconds;
        resetTimer();
        closeKeyboard();
    }

    public void startTimer() {
        mEndTime = System.currentTimeMillis() + mTimeLeftInMillis;
        mCountDownTimer = new CountDownTimer(mTimeLeftInMillis, 1000) {
            @Override
            public void onTick(long millisUntilFinished) {
                mTimeLeftInMillis = millisUntilFinished;
                updateCountDownTextAndIndicator();
            }

            @Override
            public void onFinish() {
                mTimerRunning = false;
                if(mIsWorking) {
                    mIsWorking = false;
                } else {
                    mIsWorking = true;
                }
                updateTimerInterface();
            }
        }.start();

        mTimerRunning = true;
        updateTimerInterface();
    }

    public void pauseTimer() {
        mCountDownTimer.cancel();
        mTimerRunning = false;
        updateTimerInterface();
    }

    public void resetTimer() {
        if(mIsWorking) {
            mStartTimeInMillis = mWorkTimeInMillis;
        } else {
            mStartTimeInMillis = mBreakTimeInMillis;
        }
        mTimeLeftInMillis = mStartTimeInMillis;
        updateCountDownTextAndIndicator();
        updateTimerInterface();
    }

    public void updateCountDownTextAndIndicator() {
        int hours = (int) mTimeLeftInMillis / 1000 / 3600;
        int minutes = (int) mTimeLeftInMillis / 1000 % 3600 / 60;
        int seconds = (int) mTimeLeftInMillis / 1000 % 60;

        String timeLeftFormatted;
        if(hours > 0) {
            timeLeftFormatted = String.format(Locale.getDefault(),
                    "%d:%02d:%02d",hours,minutes,seconds);
        } else {
            timeLeftFormatted = String.format(Locale.getDefault(),
                    "%02d:%02d",minutes,seconds);
        }
        mTextViewCountdown.setText(timeLeftFormatted);

        double progress = (mTimeLeftInMillis/ (double) mStartTimeInMillis) * 100;
        System.out.println("prog: " + progress+"%, timeLeft = " +mTimeLeftInMillis+", startTime = "+
                mStartTimeInMillis+", workTime = " +mWorkTimeInMillis/60000+
                ", breakTime = "+mBreakTimeInMillis/60000);
        timeCircularIndicator.setProgress((int) progress, true);
    }

    private void updateTimerInterface() {
        if(mIsWorking) {
            txtIsWorking.setText("Work");
        } else {
            txtIsWorking.setText("Break");
        }

        if(mTimerRunning) {
            mButtonReset.setVisibility(View.INVISIBLE);
            mButtonStartPause.setText("Pause");
        } else {
            mButtonStartPause.setText("Start");

            if(mTimeLeftInMillis < 1000) {
                mButtonStartPause.setVisibility(View.INVISIBLE);
            } else {
                mButtonStartPause.setVisibility(View.VISIBLE);
            }

            if(mTimeLeftInMillis < mStartTimeInMillis) {
                mButtonReset.setVisibility(View.VISIBLE);
            } else {
                mButtonReset.setVisibility(View.INVISIBLE);
            }
        }
    }

    private void closeKeyboard() {
        View view = this.getCurrentFocus();
        if(view != null) {
            InputMethodManager imm = (InputMethodManager) getSystemService(INPUT_METHOD_SERVICE);
            imm.hideSoftInputFromWindow(view.getWindowToken(),0);
        }
    }

    @Override
    protected void onStop() {
        super.onStop();

        SharedPreferences prefs = getSharedPreferences("prefs",MODE_PRIVATE);
        SharedPreferences.Editor editor = prefs.edit();

        editor.putLong("millisLeft",mTimeLeftInMillis);
        editor.putBoolean("timerRunning",mTimerRunning);
        editor.putLong("endTime",mEndTime);
        editor.putBoolean("isWorking",mIsWorking);
        editor.putLong("startTimeInMillis",mStartTimeInMillis);

        editor.apply();

        if(mCountDownTimer != null) {
            mCountDownTimer.cancel();
        }
    }

    @Override
    protected void onStart() {
        super.onStart();

        SharedPreferences prefs = getSharedPreferences("prefs",MODE_PRIVATE);


        mTimerRunning = prefs.getBoolean("timerRunning", false);
        mWorkTimeInMillis = prefs.getLong("workTimeInMillis", 1500000);
        mBreakTimeInMillis = prefs.getLong("breakTimeInMillis",300000);
        mStartTimeInMillis = prefs.getLong("startTimeInMillis", mWorkTimeInMillis);
        mTimeLeftInMillis = prefs.getLong("millisLeft", mStartTimeInMillis);
        mIsWorking = prefs.getBoolean("isWorking",true);
        updateCountDownTextAndIndicator();
        updateTimerInterface();

        if(mTimerRunning) {
            mEndTime = prefs.getLong("endTime",0);
            mTimeLeftInMillis = mEndTime - System.currentTimeMillis();

            if(mTimeLeftInMillis < 0) {
                mTimeLeftInMillis = 0;
                mTimerRunning = false;
                updateCountDownTextAndIndicator();
                updateTimerInterface();
            } else {
                startTimer();
            }
        }
    }

    @Override
    public void applyTexts(long workStartTime, long breakStartTime) {
        mWorkTimeInMillis = workStartTime;
        mStartTimeInMillis = mWorkTimeInMillis;
        mBreakTimeInMillis = breakStartTime;
        mIsWorking = true;

        SharedPreferences prefs = getSharedPreferences("prefs",MODE_PRIVATE);
        SharedPreferences.Editor editor = prefs.edit();
        editor.putLong("workTimeInMillis",mWorkTimeInMillis);
        editor.putLong("breakTimeInMillis",mBreakTimeInMillis);
        editor.apply();

        pauseTimer();
        resetTimer();
    }
}
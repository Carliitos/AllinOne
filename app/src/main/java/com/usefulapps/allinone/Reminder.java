package com.usefulapps.allinone;

import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class Reminder extends AppCompatActivity {

    TextView txt;
    EditText minutes;
    EditText seconds;
    Button start;
    CountDownTimer cd;

    int min, sec, time;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reminder);

        start = findViewById(R.id.start);

        minutes = findViewById(R.id.minutes);
        seconds = findViewById(R.id.seconds);

        start.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {

                min = Integer.valueOf(minutes.getText().toString());
                sec = Integer.valueOf(seconds.getText().toString());

                setReminder(min*60+sec);
            }
        });
    }

    public void setReminder(int time) {
        txt = findViewById(R.id.txt);

        start = findViewById(R.id.start);
        start.setText("STOP");

        cd = new CountDownTimer(time*1000, 1000) {

            public void onTick(long millisUntilFinished) {
                txt.setText("seconds remaining: " + millisUntilFinished / 1000);
            }

            public void onFinish() {
                txt.setText("done!");
            }
        }.start();

        start.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = getIntent();
                finish();
                startActivity(intent);
            }
        });

    }
}

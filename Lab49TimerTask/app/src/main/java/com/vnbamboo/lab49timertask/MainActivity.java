package com.vnbamboo.lab49timertask;

import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;

public class MainActivity extends AppCompatActivity {

    Random random = new Random();
    @Override
    protected void onCreate( Bundle savedInstanceState ) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        final TextView txtTime, txtBack;

        txtTime = findViewById(R.id.txtTimer);
        txtBack = findViewById(R.id.txtBack);

        final SimpleDateFormat simpleDateFormat = new SimpleDateFormat("hh:mm:ss aaa");
        TimerTask timerTask = new TimerTask() {
            @Override
            public void run() {
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        Calendar calendar = Calendar.getInstance();
                        txtTime.setText(simpleDateFormat.format(calendar.getTime()));
                        int r = random.nextInt(256);
                        int g = random.nextInt(256);
                        int b = random.nextInt(256);
                        txtBack.setBackgroundColor(Color.argb(255, r, g, b));
                    }
                });
            }
        };
        Timer timer = new Timer();
        timer.schedule(timerTask, 0,1000);
    }
}

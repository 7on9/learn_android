package com.vnbamboo.lab49asynctask;

import android.content.Context;
import android.os.AsyncTask;
import android.os.SystemClock;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.Random;

public class MainActivity extends AppCompatActivity {

    Button btnCreate;
    EditText txtBtn;
    TextView txtPercent;
    LinearLayout llBtn;
    LinearLayout.LayoutParams layoutParams =
            new LinearLayout.LayoutParams(
                    LinearLayout.LayoutParams.MATCH_PARENT,
                    LinearLayout.LayoutParams.WRAP_CONTENT);
    Random random = new Random();
    @Override
    protected void onCreate( Bundle savedInstanceState ) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        setControl();
        addEvent();
    }

    private void setControl() {
        btnCreate = findViewById(R.id.btnGenerate);
        txtBtn = findViewById(R.id.txtNumBtn);
        txtPercent = findViewById(R.id.txtPercent);
        llBtn = findViewById(R.id.llBtn);
    }

    private void addEvent(){
        btnCreate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick( View v ) {
                int n = Integer.parseInt(txtBtn.getText().toString());
                ButtonTask task = new ButtonTask();
                task.execute(n);
            }
        });
    }

    class ButtonTask extends AsyncTask<Integer, Integer, Void>{

        @Override
        protected Void doInBackground( Integer... integers ) {
            int n = integers[0];
            for (int i = 0; i < n; i++){
                publishProgress(i*100/n, random.nextInt(100));
                SystemClock.sleep(100);
            }
            return null;
        }

        @Override
        protected void onPreExecute() {
            txtPercent.setText("0%");
            llBtn.removeAllViews();
            super.onPreExecute();
        }

        @Override
        protected void onPostExecute( Void aVoid ) {
            txtPercent.setText("100%");
            super.onPostExecute(aVoid);
        }

        @Override
        protected void onProgressUpdate( Integer... values ) {
            super.onProgressUpdate(values);
            txtPercent.setText(values[0] + "%");
            Button btn = new Button(MainActivity.this);
            btn.setText("btn val : "  + values[1]);
            btn.setLayoutParams(layoutParams);
            llBtn.addView(btn);
        }
    }
}

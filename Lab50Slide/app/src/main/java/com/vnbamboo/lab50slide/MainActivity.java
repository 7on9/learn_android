package com.vnbamboo.lab50slide;

import android.annotation.SuppressLint;
import android.app.Dialog;
import android.content.DialogInterface;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.os.Build;
import android.provider.ContactsContract;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.ImageView;

import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;

public class MainActivity extends AppCompatActivity {

    ArrayList<String> links = new ArrayList<>();
    Button btnNext, btnPrev;
    int idImg = 0;
    int time;
    Timer timer;
    CheckBox cbxAuto;
    ImageView imgDownload;
    Dialog dialog;

    @Override
    protected void onCreate( Bundle savedInstanceState ) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setControl();
        addEvent();
    }

    private void addEvent() {
        DownImage task = new DownImage();
        task.execute(links.get(idImg));
        cbxAuto.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @SuppressLint("NewApi")
            @Override
            public void onCheckedChanged( CompoundButton buttonView, boolean isChecked ) {
                if (isChecked) {
                    btnNext.setEnabled(false);
                    btnPrev.setEnabled(false);
                    dialog = new Dialog(MainActivity.this);
                    dialog.setContentView(R.layout.dialog_setting_layout);
                    final EditText txtTime = dialog.findViewById(R.id.txtTime);
                    Button btnSave = dialog.findViewById(R.id.btnSave);
                    Button btnCancel = dialog.findViewById(R.id.btnCancel);
                    btnCancel.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick( View v ) {
                            dialog.dismiss();
                            cbxAuto.setChecked(false);
                        }
                    });
                    btnSave.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick( View v ) {
                            dialog.dismiss();
                            time = Integer.parseInt(txtTime.getText().toString());
                            autoShow();
                        }
                    });
                    dialog.show();
                    autoShow();
                } else {
                    btnNext.setEnabled(true);
                    btnPrev.setEnabled(true);
                    time = 0;
                    timer.cancel();
                }
            }
        });
        btnPrev.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick( View v ) {
                idImg = (idImg - 1) % links.size();
                idImg = idImg < 0 ? links.size() - 1 : idImg;
                DownImage task = new DownImage();
                task.execute(links.get(idImg));
            }
        });
        btnNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick( View v ) {
                idImg = (idImg + 1) % links.size();
                DownImage task = new DownImage();
                task.execute(links.get(idImg));
            }
        });
    }

    private void setControl() {
        //add link
        links.add("http://mangaqq.com/244/25.0/3.jpg");
        links.add("http://mangaqq.com/244/25.0/4.jpg");
        links.add("http://mangaqq.com/244/25.0/5.jpg");
        links.add("http://mangaqq.com/244/25.0/6.jpg");
        links.add("http://mangaqq.com/244/25.0/7.jpg");
        links.add("http://mangaqq.com/244/25.0/8.jpg");
        links.add("http://mangaqq.com/244/25.0/9.jpg");
        links.add("http://mangaqq.com/244/25.0/10.jpg");
        links.add("http://mangaqq.com/244/25.0/11.jpg");
        links.add("http://mangaqq.com/244/25.0/12.jpg");
        links.add("http://mangaqq.com/244/25.0/13.jpg");
        links.add("http://mangaqq.com/244/25.0/14.jpg");
        links.add("http://mangaqq.com/244/25.0/15.jpg");
        links.add("http://mangaqq.com/244/25.0/16.jpg");
        links.add("http://mangaqq.com/244/25.0/17.jpg");
        links.add("http://mangaqq.com/244/25.0/18.jpg");
        links.add("http://mangaqq.com/244/25.0/19.jpg");
        links.add("http://mangaqq.com/244/25.0/20.jpg");
        links.add("http://mangaqq.com/244/25.0/21.jpg");
        links.add("http://mangaqq.com/244/25.0/22.jpg");
        links.add("http://mangaqq.com/244/25.0/23.jpg");

        btnNext = findViewById(R.id.btnNext);
        btnPrev = findViewById(R.id.btnPre);
        cbxAuto = findViewById(R.id.cbxAuto);
        imgDownload = findViewById(R.id.imgDownload);
    }

    private void autoShow() {
        if(time == 0){
//            timer.cancel;
            return;
        }

        TimerTask timerTask = new TimerTask() {
            @Override
            public void run() {
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        DownImage task = new DownImage();
                        task.execute(links.get(idImg));
                        idImg = (idImg + 1) % links.size();
                    }
                });
            }
        };
        timer = new Timer();
        timer.schedule(timerTask, 0, time * 1000);
    }

    class DownImage extends AsyncTask<String, Void, Bitmap> {
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
        }

        @Override
        protected void onPostExecute( Bitmap bitmap ) {
            super.onPostExecute(bitmap);
            imgDownload.setImageBitmap(bitmap);
        }

        @Override
        protected void onProgressUpdate( Void... values ) {
            super.onProgressUpdate(values);
        }

        @Override
        protected Bitmap doInBackground( String... strings ) {
            try {
                URL url = new URL(strings[0]);
                HttpURLConnection connection = (HttpURLConnection) url.openConnection();
                return BitmapFactory.decodeStream(connection.getInputStream());
            } catch (Exception e) {

            }
            return null;
        }
    }
}
package com.vnbamboo.b50asynctask2;

import android.app.ProgressDialog;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import java.net.HttpURLConnection;
import java.net.URL;

public class MainActivity extends AppCompatActivity {

    Button btnDownload;
    ImageView imgDownload;
    ProgressDialog dialog;
    @Override
    protected void onCreate( Bundle savedInstanceState ) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        btnDownload = findViewById(R.id.btnDownload);
        imgDownload = findViewById(R.id.imgDownload);
        dialog = new ProgressDialog(MainActivity.this);
        dialog.setTitle("Thông báo");
        dialog.setMessage("Đang tải hình, hãy đợi ...");

        btnDownload.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick( View v ) {
                DownImage task = new DownImage();
                task.execute("https://upload.wikimedia.org/wikipedia/id/d/d0/CaptainMarvelPoster2019.jpg");
            }
        });
    }
    class DownImage extends AsyncTask<String, Void, Bitmap>{
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            dialog.show();
        }

        @Override
        protected void onPostExecute( Bitmap bitmap ) {
            super.onPostExecute(bitmap);
            dialog.dismiss();
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
                Bitmap bitmap = BitmapFactory.decodeStream(connection.getInputStream());
                return bitmap;
            }catch (Exception e){

            }
            return null;
        }
    }
}

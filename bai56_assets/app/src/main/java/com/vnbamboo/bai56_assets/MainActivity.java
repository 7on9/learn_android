package com.vnbamboo.bai56_assets;

import android.content.res.AssetFileDescriptor;
import android.content.res.AssetManager;
import android.graphics.Typeface;
import android.media.MediaPlayer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import java.io.IOException;

public class MainActivity extends AppCompatActivity {

    ListView lsvFonts;
    ArrayAdapter<String> arrayAdapter;
    TextView txtTextShow;
    @Override
    protected void onCreate( Bundle savedInstanceState ) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        txtTextShow = findViewById(R.id.txtTextShow);
        lsvFonts = findViewById(R.id.lsvFonts);
        arrayAdapter = new ArrayAdapter<>(MainActivity.this, android.R.layout.simple_list_item_1);
        lsvFonts.setAdapter(arrayAdapter);

        try {
            AssetManager assetManager = getAssets();
            String [] arrFonts = assetManager.list("fonts");
            arrayAdapter.addAll(arrFonts);
        } catch (IOException e) {
            e.printStackTrace();
        }

        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.KITKAT) {
            lsvFonts.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick( AdapterView<?> parent, View view, int position, long id ) {
                    String fontName = arrayAdapter.getItem(position);
                    Typeface typeface = Typeface.createFromAsset(getAssets(), "fonts/" + fontName);
                    txtTextShow.setTypeface(typeface);

                    try {
                        AssetFileDescriptor assetFileDescriptor = getAssets().openFd("musics/press.wav");
                        MediaPlayer player = new MediaPlayer();
                        player.setDataSource(assetFileDescriptor.getFileDescriptor(), assetFileDescriptor.getStartOffset(), assetFileDescriptor.getLength());
                        assetFileDescriptor.close();
                        player.prepare();
                        player.start();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            });
        }
    }
}

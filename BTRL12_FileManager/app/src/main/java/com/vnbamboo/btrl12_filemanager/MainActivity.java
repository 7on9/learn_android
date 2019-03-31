package com.vnbamboo.btrl12_filemanager;

import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Environment;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.vnbamboo.adapter.FileAdapter;
import com.vnbamboo.model.FileSupport;
import com.vnbamboo.model.MyFile;
import com.vnbamboo.util.Converter;

import java.io.File;

public class MainActivity extends AppCompatActivity {
    ListView lsvFile;
    FileAdapter fileAdapter;
    @Override
    protected void onCreate( Bundle savedInstanceState ) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        setControl();
        addEvent();

        readDir();
    }

    private void readDir() {
        String root = Environment.getExternalStorageDirectory().getAbsolutePath();
        Intent intentParent = getIntent();
        if(intentParent != null){
            if(intentParent.hasExtra("PARENT")) {
                root = intentParent.getStringExtra("PARENT");
            }
        }
        File file = new File(root);
        File []files = file.listFiles();
        fileAdapter.clear();
        for (File f : files){
            MyFile myFile = new MyFile();
            myFile.setName(f.getName());
            myFile.setPath(f.getAbsolutePath());
            if(f.isDirectory()){
                myFile.setFileType(FileSupport.FOLDER);
                myFile.setDecription("Có " + f.list().length + " tập tin con.");
                myFile.setHasChild(true);
            }else {
                if(f.getPath().toLowerCase().endsWith(".mp3")) {
                    myFile.setFileType(FileSupport.MP3);
                }else {
                    if(f.getPath().toLowerCase().endsWith(".mp4")) {
                        myFile.setFileType(FileSupport.MP4);
                    }else {
                        myFile.setFileType(FileSupport.NOT_SUPPORT);
                    }
                }
                myFile.setDecription(Converter.humanReadableByteCount(f.length(), true));
                myFile.setHasChild(false);
            }
            fileAdapter.add(myFile);

        }
    }

    private void addEvent() {
        lsvFile.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick( AdapterView<?> parent, View view, int position, long id ) {
                handleOpenFile(fileAdapter.getItem(position));
            }
        });
    }

    private void handleOpenFile( MyFile file ) {
        if (file.getFileType() == FileSupport.NOT_SUPPORT) {
            AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
            builder.setTitle("Thông báo");
            builder.setMessage("File không được hỗ trợ");
            builder.setIcon(android.R.drawable.ic_dialog_alert);
            builder.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                @Override
                public void onClick( DialogInterface dialog, int which ) {
                    dialog.dismiss();
                }
            });
            builder.show();
        } else {
            if(file.getHasChild()){
                Intent intent = new Intent(MainActivity.this, MainActivity.class);
                intent.putExtra("PARENT", file.getPath());
                startActivity(intent);
            }else {
                Intent intent = new Intent(Intent.ACTION_VIEW);
                intent.setData(Uri.parse(file.getPath()));
                startActivity(intent);
            }
        }
    }

    private void setControl() {
        lsvFile = findViewById(R.id.lsvFile);
        fileAdapter = new FileAdapter(this, R.layout.item_layout);
        lsvFile.setAdapter(fileAdapter);
    }
}

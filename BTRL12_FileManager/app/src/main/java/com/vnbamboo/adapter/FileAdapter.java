package com.vnbamboo.adapter;

import android.app.Activity;
import android.app.Application;
import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.vnbamboo.btrl12_filemanager.R;
import com.vnbamboo.model.MyFile;

public class FileAdapter extends ArrayAdapter<MyFile> {
    Activity context;
    int resouce;

    public FileAdapter( Activity context, int resource ) {
        super(context, resource);
        this.context = context;
        this.resouce = resource;
    }
    
    @Override
    public View getView( int position, View convertView,  ViewGroup parent ) {
        View customView = this.context.getLayoutInflater().inflate(this.resouce, null);
        TextView txtFileName = customView.findViewById(R.id.txtName);
        TextView txtSize = customView.findViewById(R.id.txtSize);
        ImageView imgFile = customView.findViewById(R.id.imgFile);
        MyFile myFile = getItem(position);

        switch (myFile.getFileType()){
            case MP3:
                imgFile.setImageResource(R.mipmap.mp3);
                break;
            case MP4:
                imgFile.setImageResource(R.mipmap.mp3);
                break;
            case FOLDER:
                imgFile.setImageResource(R.mipmap.folder);
                break;
            default:
                imgFile.setImageResource(R.mipmap.file);
                break;
        }
        txtSize.setText(myFile.getDecription());
        txtFileName.setText(myFile.getName());
        return customView;
    }
}

package com.vnbamboo.bai68_sp;

import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import java.util.HashSet;
import java.util.Set;

public class MainActivity extends AppCompatActivity {

    Button btnSave;
    @Override
    protected void onCreate( Bundle savedInstanceState ) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        btnSave = findViewById(R.id.btnSave);
        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick( View v ) {
                writeSP();
            }
        });
    }

    private void writeSP() {
        SharedPreferences pre=getSharedPreferences("demo", MODE_PRIVATE);
        //tạo đối tượng Editor để lưu thay đổi
        SharedPreferences.Editor editor=pre.edit();
        {
            //lưu vào editor
            editor.putString("b", "blue");
            editor.putBoolean("yes", !false);
            editor.putFloat("f", 3.14f);
            editor.putBoolean("remembered", true);
            Set<String> data = new HashSet<>();
            data.add("I");
            data.add("am");
            data.add("Long");
            editor.putStringSet("name", data);
        }
        //chấp nhận lưu xuống file
        editor.commit();
    }

}

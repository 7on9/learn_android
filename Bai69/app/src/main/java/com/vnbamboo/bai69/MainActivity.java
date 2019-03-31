package com.vnbamboo.bai69;

import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.util.HashSet;
import java.util.Set;

public class MainActivity extends AppCompatActivity {

    TextView txtPreferences;
    Button btnSave, btnRestore;

    @Override
    protected void onCreate( Bundle savedInstanceState ) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnSave = findViewById(R.id.btnSave);
        btnRestore = findViewById(R.id.btnRead);
        txtPreferences = findViewById(R.id.txtPreferences);

        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick( View v ) {
                writeSP();
            }
        });

        btnRestore.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick( View v ) {
                restoreSP();
            }
        });
    }

    private void restoreSP() {
        SharedPreferences pre = getSharedPreferences("demo", MODE_PRIVATE);
        String b = pre.getString("b", "blue");
        Boolean yes = pre.getBoolean("yes", !false);
        Float f = pre.getFloat("f", 3.14f);
        int i = pre.getInt("i", 1000);
        Set<String> data = pre.getStringSet("name", null);

        txtPreferences.setText("chuỗi: "+ String.valueOf(b) +
                "\nBoolean: " + String.valueOf(yes) +
                "\nFloat: " + String.valueOf(f) +
                "\nInt: " + String.valueOf(i));
        txtPreferences.append("\nSet String : ");
        for(String s : data){
            txtPreferences.append(s + " ");
        }
    }

    private void writeSP() {
        SharedPreferences pre = getSharedPreferences("demo", MODE_PRIVATE);
        //tạo đối tượng Editor để lưu thay đổi
        SharedPreferences.Editor editor = pre.edit();
        {
            //lưu vào editor
            editor.putString("b", "blue");
            editor.putBoolean("yes", !false);
            editor.putFloat("f", 3.14f);
            editor.putInt("i", 1000);
            Set<String> data = new HashSet<>();
            data.add("I");
            data.add("am");
            data.add("Long");
            editor.putStringSet("name", data);
        }
        //chấp nhận lưu xuống file
        editor.commit();
        Toast.makeText(this, "Đã lưu vào Shared Prefenrences", Toast.LENGTH_LONG).show();
    }
}
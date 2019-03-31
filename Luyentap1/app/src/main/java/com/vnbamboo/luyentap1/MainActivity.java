package com.vnbamboo.luyentap1;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Vector;

import static java.lang.Math.abs;

public class MainActivity extends AppCompatActivity {
    String arrCan[] = {"Giáp", "Ất", "Bính", "Đinh", "Mậu", "Kỷ", "Canh", "Tân", "Nhâm", "Quý"};
    String arrChi[] = {"Tý", "Sửu", "Dần", "Mão", "Thìn", "Tỵ", "Ngọ", "Mùi", "Thân", "Dậu", "Tuất", "Hợi"};
    Vector<String> lunnarYear = new Vector<>();
    @Override
    protected void onCreate( Bundle savedInstanceState ) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initTable();
        Button btnConvert;
        final EditText txtYear;
        final TextView txtLunarYear;
        btnConvert = findViewById(R.id.btnConvert);
        txtLunarYear = findViewById(R.id.txtLunarYear);
        txtYear = findViewById(R.id.txtYear);
        btnConvert.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick( View v ) {
                txtLunarYear.setText(ConvertToLunarYear(Integer.parseInt(txtYear.getText().toString())));
            }
        });
    }
    private void initTable(){
        for(int i = 0; i < 60; i++){
            lunnarYear.add(arrCan[i%10] + " " + arrChi[i%12]);
        }
    }
    private String ConvertToLunarYear(int year){
        return lunnarYear.get(abs((year - 3) % 60 - 1));
    }
}

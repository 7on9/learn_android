package com.vnbamboo.listviewbasic;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import com.vnbamboo.listviewbasic.Model.Contact;

public class MainActivity extends AppCompatActivity {
    TextView txtName, txtPhone, txtCode;
    Button btnAdd;
    ListView lsvData;
    CustomListViewAdapter customListViewAdapter;
    @Override
    protected void onCreate( Bundle savedInstanceState ) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setControll();
        addEvent();
    }

    private void setControll() {
        txtName = findViewById(R.id.txtName);
        txtPhone = findViewById(R.id.txtPhone);
        txtCode = findViewById(R.id.txtCode);
        btnAdd = findViewById(R.id.btnAdd);
        lsvData = findViewById(R.id.lsvData);
        customListViewAdapter = new CustomListViewAdapter(this, R.layout.contact_layout);
        lsvData.setAdapter(customListViewAdapter);
    }

    private void addEvent() {
        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick( View v ) {
                customListViewAdapter.add(new Contact(txtCode.getText().toString(), txtName.getText().toString(), txtPhone.getText().toString()));
            }
        });
    }


}

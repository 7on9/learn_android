package com.vnbamboo.btrl7;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import com.vnbamboo.btrl7.Model.Contact;

import java.util.Random;

public class MainActivity extends AppCompatActivity {
    CustomListViewAdapter customListViewAdapter;
    ListView lsvData;
    @Override
    protected void onCreate( Bundle savedInstanceState ) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setControll();
        addEvent();
        initData();
    }

    private void initData() {
        String[] banks = {"ACB", "HSBC", "GOLDEN", "ABC"};
        Random random = new Random();
        for (int i = 0; i < 100; i++){
            customListViewAdapter.add(new Contact(String.valueOf(i),
                    "Tên "+ String.valueOf(i),
                    banks[Math.abs(random.nextInt()%4)],
                    "096" + String.valueOf(System.currentTimeMillis()%10000000)));
        }
    }

    private void setControll() {
        lsvData = findViewById(R.id.lsvData);
        customListViewAdapter = new CustomListViewAdapter(this, R.layout.contact_layout);
        lsvData.setAdapter(customListViewAdapter);
    }
    private void addEvent() {
    }


}

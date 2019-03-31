package com.vnbamboo.listviewnangcao;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.Random;

import model.Product;

public class MainActivity extends AppCompatActivity {
    ListView lsvContact;
    CustomListViewAdapter customListViewAdapter;
    Button btnBuy;
    ArrayList<Product> productList;
    @Override
    protected void onCreate( Bundle savedInstanceState ) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setControl();
        makeFakeData();
        addEvent();
    }

    void setControl(){
        lsvContact = findViewById(R.id.lsvData);
        customListViewAdapter = new CustomListViewAdapter(this, R.layout.product_layout);
        lsvContact.setAdapter(customListViewAdapter);
    }
    void addEvent() {

    }
    void makeFakeData(){
        Random random = new Random();
        for (int i = 0; i< 300; i++){
            String[] goodName = {"CocaCola", "Sting", "7UP", "Pepsi"};
            String name = goodName[Math.abs(random.nextInt()%4)];
            Product product = new Product(name, Math.abs(random.nextInt()%20 + 1), "");
            customListViewAdapter.add(product);
        }
    }

}

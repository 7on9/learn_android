package com.vnbamboo.blt8;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.Button;
import android.widget.GridView;

import com.vnbamboo.blt8.model.Product;

import java.util.ArrayList;
import java.util.Random;

public class MainActivity extends AppCompatActivity {
    GridView lsvContact;
    CustomGridViewAdapter customGridViewAdapter;
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
        lsvContact = findViewById(R.id.grdvData);
        customGridViewAdapter = new CustomGridViewAdapter(this, R.layout.product_layout);
        lsvContact.setAdapter(customGridViewAdapter);
    }
    void addEvent() {

    }
    void makeFakeData(){
        Random random = new Random();
        Bitmap bms[] = {BitmapFactory.decodeResource(getResources(), R.mipmap.h1),
                BitmapFactory.decodeResource(getResources(), R.mipmap.h2),
                BitmapFactory.decodeResource(getResources(), R.mipmap.h3),
                BitmapFactory.decodeResource(getResources(), R.mipmap.h4),
                BitmapFactory.decodeResource(getResources(), R.mipmap.h5),
                BitmapFactory.decodeResource(getResources(), R.mipmap.h6),
                BitmapFactory.decodeResource(getResources(), R.mipmap.h7),
                BitmapFactory.decodeResource(getResources(), R.mipmap.h8),
                BitmapFactory.decodeResource(getResources(), R.mipmap.h9)
        };


        for (int i = 0; i< 300; i++){
            String[] goodName = {"Hồng", "Bằng Lăng", "Huệ", "Mai"};
            String name = goodName[Math.abs(random.nextInt()%4)];
            Product product = new Product(name, Math.abs(random.nextInt()%20 + 1), bms[Math.abs(random.nextInt()%9)]);
            customGridViewAdapter.add(product);
        }
    }

}

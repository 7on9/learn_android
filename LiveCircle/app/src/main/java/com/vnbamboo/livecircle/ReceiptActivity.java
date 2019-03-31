package com.vnbamboo.livecircle;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class ReceiptActivity extends AppCompatActivity {

    @Override
    protected void onCreate( Bundle savedInstanceState ) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_receipt);
        TextView txtName = findViewById(R.id.txtName);
        TextView txtGender = findViewById(R.id.txtGender);

        boolean gender = this.getIntent().getBooleanExtra("gender", false);
        txtName.setText(this.getIntent().getStringExtra("name"));
        txtGender.setText(gender ? "Nữ" : "Nam");

        txtGender.setBackgroundResource(gender ? R.color.colorAccent : R.color.colorPrimary);
    }
}

package com.vnbamboo.livecircle;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate( Bundle savedInstanceState ) {
        final Intent intent = new Intent(MainActivity.this, ReceiptActivity.class);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Button btnSendData = findViewById(R.id.btnSendData);
        final EditText txtName = findViewById(R.id.txtName);
        final RadioButton rbtnMan = findViewById(R.id.rbtnMan);
        btnSendData.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick( View v ) {
                intent.putExtra("name", txtName.getText().toString());
                intent.putExtra("gender", rbtnMan.isChecked() ? false : true);
                startActivity(intent);
            }
        });
    }
}

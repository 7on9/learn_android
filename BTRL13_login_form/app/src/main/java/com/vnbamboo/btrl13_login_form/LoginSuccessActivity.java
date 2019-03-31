package com.vnbamboo.btrl13_login_form;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class LoginSuccessActivity extends AppCompatActivity {
    Button btnLogout;
    TextView txtMsg;
    @Override
    protected void onCreate( Bundle savedInstanceState ) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_success);
        txtMsg = (TextView) findViewById(R.id.txtmsg);
        btnLogout = (Button) findViewById(R.id.btnLogout);
        btnLogout.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick( View arg0 ) {
                // TODO Auto-generated method stub
                finish();
            }
        });

        Intent i = getIntent();
        txtMsg.setText("Hello : " + i.getStringExtra("user"));
    }

}
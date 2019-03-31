package com.vnbamboo.tabselector;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TabHost;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    TabHost tabHost;
    Context thisContext = this;
    EditText txtUsername, txtPassword;
    Button btnLogin, btnExit;

    @Override
    protected void onCreate( Bundle savedInstanceState ) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setControl();
        addEvent();
    }

    private void addEvent() {
        tabHost.setOnTabChangedListener(new TabHost.OnTabChangeListener() {
            @Override
            public void onTabChanged( String tabId ) {
                if(tabId.equals("t1")){
                    Toast.makeText(thisContext,"Tab đăng nhập", Toast.LENGTH_LONG).show();
                }else{
                    Toast.makeText(thisContext,"Tab hướng dẫn", Toast.LENGTH_LONG).show();
                }
            }
        });

        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick( View v ) {
                Toast.makeText(thisContext,"username : "
                        + txtUsername.getText().toString()
                        + " pass : "
                        + txtPassword.getText().toString(),
                        Toast.LENGTH_LONG).show();
            }
        });
        btnExit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick( View v ) {
                finish();
            }
        });
    }

    private void setControl() {
        tabHost = (TabHost) findViewById(R.id.tabHost);
        tabHost.setup();

        TabHost.TabSpec tabSpec = tabHost.newTabSpec("t1");
        tabSpec.setContent(R.id.tab1);
        tabSpec.setIndicator("Đăng nhập");
        tabHost.addTab(tabSpec);

        TabHost.TabSpec tabSpec2 = tabHost.newTabSpec("t2");
        tabSpec2.setContent(R.id.tab2);
        tabSpec2.setIndicator("Hướng dẫn");
        tabHost.addTab(tabSpec2);

        btnLogin = findViewById(R.id.btnLogin);
        btnExit = findViewById(R.id.btnExit);
        txtPassword = findViewById(R.id.txtPassword);
        txtUsername = findViewById(R.id.txtUsername);
    }

}

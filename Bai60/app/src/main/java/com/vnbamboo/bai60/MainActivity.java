package com.vnbamboo.bai60;

import android.Manifest;
import android.app.Activity;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.net.Uri;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.telephony.SmsManager;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    Button btnDial, btnCall, btnSMS1, btnSMS2;
    EditText txtPhone, txtMessage;

    @Override
    protected void onCreate( Bundle savedInstanceState ) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setControl();
        addEvent();
    }

    private void addEvent() {
        btnDial.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick( View v ) {
                Uri uri = Uri.parse("tel:" + txtPhone.getText().toString());
                Intent intent = new Intent(Intent.ACTION_DIAL, uri);
                startActivity(intent);
            }
        });
        btnCall.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick( View v ) {
                Uri uri = Uri.parse("tel:" + txtPhone.getText().toString());
                Intent intent = new Intent(Intent.ACTION_CALL, uri);
                startActivity(intent);
            }
        });
        btnSMS1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick( View v ) {
                if(txtMessage.getText().length() == 0){
                    Toast.makeText(MainActivity.this,"Nhập tin nhắn trước đã!", Toast.LENGTH_LONG).show();
                    return;
                }
                SmsManager smsManager = SmsManager.getDefault();
                smsManager.sendTextMessage(txtPhone.getText().toString(), null, txtMessage.getText().toString(), null, null);
            }
        });
        btnSMS2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick( View v ) {
                if(txtMessage.getText().length() == 0){
                    Toast.makeText(MainActivity.this,"Nhập tin nhắn trước đã!", Toast.LENGTH_LONG).show();
                    return;
                }

                SmsManager smsManager = SmsManager.getDefault();
                Intent intent = new Intent("ACTION_SMS_SENT");
                PendingIntent pendingIntent = PendingIntent.getBroadcast(MainActivity.this, 0, intent, 0);
                registerReceiver(new BroadcastReceiver() {
                    @Override
                    public void onReceive( Context context, Intent intent ) {
                        Toast.makeText(MainActivity.this,
                                getResultCode() == Activity.RESULT_OK ?
                                        "Đã gửi tin nhắn!" :
                                        "Gửi tin nhắn thất bại",
                                Toast.LENGTH_LONG).show();
                    }
                }, new IntentFilter("ACTION_SMS_SENT"));
                smsManager.sendTextMessage(txtPhone.getText().toString(), null, txtMessage.getText().toString(), pendingIntent, null);
            }
        });

    }

    void setControl() {
        btnDial = findViewById(R.id.btnDial);
        btnCall = findViewById(R.id.btnCall);
        btnSMS1 = findViewById(R.id.btnSMS1);
        btnSMS2 = findViewById(R.id.btnSMS2);
        txtPhone = findViewById(R.id.txtPhone);
        txtMessage = findViewById(R.id.txtMessage);
    }
}
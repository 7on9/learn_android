package com.vnbamboo.btrl13_login_form;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {
    Button btnlogin;
    EditText edituser, editpassword;
    CheckBox cbxSaveInfo;
    String prefname = "loginInfo";

    protected void onCreate( Bundle savedInstanceState ) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        btnlogin = findViewById(R.id.btnlogin);
        edituser = findViewById(R.id.editUser);
        editpassword = findViewById(R.id.editPassword);
        cbxSaveInfo = findViewById(R.id.cbxSaveInfo);
        btnlogin.setOnClickListener(new View.OnClickListener() {
                    public void onClick( View arg0 ) {
                        doLogin();
                    }
                });
    }
    public void doLogin() {
        finish();
        Intent intent = new Intent(this, LoginSuccessActivity.class);
        intent.putExtra("user", edituser.getText().toString());
        startActivity(intent);
    }

    @Override
    protected void onPause() {
        // TODO Auto-generated method stub
        super.onPause();
        savingPreferences();
    }

    @Override
    protected void onResume() {
        // TODO Auto-generated method stub
        super.onResume();
        restoringPreferences();
    }

    public void savingPreferences() {
        SharedPreferences pre = getSharedPreferences(prefname, MODE_PRIVATE);
        SharedPreferences.Editor editor = pre.edit();
        String user = edituser.getText().toString();
        String pwd = editpassword.getText().toString();
        boolean bchk = cbxSaveInfo.isChecked();
        if (!bchk) {
            editor.clear();
        } else {
            editor.putString("user", user);
            editor.putString("pwd", pwd);
            editor.putBoolean("checked", bchk);
        }
        editor.commit();
    }

    public void restoringPreferences() {
        SharedPreferences pre = getSharedPreferences(prefname, MODE_PRIVATE);
        boolean bchk = pre.getBoolean("checked", false);
        if (bchk) {
            String user = pre.getString("user", "");
            String pwd = pre.getString("pwd", "");
            edituser.setText(user);
            editpassword.setText(pwd);
        }
        cbxSaveInfo.setChecked(bchk);
    }
}
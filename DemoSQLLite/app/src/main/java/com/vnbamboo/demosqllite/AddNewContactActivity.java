package com.vnbamboo.demosqllite;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.vnbamboo.demosqllite.model.Contact;

public class AddNewContactActivity extends AppCompatActivity {
    String DATABASE_NAME = "testDB.db";
    SQLiteDatabase database = null;
    EditText txtID, txtName, txtPhone;
    Button btnSave, btnExit, btnDelete;

    @Override
    protected void onCreate( Bundle savedInstanceState ) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_new_contact);

        btnSave = findViewById(R.id.btnSave);
        btnExit = findViewById(R.id.btnExit);
        btnDelete = findViewById(R.id.btnDelete);
        txtID = findViewById(R.id.txtCode);
        txtName = findViewById(R.id.txtName);
        txtPhone = findViewById(R.id.txtPhone);
        btnDelete.setEnabled(false);
        btnExit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick( View v ) {
                onBackPressed();
            }
        });

        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick( View v ) {
                addNew();
            }
        });


    }


    private void addNew() {
        database = openOrCreateDatabase(DATABASE_NAME, MODE_PRIVATE, null);
        ContentValues row = new ContentValues();
        row.put("ID", txtID.getText().toString());
        row.put("NAME", txtName.getText().toString());
        row.put("PHONE", txtPhone.getText().toString());

        long res = database.insert("TEST", null, row);
        if(res > 0){
            Toast.makeText(this, "Thêm mới thành công!",Toast.LENGTH_LONG).show();
            txtID.setText("");
            txtName.setText("");
            txtPhone.setText("");
        }else {
            Toast.makeText(this, "Thêm mới thất bại!",Toast.LENGTH_LONG).show();
        }
    }
}

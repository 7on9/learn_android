package com.vnbamboo.demosqllite;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.vnbamboo.demosqllite.model.Contact;

import java.io.Serializable;
import java.util.AbstractList;

public class ContactActivity extends AppCompatActivity {
    String DATABASE_NAME = "testDB.db";
    String DB_PATH_SUFFIX = "/databases/";
    SQLiteDatabase database = null;

    ArrayAdapter<Contact> customersAdapter;
    ListView lsvContact;
    @Override
    protected void onCreate( Bundle savedInstanceState ) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contact);
        lsvContact = findViewById(R.id.lsvContact);

        customersAdapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1);
        lsvContact.setAdapter(customersAdapter);
        lsvContact.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick( AdapterView<?> parent, View view, int position, long id ) {
                Intent intent = new Intent(view.getContext(), EditContactActivity.class);
                intent.putExtra("Contact", customersAdapter.getItem(position));
                startActivity(intent);
            }
        });
        viewCustomerList();
    }
    private void viewCustomerList() {
        database = openOrCreateDatabase(DATABASE_NAME, MODE_PRIVATE, null);
        Cursor cursor = database.rawQuery("SELECT * from TEST", null);
        while (cursor.moveToNext()) {
            int id = cursor.getInt(0);
            String name = cursor.getString(1);
            String phone = cursor.getString(2);
            customersAdapter.add(new Contact(id, name, phone));
        }
    }
}

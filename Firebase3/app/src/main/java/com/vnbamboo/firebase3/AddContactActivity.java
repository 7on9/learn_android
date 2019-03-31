package com.vnbamboo.firebase3;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class AddContactActivity extends AppCompatActivity {
    EditText txtId, txtName, txtPhone, txtEmail;
    Button btnAdd;

    @Override
    protected void onCreate( Bundle savedInstanceState ) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_contact);
        setControls();
        addEvent();
    }

    private void setControls() {
        txtId = findViewById(R.id.txtId);
        txtName = findViewById(R.id.txtName);
        txtPhone = findViewById(R.id.txtPhone);
        txtEmail = findViewById(R.id.txtEmail);
        btnAdd = findViewById(R.id.btnAdd);
    }

    private void addEvent() {
        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick( View v ) {
                addNewContact(v);
            }
        });
    }

    public void addNewContact( View view ) {
        try {
            FirebaseDatabase database = FirebaseDatabase.getInstance();

            DatabaseReference myRef = database.getReference("contacts");
            String contactId = txtId.getText().toString();
            String ten = txtName.getText().toString();
            String phone = txtPhone.getText().toString();
            String email = txtEmail.getText().toString();
            myRef.child(contactId).child("phone").setValue(phone);
            myRef.child(contactId).child("email").setValue(email);
            myRef.child(contactId).child("name").setValue(ten);
            finish();
        } catch (Exception ex) {
            Toast.makeText(this, "Error:" + ex.toString(), Toast.LENGTH_LONG).show();
        }
    }
}

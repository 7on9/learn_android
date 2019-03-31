package com.vnbamboo.firebase4_5;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.vnbamboo.adapter.ContactAdapter;
import com.vnbamboo.model.Contact;

public class MainActivity extends AppCompatActivity {
    ListView lsvContact;
    String TAG = "FIRE_BASE";
    ContactAdapter adapter;

    @Override
    protected void onCreate( Bundle savedInstanceState ) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        addControls();
        getContactsFromFirebase();
        addEvents();
    }

    private void addEvents() {
        lsvContact.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick( AdapterView<?> parent, View view, int position, long id ) {
                final Contact contact = adapter.getItem(position);
                FirebaseDatabase firebaseDatabase = FirebaseDatabase.getInstance();
                DatabaseReference myRef = firebaseDatabase.getReference("contacts");
                myRef.child(contact.getContactId()).removeValue()
                        .addOnSuccessListener(new OnSuccessListener<Void>() {
                            @Override
                            public void onSuccess( Void aVoid ) {
                                Toast.makeText(MainActivity.this, "Thành công", Toast.LENGTH_LONG).show();
                                adapter.remove(contact);
                            }
                        })
                        .addOnFailureListener(new OnFailureListener() {
                            @Override
                            public void onFailure( @NonNull Exception e ) {
                                Toast.makeText(MainActivity.this, "Lỗi rồi :" + e.toString(), Toast.LENGTH_LONG).show();
                            }
                        });
                return false;
            }
        });
        lsvContact.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Contact data=adapter.getItem(position);
                Intent intent=new Intent(MainActivity.this, UpdateContactActivity.class);
                intent.putExtra("KEY",data.getContactId());
                startActivity(intent);
            }
        });
    }

    private void getContactsFromFirebase() {
        FirebaseDatabase firebaseDatabase = FirebaseDatabase.getInstance();
        DatabaseReference myRef = firebaseDatabase.getReference("contacts");
        myRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange( @NonNull DataSnapshot dataSnapshot ) {
                adapter.clear();
                for (DataSnapshot dss : dataSnapshot.getChildren()) {
                    Contact contact = dss.getValue(Contact.class);
                    String key = dss.getKey();
                    contact.setContactId(key);
                    adapter.add(contact);
                }
            }

            @Override
            public void onCancelled( @NonNull DatabaseError databaseError ) {

            }
        });
    }

    private void addControls() {
        lsvContact = findViewById(R.id.lsvContact);
        adapter = new ContactAdapter(this, R.layout.item);
        lsvContact.setAdapter(adapter);
    }

    @Override
    public boolean onCreateOptionsMenu( Menu menu ) {
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.main_menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected( MenuItem item ) {
        if (item.getItemId() == R.id.mnuAdd) {
            //mở màn hình thêm ở đây
            Intent intent = new Intent(MainActivity.this, AddContactActivity.class);
            startActivity(intent);
        }
        return super.onOptionsItemSelected(item);
    }
}

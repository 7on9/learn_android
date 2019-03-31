package com.vnbamboo.firebase1;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import static com.google.firebase.database.FirebaseDatabase.getInstance;

public class MainActivity extends AppCompatActivity {

    String TAG = "FIRE_BASE";
    ListView lsvContact;
    ArrayAdapter<String> adapter;

    @Override
    protected void onCreate( Bundle savedInstanceState ) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        adapter=new ArrayAdapter<>(this,android.R.layout.simple_list_item_1);
        lsvContact =findViewById(R.id.lsvContact);
        lsvContact.setAdapter(adapter);

        FirebaseDatabase database = getInstance();

        DatabaseReference myRef = database.getReference("contacts");

        myRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange( DataSnapshot dataSnapshot ) {
                adapter.clear();
                for (DataSnapshot data : dataSnapshot.getChildren()) {
                    String key = data.getKey();
                    String value = data.getValue().toString();
                    adapter.add("Key : " + key + "\n Giá trị : " + value);
                }
            }

            @Override
            public void onCancelled( DatabaseError databaseError ) {
                Log.w(TAG, "loadPost:onCancelled", databaseError.toException());
            }
        });
    }
}

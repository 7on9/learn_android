package com.vnbamboo.firebase4_5;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Base64;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.vnbamboo.model.Contact;

import java.io.ByteArrayOutputStream;
import java.io.IOException;

public class UpdateContactActivity extends AppCompatActivity {

    EditText txtId, txtTen, txtPhone, txtEmail;
    ImageView imgPicture;
    ImageButton btnCapture;
    ImageButton btnChoose;
    Bitmap selectedBitmap;

    @Override
    protected void onCreate( Bundle savedInstanceState ) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_contact);
        addControls();
        getContactDetail();
        addEvents();
    }

    private void getContactDetail() {
        Intent intent = getIntent();
        final String key = intent.getStringExtra("KEY");
        final FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference myRef = database.getReference("contacts");

        myRef.child(key).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange( DataSnapshot dataSnapshot ) {
                try {
                    Contact contact = dataSnapshot.getValue(Contact.class);
                    contact.setContactId(dataSnapshot.getKey());
                    txtId.setText(contact.getContactId());
                    txtTen.setText(contact.getName());
                    txtEmail.setText(contact.getEmail());
                    txtPhone.setText(contact.getPhone());
                    if (contact.getPicture() != null) {
                        byte[] decodedString = Base64.decode(contact.getPicture(), Base64.DEFAULT);
                        Bitmap decodedByte = BitmapFactory.decodeByteArray(decodedString, 0, decodedString.length);
                        imgPicture.setImageBitmap(decodedByte);
                    }
                } catch (Exception ex) {
                    Log.e("LOI_JSON", ex.toString());
                }
            }

            @Override
            public void onCancelled( DatabaseError databaseError ) {
                Log.w("LOI_CHITIET", "loadPost:onCancelled", databaseError.toException());
            }
        });
    }

    private void addControls() {
        btnCapture = (ImageButton) findViewById(R.id.btnCapture);
        btnChoose = (ImageButton) findViewById(R.id.btnChoose);
        imgPicture = (ImageView) findViewById(R.id.imgPicture);
        txtId = findViewById(R.id.txtContactId);
        txtTen = findViewById(R.id.txtTen);
        txtPhone = findViewById(R.id.txtPhone);
        txtEmail = findViewById(R.id.txtEmail);
    }

    public void addEvents() {
        btnCapture.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick( View view ) {
                capturePicture();
            }
        });
        btnChoose.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick( View view ) {
                choosePicture();
            }
        });
    }

    private void choosePicture() {
        Intent pickPhoto = new Intent(Intent.ACTION_PICK,
                MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
        startActivityForResult(pickPhoto, 200);//one can be replaced with any action code
    }

    private void capturePicture() {
        Intent cInt = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        startActivityForResult(cInt, 100);
    }

    protected void onActivityResult( int requestCode, int resultCode, Intent data ) {
        if (requestCode == 100 && resultCode == RESULT_OK) {
            //xử lý lấy ảnh trực tiếp lúc chụp hình:
            selectedBitmap = (Bitmap) data.getExtras().get("data");
            imgPicture.setImageBitmap(selectedBitmap);
        } else if (requestCode == 200 && resultCode == RESULT_OK) {
            try {
                //xử lý lấy ảnh chọn từ điện thoại:
                Uri imageUri = data.getData();
                selectedBitmap = MediaStore.Images.Media.getBitmap(this.getContentResolver(), imageUri);
                imgPicture.setImageBitmap(selectedBitmap);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public void xuLyCapNhat( View view ) {
        try {
            FirebaseDatabase database = FirebaseDatabase.getInstance();
            DatabaseReference myRef = database.getReference("contacts");
            String contactId = txtId.getText().toString();
            String name = txtTen.getText().toString();
            String phone = txtPhone.getText().toString();
            String email = txtEmail.getText().toString();
            myRef.child(contactId).child("phone").setValue(phone);
            myRef.child(contactId).child("email").setValue(email);
            myRef.child(contactId).child("name").setValue(name);

            //đưa bitmap về base64string:
            ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
            selectedBitmap.compress(Bitmap.CompressFormat.PNG, 100, byteArrayOutputStream);
            byte[] byteArray = byteArrayOutputStream.toByteArray();
            String imgeEncoded = Base64.encodeToString(byteArray, Base64.DEFAULT);
            myRef.child(contactId).child("picture").setValue(imgeEncoded);

            finish();
        } catch (Exception ex) {
            Toast.makeText(this, "Error:" + ex.toString(), Toast.LENGTH_LONG).show();
        }
    }
}

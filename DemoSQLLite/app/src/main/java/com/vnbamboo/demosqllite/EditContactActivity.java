package com.vnbamboo.demosqllite;

import android.content.ContentValues;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.vnbamboo.demosqllite.model.Contact;

public class EditContactActivity extends AddNewContactActivity {
    String DATABASE_NAME = "testDB.db";
    String DB_PATH_SUFFIX = "/databases/";
    SQLiteDatabase database = null;
    Contact contact;

    @Override
    protected void onCreate( Bundle savedInstanceState ) {
        super.onCreate(savedInstanceState);
        getDataFromEvent();
        database = openOrCreateDatabase(DATABASE_NAME, MODE_PRIVATE, null);
    }
    private void getDataFromEvent(){
        Intent intent = this.getIntent();
        contact = (Contact)intent.getSerializableExtra("Contact");

        txtID.setText(contact.getId() + "");
        txtName.setText(contact.getName());
        txtPhone.setText(contact.getPhone());
        btnDelete = findViewById(R.id.btnDelete);
        btnDelete.setEnabled(true);

        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick( View v ) {
                updateContact(new Contact(Integer.parseInt(txtID.getText().toString()), txtName.getText().toString(), txtPhone.getText().toString()));
            }
        });

        btnDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick( View v ) {
                handlerDelete();
            }
        });
    }
    public void updateContact(Contact contact) {
        ContentValues values = new ContentValues();
        values.put("ID", contact.getId());
        values.put("NAME", contact.getName());
        values.put("PHONE", contact.getPhone());

        database.update("TEST", values, "ID" + " = ?", new String[] { String.valueOf(contact.getId()) });
        database.close();
        Toast.makeText(this, "Đã cập nhật thành công dữ liệu!", Toast.LENGTH_LONG).show();
    }


    private void handlerDelete() {
        AlertDialog.Builder builder = new AlertDialog.Builder(EditContactActivity.this);
        builder.setTitle("Xác nhận?");
        builder.setMessage("Bạn có chắc muốn xóa?");
        builder.setIcon(android.R.drawable.ic_dialog_alert);
        builder.setPositiveButton("Có", new DialogInterface.OnClickListener() {
            @Override
            public void onClick( DialogInterface dialog, int which ) {
                int res = database.delete("TEST", "ID" + " = ?", new String[] { String.valueOf(contact.getId()) });
                if(res > 0){
                    Toast.makeText(EditContactActivity.this,"Đã xóa!", Toast.LENGTH_LONG).show();
                    Intent intent = new Intent(EditContactActivity.this, ContactActivity.class);
                    startActivity(intent);
                }
                dialog.dismiss();
            }
        });
        builder.setNegativeButton("Không", new DialogInterface.OnClickListener() {
            @Override
            public void onClick( DialogInterface dialog, int which ) {
                dialog.dismiss();
            }
        });
        builder.create().show();
    }
}

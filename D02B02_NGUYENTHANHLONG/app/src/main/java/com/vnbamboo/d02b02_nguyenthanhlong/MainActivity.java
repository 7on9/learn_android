package com.vnbamboo.d02b02_nguyenthanhlong;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import model.Product;

public class MainActivity extends AppCompatActivity {
    String DATABASE_NAME = "D02B02_NGUYENTHANHLONG.db";
    Dialog dialog;
    String DB_PATH_SUFFIX = "/databases/";
    SQLiteDatabase database = null;
    ArrayAdapter<Product> productArrayAdapter;
    ListView lsvProduct;
    @Override
    protected void onCreate( Bundle savedInstanceState ) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setControl();
        addEvent();
        bindData();
    }

    private void addEvent() {
        lsvProduct.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick( AdapterView<?> parent, View view, final int position, long id ) {
                final Product product = (Product)lsvProduct.getItemAtPosition(position);
                dialog = new Dialog(MainActivity.this);
                dialog.setContentView(R.layout.layout_dialog);
                final EditText txtName = dialog.findViewById(R.id.txtName);
                final EditText txtCode = dialog.findViewById(R.id.txtCode);
                final EditText txtPrice = dialog.findViewById(R.id.txtPrice);

                Button btnDelete = dialog.findViewById(R.id.btnDelete);
                Button btnCancel = dialog.findViewById(R.id.btnCancel);

                txtCode.setText(product.getCode());
                txtName.setText(product.getName());
                txtPrice.setText(String.valueOf(product.getPrice()));

                btnCancel.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick( View v ) {
                        dialog.dismiss();
                    }
                });
                btnDelete.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick( View v ) {
                        handlerDelete(product.getCode());
                        dialog.dismiss();
                    }
                });
                dialog.show();
                return false;
            }
        });
    }

    private void setControl() {
        lsvProduct = findViewById(R.id.lsvData);
        productArrayAdapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1);
        lsvProduct.setAdapter(productArrayAdapter);
    }
    private void bindData() {
        productArrayAdapter.clear();
        database = openOrCreateDatabase(DATABASE_NAME, MODE_PRIVATE, null);
        Cursor cursor = database.rawQuery("SELECT * from PRODUCT", null);
        while (cursor.moveToNext()) {
            String id = cursor.getString(0);
            String name = cursor.getString(1);
            int price = cursor.getInt(2);
            productArrayAdapter.add(new Product(id, name, price));
        }
    }

    private void handlerDelete( final String code) {
        AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
        builder.setTitle("Xác nhận?");
        builder.setMessage("Bạn có chắc muốn xóa?");
        builder.setIcon(android.R.drawable.ic_dialog_alert);
        builder.setPositiveButton("Có", new DialogInterface.OnClickListener() {
            @Override
            public void onClick( DialogInterface dialog, int which ) {
                int res = database.delete("PRODUCT", "CODE" + " = ?", new String[] { code });
                if(res > 0){
                    Toast.makeText(MainActivity.this,"Đã xóa!", Toast.LENGTH_LONG).show();
                }
                bindData();
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

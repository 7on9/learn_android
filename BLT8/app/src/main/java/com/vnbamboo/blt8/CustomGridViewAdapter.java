package com.vnbamboo.blt8;

import android.app.Activity;
import android.graphics.Bitmap;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.vnbamboo.blt8.model.Product;

public class CustomGridViewAdapter extends ArrayAdapter<Product> {
    Activity context; 
    int resource;

    public CustomGridViewAdapter( Activity context, int resource ) {
        super(context, resource);
        this.context = context;
        this.resource = resource;
    }

    public void add( Product object, Bitmap img ) {

        super.add(object);

    }

    @Override
    public View getView( int position,  View convertView, ViewGroup parent ) {
        View customView = context.getLayoutInflater().inflate(resource, null);
        Product product = getItem(position);
        final TextView txtPrice = customView.findViewById(R.id.txtPrice);
        final TextView txtName = customView.findViewById(R.id.txtName);
        ImageView imgProduct = customView.findViewById(R.id.imgProduct);

        txtName.setText(product.getName());
        txtPrice.setText(String.valueOf(product.getPrice()) + " 000 VNĐ");
        imgProduct.setImageBitmap(product.getBitmap());
        customView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick( View v ) {
                Toast.makeText(v.getContext(),txtName.getText().toString() + " giá " + txtPrice.getText().toString(), Toast.LENGTH_LONG).show();
            }
        });
        return customView;
    }
}

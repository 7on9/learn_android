package com.vnbamboo.listviewnangcao;

import android.app.Activity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;
import android.widget.Toast;

import model.Product;

public class CustomListViewAdapter extends ArrayAdapter<Product> {
    Activity context; 
    int resource;

    public CustomListViewAdapter( Activity context, int resource ) {
        super(context, resource);
        this.context = context;
        this.resource = resource;
    }

    @Override
    public View getView( int position,  View convertView, ViewGroup parent ) {
        View customView = context.getLayoutInflater().inflate(resource, null);
        Product product = getItem(position);
        final TextView txtPrice = customView.findViewById(R.id.txtPrice);
        final TextView txtName = customView.findViewById(R.id.txtName);

        txtName.setText(product.getName());
        txtPrice.setText(String.valueOf(product.getPrice()) + " 000 VNĐ");
        customView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick( View v ) {
                Toast.makeText(v.getContext(),
                        txtName.getText().toString() + " giá " + txtPrice.getText().toString(),
                        Toast.LENGTH_LONG).show();
            }
        });
        return customView;
    }
}

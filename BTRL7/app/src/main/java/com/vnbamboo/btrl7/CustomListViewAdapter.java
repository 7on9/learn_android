package com.vnbamboo.btrl7;

import android.app.Activity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.vnbamboo.btrl7.Model.Contact;

public class CustomListViewAdapter extends ArrayAdapter<Contact> {
    Activity context;
    int resource;

    public CustomListViewAdapter( Activity context, int resource ) {
        super(context, resource);
        this.context = context;
        this.resource = resource;
    }
    @Override
    public View getView( int position, View convertView, ViewGroup parent ) {
        View customView = context.getLayoutInflater().inflate(resource, null);
        final Contact contact = getItem(position);
        TextView txtPhone = customView.findViewById(R.id.txtPhone);
        TextView txtName = customView.findViewById(R.id.txtName);
        ImageView imgPhone = customView.findViewById(R.id.imgPhone);
        ImageView imgSMS = customView.findViewById(R.id.imgSMS);
        ImageView imageDetail = customView.findViewById(R.id.imgDetail);

        imageDetail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick( View v ) {
                Toast.makeText(v.getContext(), contact.getInfo() + " " + contact.getPhone() , Toast.LENGTH_LONG).show();
            }
        });
        imgPhone.setOnClickListener(new View.OnClickListener() {t
            @Override
            public void onClick( View v ) {
                Toast.makeText(v.getContext(),"Bạn gọi đến số " + contact.getPhone(), Toast.LENGTH_LONG).show();
            }
        });
        imgSMS.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick( View v ) {
                Toast.makeText(v.getContext(),"Bạn nhắn tin đến số " + contact.getPhone(), Toast.LENGTH_LONG).show();
            }
        });
        txtName.setText(contact.getInfo());
        txtPhone.setText(contact.getPhone());
        return customView;
    }
}

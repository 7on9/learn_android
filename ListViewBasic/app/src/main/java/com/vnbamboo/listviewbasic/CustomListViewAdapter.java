package com.vnbamboo.listviewbasic;

import android.app.Activity;
import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.TextView;

import com.vnbamboo.listviewbasic.Model.Contact;

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
        Contact contact = getItem(position);
        TextView txtCode = customView.findViewById(R.id.txtCode);
        TextView txtPhone = customView.findViewById(R.id.txtPhone);
        TextView txtName = customView.findViewById(R.id.txtName);

        txtName.setText(contact.getName());
        txtPhone.setText(contact.getPhone());
        txtCode.setText(contact.getCode());
        return customView;
    }
}

package com.vnbamboo.adapter;

import android.app.Activity;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.vnbamboo.firebase4_5.R;
import com.vnbamboo.model.Contact;

public class ContactAdapter extends ArrayAdapter<Contact> {
    Activity context;
    int resource;

    public ContactAdapter( Activity context, int resource ) {
        super(context, resource);
        this.context = context;
        this.resource = resource;
    }

    @NonNull
    @Override
    public View getView( int position, @Nullable View convertView, @NonNull ViewGroup parent ) {
        View custom = context.getLayoutInflater().inflate(resource, null);
        TextView txtId = custom.findViewById(R.id.txtId);
        TextView txtName = custom.findViewById(R.id.txtName);
        TextView txtPhone = custom.findViewById(R.id.txtPhone);
        TextView txtEmail = custom.findViewById(R.id.txtEmail);
        Contact contact = getItem(position);
        txtId.setText(contact.getContactId());
        txtName.setText(contact.getName());
        txtPhone.setText(contact.getPhone());
        txtEmail.setText(contact.getEmail());
        return custom;
    }
}

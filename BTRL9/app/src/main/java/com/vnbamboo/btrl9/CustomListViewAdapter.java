package com.vnbamboo.btrl9;

import android.app.Activity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.vnbamboo.btrl9.model.Employee;

public class CustomListViewAdapter extends ArrayAdapter<Employee> {
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
        final Employee employee = getItem(position);
        TextView txtCode = customView.findViewById(R.id.txtCode);
        TextView txtName = customView.findViewById(R.id.txtName);
        ImageView imageView = customView.findViewById(R.id.imgAvatar);
        CheckBox cbxSelect = customView.findViewById(R.id.cbxSelect);

        txtName.setText(employee.getName());
        txtCode.setText(employee.getCode());
        imageView.setImageResource(employee.isMan() ? R.mipmap.man : R.mipmap.woman);

        cbxSelect.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged( CompoundButton buttonView, boolean isChecked ) {
                employee.isChecked = isChecked;
            }
        });
        return customView;
    }
}

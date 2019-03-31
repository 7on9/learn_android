package com.vnbamboo.btrl9;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.RadioButton;
import android.widget.TextView;

import com.vnbamboo.btrl9.model.Employee;

public class MainActivity extends AppCompatActivity {
    TextView txtName, txtCode;
    RadioButton rbtnMan;
    Button btnAdd, btnRemove;
    ListView lsvData;
    CustomListViewAdapter customListViewAdapter;
    @Override
    protected void onCreate( Bundle savedInstanceState ) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setControll();
        addEvent();
    }

    private void setControll() {
        txtName = findViewById(R.id.txtName);
        txtCode = findViewById(R.id.txtCode);
        btnAdd = findViewById(R.id.btnAdd);
        lsvData = findViewById(R.id.lsvData);
        rbtnMan = findViewById(R.id.rbtnMan);
        btnRemove = findViewById(R.id.btnRemove);
        customListViewAdapter = new CustomListViewAdapter(this, R.layout.contact_layout);
        lsvData.setAdapter(customListViewAdapter);
    }

    private void addEvent() {
        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick( View v ) {
                customListViewAdapter.add(new Employee(txtCode.getText().toString(), txtName.getText().toString(), rbtnMan.isChecked()));
                txtCode.setText("");
                txtName.setText("");
            }
        });
        btnRemove.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick( View v ) {
                for(int i = customListViewAdapter.getCount() - 1; i >= 0; i--){
                    Employee tmpEmployee = customListViewAdapter.getItem(i);
                    if(tmpEmployee.isChecked){
                        customListViewAdapter.remove(tmpEmployee);
                    }
                }
            }
        });
    }


}

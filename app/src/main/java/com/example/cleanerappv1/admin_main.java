package com.example.cleanerappv1;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListAdapter;
import android.widget.ListView;

public class admin_main extends AppCompatActivity {

    private ListView service;
    private String [] array = {"Basic House Keeping", "Car Cleaning", "Spring Cleaning", "Move In/Out Cleaning", "Office/Commercial Cleaning"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_main);

        this.setTitle("Main Page");
        service =findViewById(R.id.services);

        ListAdapter listAdapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, array);
        service.setAdapter(listAdapter);
    }
}
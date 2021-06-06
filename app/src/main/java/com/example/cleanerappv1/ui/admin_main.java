package com.example.cleanerappv1.ui;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.ArrayAdapter;
import android.widget.ListAdapter;
import android.widget.ListView;

import com.example.cleanerappv1.R;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class admin_main extends AppCompatActivity {


    BottomNavigationView bottomNavigationView;
    private ListView service;
    private String [] array = {"Basic House Keeping", "Car Cleaning", "Spring Cleaning", "Move In/Out Cleaning", "Office/Commercial Cleaning"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_main);

        this.setTitle("Main Page");
        service =findViewById(R.id.services);
        bottomNavigationView = findViewById(R.id.bottom_nav);

        ListAdapter listAdapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, array);
        service.setAdapter(listAdapter);
        setBottomNavi();
    }

    private void setBottomNavi(){
        //bottom nav
       // BottomNavigationView bottomNavigationView = findViewById(R.id.bottom_nav);
        bottomNavigationView.setSelectedItemId(R.id.nav_home);
        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()){
                    case R.id.nav_user:
                        startActivity(new Intent(getApplicationContext(), admin_view_user.class));
                        overridePendingTransition(0,0);
                        return true;
                    case R.id.nav_home:
                        return true;
                    case R.id.nav_setting:
                        startActivity(new Intent(admin_main.this, admin_setting.class));
                        overridePendingTransition(0,0);
                        return true;
                }
                return false;
            }
        });
    }
}
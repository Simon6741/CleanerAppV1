package com.example.cleanerappv1.ui;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListAdapter;
import android.widget.ListView;

import com.example.cleanerappv1.R;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class AdminMainActivity extends AppCompatActivity {


    BottomNavigationView bottomNavigationView;
    Button btnBasicHouse, btnCarCleaning, btnSpringCleaning, btnMoveInOut, btnOffice;
  //  private ListView service;
  //  private String [] array = {"Basic House Keeping", "Car Cleaning", "Spring Cleaning", "Move In/Out Cleaning", "Office/Commercial Cleaning"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_main);

        this.setTitle("Main Page");

        initView();
        setBottomNavi();
    }

    private void initView(){
        bottomNavigationView = findViewById(R.id.bottom_nav);
        btnBasicHouse = findViewById(R.id.btn_house_keeping);
        btnCarCleaning = findViewById(R.id.btn_car_cleaning);
        btnSpringCleaning = findViewById(R.id.btn_spring_cleaning);
        btnMoveInOut = findViewById(R.id.btn_move_in_out);
        btnOffice = findViewById(R.id.btn_office_cleaning);
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
                        startActivity(new Intent(getApplicationContext(), AdminViewUserActivity.class));
                        overridePendingTransition(0,0);
                        return true;
                    case R.id.nav_home:
                        return true;
                    case R.id.nav_setting:
                        startActivity(new Intent(AdminMainActivity.this, AdminSettingActivity.class));
                        overridePendingTransition(0,0);
                        return true;
                }
                return false;
            }
        });
    }
}
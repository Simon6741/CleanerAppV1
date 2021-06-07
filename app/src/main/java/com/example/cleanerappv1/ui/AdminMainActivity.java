package com.example.cleanerappv1.ui;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.example.cleanerappv1.R;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import static com.example.cleanerappv1.util.Constant.INTENT_VIEWTYPE;

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
        setOnClick();
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

    private void setOnClick(){

        btnBasicHouse.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                intentAdminBooking(getString(R.string.admin_service_basicHouse));
            }
        });

        btnCarCleaning.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                intentAdminBooking(getString(R.string.admin_service_carCleaning));
            }
        });

        btnMoveInOut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                intentAdminBooking(getString(R.string.admin_service_moveInOut));
            }
        });

        btnOffice.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                intentAdminBooking(getString(R.string.admin_service_officeCommercial));
            }
        });

        btnSpringCleaning.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                intentAdminBooking(getString(R.string.admin_service_springCleaning));
            }
        });


    }

    private void intentAdminBooking(String viewType){
        Log.d("Try", viewType);
        Toast.makeText(getApplicationContext(),viewType,Toast.LENGTH_LONG);
        Intent intent = new Intent(getApplicationContext(), AdminBookingActivity.class);
        intent.putExtra(INTENT_VIEWTYPE, viewType);
        startActivity(intent);

        overridePendingTransition(0,0);
    }
}
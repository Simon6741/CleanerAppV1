package com.example.cleanerappv1.ui;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

import com.example.cleanerappv1.R;
import com.example.cleanerappv1.cleaner_book;
import com.example.cleanerappv1.cleaner_main;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class AdminViewUserActivity extends AppCompatActivity {
    BottomNavigationView bottomNavigationView;
    Button btnCustomer, btnCleaner;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_view_user);

        bottomNavigationView = findViewById(R.id.navigation);
        btnCustomer = findViewById(R.id.btn_cust);
        btnCleaner = findViewById(R.id.btn_clean);

        initView();
        setBottomNavi();
        setOnClick();
    }

    private void initView(){

    }

    private void setBottomNavi(){
        //bottom nav
        // BottomNavigationView bottomNavigationView = findViewById(R.id.bottom_nav);
        bottomNavigationView.setSelectedItemId(R.id.nav_user);
        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()){
                    case R.id.nav_user:
//                        startActivity(new Intent(getApplicationContext(), admin_user.class));
//                        overridePendingTransition(0,0);
                        return true;
                    case R.id.nav_home:
                        startActivity(new Intent(getApplicationContext(), AdminMainActivity.class));
                        overridePendingTransition(0,0);
                        return true;
                    case R.id.nav_setting:
                        startActivity(new Intent(getApplicationContext(), AdminSettingActivity.class));
                        overridePendingTransition(0,0);
                        return true;
                }
                return false;
            }
        });
    }

    private void setOnClick(){
       btnCustomer.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View v) {
               Intent intent =  new Intent(getApplicationContext(), AdminUserListActivity.class);
               intent.putExtra("viewType", "Customer");
               startActivity(intent);

               overridePendingTransition(0,0);
           }
       });

        btnCleaner.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent =  new Intent(getApplicationContext(), AdminUserListActivity.class);
                intent.putExtra("viewType", "Cleaner");
                startActivity(intent);

                overridePendingTransition(0,0);
            }
        });
    }
}
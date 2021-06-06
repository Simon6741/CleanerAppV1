package com.example.cleanerappv1.ui;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;

import com.example.cleanerappv1.R;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class admin_view_user extends AppCompatActivity {
    BottomNavigationView bottomNavigationView;
    Button btnCustomer, btnCleaner;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_view_user);

        initView();
        setBottomNavi();
    }

    private void initView(){
        bottomNavigationView = findViewById(R.id.navigation);
        btnCustomer = findViewById(R.id.btn_customer);
        btnCleaner = findViewById(R.id.btn_cleaner);
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
                        startActivity(new Intent(getApplicationContext(), admin_main.class));
                        overridePendingTransition(0,0);
                        return true;
                    case R.id.nav_setting:
                        startActivity(new Intent(getApplicationContext(), admin_setting.class));
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
                startActivity(new Intent(getApplicationContext(), admin_.class));
                overridePendingTransition(0,0);
            }
        });
    }
}
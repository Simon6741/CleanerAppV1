package com.example.cleanerappv1.ui;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;

import com.example.cleanerappv1.R;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class AdminSettingActivity extends AppCompatActivity {
    BottomNavigationView bottomNavigationView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
       setContentView(R.layout.activity_admin_setting);

        bottomNavigationView = findViewById(R.id.navigation);
        setBottomNavi();
    }

    private void setBottomNavi(){
        //bottom nav
        // BottomNavigationView bottomNavigationView = findViewById(R.id.bottom_nav);
        bottomNavigationView.setSelectedItemId(R.id.nav_setting);
        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()){
                    case R.id.nav_user:
                        startActivity(new Intent(getApplicationContext(), AdminViewUserActivity.class));
                        overridePendingTransition(0,0);
                        return true;
                    case R.id.nav_home:
                        startActivity(new Intent(getApplicationContext(), AdminMainActivity.class));
                        overridePendingTransition(0,0);
                        return true;
                    case R.id.nav_setting:
//                        startActivity(new Intent(customer_main.this, admin_setting.class));
//                        overridePendingTransition(0,0);
                        return true;
                }
                return false;
            }
        });
    }
}
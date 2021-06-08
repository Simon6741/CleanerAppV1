package com.example.cleanerappv1.ui;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;

import android.widget.Button;
import com.example.cleanerappv1.R;
import com.google.android.material.bottomnavigation.BottomNavigationView;


public class AdminSettingActivity extends AppCompatActivity {
    BottomNavigationView bottomNavigationView;
    Button btnFAQ,btnTNC,btnComplaint;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
       setContentView(R.layout.activity_admin_setting);

       btnFAQ = findViewById(R.id.btn_faq);
       btnTNC = findViewById(R.id.btn_tnc);
       btnComplaint = findViewById(R.id.btn_complaint);

        bottomNavigationView = findViewById(R.id.navigation);
        setBottomNavi();
        setOnclick();
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

    private void setOnclick(){
        btnFAQ.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent =  new Intent(getApplicationContext(), admin_faq.class);
//                intent.putExtra("viewType", "Customer");
                startActivity(intent);

                overridePendingTransition(0,0);
            }
        });

        this.btnComplaint.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent =  new Intent(getApplicationContext(), admin_complaint.class);
                startActivity(intent);

                overridePendingTransition(0,0);
            }
        });

        this.btnTNC.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent =  new Intent(getApplicationContext(), admin_term.class);
                startActivity(intent);

                overridePendingTransition(0,0);
            }
        });
    }
}
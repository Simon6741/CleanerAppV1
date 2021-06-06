package com.example.cleanerappv1;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;

import com.example.cleanerappv1.ui.admin_main;
import com.example.cleanerappv1.ui.admin_setting;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class admin_user extends AppCompatActivity {
    BottomNavigationView bottomNavigationView;
    //Button btnCustomer, btnCleaner;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_user);


    }


}
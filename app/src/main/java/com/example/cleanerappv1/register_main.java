package com.example.cleanerappv1;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class register_main extends AppCompatActivity {

    Button registerAsCustomer, registerAsCleaner;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register_main);

        registerAsCleaner = findViewById(R.id.registerAsCleaner);
        registerAsCustomer = findViewById(R.id.registerAsCustomer);

        registerAsCustomer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent2customerRegister = new Intent(register_main.this, register_customer.class);
                startActivity(intent2customerRegister);
            }
        });

        registerAsCleaner.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent2cleanerRegister = new Intent(register_main.this, register_cleaner.class);
                startActivity(intent2cleanerRegister);
            }
        });
    }
}
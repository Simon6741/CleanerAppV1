package com.example.cleanerappv1.ui;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import com.example.cleanerappv1.R;

import android.view.View;
import android.widget.Button;

public class AdminTNCActivity extends AppCompatActivity {

    Button btnAdd;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_terms);

        btnAdd = findViewById(R.id.btn_tnc_add);
        setOnclick();
    }

    private void setupView() {
//        rvComplaint = findViewById(R.id.recyclerViewComplaint);
    }


    private void setOnclick() {
        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), AddTNCActivity.class);
                startActivity(intent);
                overridePendingTransition(0, 0);

            }
        });

    }
}
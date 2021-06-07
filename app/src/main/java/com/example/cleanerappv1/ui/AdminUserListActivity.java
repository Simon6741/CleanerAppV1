package com.example.cleanerappv1.ui;

import android.os.Bundle;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.cleanerappv1.R;

public class AdminUserListActivity extends AppCompatActivity {
    //TextView txtUserType;
    String userType;
    TextView txtUserType;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_cleaner);

        setupView();

    }

    private void setupView(){
        txtUserType = findViewById(R.id.txt_userType);
        userType = getIntent().getStringExtra("viewType");
        txtUserType.setText(userType);
    }

}

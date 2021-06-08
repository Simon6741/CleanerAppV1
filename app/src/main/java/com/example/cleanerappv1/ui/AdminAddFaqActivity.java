package com.example.cleanerappv1.ui;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import android.content.Intent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.cleanerappv1.R;
import com.example.cleanerappv1.model.FAQ;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class AdminAddFaqActivity extends AppCompatActivity {

    Button btnAdd;
    EditText et_title, et_details;

    String title,details;
    private FirebaseDatabase db;
    private DatabaseReference mDatabase;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_add_faq);

        setupView();
        setOnclick();
    }

    private void setupView() {
        btnAdd = findViewById(R.id.btn_add);
        et_title = findViewById(R.id.edit_title);
        et_details = findViewById(R.id.edit_details);

        mDatabase = FirebaseDatabase.getInstance().getReference();
    }

    private void setOnclick() {
        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                title = et_title.getText().toString();
                details = et_details.getText().toString();

                final String timestamp = "" + System.currentTimeMillis();

                FAQ faq = new FAQ(title,details,timestamp);

                db = FirebaseDatabase.getInstance();

                mDatabase = db.getReference("faq");
                mDatabase.child(timestamp).setValue(faq);

            }
        });

    }
}
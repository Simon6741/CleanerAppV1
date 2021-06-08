package com.example.cleanerappv1.ui;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import com.example.cleanerappv1.R;
import com.example.cleanerappv1.model.TNC;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

public class AddTNCActivity extends AppCompatActivity {

    Button btnAdd;
    EditText et_title;

    String title;
    private FirebaseDatabase db;
    private DatabaseReference mDatabase;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_add_tnc);

        setupView();
        setOnclick();
    }

    private void setupView() {
        btnAdd = findViewById(R.id.btn_add);
        et_title = findViewById(R.id.edit_tnc);
        mDatabase = FirebaseDatabase.getInstance().getReference();
    }

    private void setOnclick() {
        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                title = et_title.getText().toString();
                TNC faq = new TNC(title);
                final String timestamp = "" + System.currentTimeMillis();

                db = FirebaseDatabase.getInstance();

                mDatabase = db.getReference("TermCondition");
                mDatabase.child(timestamp).setValue(faq);

            }
        });

    }



}
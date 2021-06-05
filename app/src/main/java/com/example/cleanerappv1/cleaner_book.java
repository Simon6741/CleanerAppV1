package com.example.cleanerappv1;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class cleaner_book extends AppCompatActivity {

    private TextView name, add, ph, servType, exServ, date, time, spec;
    private Button accept;

    private FirebaseDatabase db;
    private DatabaseReference dbref;
    private FirebaseAuth auth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cleaner_book);

        name  = findViewById(R.id.cusName);
        add  = findViewById(R.id.cusAddress);
        ph  = findViewById(R.id.cusHP);
        servType  = findViewById(R.id.serviceType);
        exServ  = findViewById(R.id.extraServ);
        date  = findViewById(R.id.detail_date);
        time  = findViewById(R.id.detail_time);
        spec  = findViewById(R.id.detail_special);
        accept = findViewById(R.id.btn_accept);

        name.setText(getIntent().getStringExtra("book").toString());

        db = FirebaseDatabase.getInstance();
        dbref = db.getReference("CustomerBookings");
        auth = FirebaseAuth.getInstance();
        dbref.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {
                accept.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        String cleanerID = auth.getUid();
                        String stat = "Accepted";
                        String num = name.getText().toString();
                        dbref.child(num).child("status").setValue(stat);
                        dbref.child(num).child("cid").setValue(cleanerID);
                    }
                });
            }

            @Override
            public void onChildChanged(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {

            }

            @Override
            public void onChildRemoved(@NonNull DataSnapshot snapshot) {

            }

            @Override
            public void onChildMoved(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

    }
}
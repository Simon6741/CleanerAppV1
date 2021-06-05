package com.example.cleanerappv1;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class complete_task_detail extends AppCompatActivity {

        private TextView compTask_detail_cusName, compTask_detail_cusAddress, compTask_detail_cusHP,
            compTask_detail_serviceType, compTask_detail_extraSer,
            compTask_detail_date, compTask_detail_time, compTask_detail_special;

        private FirebaseDatabase db;
        private DatabaseReference dbref;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_complete_task_detail);

        compTask_detail_cusName = findViewById(R.id. compTask_detail_cusName);
        compTask_detail_cusAddress = findViewById(R.id.compTask_detail_cusAddress);
        compTask_detail_cusHP = findViewById(R.id.compTask_detail_cusHP);
        compTask_detail_extraSer = findViewById(R.id.compTask_detail_extraSer);
        compTask_detail_serviceType = findViewById(R.id.compTask_detail_serviceType);
        compTask_detail_date = findViewById(R.id.compTask_detail_date);
        compTask_detail_time = findViewById(R.id.compTask_detail_time);
        compTask_detail_special = findViewById(R.id.compTask_detail_special);

        db = FirebaseDatabase.getInstance();
        dbref = db.getReference("CustomerBookings").child(getIntent().getStringExtra("book").toString());
        dbref.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                String name = snapshot.child("name").getValue().toString();
                String address = snapshot.child("address").getValue().toString();
                String contact = snapshot.child("phone").getValue().toString();
                String serType = snapshot.child("service").getValue().toString();
                String extraSer = snapshot.child("extra").getValue().toString();
                String date = snapshot.child("date").getValue().toString();
                String time = snapshot.child("time").getValue().toString();
                String special = snapshot.child("special").getValue().toString();

                compTask_detail_cusName.setText(name);
                compTask_detail_cusAddress.setText(address);
                compTask_detail_cusHP.setText(contact);
                compTask_detail_serviceType.setText(serType);
                compTask_detail_extraSer.setText(extraSer);
                compTask_detail_date.setText(date);
                compTask_detail_time.setText(time);
                compTask_detail_special.setText(special);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

    }


}
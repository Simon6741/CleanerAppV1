package com.example.cleanerappv1;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.HashMap;

public class pending_task_detail extends AppCompatActivity {

    private TextView penTask_detail_cusName, penTask_detail_cusAddress, penTask_detail_cusHP,
            penTask_detail_serviceType, penTask_detail_extraSer,
            penTask_detail_date, penTask_detail_time, penTask_detail_special, b_id;
    private Button btn_complete;
    private FirebaseDatabase db;
    private DatabaseReference dbref;
    private FirebaseAuth mAuth;
    String Book_ID;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pending_task_detail);

        penTask_detail_cusName = findViewById(R.id. penTask_detail_cusName);
        penTask_detail_cusAddress = findViewById(R.id.penTask_detail_cusAddress);
        penTask_detail_cusHP = findViewById(R.id.penTask_detail_cusHP);
        penTask_detail_extraSer = findViewById(R.id.penTask_detail_extraSer);
        penTask_detail_serviceType = findViewById(R.id.penTask_detail_serviceType);
        penTask_detail_date = findViewById(R.id.penTask_detail_date);
        penTask_detail_time = findViewById(R.id.penTask_detail_time);
        penTask_detail_special = findViewById(R.id.penTask_detail_special);
        btn_complete = findViewById(R.id.penTask_btn_complete);
        b_id = findViewById(R.id.tv_book);

        penTask_detail_cusName.setText(getIntent().getStringExtra("CusName"));
        penTask_detail_cusAddress.setText(getIntent().getStringExtra("CusAdd"));
        penTask_detail_cusHP.setText(getIntent().getStringExtra("CusHP"));
        penTask_detail_extraSer.setText(getIntent().getStringExtra("ExtraService"));
        penTask_detail_serviceType.setText(getIntent().getStringExtra("Service"));
        penTask_detail_date.setText(getIntent().getStringExtra("Date"));
        penTask_detail_time.setText(getIntent().getStringExtra("Time"));
        penTask_detail_special.setText(getIntent().getStringExtra("SpecialInt"));

        db = FirebaseDatabase.getInstance();
        mAuth = FirebaseAuth.getInstance();
        dbref = db.getReference("CustomerBookings");
        dbref.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {
                String number = snapshot.child("booking_id").getValue().toString();
                b_id.setText(number.toString());

                btn_complete.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        String cleanerID = getIntent().getStringExtra("CleanerID").toString();
                        Book_ID = b_id.getText().toString().trim();
                        String value = "Accepted";
                        dbref.child(Book_ID).child("status").setValue(value);

                        dbref.child(Book_ID).child("c_id").setValue(cleanerID);
                        //dbref.child(timestamp).setValue(hashMap);
                        Toast.makeText(pending_task_detail.this, "You accepted the booking.", Toast.LENGTH_SHORT).show();
                        Intent intent2main = new Intent(pending_task_detail.this, cleaner_main.class);
                        startActivity(intent2main);

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
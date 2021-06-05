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

import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class view_booking extends AppCompatActivity {

    private TextView service, date, time, status, b_id;
    private Button update;
    private FirebaseDatabase db;
    private DatabaseReference dbref;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_booking);

        this.setTitle("Booking List");

        service = findViewById(R.id.v_service);
        date = findViewById(R.id.v_date);
        time = findViewById(R.id.v_time);
        status = findViewById(R.id.v_status);
        update = findViewById(R.id.s_update);
        b_id = findViewById(R.id.v_id);

        b_id.setText(getIntent().getStringExtra("b_id"));
        service.setText(getIntent().getStringExtra("service"));
        date.setText(getIntent().getStringExtra("date"));
        time.setText(getIntent().getStringExtra("time"));
        status.setText(getIntent().getStringExtra("status"));

        db = FirebaseDatabase.getInstance();
        dbref = db.getReference("CustomerBookings");
        dbref.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {
                update.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        String number = b_id.getText().toString();
                        String value = "Completed";
                        dbref.child(number).child("status").setValue(value);
                        Toast.makeText(view_booking.this, "The Booking is done.", Toast.LENGTH_SHORT).show();
                        Intent intent2booking = new Intent(view_booking.this, book_list.class);
                        startActivity(intent2booking);



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
package com.example.cleanerappv1;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.HashMap;

public class payment extends AppCompatActivity {

    private TextView p_service, p_detail, p_id, p_total, p_address;

    private Button pay;

    private FirebaseAuth auth;
    private FirebaseDatabase db;
    private DatabaseReference dbref;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_payment);
        this.setTitle("Payment");


        p_service = findViewById(R.id.p_service);
        p_detail = findViewById(R.id.p_detail);
        p_id = findViewById(R.id.p_id);
        p_total = findViewById(R.id.p_total);
        p_address = findViewById(R.id.p_address);
        auth = FirebaseAuth.getInstance();

        pay = findViewById(R.id.pay);

        p_id.setText(getIntent().getStringExtra("timestamp").toString());
        p_service.setText(getIntent().getStringExtra("service").toString());
        p_detail.setText(getIntent().getStringExtra("date").toString()+" @ "+getIntent().getStringExtra("time").toString());
        p_address.setText(getIntent().getStringExtra("address").toString());
        p_total.setText(getIntent().getStringExtra("total").toString());


        db = FirebaseDatabase.getInstance();
        dbref = db.getReference("CustomerBookings");
        dbref.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {

                pay.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        String number = p_id.getText().toString();
                        String value = "Paid";
                        dbref.child(number).child("status").setValue(value);
                        Toast.makeText(payment.this, "Payment successfully with "+getIntent().getStringExtra("payment").toString(), Toast.LENGTH_SHORT).show();
                        Intent intent2main = new Intent(payment.this, customer_main.class);
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
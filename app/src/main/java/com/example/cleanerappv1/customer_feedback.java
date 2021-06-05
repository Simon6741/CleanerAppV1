package com.example.cleanerappv1;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import org.w3c.dom.Text;

import java.util.HashMap;

public class customer_feedback extends AppCompatActivity {

    private TextView h_id;
    private RadioButton excellent, good, average, bad, poor;
    private EditText comment;
    private Button feedback;

    private FirebaseAuth auth;
    private FirebaseDatabase db;
    private DatabaseReference dbref;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_customer_feedback);

        this.setTitle("History");

        h_id = findViewById(R.id.h_id);
        excellent = findViewById(R.id.excellent);
        good = findViewById(R.id.good);
        average = findViewById(R.id.average);
        bad = findViewById(R.id.bad);
        poor = findViewById(R.id.poor);
        comment = findViewById(R.id.comment);
        feedback = findViewById(R.id.btn_feedback);



        h_id.setText("Booking ID: "+getIntent().getStringExtra("b_id").toString());

        auth = FirebaseAuth.getInstance();
        db = FirebaseDatabase.getInstance();
        dbref = db.getReference("CustomerFeedbacks");


        feedback.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final String timestamp = ""+System.currentTimeMillis();
                String rating = "";
                if (excellent.isChecked()){
                    rating = "Excellent";
                } else if (good.isChecked()){
                    rating = "Good";
                }else if (average.isChecked()){
                    rating = "Average";
                }else if (bad.isChecked()){
                    rating = "Bad";
                }else if (poor.isChecked()){
                    rating = "Poor";
                }else{
                    Toast.makeText(customer_feedback.this, "Please select one", Toast.LENGTH_SHORT).show();
                }

                HashMap<String, Object> hashMap = new HashMap<>();
                hashMap.put("uid", "" + auth.getUid());
                hashMap.put("feedback_id", ""+timestamp);
                hashMap.put("booking_id", "" + getIntent().getStringExtra("b_id").toString());
                hashMap.put("rating", "" + String.valueOf(rating));
                hashMap.put("comment", "" + comment.getText().toString());

                String rated = "Rated";
                db.getReference("CustomerBookings").child(getIntent().getStringExtra("b_id").toString()).child("status").setValue(rated);
                dbref.child(timestamp).setValue(hashMap).addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void aVoid) {
                        Toast.makeText(customer_feedback.this, "Feedback Submit Successfully", Toast.LENGTH_SHORT).show();
                        Intent intent2main = new Intent(customer_feedback.this, customer_main.class);
                        startActivity(intent2main);
                    }
                }).addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Toast.makeText(customer_feedback.this, "Feedback Submit Failed", Toast.LENGTH_SHORT).show();
                    }
                });
            }
        });

    }
}
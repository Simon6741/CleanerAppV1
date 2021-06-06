package com.example.cleanerappv1;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class email_verification extends AppCompatActivity {

    private FirebaseAuth auth;
    private DatabaseReference dbRef;

    private Button verifyBtn, continueBtn;
    private TextView verifyText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_email_verification);

        auth = FirebaseAuth.getInstance();
        verifyBtn = findViewById(R.id.emailVerificationBtn);
        verifyText = findViewById(R.id.emailVerificationText);
        continueBtn = findViewById(R.id.emailVerificationContinueBtn);


        verifyBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                auth.getCurrentUser().sendEmailVerification().addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void aVoid) {
                        Toast.makeText(email_verification.this, "Email Verification Is Sent.", Toast.LENGTH_SHORT).show();
                        verifyText.setVisibility(View.GONE);
                        verifyBtn.setVisibility(View.GONE);
                        continueBtn.setVisibility(View.VISIBLE);
                    }
                });
            }
        });

        continueBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(), MainActivity.class));
                finish();
                Toast.makeText(email_verification.this, "Please login again.", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private boolean isEmailValid(CharSequence email) {
        return android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches();
    }
}
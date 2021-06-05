package com.example.cleanerappv1;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class customer_edit_profile extends AppCompatActivity {

    private EditText e_username, e_fullname, e_email, e_phone;
    private Button update;

    private FirebaseDatabase db;
    private DatabaseReference dbref;
    private FirebaseAuth auth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_customer_edit_profile);

        this.setTitle("Edit Profile");

        e_email = findViewById(R.id.editEmail);
        e_fullname = findViewById(R.id.editFullName);
        e_username = findViewById(R.id.editUsername);
        e_phone = findViewById(R.id.editContactNo);
        update = findViewById(R.id.btn_edit);

        auth = FirebaseAuth.getInstance();
        db = FirebaseDatabase.getInstance();
        dbref = db.getReference("Users");

        e_email.setText(getIntent().getStringExtra("email"));
        e_fullname.setText(getIntent().getStringExtra("full_name"));
        e_phone.setText(getIntent().getStringExtra("phone"));
        e_username.setText(getIntent().getStringExtra("username"));

        dbref.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {
                update.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                       if(e_phone.getText().length()!=0 && e_fullname.getText().length()!=0 && e_email.getText().length()!=0 && e_username.getText().length()!=0 ) {
                            String uid = auth.getUid();
                            String username_ = e_username.getText().toString();
                            String fullname_ = e_fullname.getText().toString();
                            String phone_ = e_phone.getText().toString();
                            String email_ = e_email.getText().toString();
                            dbref.child(uid).child("username").setValue(username_);
                            dbref.child(uid).child("contactNumber").setValue(phone_);
                            dbref.child(uid).child("fullName").setValue(fullname_);
                            dbref.child(uid).child("emailAddress").setValue(email_);
                            Toast.makeText(customer_edit_profile.this, "Profile Update Successful!", Toast.LENGTH_SHORT).show();
                            Intent intent2main = new Intent(customer_edit_profile.this, customer_main.class);
                            startActivity(intent2main);
                        } else {
                            Toast.makeText(customer_edit_profile.this, "All field must be filled!", Toast.LENGTH_SHORT).show();
                       }
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
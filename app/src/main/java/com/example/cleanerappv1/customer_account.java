package com.example.cleanerappv1;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Parcelable;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class customer_account extends AppCompatActivity {

    private TextView username, full_name, phone, email;
    private Button edit_profile, logout;

    private FirebaseDatabase db;
    private DatabaseReference dbref;
    private FirebaseAuth auth;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_customer_account);

        this.setTitle("My Account");

        username = findViewById(R.id.cust_username);
        full_name = findViewById(R.id.cust_fullname);
        phone = findViewById(R.id.cust_phone);
        email = findViewById(R.id.cust_email);
        edit_profile = findViewById(R.id.cust_edit);
        logout = findViewById(R.id.cust_logout);

        auth = FirebaseAuth.getInstance();
        db = FirebaseDatabase.getInstance();
        dbref = db.getReference("Users").child(auth.getUid());

        dbref.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                String username_ = snapshot.child("username").getValue().toString();
                String full_name_ = snapshot.child("fullName").getValue().toString();
                String phone_ = snapshot.child("contactNumber").getValue().toString();
                String email_ = snapshot.child("emailAddress").getValue().toString();

                username.setText(username_);
                full_name.setText(full_name_);
                phone.setText(phone_);
                email.setText(email_);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });


        edit_profile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent2edit = new Intent(customer_account.this, customer_edit_profile.class);
                intent2edit.putExtra("username", username.getText().toString());
                intent2edit.putExtra("full_name", full_name.getText().toString());
                intent2edit.putExtra("phone", phone.getText().toString());
                intent2edit.putExtra("email", email.getText().toString());

                startActivity(intent2edit);
            }
        });

        logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                auth.signOut();
                Intent intent = new Intent(customer_account.this, MainActivity.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                startActivity(intent);
                finish();
            }
        });
        //bottom nav
        BottomNavigationView bottomNavigationView = findViewById(R.id.bottom_nav);
        bottomNavigationView.setSelectedItemId(R.id.account);
        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()){
                    case R.id.book:
                        startActivity(new Intent(getApplicationContext(), book_list.class));
                        overridePendingTransition(0,0);
                        return true;
                    case R.id.home:
                        startActivity(new Intent(getApplicationContext(), customer_main.class));
                        overridePendingTransition(0,0);
                        return true;
                    case R.id.account:
                        return true;
                }
                return false;
            }
        });
    }
}
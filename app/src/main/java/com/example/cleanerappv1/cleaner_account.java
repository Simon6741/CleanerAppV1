package com.example.cleanerappv1;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.squareup.picasso.Picasso;

public class cleaner_account extends AppCompatActivity {

    private TextView tv_c_name, tv_c_email, tv_c_phone;
    private ImageView iv_c_image;
    private Button cleaner_btn_logout, cleaner_btn_tc, cleaner_btn_help, cleaner_btn_edit;
    private FirebaseUser cleaner;
    private DatabaseReference cleanerAccountDBRef;
    private String userID;
    private FirebaseAuth auth;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cleaner_account);

        tv_c_name = (TextView)findViewById(R.id.tv_c_name);
        tv_c_email = (TextView)findViewById(R.id.tv_c_email);
        tv_c_phone = (TextView)findViewById(R.id.tv_c_phone);
        iv_c_image = findViewById(R.id.iv_c_image);

        cleaner_btn_logout = findViewById(R.id.cleaner_btn_logout);
        cleaner_btn_tc = findViewById(R.id.cleaner_btn_tc);
        cleaner_btn_help = findViewById(R.id.cleaner_btn_help);
        cleaner_btn_edit = findViewById(R.id.cleaner_btn_edit);

        setTitle("My Account");

        BottomNavigationView navigationView = findViewById(R.id.navigation);
        //set Account Selected
        navigationView.setSelectedItemId(R.id.nav_account);

        navigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()){
                    case R.id.nav_home:
                        startActivity(new Intent(getApplicationContext(),cleaner_main.class));
                        overridePendingTransition(0,0);
                        return true;

                    case R.id.nav_complaint:
                        startActivity(new Intent(getApplicationContext(), cleaner_complaint.class));
                        overridePendingTransition(0,0);
                        return true;

                    case R.id.nav_account:
                        return true;
                }
                return false;
            }
        });

        cleaner = FirebaseAuth.getInstance().getCurrentUser();
        cleanerAccountDBRef = FirebaseDatabase.getInstance().getReference("Users");
        auth = FirebaseAuth.getInstance();

        //get user ID
        userID = cleaner.getUid();

        cleanerAccountDBRef.child(userID).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                String cleanerUserName = ""+snapshot.child("username").getValue();
                String cleanerEmail  = ""+snapshot.child("emailAddress").getValue();
                String cleanerPhone = ""+snapshot.child("contactNumber").getValue();
                String cleanerImage = ""+snapshot.child("image").getValue();

                tv_c_name.setText(cleanerUserName);
                tv_c_email.setText(cleanerEmail);
                tv_c_phone.setText(cleanerPhone);

                try{
                    Picasso.get().load(cleanerImage).placeholder(R.drawable.add_image).into(iv_c_image);
                }
                catch (Exception e) {

                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Toast.makeText(cleaner_account.this,"Something Wrong Happened!",Toast.LENGTH_SHORT).show();
            }

        });

        cleaner_btn_tc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent2TC = new Intent(cleaner_account.this,cleaner_tc.class);
                startActivity(intent2TC );
            }
        });

        cleaner_btn_help.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent2faq = new Intent(cleaner_account.this,cleaner_faq.class);
                startActivity(intent2faq);
            }
        });

        cleaner_btn_edit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent2edit = new Intent(cleaner_account.this,cleaner_edit_profile.class);
                startActivity(intent2edit);
            }
        });

        cleaner_btn_logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                auth.signOut();
                Intent intent = new Intent(cleaner_account.this, MainActivity.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                startActivity(intent);
                finish();
            }
        });


    }
}

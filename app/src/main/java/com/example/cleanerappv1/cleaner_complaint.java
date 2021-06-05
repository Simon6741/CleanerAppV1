package com.example.cleanerappv1;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.HashMap;

public class cleaner_complaint extends AppCompatActivity {

    private EditText et_complain_reason, et_complain_name;
    private Button btn_complain_submit;
    private FirebaseAuth mAuth;
    String name, reason;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cleaner_complaint);

        et_complain_reason = (EditText) findViewById(R.id.et_complain_reason);
        et_complain_name = (EditText) findViewById(R.id.et_complain_name);
        btn_complain_submit = findViewById(R.id.btn_complain_submit);
        BottomNavigationView navigationView = findViewById(R.id.navigation);

        setTitle("Complaint");

        //set Complaint Selected
        navigationView.setSelectedItemId(R.id.nav_complaint);
        navigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.nav_home:
                        startActivity(new Intent(getApplicationContext(), cleaner_main.class));
                        overridePendingTransition(0, 0);
                        return true;

                    case R.id.nav_complaint:
                        return true;

                    case R.id.nav_account:
                        startActivity(new Intent(getApplicationContext(), cleaner_account.class));
                        overridePendingTransition(0, 0);
                        return true;
                }
                return false;
            }
        });

        mAuth = FirebaseAuth.getInstance();


        btn_complain_submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addComplaint();
            }
        });

    }

    //insert complaint data
    private void addComplaint() {
        name = et_complain_name.getText().toString().trim();
        reason = et_complain_reason.getText().toString().trim();

        if (et_complain_name.getText().toString().matches("")) {
            Toast.makeText(getApplicationContext(), "Customer Name is required", Toast.LENGTH_SHORT).show();
            return;
        }
        if (et_complain_reason.getText().toString().matches("")) {
            Toast.makeText(getApplicationContext(), "Complain Reason is required", Toast.LENGTH_SHORT).show();
            return;
        }

        insertComplaint();
    }

    private void insertComplaint() {

        final String timestamp = "" + System.currentTimeMillis();

        //add complaint data to database
        HashMap<String, Object> hashMap = new HashMap<>();
        hashMap.put("complaint_id", "" + timestamp);
        hashMap.put("Name", "" + name);
        hashMap.put("Reason", "" + reason);
        hashMap.put("uid", "" + mAuth.getUid());

        //save to database
        DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference("CleanerComplaint");
        databaseReference.child(timestamp).setValue(hashMap).addOnSuccessListener(new OnSuccessListener<Void>() {
            @Override
            public void onSuccess(Void aVoid) {
                Toast.makeText(getApplicationContext(), "Data Inserted Successfully", Toast.LENGTH_SHORT).show();
                clearData();
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Toast.makeText(getApplicationContext(), "Data Inserted Unsuccessfully", Toast.LENGTH_SHORT).show();
            }
        });
    }

    //clear data
    private void clearData() {
        et_complain_reason.setText("");
        et_complain_name.setText("");
    }
}
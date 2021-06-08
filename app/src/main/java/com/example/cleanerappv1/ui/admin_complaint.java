package com.example.cleanerappv1.ui;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;
import android.widget.Toast;

import com.example.cleanerappv1.R;
import com.example.cleanerappv1.adapter.ComplaintListAdapter;
import com.example.cleanerappv1.adapter.ServiceListAdapter;
import com.example.cleanerappv1.model.Complain;
import com.example.cleanerappv1.model.ServiceDetails;
import com.example.cleanerappv1.model.ServiceItem;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class admin_complaint extends AppCompatActivity {

    RecyclerView rvComplaint;
    ComplaintListAdapter complaintListAdapter;
    private ArrayList<Complain> complainArrayList;
    private DatabaseReference databaseReference;

    String bookingID,commnet,feedback_id,rating;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_complaint);

        complainArrayList = new ArrayList<>();

        setupView();
        setupAdapter();
        setupData();

    }

    private void setupView() {
        rvComplaint = findViewById(R.id.recyclerViewComplaint);
    }

    private void setupAdapter() {
        rvComplaint.setHasFixedSize(true);

        rvComplaint.setLayoutManager(new LinearLayoutManager(this));

        complaintListAdapter = new ComplaintListAdapter(this, complainArrayList);
        rvComplaint.setAdapter(complaintListAdapter);
    }

    private void setupData() {
        databaseReference = FirebaseDatabase.getInstance().getReference();
        Query query = databaseReference.child("CleanerComplaint");
        query.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if (snapshot.exists()) {
                    for (DataSnapshot dataSnapshot : snapshot.getChildren()) {
                        Complain complain = dataSnapshot.getValue(Complain.class);
                        complainArrayList.add(complain);
                    }
  //                  Log.d("complaint", String.valueOf(complainArrayList.size()));

                    complaintListAdapter.notifyDataSetChanged();

                    if (complainArrayList.isEmpty()) {
                        Toast.makeText(getApplicationContext(), "No Data Found", Toast.LENGTH_LONG).show();
                    }

                }

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });


    }

}
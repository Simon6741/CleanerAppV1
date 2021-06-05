package com.example.cleanerappv1;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class cleaner_tc extends AppCompatActivity {

    private RecyclerView recyclerViewTC;
    private DatabaseReference db;
    private ArrayList<CleanerComplaint> CleanerTCList; //need to modify
    private AdapterCleanerTC adapterCleanerTC;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cleaner_tc);

        setTitle("Terms and Conditions");

        recyclerViewTC = findViewById(R.id.recyclerViewTC);
        db = FirebaseDatabase.getInstance().getReference("CleanerComplaint");

        recyclerViewTC.setHasFixedSize(true);
        recyclerViewTC.setLayoutManager(new LinearLayoutManager(this));

        CleanerTCList = new ArrayList<>();
        adapterCleanerTC = new AdapterCleanerTC(this,CleanerTCList);
        recyclerViewTC.setAdapter(adapterCleanerTC);

        db.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for (DataSnapshot dataSnapshot : snapshot.getChildren()){
                    CleanerComplaint cleanerComplaint = dataSnapshot.getValue(CleanerComplaint.class); //need to modify
                    CleanerTCList.add(cleanerComplaint); //need to modify

                }
                adapterCleanerTC.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

    }

}
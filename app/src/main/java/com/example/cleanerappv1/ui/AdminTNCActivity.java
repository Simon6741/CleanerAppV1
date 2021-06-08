package com.example.cleanerappv1.ui;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import com.example.cleanerappv1.R;
import com.example.cleanerappv1.adapter.FaqListAdapter;
import com.example.cleanerappv1.adapter.TncListAdapter;
import com.example.cleanerappv1.model.FAQ;
import com.example.cleanerappv1.model.TNC;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import java.util.ArrayList;

public class AdminTNCActivity extends AppCompatActivity {

    Button btnAdd;
    RecyclerView rvTNC;
    TncListAdapter tncListAdapter;
    private ArrayList<TNC> tncArrayList;
    private DatabaseReference databaseReference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_terms);
        tncArrayList = new ArrayList<> ();

        setupView();
        setOnclick();
        setupAdapter();
        setupData();
    }

    private void setupView() {
        btnAdd = findViewById(R.id.btn_tnc_add);

        rvTNC = findViewById(R.id.recyclerViewTNC);
    }

    private void setupAdapter() {
        rvTNC.setHasFixedSize(true);
        rvTNC.setLayoutManager(new LinearLayoutManager(this));

        tncListAdapter = new TncListAdapter(this, tncArrayList);
        rvTNC.setAdapter(tncListAdapter);
    }

    private void setupData() {
        databaseReference = FirebaseDatabase.getInstance().getReference();
        Query query = databaseReference.child("TermCondition");
        query.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if (snapshot.exists()) {
                    for (DataSnapshot dataSnapshot : snapshot.getChildren()) {
                        TNC tnc = dataSnapshot.getValue(TNC.class);
                        tncArrayList.add(tnc);
//                        Log.d("TItle", tnc.getTerm());
                    }
                    Log.d("TAG", String.valueOf(tncArrayList.size()));


                    tncListAdapter.notifyDataSetChanged();

                    if (tncArrayList.isEmpty()) {
                        Toast.makeText(getApplicationContext(), "No Data Found", Toast.LENGTH_LONG).show();
                    }

                }

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });


    }


    private void setOnclick() {
        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), AddTNCActivity.class);
                startActivity(intent);
                overridePendingTransition(0, 0);

            }
        });

    }
}
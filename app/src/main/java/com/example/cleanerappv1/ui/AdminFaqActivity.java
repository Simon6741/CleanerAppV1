package com.example.cleanerappv1.ui;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;

import com.example.cleanerappv1.R;
import com.example.cleanerappv1.adapter.FaqListAdapter;
import com.example.cleanerappv1.model.FAQ;
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


public class AdminFaqActivity extends AppCompatActivity implements FaqListAdapter.ItemClickListener {

    Button btnAdd;
    RecyclerView rvFaq;
    FaqListAdapter faqListAdapter;
    private ArrayList<FAQ> faqArrayList;
    private DatabaseReference databaseReference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_faq);

        faqArrayList = new ArrayList<> ();

        btnAdd = findViewById(R.id.btn_faq_add);
        setOnclick();
        setupView();
        setupAdapter();
        setupData();

    }

    private void setupView() {
        rvFaq = findViewById(R.id.recyclerViewFAQ);
    }

    private void setupAdapter() {
        rvFaq.setHasFixedSize(true);
        rvFaq.setLayoutManager(new LinearLayoutManager(this));

        faqListAdapter = new FaqListAdapter(this, faqArrayList,this);
        rvFaq.setAdapter(faqListAdapter);
    }

    private void setupData() {
        databaseReference = FirebaseDatabase.getInstance().getReference();
        Query query = databaseReference.child("faq");
        query.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if (snapshot.exists()) {
                    for (DataSnapshot dataSnapshot : snapshot.getChildren()) {
                        FAQ faq = dataSnapshot.getValue(FAQ.class);
                        faqArrayList.add(faq);
                        Log.d("TItle", faq.getTitle());
                    }
                    Log.d("TAG", String.valueOf(faqArrayList.size()));


                    faqListAdapter.notifyDataSetChanged();

                    if (faqArrayList.isEmpty()) {
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
                Intent intent = new Intent(getApplicationContext(), AdminAddFaqActivity.class);
//                intent.putExtra("viewType", "Customer");
                startActivity(intent);

                overridePendingTransition(0, 0);
            }
        });


    }

    @Override
    public void onItemClick(int position) {
        startActivity(new Intent(this, ActivityEditFAQ.class));
    }
}
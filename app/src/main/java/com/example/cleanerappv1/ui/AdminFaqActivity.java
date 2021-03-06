package com.example.cleanerappv1.ui;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.Activity;
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
import android.widget.RelativeLayout;
import android.widget.Toast;

import java.util.ArrayList;

import static com.example.cleanerappv1.util.Constant.INTENT_CONTACT;
import static com.example.cleanerappv1.util.Constant.INTENT_EMAIL;
import static com.example.cleanerappv1.util.Constant.INTENT_FAQ_DESC;
import static com.example.cleanerappv1.util.Constant.INTENT_FAQ_TITLE;
import static com.example.cleanerappv1.util.Constant.INTENT_ID;
import static com.example.cleanerappv1.util.Constant.INTENT_NAME;
import static com.example.cleanerappv1.util.Constant.INTENT_PASSWORD;
import static com.example.cleanerappv1.util.Constant.INTENT_USERNAME;
import static com.example.cleanerappv1.util.Constant.INTENT_USER_ID;
import static com.example.cleanerappv1.util.Constant.INTENT_VIEWTYPE;
import static com.example.cleanerappv1.util.Constant.START_FOR_RESULT;
import static com.example.cleanerappv1.util.Constant.TYPE_CUSTOMER;

import static com.example.cleanerappv1.util.Constant.INTENT_VIEWTYPE;
import static com.example.cleanerappv1.util.Constant.START_FOR_RESULT;
import static com.example.cleanerappv1.util.Constant.START_FOR_RESULT_FAQ_ADD;


public class AdminFaqActivity extends AppCompatActivity implements FaqListAdapter.ItemClickListener {

    Button btnAdd;
    RecyclerView rvFaq;
    FaqListAdapter faqListAdapter;
    private ArrayList<FAQ> faqArrayList;
    private DatabaseReference databaseReference;
    RelativeLayout progressIndicator;


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

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode == START_FOR_RESULT){
            if(resultCode == Activity.RESULT_OK){
                faqArrayList.clear();
                setupData();
            }
        }

    }

    private void setupView() {
        rvFaq = findViewById(R.id.recyclerViewFAQ);
        progressIndicator = findViewById(R.id.progress_service);
        progressIndicator.setVisibility(View.VISIBLE);
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
                progressIndicator.setVisibility(View.INVISIBLE);

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
                progressIndicator.setVisibility(View.INVISIBLE);
            }
        });


    }



    private void setOnclick() {
        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), AdminAddFaqActivity.class);
                startActivityForResult(intent, START_FOR_RESULT);
                overridePendingTransition(0, 0);
            }
        });


    }

    @Override
    public void onItemClick(int position) {

        Intent intent = new Intent(this, ActivityEditFAQ.class);
        intent.putExtra(INTENT_FAQ_TITLE,faqArrayList.get(position).getTitle());
        intent.putExtra(INTENT_FAQ_DESC,faqArrayList.get(position).getDetail());
        intent.putExtra(INTENT_ID,faqArrayList.get(position).getId());
        startActivityForResult(intent,START_FOR_RESULT);
        overridePendingTransition(0, 0);
    }
}
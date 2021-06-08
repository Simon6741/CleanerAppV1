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
import android.widget.RelativeLayout;
import android.widget.Toast;

import java.util.ArrayList;

import static com.example.cleanerappv1.util.Constant.INTENT_VIEWTYPE;
import static com.example.cleanerappv1.util.Constant.START_FOR_RESULT;

import static com.example.cleanerappv1.util.Constant.INTENT_FAQ_DESC;
import static com.example.cleanerappv1.util.Constant.INTENT_FAQ_TITLE;
import static com.example.cleanerappv1.util.Constant.INTENT_ID;
import static com.example.cleanerappv1.util.Constant.START_FOR_RESULT;

public class AdminTNCActivity extends AppCompatActivity implements TncListAdapter.ItemClickListener {

    Button btnAdd;
    RecyclerView rvTNC;
    TncListAdapter tncListAdapter;
    private ArrayList<TNC> tncArrayList;
    private DatabaseReference databaseReference;
    RelativeLayout progressIndicator;


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

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if(requestCode == START_FOR_RESULT){
            if(resultCode == Activity.RESULT_OK){
                tncArrayList.clear();
                setupData();
            }
        }
    }

    private void setupView() {
        btnAdd = findViewById(R.id.btn_tnc_add);

        rvTNC = findViewById(R.id.recyclerViewTNC);
        progressIndicator = findViewById(R.id.progress_service);
        progressIndicator.setVisibility(View.VISIBLE);
    }

    private void setupAdapter() {
        rvTNC.setHasFixedSize(true);
        rvTNC.setLayoutManager(new LinearLayoutManager(this));

        tncListAdapter = new TncListAdapter(this, tncArrayList,this);
        rvTNC.setAdapter(tncListAdapter);
    }

    private void setupData() {
        databaseReference = FirebaseDatabase.getInstance().getReference();
        Query query = databaseReference.child("TermCondition");
        query.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                progressIndicator.setVisibility(View.INVISIBLE);
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
                startActivityForResult(intent, START_FOR_RESULT);
                overridePendingTransition(0, 0);

            }
        });

    }

    @Override
    public void onItemClick(int position) {
        Intent intent = new Intent(this, ActivityEditTerm.class);
        intent.putExtra(INTENT_FAQ_DESC,tncArrayList.get(position).getTerm());
        intent.putExtra(INTENT_ID,tncArrayList.get(position).getId());
        startActivityForResult(intent,START_FOR_RESULT);
        overridePendingTransition(0, 0);
    }
}
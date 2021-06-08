package com.example.cleanerappv1.ui;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.cleanerappv1.R;
import com.example.cleanerappv1.adapter.ServiceListAdapter;
import com.example.cleanerappv1.model.ServiceDetails;
import com.example.cleanerappv1.model.ServiceItem;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import static com.example.cleanerappv1.util.Constant.INTENT_VIEWTYPE;
import static com.example.cleanerappv1.util.Constant.STATUS_COMPLETED;
import static com.example.cleanerappv1.util.Constant.STATUS_RATED;

public class AdminBookingActivity extends AppCompatActivity {

    RecyclerView rvInProgress, rvHistory;
    TextView txtServiceTitle;
    String viewType;
    ServiceListAdapter inProgressAdapter, historyAdapter;
    RelativeLayout progressIndicator;

    private ArrayList<ServiceItem> inProgressList, historyList, rawList;
    private ArrayList<ServiceDetails> serviceDetailsList;
    private DatabaseReference databaseReference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_booking);

        inProgressList = new ArrayList<>();
        historyList = new ArrayList<>();
        rawList = new ArrayList<>();

        serviceDetailsList = new ArrayList<>();

        viewType = getIntent().getStringExtra(INTENT_VIEWTYPE);
        setupView();
        initView();
        setupData();
        setupAdapter();
    }

    private void setupView() {
        txtServiceTitle = findViewById(R.id.txt_service_title);
        rvInProgress = findViewById(R.id.recycleViewProgress);
        progressIndicator = findViewById(R.id.progress_service);
   //     rvHistory = findViewById(R.id.recyclerViewHistory);
    }

    private void initView() {
        txtServiceTitle.setText(viewType);

    }

    private void setupAdapter() {
        rvInProgress.setHasFixedSize(true);

        rvInProgress.setLayoutManager(new LinearLayoutManager(this));

        inProgressAdapter = new ServiceListAdapter(this, serviceDetailsList);
        rvInProgress.setAdapter(inProgressAdapter);


    }

    private void setupData() {
        progressIndicator.setVisibility(View.VISIBLE);
        databaseReference = FirebaseDatabase.getInstance().getReference();
        Query query = databaseReference.child("CustomerBookings").orderByChild("service").equalTo(viewType);
        query.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if (snapshot.exists()) {
                    for (DataSnapshot dataSnapshot : snapshot.getChildren()) {
                        ServiceDetails serviceDetails = dataSnapshot.getValue(ServiceDetails.class);
                        serviceDetailsList.add(serviceDetails);
                    }



                    inProgressAdapter.notifyDataSetChanged();
                    progressIndicator.setVisibility(View.GONE);

                }

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

    }


}
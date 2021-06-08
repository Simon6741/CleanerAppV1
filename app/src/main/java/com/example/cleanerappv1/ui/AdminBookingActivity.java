package com.example.cleanerappv1.ui;

import android.os.Bundle;
import android.util.Log;
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
    private ArrayList<ServiceItem> inProgressList, historyList, rawList;
    private ArrayList<ServiceDetails> serviceDetailsList;
    private DatabaseReference databaseReference;

    String userName, userContact, userEmail, cleanerName, cleanerContact;
    ServiceItem service;

    int i = 0;

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
   //     rvHistory = findViewById(R.id.recyclerViewHistory);
    }

    private void initView() {
        txtServiceTitle.setText(viewType);
    }

    private void setupAdapter() {
        rvInProgress.setHasFixedSize(true);
       // rvInProgress.setNestedScrollingEnabled(false);
        rvInProgress.setLayoutManager(new LinearLayoutManager(this));

        inProgressAdapter = new ServiceListAdapter(this, serviceDetailsList);
        rvInProgress.setAdapter(inProgressAdapter);

//        rvHistory.setHasFixedSize(true);
//        rvHistory.setNestedScrollingEnabled(false);
//        rvHistory.setLayoutManager(new LinearLayoutManager(this));
//
//        historyAdapter = new ServiceListAdapter(this, historyList);
//        rvHistory.setAdapter(historyAdapter);

    }

    private void setupData() {
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

                    Log.d("RAW", String.valueOf(serviceDetailsList.size()));

                    inProgressAdapter.notifyDataSetChanged();
                    Log.d("RAW", String.valueOf(inProgressAdapter.getItemCount()));

//
//                    if (rawList.isEmpty()) {
//                        Toast.makeText(getApplicationContext(), "No Data Found", Toast.LENGTH_LONG).show();
//                    } else {
//                        filterRawList();
//                    }


                }

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

//        query.addListenerForSingleValueEvent(new ValueEventListener() {
//            @Override
//            public void onDataChange(@NonNull DataSnapshot snapshot) {
//
//                if (snapshot.exists()) {
//                    for(DataSnapshot dataSnapshot : snapshot.getChildren()){
//                        ServiceDetails serviceDetails = dataSnapshot.getValue(ServiceDetails.class);
//                        serviceDetailsList.add(serviceDetails);
//                        //setupCustomerDetails(serviceDetails);
//                    }
//
//                    Log.d("RAW", String.valueOf(serviceDetailsList.size()));
//
//                    if(rawList.isEmpty()){
//                        Toast.makeText(getApplicationContext(),"No Data Found", Toast.LENGTH_LONG).show();
//                    } else {
//
//                  //      filterRawList();
//                    }
//
//
//                } else {
//                    Toast.makeText(getApplicationContext(),"No Data Found", Toast.LENGTH_LONG).show();
//                }
//            }
//
//            @Override
//            public void onCancelled(@NonNull DatabaseError error) {
//                Toast.makeText(getApplicationContext(),error.getDetails(), Toast.LENGTH_LONG).show();
//            }
//        });
    }

    private void setupCustomerDetails(ServiceDetails serviceDetails) {
        DatabaseReference dRrefUser = FirebaseDatabase.getInstance().getReference().child("Users").child(serviceDetails.getUid());

        dRrefUser.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {

                userName = snapshot.child("username").getValue().toString();
                userEmail = snapshot.child("emailAddress").getValue().toString();
                userContact = snapshot.child("contactNumber").getValue().toString();
                Log.d("UserName", userName);

                service = new ServiceItem(userName, userEmail, userContact, cleanerName, cleanerContact, serviceDetails.getStatus());
                Log.d("UserName2", service.getCustomerName());
                //rawList.add(service);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
//        if(serviceDetails.getCid() != null && serviceDetails.getCid() != ""){
//            Log.d("Cleaner Out",serviceDetails.getCid());
//            DatabaseReference dRrefCleaner = FirebaseDatabase.getInstance().getReference().child("Users").child(serviceDetails.getCid());
//
//            dRrefCleaner.addValueEventListener(new ValueEventListener() {
//                @Override
//                public void onDataChange(@NonNull DataSnapshot snapshot) {
//                 //   Log.d("Cleaner",snapshot.child("username").getValue().toString());
//                cleanerName = snapshot.child("username").getValue().toString();
//                cleanerContact = snapshot.child("contactNumber").getValue().toString();
//                }
//
//                @Override
//                public void onCancelled(@NonNull DatabaseError error) {
//
//                }
//            });
//        } else {
//            Log.d("Cleaner","Empty");
//            cleanerName = "";
//            cleanerContact = "";
//        }
        Log.d("UserName3", service.getCustomerName());
      //  rawList.add(service);
    }

//    private void filterRawList() {
////        historyList.clear();
////        inProgressList.clear();
//        for (ServiceItem serviceItem : rawList) {
//            Log.d("Booking Type", serviceItem.getBookingStatus());
//            if (serviceItem.getBookingStatus().equals(STATUS_COMPLETED) || serviceItem.getBookingStatus().equals(STATUS_RATED)) {
//                historyList.add(serviceItem);
//              // Log.d("Booking Type", serviceItem.getBookingStatus());
//            } else {
//                inProgressList.add(serviceItem);
//            }
//        }
//
//        Log.d("Histoy", String.valueOf(historyList.size()));
//        Log.d("in progress", String.valueOf(inProgressList.size()));
//
//        for(ServiceItem service : historyList){
//            Log.d("Histoy list", "YEs");
//        }
//
//        for(ServiceItem serviceItem : inProgressList){
//            Log.d("in progress list", "No");
//        }
//
//
////
////        historyAdapter.notifyDataSetChanged();
////        inProgressAdapter.notifyDataSetChanged();
//    }
}
package com.example.cleanerappv1.ui;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.cleanerappv1.AdapterTaskHistory;
import com.example.cleanerappv1.CustomerBookings;
import com.example.cleanerappv1.R;
import com.example.cleanerappv1.adapter.CleanerListAdapter;
import com.example.cleanerappv1.adapter.CustomerListAdapter;
import com.example.cleanerappv1.model.Cleaner;
import com.example.cleanerappv1.model.Customer;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;


import static com.example.cleanerappv1.util.Constant.INTENT_CONTACT;
import static com.example.cleanerappv1.util.Constant.INTENT_EMAIL;
import static com.example.cleanerappv1.util.Constant.INTENT_NAME;
import static com.example.cleanerappv1.util.Constant.INTENT_PASSWORD;
import static com.example.cleanerappv1.util.Constant.INTENT_USERNAME;
import static com.example.cleanerappv1.util.Constant.INTENT_USER_ID;
import static com.example.cleanerappv1.util.Constant.INTENT_VIEWTYPE;
import static com.example.cleanerappv1.util.Constant.START_FOR_RESULT;
import static com.example.cleanerappv1.util.Constant.TYPE_CUSTOMER;


public class AdminUserListActivity extends AppCompatActivity implements CustomerListAdapter.ItemClickListener {
    //TextView txtUserType;
    String userType;
    TextView txtUserType;
    private RecyclerView recyclerViewUserList;
    private DatabaseReference databaseReference;
    private ArrayList<Customer> customerArrayList;
    private ArrayList<Cleaner> cleanerArrayList;
    private CleanerListAdapter cleanerListAdapter;
    private CustomerListAdapter customerListAdapter;

    RelativeLayout progressIndicator;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_cleaner);

        customerArrayList = new ArrayList<>();
        cleanerArrayList = new ArrayList<>();

        setupView();
        setupAdapter();
        setupData();

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if(requestCode == START_FOR_RESULT){
            if(resultCode == Activity.RESULT_OK){
                customerArrayList.clear();
                cleanerArrayList.clear();
                userType = data.getStringExtra(INTENT_VIEWTYPE);
                setupData();
            }
        }
    }

    private void setupView(){
        txtUserType = findViewById(R.id.txt_userType);
        recyclerViewUserList = findViewById(R.id.recyclerViewUser);
        progressIndicator = findViewById(R.id.progress_indicator);

        userType = getIntent().getStringExtra("viewType");
        txtUserType.setText(userType);


    }

    private void setupAdapter() {
        recyclerViewUserList.setHasFixedSize(true);
        recyclerViewUserList.setLayoutManager(new LinearLayoutManager(this));

        if(userType.equals("Customer")){
            customerListAdapter = new CustomerListAdapter(this, customerArrayList, this);
            recyclerViewUserList.setAdapter(customerListAdapter);
        }
        else {
            cleanerListAdapter = new CleanerListAdapter(this, cleanerArrayList, this);
            recyclerViewUserList.setAdapter(cleanerListAdapter);
        }
    }

    private void setupData(){
        progressIndicator.setVisibility(View.VISIBLE);

        databaseReference = FirebaseDatabase.getInstance().getReference();
        Query query = databaseReference.child("Users").orderByChild("userType").equalTo(userType);

        query.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {

                if (snapshot.exists()) {
                    if(userType.equals("Customer")){
                        for (DataSnapshot dataSnapshot : snapshot.getChildren()) {
                            Customer customer = dataSnapshot.getValue(Customer.class);
                            customerArrayList.add(customer);
                        }
                        Log.d("Customer", String.valueOf(customerArrayList.size()));
                        customerListAdapter.notifyDataSetChanged();
                        progressIndicator.setVisibility(View.GONE);
                    }
                    else {
                        for (DataSnapshot dataSnapshot : snapshot.getChildren()) {
                            Cleaner cleaner = dataSnapshot.getValue(Cleaner.class);
                            cleanerArrayList.add(cleaner);
                        }
                        Log.d("Cleaner", String.valueOf(cleanerArrayList.size()));
                        cleanerListAdapter.notifyDataSetChanged();
                        progressIndicator.setVisibility(View.GONE);
                    }
                } else {
                    Toast.makeText(getApplicationContext(),"No Data Found", Toast.LENGTH_LONG).show();
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Toast.makeText(getApplicationContext(),error.getDetails(), Toast.LENGTH_LONG).show();
            }
        });
        progressIndicator.setVisibility(View.GONE);
    }

    @Override
    public void onItemClick(int position, String viewType) {

        Intent intent = new Intent(this, AdminUserEditActivity.class);
        intent.putExtra(INTENT_VIEWTYPE,viewType);
        if(viewType.equals(TYPE_CUSTOMER)){
            intent.putExtra(INTENT_USERNAME,customerArrayList.get(position).getUsername());
            intent.putExtra(INTENT_NAME,customerArrayList.get(position).getFullName());
            intent.putExtra(INTENT_EMAIL,customerArrayList.get(position).getEmailAddress());
            intent.putExtra(INTENT_CONTACT,customerArrayList.get(position).getContactNumber());
            intent.putExtra(INTENT_USER_ID,customerArrayList.get(position).getUid());
            intent.putExtra(INTENT_PASSWORD, customerArrayList.get(position).getPassword());
        } else {
            intent.putExtra(INTENT_USERNAME,cleanerArrayList.get(position).getUsername());
            intent.putExtra(INTENT_NAME,cleanerArrayList.get(position).getFullName());
            intent.putExtra(INTENT_EMAIL,cleanerArrayList.get(position).getEmailAddress());
            intent.putExtra(INTENT_CONTACT,cleanerArrayList.get(position).getContactNumber());
            intent.putExtra(INTENT_USER_ID,cleanerArrayList.get(position).getUid());
            intent.putExtra(INTENT_PASSWORD, cleanerArrayList.get(position).getPassword());
        }

        startActivityForResult(intent,START_FOR_RESULT);
        //startActivity(new Intent(this, AdminUserEditActivity.class));
        //Toast.makeText(this, customerArrayList.get(position).getUsername(), Toast.LENGTH_LONG);

    }
}


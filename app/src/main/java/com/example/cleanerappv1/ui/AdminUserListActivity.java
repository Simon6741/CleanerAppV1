package com.example.cleanerappv1.ui;

import android.os.Bundle;
import android.util.Log;
import android.widget.AdapterView;
import android.widget.ImageView;
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



public class AdminUserListActivity extends AppCompatActivity{
    //TextView txtUserType;
    String userType;
    TextView txtUserType;
    private RecyclerView recyclerViewUserList;
    private DatabaseReference databaseReference;
    private ArrayList<Customer> customerArrayList;
    private ArrayList<Cleaner> cleanerArrayList;
    private CleanerListAdapter cleanerListAdapter;
    private CustomerListAdapter customerListAdapter;

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

    private void setupView(){
        txtUserType = findViewById(R.id.txt_userType);
        recyclerViewUserList = findViewById(R.id.recyclerViewUser);

        userType = getIntent().getStringExtra("viewType");
        txtUserType.setText(userType);

    }

    private void setupAdapter() {
        recyclerViewUserList.setHasFixedSize(true);
        recyclerViewUserList.setLayoutManager(new LinearLayoutManager(this));

        if(userType == "Customer"){
            customerListAdapter = new CustomerListAdapter(this, customerArrayList);
            recyclerViewUserList.setAdapter(customerListAdapter);
        } else {
            cleanerListAdapter = new CleanerListAdapter(this, cleanerArrayList);
            recyclerViewUserList.setAdapter(cleanerListAdapter);
        }
    }

    private void setupData(){
        databaseReference = FirebaseDatabase.getInstance().getReference();
        Query query = databaseReference.child("Users").orderByChild("userType").equalTo(userType);

        query.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                Log.d("customer", "Hello3");
                if (snapshot.exists()) {
                    if(userType == "Customer"){
                        for (DataSnapshot dataSnapshot : snapshot.getChildren()) {
                            Customer customer = dataSnapshot.getValue(Customer.class);
                            customerArrayList.add(customer);
                        }
                        customerListAdapter.notifyDataSetChanged();
                    } else {
                        for (DataSnapshot dataSnapshot : snapshot.getChildren()) {
                            Cleaner cleaner = dataSnapshot.getValue(Cleaner.class);
                            cleanerArrayList.add(cleaner);
                        }
                        cleanerListAdapter.notifyDataSetChanged();
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
    }

}


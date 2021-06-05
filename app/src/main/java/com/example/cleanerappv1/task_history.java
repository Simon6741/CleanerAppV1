package com.example.cleanerappv1;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.widget.ImageView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class task_history extends AppCompatActivity {

    private RecyclerView recyclerViewTaskHistory;
    private DatabaseReference databaseReference;
    private ArrayList<CustomerBookings> historyLists;
    private AdapterTaskHistory adapterTaskHistory;
    private ImageView imageViewNext;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_task_history);

        setTitle("History");

        imageViewNext = findViewById(R.id.imageViewNext);
        recyclerViewTaskHistory = findViewById(R.id.recyclerViewTaskHistory);

        recyclerViewTaskHistory.setHasFixedSize(true);
        recyclerViewTaskHistory.setLayoutManager(new LinearLayoutManager(this));

        historyLists = new ArrayList<>();
        adapterTaskHistory = new AdapterTaskHistory(this,historyLists);
        recyclerViewTaskHistory.setAdapter(adapterTaskHistory);



        Query query1 = FirebaseDatabase.getInstance().getReference("CustomerBookings")
                .orderByChild("status")
                .equalTo("Accepted");

                //query to show the completed task only
        query1.addListenerForSingleValueEvent(valueEventListener);

        // databaseReference.addValueEventListener(new ValueEventListener() {
        // @Override
        //public void onDataChange(@NonNull DataSnapshot snapshot) {
        //for (DataSnapshot dataSnapshot : snapshot.getChildren()){
        // CustomerBookings customerBookings = dataSnapshot.getValue(CustomerBookings.class); //need to modify
        //historyLists.add(customerBookings);

        // }
        // adapterTaskHistory.notifyDataSetChanged();
        //  }

        // @Override
        //public void onCancelled(@NonNull DatabaseError error) {

        //}
        // });
    }



    ValueEventListener valueEventListener = new ValueEventListener() {
        @Override
        public void onDataChange(@NonNull DataSnapshot snapshot) {
            if (snapshot.exists()) {
                historyLists.clear();
                for (DataSnapshot dataSnapshot : snapshot.getChildren()) {
                    CustomerBookings customerBookings = dataSnapshot.getValue(CustomerBookings.class);
                    historyLists.add(customerBookings);
                }
                adapterTaskHistory.notifyDataSetChanged();
            }

        }

        @Override
        public void onCancelled(@NonNull DatabaseError error) {

        }
    };
}
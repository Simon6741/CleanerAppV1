package com.example.cleanerappv1;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;

public class book_list extends AppCompatActivity {

    private ListView lv_booking, lv_history, lv_rated;
    ArrayList<String> myArrayList = new ArrayList<>();
    ArrayList<String> myArrayList1 = new ArrayList<>();
    ArrayList<String> myArrayList2 = new ArrayList<>();
    private FirebaseDatabase db;
    private DatabaseReference dbref;
    private FirebaseAuth auth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_book_list);

        this.setTitle("Booking List");

        //history list
        lv_history = findViewById(R.id.lv_history);
        ArrayAdapter<String> myArrayAdapter1 = new ArrayAdapter<String>(book_list.this, android.R.layout.simple_list_item_1, myArrayList1);
        lv_history.setAdapter(myArrayAdapter1);

        //booking list array
        lv_booking = findViewById(R.id.lv_booking);
        ArrayAdapter<String> myArrayAdapter = new ArrayAdapter<String>(book_list.this, android.R.layout.simple_list_item_1, myArrayList);
        lv_booking.setAdapter(myArrayAdapter);

        //rated list array
        lv_rated = findViewById(R.id.lv_rated);
        ArrayAdapter<String> myArrayAdapter2 = new ArrayAdapter<String>(book_list.this, android.R.layout.simple_list_item_1, myArrayList2);
        lv_rated.setAdapter(myArrayAdapter2);

        //firebase
        db = FirebaseDatabase.getInstance();
        dbref = db.getReference("CustomerBookings");
        auth = FirebaseAuth.getInstance();

        //booking list
        dbref.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {
                String uid = snapshot.child("uid").getValue().toString();
                String serv = snapshot.child("service").getValue().toString();
                String date = snapshot.child("date").getValue().toString();
                String time = snapshot.child("time").getValue().toString();
                String status = snapshot.child("status").getValue().toString();
                String b_id = snapshot.child("booking_id").getValue().toString();
                String show = serv + " @ " + date;
                String auth_id = auth.getUid().toString();
                //booking list
                if (uid.contentEquals(auth_id)) {
                    if (status.contentEquals("Paid")) {
                        myArrayList.add(show);
                        myArrayAdapter.notifyDataSetChanged();
                        lv_booking.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                            @Override
                            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                                Intent intent2update = new Intent(book_list.this, view_booking.class);
                                intent2update.putExtra("service", serv);
                                intent2update.putExtra("date", date);
                                intent2update.putExtra("time", time);
                                intent2update.putExtra("status", status);
                                intent2update.putExtra("b_id", b_id);

                                startActivity(intent2update);
                            }
                        });
                    }
                    //history list
                    else if (status.contentEquals("Completed")) {
                        myArrayList1.add(show);
                        myArrayAdapter1.notifyDataSetChanged();

                            lv_history.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                                @Override
                                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                                    Intent intent2feedback = new Intent(book_list.this, customer_feedback.class);
                                    intent2feedback.putExtra("b_id", b_id);

                                    startActivity(intent2feedback);
                                }
                            });
                    } else if (status.contentEquals("Rated")){
                        myArrayList2.add(show);
                        myArrayAdapter2.notifyDataSetChanged();
                    }
                } else
            {
                Toast.makeText(book_list.this, "Error", Toast.LENGTH_SHORT).show();
            }


        }
            @Override
            public void onChildChanged(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {
                myArrayAdapter.notifyDataSetChanged();
                myArrayAdapter1.notifyDataSetChanged();
                myArrayAdapter2.notifyDataSetChanged();
            }

            @Override
            public void onChildRemoved(@NonNull DataSnapshot snapshot) {

            }

            @Override
            public void onChildMoved(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });



        //history list



        //bottom nav
        BottomNavigationView bottomNavigationView = findViewById(R.id.bottom_nav);
        bottomNavigationView.setSelectedItemId(R.id.book);
        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()){
                    case R.id.book:
                        return true;
                    case R.id.home:
                        startActivity(new Intent(getApplicationContext(), customer_main.class));
                        overridePendingTransition(0,0);
                        return true;
                    case R.id.account:
                        startActivity(new Intent(getApplicationContext(), customer_account.class));
                        overridePendingTransition(0,0);
                        return true;
                }
                return false;
            }
        });
    }
}
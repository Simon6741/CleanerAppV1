package com.example.cleanerappv1;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;
import java.util.List;

public class cleaner_main_new extends AppCompatActivity {

    private ListView lv_pend;
    private Button viewTask;
    private TextView tv_name;
    ArrayList<String> myArrayList = new ArrayList<>();
    private FirebaseDatabase db;
    private DatabaseReference dbref;

    private FirebaseAuth auth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cleaner_main_new);

        lv_pend = findViewById(R.id.lv_pend);
       tv_name = findViewById(R.id.tv_name);
          viewTask = findViewById(R.id.viewTask);

        viewTask.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent2viewHistory = new Intent(cleaner_main_new.this, TaskHistoryCleaner.class);
                startActivity(intent2viewHistory);
            }
        });

        lv_pend = findViewById(R.id.lv_pend);
        ArrayAdapter<String> myArrayAdapter = new ArrayAdapter<String>(cleaner_main_new.this, android.R.layout.simple_list_item_1, myArrayList);
        lv_pend.setAdapter(myArrayAdapter);

        auth = FirebaseAuth.getInstance();
        db = FirebaseDatabase.getInstance();
        dbref= db.getReference("Users");
        dbref.orderByChild("uid").equalTo(auth.getUid()).addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {
                // String cleaner = auth.getUid().toString();
                String c_name = snapshot.child("fullName").getValue().toString();
                tv_name.setText(String.valueOf(c_name));
            }

            @Override
            public void onChildChanged(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {

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

        db = FirebaseDatabase.getInstance();
        dbref = db.getReference("CustomerBookings");
        dbref.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {
                String book = snapshot.child("booking_id").getValue().toString();
                String stat = snapshot.child("status").getValue().toString();
                String serv = snapshot.child("name").getValue().toString();
                String show = serv + "@" + stat;
                if(stat.contentEquals("Paid")) {
                    myArrayList.add(show);
                    myArrayAdapter.notifyDataSetChanged();
                    lv_pend.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                        @Override
                        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                            Intent intent2next = new Intent(cleaner_main_new.this, cleaner_book.class);
                            intent2next.putExtra("book", book);
                            startActivity(intent2next);
                        }
                    });
                }

            }

            @Override
            public void onChildChanged(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {
                myArrayAdapter.notifyDataSetChanged();
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

    }
}
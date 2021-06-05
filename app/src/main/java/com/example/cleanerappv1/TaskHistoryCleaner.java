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

public class TaskHistoryCleaner extends AppCompatActivity {

    private ListView lv_task;
    private FirebaseDatabase db;
    private DatabaseReference dbref;
    private FirebaseAuth auth;

    ArrayList<String> myArrayList6 = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_task_history_cleaner);

        lv_task = findViewById(R.id.lv_historyAccepted);
        ArrayAdapter<String> myArrayAdapter6 = new ArrayAdapter<String>(TaskHistoryCleaner.this, android.R.layout.simple_list_item_1, myArrayList6);
        lv_task.setAdapter(myArrayAdapter6);

        db = FirebaseDatabase.getInstance();
        dbref = db.getReference("CustomerBookings");

        dbref.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {
                String book = snapshot.child("booking_id").getValue().toString();
                String name = snapshot.child("name").getValue().toString();
                String status = snapshot.child("status").getValue().toString();
                String cid = snapshot.child("cid").getValue().toString();
                String auth_id = auth.getUid().toString();

                    if (cid.contentEquals(auth_id)) {
                        if (status.contentEquals("Accepted")) {
                            myArrayList6.add(name);
                            myArrayAdapter6.notifyDataSetChanged();
                            lv_task.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                                @Override
                                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                                    Intent intent2next = new Intent(TaskHistoryCleaner.this, complete_task_detail.class);
                                    intent2next.putExtra("book", book);
                                    startActivity(intent2next);
                                }
                            });

                        }

                    }



            }

            @Override
            public void onChildChanged(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {
                myArrayAdapter6.notifyDataSetChanged();
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



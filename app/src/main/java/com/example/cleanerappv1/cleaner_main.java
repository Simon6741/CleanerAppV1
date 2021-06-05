package com.example.cleanerappv1;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class cleaner_main extends AppCompatActivity {

    private Button btn_viewTask;
    private TextView tv_name,tv_pending;
    private ImageView iv_image,imageViewNext;
    private RecyclerView recyclerViewPendingList;
    private ArrayList<CustomerBookings> pendingLists;
    private AdapterTaskPending adapterTaskPending;
    private FirebaseDatabase db;
    private DatabaseReference dbref;
    private String userID;
    private ListView lv_pending;
    private FirebaseAuth auth;
    ArrayList<String> myArrayList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cleaner_main);

        btn_viewTask = findViewById(R.id.btn_viewTask);


        lv_pending = findViewById(R.id.lv_pending);
        ArrayAdapter<String> myArrayAdapter = new ArrayAdapter<String>(cleaner_main.this, android.R.layout.simple_list_item_1, myArrayList);
        lv_pending.setAdapter(myArrayAdapter);
        setTitle("Cleaner Home Page");

        BottomNavigationView navigationView = findViewById(R.id.navigation);
        //set Home Selected
        navigationView.setSelectedItemId(R.id.nav_home);

        btn_viewTask.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent2viewHistory = new Intent(cleaner_main.this, task_history.class);
                startActivity(intent2viewHistory);
            }
        });

        //perform ItemSelectedListener
        navigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()){
                    case R.id.nav_home:
                        return true;

                    case R.id.nav_complaint:
                        startActivity(new Intent(getApplicationContext(),cleaner_complaint.class));
                        overridePendingTransition(0,0);
                        return true;


                    case R.id.nav_account:
                        startActivity(new Intent(getApplicationContext(), cleaner_account.class));
                        overridePendingTransition(0,0);
                        return true;
                }
                return false;
            }
        });

        auth = FirebaseAuth.getInstance();
       /* db = FirebaseDatabase.getInstance();

        //get user ID
        userID = auth.getUid();

         db.getReference("Users").child(userID).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                String cleanerName = ""+snapshot.child("fullName").getValue();
                String cleanerImages = ""+snapshot.child("image").getValue();

                tv_name.setText(cleanerName);

                try{
                    Picasso.get().load(cleanerImages).placeholder(R.drawable.add_image).into(iv_image);
                }
                catch (Exception e) {

                }
            }
            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Toast.makeText(cleaner_main.this,"Something Wrong Happened!",Toast.LENGTH_SHORT).show();
            }
        }); */

        //imageViewNext = findViewById(R.id.imageViewNext);


        //pending list

        //auth = FirebaseAuth.getInstance();

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
                    lv_pending.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                        @Override
                        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                            Intent intent2next = new Intent(cleaner_main.this, cleaner_book.class);
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
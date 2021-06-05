package com.example.cleanerappv1;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.GridView;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.util.List;

public class customer_main extends AppCompatActivity {
    private ListView service;
    private String [] array = {"Basic House Keeping", "Car Cleaning", "Spring Cleaning", "Move In/Out Cleaning", "Office/Commercial Cleaning"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_customer_main);
        this.setTitle("Main Page");

        service =findViewById(R.id.services);

        ListAdapter listAdapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, array);
        service.setAdapter(listAdapter);

        //bottom nav
        BottomNavigationView bottomNavigationView = findViewById(R.id.bottom_nav);
        bottomNavigationView.setSelectedItemId(R.id.home);
        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()){
                    case R.id.book:
                        startActivity(new Intent(getApplicationContext(), book_list.class));
                        overridePendingTransition(0,0);
                        return true;
                    case R.id.home:
                        return true;
                    case R.id.account:
                        startActivity(new Intent(customer_main.this, customer_account.class));
                        overridePendingTransition(0,0);
                        return true;
                }
                return false;
            }
        });

        service.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                if(position == 0){
                    String data = array[position];
                    String price = "RM123";
                    String desc = getString(R.string.basic);
                    String amount = "123";
                    Intent intent = new Intent(customer_main.this, customer_service1.class);
                    intent.putExtra("basic", data);
                    intent.putExtra("price", price);
                    intent.putExtra("desc", desc);
                    intent.putExtra("amount", amount);
                    startActivity(intent);
                }
                if(position == 1){
                    String data = array[position];
                    String price = "RM113";
                    String amount = "113";
                    String desc = getString(R.string.car);
                    Intent intent2 = new Intent(customer_main.this, customer_service1.class);
                    intent2.putExtra("basic", data);
                    intent2.putExtra("price", price);
                    intent2.putExtra("desc", desc);
                    intent2.putExtra("amount", amount);
                    startActivity(intent2);
                }
                if(position == 2){
                    String data = array[position];
                    String price = "RM133";
                    String amount = "133";
                    String desc = getString(R.string.spring);
                    Intent intent3 = new Intent(customer_main.this, customer_service1.class);
                    intent3.putExtra("basic", data);
                    intent3.putExtra("price", price);
                    intent3.putExtra("desc", desc);
                    intent3.putExtra("amount", amount);
                    startActivity(intent3);
                }
                if(position == 3){
                    String data = array[position];
                    String price = "RM143";
                    String amount = "143";
                    String desc = getString(R.string.move);
                    Intent intent4 = new Intent(customer_main.this, customer_service1.class);
                    intent4.putExtra("basic", data);
                    intent4.putExtra("price", price);
                    intent4.putExtra("desc", desc);
                    intent4.putExtra("amount", amount);
                    startActivity(intent4);
                }
                if(position == 4){
                    String data = array[position];
                    String price = "RM153";
                    String amount = "153";
                    String desc = getString(R.string.office);
                    Intent intent5 = new Intent(customer_main.this, customer_service1.class);
                    intent5.putExtra("basic", data);
                    intent5.putExtra("price", price);
                    intent5.putExtra("desc", desc);
                    intent5.putExtra("amount", amount);
                    startActivity(intent5);
                }

            }
        });

    }
}
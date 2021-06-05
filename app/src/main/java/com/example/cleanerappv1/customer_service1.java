package com.example.cleanerappv1;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

public class customer_service1 extends AppCompatActivity {
    private TextView s_name, s_price, s_desc, extra_s;
    private EditText s_amount;
    private CheckBox extra_time, add_tool, add_material;
    private Button cont, calc;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_customer_service1);
        setTitle("Booking");

        s_name = findViewById(R.id.service_name);
        s_price = findViewById(R.id.service_price);
        s_desc = findViewById(R.id.s_desc);
        s_amount = findViewById(R.id.s_amount);
        extra_time = findViewById(R.id.extra_time);
        add_tool = findViewById(R.id.add_tool);
        add_material = findViewById(R.id.add_material);
        cont = findViewById(R.id.cont);
        calc = findViewById(R.id.calc);
        extra_s = findViewById(R.id.extra_s);

        s_name.setText(getIntent().getStringExtra("basic").toString());
       s_price.setText(getIntent().getStringExtra("price").toString());
        s_desc.setText(getIntent().getStringExtra("desc").toString());
        //s_amount.setText(getIntent().getStringExtra("amount").toString());



        calc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                double extra = 0;
                String getextra = "";
                String serv_amount = getIntent().getStringExtra("amount").toString();
                double amount = Integer.parseInt(serv_amount);
                if (extra_time.isChecked()){
                    extra = extra + 10;
                    getextra += "Extra Time";
                }
                if (add_material.isChecked()){
                    extra = extra + 30;
                    getextra += ", Cleaning Material";
                }
                if (add_tool.isChecked()){
                    extra = extra + 10;
                    getextra += ", Cleaning Tools";
                }
                s_amount.setText(String.valueOf(amount+extra));
                extra_s.setText(String.valueOf(getextra));
            }
        });

        cont.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(s_amount.getText().length()!=0) {
                    String serv = s_name.getText().toString();
                    String tot_amount = s_amount.getText().toString();
                    Intent intent2checkout = new Intent(customer_service1.this, customer_c_out.class);
                    intent2checkout.putExtra("serv", serv);
                    intent2checkout.putExtra("tot_amount", tot_amount);
                    intent2checkout.putExtra("extra", extra_s.getText().toString());

                    startActivity(intent2checkout);
                } else {
                    Toast.makeText(customer_service1.this, "Please calculate the total amount before continue..", Toast.LENGTH_SHORT).show();
                }
            }
        });



    }
}
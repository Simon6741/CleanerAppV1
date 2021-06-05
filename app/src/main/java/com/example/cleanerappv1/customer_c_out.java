package com.example.cleanerappv1;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.common.internal.safeparcel.SafeParcelable;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.StorageReference;

import java.util.HashMap;

public class customer_c_out extends AppCompatActivity {

    private TextView serv, tot_amount, extra;
    private EditText name, address, phone, date, time, special;
    private Spinner p_method;
    private Button toPayment;

    private FirebaseAuth auth;
    private FirebaseDatabase db;
    private DatabaseReference dbref;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_customer_c_out);

        this.setTitle("Booking Checkout");

        serv = findViewById(R.id.serv);
        tot_amount = findViewById(R.id.tot_amount);
        name = findViewById(R.id.u_name);
        address = findViewById(R.id.u_address);
        phone = findViewById(R.id.u_phone);
        date = findViewById(R.id.u_date);
        time = findViewById(R.id.u_time);
        p_method = findViewById(R.id.p_method);
        special = findViewById(R.id.u_spec);
        toPayment = findViewById(R.id.toPayment);
        extra = findViewById(R.id.extra);

        serv.setText(getIntent().getStringExtra("serv").toString());
        tot_amount.setText(getIntent().getStringExtra("tot_amount").toString());
        extra.setText(getIntent().getStringExtra("extra").toString());

        auth = FirebaseAuth.getInstance();


        toPayment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(serv.getText().length()!=0 && tot_amount.getText().length()!=0 && name.getText().length()!=0 && address.getText().length()!=0 && phone.getText().length()!=0 && date.getText().length()!=0 && time.getText().length()!=0 ) {
                    String service = serv.getText().toString().trim();
                    String total = tot_amount.getText().toString().trim();
                    String c_name = name.getText().toString().trim();
                    String c_address = address.getText().toString().trim();
                    String c_phone = phone.getText().toString().trim();
                    String c_date = date.getText().toString().trim();
                    String c_time = time.getText().toString().trim();
                    String c_pay = p_method.getSelectedItem().toString();
                    String c_special = special.getText().toString().trim();
                    String c_extra = extra.getText().toString().trim();

                    final String timestamp = "" + System.currentTimeMillis();

                    HashMap<String, Object> hashMap = new HashMap<>();
                    hashMap.put("uid", "" + auth.getUid());
                    hashMap.put("booking_id", "" + timestamp);
                    hashMap.put("service", "" + service);
                    hashMap.put("total_amount", "" + total);
                    hashMap.put("name", "" + c_name);
                    hashMap.put("address", "" + c_address);
                    hashMap.put("phone", "" + c_phone);
                    hashMap.put("date", "" + c_date);
                    hashMap.put("time", "" + c_time);
                    hashMap.put("payment", "" + c_pay);
                    hashMap.put("special", "" + c_special);
                    hashMap.put("extra", "" + c_extra);
                    hashMap.put("status", "Booked");
                    hashMap.put("cid", "");

                    db = FirebaseDatabase.getInstance();
                    dbref = db.getReference("CustomerBookings");
                    dbref.child(timestamp).setValue(hashMap).addOnSuccessListener(new OnSuccessListener<Void>() {
                        @Override
                        public void onSuccess(Void aVoid) {
                            Toast.makeText(customer_c_out.this, "Booking Placed", Toast.LENGTH_SHORT).show();
                            Intent intent2payment = new Intent(customer_c_out.this, payment.class);
                            intent2payment.putExtra("timestamp", String.valueOf(timestamp));
                            intent2payment.putExtra("service", String.valueOf(service));
                            intent2payment.putExtra("time", String.valueOf(c_time));
                            intent2payment.putExtra("date", String.valueOf(c_date));
                            intent2payment.putExtra("address", String.valueOf(c_address));
                            intent2payment.putExtra("total", String.valueOf(total));
                            intent2payment.putExtra("payment", String.valueOf(c_pay));
                            startActivity(intent2payment);

                        }
                    }).addOnFailureListener(new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull Exception e) {
                            Toast.makeText(customer_c_out.this, "Booking Failed", Toast.LENGTH_SHORT).show();
                        }
                    });

                } else {
                    Toast.makeText(customer_c_out.this, "All field must be filled", Toast.LENGTH_SHORT).show();
                }
            }
        });

    }
}
package com.example.cleanerappv1;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

import com.example.cleanerappv1.ui.admin_main;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class MainActivity extends AppCompatActivity {

    private EditText email, password;
    private RadioButton admin, customer, cleaner;
    private Button login;
    private TextView clickHere, resetPassword;

    private FirebaseAuth firebaseAuth;
    private DatabaseReference dbRef;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        this.setTitle("Login");

        firebaseAuth = FirebaseAuth.getInstance();

        email = findViewById(R.id.loginEmail);
        password = findViewById(R.id.loginPassword);
        clickHere = findViewById(R.id.tvHere);
        login = findViewById(R.id.btnLogin);
        admin = findViewById(R.id.rbAdmin);
        customer = findViewById(R.id.rbCustomer);
        cleaner = findViewById(R.id.rbCleaner);
        resetPassword = findViewById(R.id.tvResetPassword);

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(admin.isChecked()) {
                    startActivity(new Intent(getApplicationContext(), admin_main.class));
                } else {
                    if(email.getText().toString().isEmpty()){
                        email.setError("Email is missing!");
                        return;
                    }
                    if(password.getText().toString().isEmpty()){
                        password.setError("Password is missing!");
                        return;
                    }

                    //login user
                    firebaseAuth.signInWithEmailAndPassword(email.getText().toString(), password.getText().toString()).addOnSuccessListener(new OnSuccessListener<AuthResult>() {
                        @Override
                        public void onSuccess(AuthResult authResult) {

                            //read data from database
                            //read from Users table
                            dbRef = FirebaseDatabase.getInstance().getReference().child("Users").child(firebaseAuth.getUid());
                            dbRef.addValueEventListener(new ValueEventListener() {
                                @Override
                                public void onDataChange(@NonNull DataSnapshot snapshot) {
                                    String userType = snapshot.child("userType").getValue().toString();
                                    if(userType.equals("Customer") && customer.isChecked()){
                                        if(firebaseAuth.getCurrentUser().isEmailVerified()){
                                            startActivity(new Intent(getApplicationContext(), customer_main.class));
                                            finish();
                                            Toast.makeText(MainActivity.this, "Successfully login as a customer.", Toast.LENGTH_SHORT).show();
                                        }
                                        else{
                                            startActivity(new Intent(getApplicationContext(), email_verification.class));
                                        }
                                    }
                                    else if(userType.equals("Cleaner") && cleaner.isChecked()){
                                        if(firebaseAuth.getCurrentUser().isEmailVerified()){
                                            startActivity(new Intent(getApplicationContext(), cleaner_main_new.class));
                                            finish();
                                            Toast.makeText(MainActivity.this, "Successfully login as a cleaner.", Toast.LENGTH_SHORT).show();
                                        }
                                        else{
                                            startActivity(new Intent(getApplicationContext(), email_verification.class));
                                        }
                                    }
                                    else if(userType.equals("Admin") && admin.isChecked()){
                                        //  if(firebaseAuth.getCurrentUser().isEmailVerified()){
//                                        startActivity(new Intent(getApplicationContext(), cleaner_main.class));
//                                        finish();
                                        Toast.makeText(MainActivity.this, "Successfully login as a admin.", Toast.LENGTH_LONG).show();
                                        startActivity(new Intent(getApplicationContext(), admin_main.class));

                                        //     }
//                                    else{
//                                        startActivity(new Intent(getApplicationContext(), email_verification.class));
//                                    }
                                    }
                                    else{
                                        finish();
                                        startActivity(getIntent());
                                        Toast.makeText(MainActivity.this, "Unsuccessfully login.", Toast.LENGTH_SHORT).show();
                                    }
                                }

                                @Override
                                public void onCancelled(@NonNull DatabaseError error) {
                                    Toast.makeText(MainActivity.this, error.getMessage(), Toast.LENGTH_SHORT).show();
                                }
                            });
                        }
                    }).addOnFailureListener(new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull Exception e) {
                            Toast.makeText(MainActivity.this, e.getMessage(), Toast.LENGTH_SHORT).show();
                        }
                    });
                }

            }
        });

        clickHere.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent2register = new Intent(MainActivity.this, register_main.class);
                startActivity(intent2register);
            }
        });

        resetPassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent2resetPassword = new Intent(MainActivity.this, reset_password.class);
                startActivity(intent2resetPassword);
            }
        });
    }
}
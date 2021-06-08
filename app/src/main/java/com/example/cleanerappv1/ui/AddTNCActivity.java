package com.example.cleanerappv1.ui;

import android.app.Activity;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RelativeLayout;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import com.example.cleanerappv1.R;
import com.example.cleanerappv1.model.TNC;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

public class AddTNCActivity extends AppCompatActivity {

    Button btnAdd;
    EditText et_title;

    String title;
    private FirebaseDatabase db;
    private DatabaseReference mDatabase;
    RelativeLayout progressIndicator;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_add_tnc);

        setupView();
        setOnclick();
    }

    private void setupView() {
        btnAdd = findViewById(R.id.btn_add);
        et_title = findViewById(R.id.edit_tnc);
        progressIndicator = findViewById(R.id.progress_service);

        mDatabase = FirebaseDatabase.getInstance().getReference();
    }

    private void setOnclick() {

        btnAdd.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                progressIndicator.setVisibility(View.VISIBLE);

                title = et_title.getText().toString();
                final String timestamp = "" + System.currentTimeMillis();

                TNC faq = new TNC(title,timestamp);

                db = FirebaseDatabase.getInstance();

                mDatabase = db.getReference("TermCondition");
                mDatabase.child(timestamp).setValue(faq).addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void unused) {
                        progressIndicator.setVisibility(View.INVISIBLE);

                        AlertDialog alertDialog = new AlertDialog.Builder(AddTNCActivity.this)
                                .setTitle(getString(R.string.dialog_add_tnc))
                                .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialogInterface, int i) {
                                        dialogInterface.dismiss();
                                        Intent intent = new Intent();
                                        setResult(Activity.RESULT_OK, intent);
                                        finish();
                                    }
                                }).show();
                    }
                });

            }
        });

    }


}
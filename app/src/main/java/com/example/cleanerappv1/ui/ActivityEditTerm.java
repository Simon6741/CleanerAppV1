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

import com.example.cleanerappv1.R;
import com.example.cleanerappv1.model.FAQ;
import com.example.cleanerappv1.model.TNC;

import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import static com.example.cleanerappv1.util.Constant.INTENT_FAQ_DESC;
import static com.example.cleanerappv1.util.Constant.INTENT_FAQ_TITLE;
import static com.example.cleanerappv1.util.Constant.INTENT_ID;

public class ActivityEditTerm extends AppCompatActivity {

    EditText et_title;
    Button btnEdit;

    String title, id;
    private FirebaseDatabase db;
    private DatabaseReference mDatabase;
    RelativeLayout progressIndicator;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_term);

        title = getIntent().getStringExtra(INTENT_FAQ_DESC);
        id = getIntent().getStringExtra(INTENT_ID);

        setupView();
        setOnclick();

    }

    private void setupView() {
        btnEdit = findViewById(R.id.btn_edit);
        et_title = findViewById(R.id.edit_tnc);
        progressIndicator = findViewById(R.id.progress_service);


        mDatabase = FirebaseDatabase.getInstance().getReference();

        et_title.setText(title);

    }

    private void setOnclick() {

        btnEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                progressIndicator.setVisibility(View.VISIBLE);

                title = et_title.getText().toString();

                TNC tnc = new TNC(title,id);


//                final String timestamp = "" + System.currentTimeMillis();

                db = FirebaseDatabase.getInstance();
                mDatabase = db.getReference("TermCondition");
                mDatabase.child(id).setValue(tnc).addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void unused) {
                        progressIndicator.setVisibility(View.INVISIBLE);

                        AlertDialog alertDialog = new AlertDialog.Builder(ActivityEditTerm.this)
                                .setTitle(getString(R.string.dialog_edit_tnc))
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
                });;

            }
        });

    }
}
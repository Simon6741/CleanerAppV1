package com.example.cleanerappv1.ui;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

import com.example.cleanerappv1.R;
import com.example.cleanerappv1.model.FAQ;
import com.example.cleanerappv1.model.TNC;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import static com.example.cleanerappv1.util.Constant.INTENT_CONTACT;
import static com.example.cleanerappv1.util.Constant.INTENT_EMAIL;
import static com.example.cleanerappv1.util.Constant.INTENT_FAQ_DESC;
import static com.example.cleanerappv1.util.Constant.INTENT_FAQ_TITLE;
import static com.example.cleanerappv1.util.Constant.INTENT_ID;

public class ActivityEditFAQ extends AppCompatActivity {

    EditText et_title, et_details;
    Button btnEdit;

    String title, detail, id;
    private FirebaseDatabase db;
    private DatabaseReference mDatabase;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_edit_faq);

        title = getIntent().getStringExtra(INTENT_FAQ_TITLE);
        detail = getIntent().getStringExtra(INTENT_FAQ_DESC);
        id = getIntent().getStringExtra(INTENT_ID);

        setupView();
        setOnclick();
    }

    private void setupView() {
        btnEdit = findViewById(R.id.btn_edit);
        et_title = findViewById(R.id.edit_title);
        et_details = findViewById(R.id.edit_details);

        mDatabase = FirebaseDatabase.getInstance().getReference();

        et_title.setText(title);
        et_details.setText(detail);

    }

    private void setOnclick() {
        btnEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                title = et_title.getText().toString();
                detail = et_details.getText().toString();

                FAQ faq = new FAQ(title,detail,id);
//                final String timestamp = "" + System.currentTimeMillis();

                db = FirebaseDatabase.getInstance();
                mDatabase = db.getReference("faq");
                mDatabase.child(id).setValue(faq);


            }
        });

    }

}

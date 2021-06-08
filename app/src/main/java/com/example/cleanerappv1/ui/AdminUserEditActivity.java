package com.example.cleanerappv1.ui;

import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

import com.example.cleanerappv1.R;
import com.example.cleanerappv1.model.Customer;

import androidx.appcompat.app.AppCompatActivity;

import static com.example.cleanerappv1.util.Constant.INTENT_CONTACT;
import static com.example.cleanerappv1.util.Constant.INTENT_EMAIL;
import static com.example.cleanerappv1.util.Constant.INTENT_NAME;
import static com.example.cleanerappv1.util.Constant.INTENT_USERNAME;
import static com.example.cleanerappv1.util.Constant.INTENT_USER_ID;

public class AdminUserEditActivity extends AppCompatActivity {

    TextView tvName, tvContact, tvEmail, tvUsername;
    Button btnDelete;
    Customer user;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_user_edit);

        user = new Customer(getIntent().getStringExtra(INTENT_CONTACT),
                getIntent().getStringExtra(INTENT_EMAIL),
                getIntent().getStringExtra(INTENT_NAME),
                getIntent().getStringExtra(INTENT_USER_ID),
                getIntent().getStringExtra(INTENT_USERNAME));

        initView();
    }

    private void initView() {

        tvName = findViewById(R.id.tv_edit_name);
        tvUsername = findViewById(R.id.tv_edit_username);
        tvEmail = findViewById(R.id.tv_edit_email);
        tvContact = findViewById(R.id.tv_edit_contact_no);
        btnDelete = findViewById(R.id.user_delete);

        tvUsername.setText(String.format(getString(R.string.username_title), user.getUsername()));
        tvName.setText(String.format(getString(R.string.user_name), user.getFullName()));
        tvEmail.setText(String.format(getString(R.string.user_contact), user.getContactNumber()));
        tvContact.setText(String.format(getString(R.string.user_email), user.getEmailAddress()));

    }
}
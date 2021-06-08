package com.example.cleanerappv1.ui;

import android.app.Activity;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.cleanerappv1.R;
import com.example.cleanerappv1.model.Customer;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import static com.example.cleanerappv1.util.Constant.INTENT_CONTACT;
import static com.example.cleanerappv1.util.Constant.INTENT_EMAIL;
import static com.example.cleanerappv1.util.Constant.INTENT_NAME;
import static com.example.cleanerappv1.util.Constant.INTENT_PASSWORD;
import static com.example.cleanerappv1.util.Constant.INTENT_USERNAME;
import static com.example.cleanerappv1.util.Constant.INTENT_USER_ID;
import static com.example.cleanerappv1.util.Constant.INTENT_VIEWTYPE;

public class AdminUserEditActivity extends AppCompatActivity {

    private FirebaseAuth firebaseAuth;

    TextView tvName, tvContact, tvEmail, tvUsername;
    String viewType;
    Button btnDelete;
    Customer user;
    String contact, email, name, userId, username, password;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_user_edit);

        firebaseAuth = FirebaseAuth.getInstance();

        viewType = getIntent().getStringExtra(INTENT_VIEWTYPE);

        setTitle(viewType + " Details");

        contact = getIntent().getStringExtra(INTENT_CONTACT);
        email = getIntent().getStringExtra(INTENT_EMAIL);
        name = getIntent().getStringExtra(INTENT_NAME);
        userId = getIntent().getStringExtra(INTENT_USER_ID);
        username = getIntent().getStringExtra(INTENT_USERNAME);
        password = getIntent().getStringExtra(INTENT_PASSWORD);

//        user = new Customer(getIntent().getStringExtra(INTENT_CONTACT),
//                getIntent().getStringExtra(INTENT_EMAIL),
//                getIntent().getStringExtra(INTENT_NAME),
//                getIntent().getStringExtra(INTENT_USER_ID),
//                getIntent().getStringExtra(INTENT_USERNAME));

        initView();
        setOnClick();
    }

    private void initView() {

        tvName = findViewById(R.id.tv_edit_name);
        tvUsername = findViewById(R.id.tv_edit_username);
        tvEmail = findViewById(R.id.tv_edit_email);
        tvContact = findViewById(R.id.tv_edit_contact_no);
        btnDelete = findViewById(R.id.user_delete);

        tvUsername.setText(String.format(getString(R.string.username_title), username));
        tvName.setText(String.format(getString(R.string.user_name), name));
        tvEmail.setText(String.format(getString(R.string.user_contact), contact));
        tvContact.setText(String.format(getString(R.string.user_email), email));

    }

    private void setOnClick() {
        btnDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog alertDialog = new AlertDialog.Builder(AdminUserEditActivity.this)
                        .setTitle(getString(R.string.dialog_delete_ask_title))
                        .setMessage(getString(R.string.dialog_delete_ask_user))
                        .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                dialogInterface.dismiss();
                               loginForDeleteUser();
                            }
                        }).setNegativeButton("No", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                dialogInterface.dismiss();
                            }
                        }).show();

            }
        });
    }

    private void loginForDeleteUser() {
        firebaseAuth.signInWithEmailAndPassword(email, password).addOnSuccessListener(new OnSuccessListener<AuthResult>() {
            @Override
            public void onSuccess(AuthResult authResult) {
                FirebaseUser user = firebaseAuth.getCurrentUser();
                user.delete().addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        if (task.isSuccessful()) {
                            deleteUser();
                        } else {
                            Toast.makeText(AdminUserEditActivity.this, "Failed to delete user!", Toast.LENGTH_SHORT).show();
                        }
                    }
                });
            }
        });
    }

    private void deleteUser() {
        DatabaseReference dRef = FirebaseDatabase.getInstance().getReference();
        Query query = dRef.child("Users").orderByChild("uid").equalTo(userId);

        query.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for (DataSnapshot dataSnapshot : snapshot.getChildren()) {
                    dataSnapshot.getRef().removeValue().addOnSuccessListener(new OnSuccessListener<Void>() {
                        @Override
                        public void onSuccess(Void unused) {
                            AlertDialog alertDialog = new AlertDialog.Builder(AdminUserEditActivity.this)
                                    .setTitle(getString(R.string.dialog_delete_user))
                                    .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                                        @Override
                                        public void onClick(DialogInterface dialogInterface, int i) {
                                            dialogInterface.dismiss();
                                            Intent intent = new Intent();
                                            intent.putExtra(INTENT_VIEWTYPE, viewType);
                                            setResult(Activity.RESULT_OK, intent);
                                            finish();
                                        }
                                    }).show();
                            //Toast.makeText(AdminUserEditActivity.this, "User is deleted:", Toast.LENGTH_SHORT).show();

                        }
                    });
//                            Customer customer = dataSnapshot.getValue(Customer.class);
//                          Log.d("Remove", customer.getEmailAddress());
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }
}
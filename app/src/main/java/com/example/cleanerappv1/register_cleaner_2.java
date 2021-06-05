package com.example.cleanerappv1;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.app.AlertDialog;
import android.content.ContentValues;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.media.Image;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import java.util.HashMap;

public class register_cleaner_2 extends AppCompatActivity {

    private EditText registerEmail, registerContactNo, registerUsername, registerPassword, registerConfPassword;
    private Button registerBtnCreateAccount, registerBtnToLogin;
    private ImageView image;

    private FirebaseAuth auth;
    private FirebaseDatabase db;
    private DatabaseReference dbref;
    private StorageReference storageRef;

    private String fullName, age, yearExp, gender, emailAddress, contactNumber, username, password, confirmPassword;
    private String basicHouseKeeping, carCleaning, springCleaning, moveInOutCleaning, officeCommercialCleaning;

    //permission constants
    private static final int CAMERA_REQUEST_CODE = 100;
    private static final int STORAGE_REQUEST_CODE = 200;
    //image pick constants
    private static final int IMAGE_PICK_GALLERY_CODE = 300;
    private static final int IMAGE_PICK_CAMERA_CODE = 400;
    //permission array
    private String[] cameraPermission;
    private String[] storagePermission;
    //image picked uri
    private Uri image_uri;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register_cleaner_2);

        registerBtnCreateAccount = findViewById(R.id.registerCleanerCreateAccountBtn);
        image = findViewById(R.id.imageC);
        registerEmail = findViewById(R.id.emailC);
        registerContactNo = findViewById(R.id.contactC);
        registerUsername = findViewById(R.id.usernameC);
        registerPassword = findViewById(R.id.passwordC);
        registerConfPassword = findViewById(R.id.confPasswordC);
        registerBtnToLogin = findViewById(R.id.registerCleanerLoginBtn);

        auth = FirebaseAuth.getInstance();

        //init permission array
        cameraPermission =  new String[]{Manifest.permission.CAMERA, Manifest.permission.WRITE_EXTERNAL_STORAGE};
        storagePermission =  new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE};

        image.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showImageDialog();
            }
        });

        registerBtnToLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent2login = new Intent(register_cleaner_2.this, MainActivity.class);
                startActivity(intent2login);
                finish();
            }
        });

        registerBtnCreateAccount.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                inputData();
            }
        });

    }

    private void inputData(){
        fullName = getIntent().getStringExtra("fullName");
        age = getIntent().getStringExtra("age");
        yearExp = getIntent().getStringExtra("yearExp");
        gender = getIntent().getStringExtra("gender");
        basicHouseKeeping = getIntent().getStringExtra("basicHouseKeeping");
        carCleaning = getIntent().getStringExtra("carCleaning");
        springCleaning = getIntent().getStringExtra("springCleaning");
        moveInOutCleaning = getIntent().getStringExtra("moveInOutCleaning");
        officeCommercialCleaning = getIntent().getStringExtra("officeCommercialCleaning");
        emailAddress = registerEmail.getText().toString().trim();
        contactNumber = registerContactNo.getText().toString().trim();
        username = registerUsername.getText().toString().trim();
        password = registerPassword.getText().toString().trim();
        confirmPassword = registerConfPassword.getText().toString().trim();

        if(emailAddress.isEmpty()){
            registerEmail.setError("Email Address is required");
            return;
        }
        if(contactNumber.isEmpty()){
            registerContactNo.setError("Contact Number is required");
            return;
        }
        if(username.isEmpty()){
            registerUsername.setError("Username is required");
            return;
        }
        if(password.isEmpty()){
            registerPassword.setError("Password is required");
            return;
        }
        if(confirmPassword.isEmpty()){
            registerConfPassword.setError("Confirm Password is required");
            return;
        }

        if(!password.equals(confirmPassword)) {
            registerConfPassword.setError("Password does not match!");
            return;
        }

        createAccount();
    }

    private void createAccount(){
        auth.createUserWithEmailAndPassword(emailAddress, password).addOnSuccessListener(new OnSuccessListener<AuthResult>() {
            @Override
            public void onSuccess(AuthResult authResult) {
                saveAccountData();
                registerUsername.getText().clear();
                registerConfPassword.getText().clear();
                registerContactNo.getText().clear();
                registerEmail.getText().clear();
                registerPassword.getText().clear();
                image.setImageResource(android.R.color.transparent);
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Toast.makeText(register_cleaner_2.this, e.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void saveAccountData(){
        //add data w/o image
        if (image_uri == null) {
            HashMap<String, Object> hashMap = new HashMap<>();
            hashMap.put("uid", "" + auth.getUid());
            hashMap.put("userType", "Cleaner");
            hashMap.put("fullName", "" + fullName);
            hashMap.put("age", "" + age);
            hashMap.put("yearExperience", "" + yearExp);
            hashMap.put("gender", "" + gender);
            hashMap.put("emailAddress", "" + emailAddress);
            hashMap.put("contactNumber", "" + contactNumber);
            hashMap.put("username", "" + username);
            hashMap.put("password", "" + password);
            hashMap.put("basicHouseKeeping", "" + basicHouseKeeping);
            hashMap.put("carCleaning", "" + carCleaning);
            hashMap.put("springCleaning", "" + springCleaning);
            hashMap.put("moveInOutCleaning", "" + moveInOutCleaning);
            hashMap.put("officeCommercialCleaning", "" + officeCommercialCleaning);

            //add data to database
            db = FirebaseDatabase.getInstance();
            dbref = db.getReference("Users");
            dbref.child(auth.getUid()).setValue(hashMap).addOnSuccessListener(new OnSuccessListener<Void>() {
                @Override
                public void onSuccess(Void aVoid) {
                    Toast.makeText(register_cleaner_2.this, "Register Successfully!", Toast.LENGTH_SHORT).show();
                    if (auth.getCurrentUser().isEmailVerified()) {
                        Intent intent2customer_main = new Intent(register_cleaner_2.this, customer_main.class);
                        startActivity(intent2customer_main);
                        finish();
                    } else {
                        startActivity(new Intent(getApplicationContext(), email_verification.class));
                    }
                }
            }).addOnFailureListener(new OnFailureListener() {
                @Override
                public void onFailure(@NonNull Exception e) {
                    Toast.makeText(register_cleaner_2.this, "Register Unsuccessfully!", Toast.LENGTH_SHORT).show();
                }
            });
        }
        //add data w/ image
        else{
            String filePathAndName = "profile_images/" + "" + auth.getUid();
            storageRef = FirebaseStorage.getInstance().getReference(filePathAndName);
            storageRef.putFile(image_uri).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                @Override
                public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                    //get uri of uploaded image
                    Task<Uri> uriTask = taskSnapshot.getStorage().getDownloadUrl();
                    while (!uriTask.isSuccessful());
                    Uri downloadImageUri = uriTask.getResult();

                    if (uriTask.isSuccessful()){
                        //set data to save
                        HashMap<String, Object> hashMap = new HashMap<>();
                        hashMap.put("uid", "" + auth.getUid());
                        hashMap.put("userType", "Cleaner");
                        hashMap.put("fullName", "" + fullName);
                        hashMap.put("age", "" + age);
                        hashMap.put("yearExperience", "" + yearExp);
                        hashMap.put("gender", "" + gender);
                        hashMap.put("emailAddress", "" + emailAddress);
                        hashMap.put("contactNumber", "" + contactNumber);
                        hashMap.put("username", "" + username);
                        hashMap.put("password", "" + password);
                        hashMap.put("basicHouseKeeping", "" + basicHouseKeeping);
                        hashMap.put("carCleaning", "" + carCleaning);
                        hashMap.put("springCleaning", "" + springCleaning);
                        hashMap.put("moveInOutCleaning", "" + moveInOutCleaning);
                        hashMap.put("officeCommercialCleaning", "" + officeCommercialCleaning);
                        hashMap.put("image",""+ downloadImageUri); //uri of uploaded image

                        //save to database
                        DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference("Users");
                        databaseReference.child(auth.getUid()).setValue(hashMap).addOnSuccessListener(new OnSuccessListener<Void>() {
                            @Override
                            public void onSuccess(Void aVoid) {
                                Toast.makeText(register_cleaner_2.this, "Register Successfully!", Toast.LENGTH_SHORT).show();
                                if (auth.getCurrentUser().isEmailVerified()) {
                                    Intent intent2customer_main = new Intent(register_cleaner_2.this, customer_main.class);
                                    startActivity(intent2customer_main);
                                    finish();
                                } else {
                                    startActivity(new Intent(getApplicationContext(), email_verification.class));
                                }
                            }
                        }).addOnFailureListener(new OnFailureListener() {
                            @Override
                            public void onFailure(@NonNull Exception e) {
                                Toast.makeText(register_cleaner_2.this, "Register Unsuccessfully!", Toast.LENGTH_SHORT).show();
                            }
                        });
                    }
                }
            }).addOnFailureListener(new OnFailureListener() {
                @Override
                public void onFailure(@NonNull Exception e) {
                    Toast.makeText(getApplicationContext(),""+e.getMessage(),Toast.LENGTH_SHORT).show();
                }
            });
        }
    }

    private void showImageDialog() {
        //options to display in dialog
        String[] options = {"Camera", "Gallery"};
        //dialog
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Choose Image").setItems(options, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                //handle item clicks
                if (which==0){
                    //camera clicked
                    if (checkCameraPermission()){
                        //allowed, open camera
                        pickFromCamera();
                    }
                    else {
                        requestCameraPermission();
                    }
                }
                else {
                    //gallery clicked
                    if (checkStoragePermission()){
                        //allowed, open gallery
                        pickFromGallery();
                    }
                    else
                        requestStoragePermission();
                }
            }
        }).show();
    }

    private void pickFromGallery() {
        Intent intentToGallery = new Intent(Intent.ACTION_PICK);
        intentToGallery.setType("image/*");
        startActivityForResult(intentToGallery, IMAGE_PICK_GALLERY_CODE);
    }

    private void pickFromCamera() {
        //intent to pick image from camera
        ContentValues contentValues = new ContentValues();
        contentValues.put(MediaStore.Images.Media.TITLE, "Temp_Image Title");
        contentValues.put(MediaStore.Images.Media.DESCRIPTION, "Temp_Image Description");

        image_uri = getContentResolver().insert(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, contentValues);

        Intent intentToCamera = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        intentToCamera.putExtra(MediaStore.EXTRA_OUTPUT, image_uri);
        startActivityForResult(intentToCamera, IMAGE_PICK_CAMERA_CODE);
    }

    private boolean checkStoragePermission() {
        boolean result = ContextCompat.checkSelfPermission(this, Manifest.permission.WRITE_EXTERNAL_STORAGE) == (PackageManager.PERMISSION_GRANTED);
        return result;
    }

    private void requestStoragePermission() {
        ActivityCompat.requestPermissions(this, storagePermission, STORAGE_REQUEST_CODE);
    }

    private boolean checkCameraPermission() {
        boolean result = ContextCompat.checkSelfPermission(this, Manifest.permission.CAMERA) == (PackageManager.PERMISSION_GRANTED);
        boolean result1 = ContextCompat.checkSelfPermission(this, Manifest.permission.WRITE_EXTERNAL_STORAGE) == (PackageManager.PERMISSION_GRANTED);
        return result && result1;
    }

    private void requestCameraPermission() {
        ActivityCompat.requestPermissions(this, cameraPermission, CAMERA_REQUEST_CODE);
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        switch (requestCode) {
            case CAMERA_REQUEST_CODE:{
                if(grantResults.length>0){
                    boolean cameraAccepted = grantResults[0] == PackageManager.PERMISSION_GRANTED;
                    boolean storageAccepted = grantResults[1] == PackageManager.PERMISSION_GRANTED;
                    if (cameraAccepted && storageAccepted) {
                        pickFromCamera();
                    }
                    else {
                        Toast.makeText(getApplicationContext(),"Camera permissions are necessary...",Toast.LENGTH_SHORT).show();
                    }
                }
            }
            break;
            case STORAGE_REQUEST_CODE:{
                if(grantResults.length>0){
                    boolean storageAccepted = grantResults[0] == PackageManager.PERMISSION_GRANTED;
                    if (storageAccepted) {
                        pickFromGallery();
                    }
                    else {
                        Toast.makeText(getApplicationContext(),"Storage permissions are necessary...",Toast.LENGTH_SHORT).show();
                    }
                }
            }
            break;
        }
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        if (resultCode == RESULT_OK) {
            if (requestCode == IMAGE_PICK_GALLERY_CODE){
                //get chosen image
                image_uri = data.getData();
                //set to image view
                image.setImageURI(image_uri);
            }
            else if (requestCode == IMAGE_PICK_CAMERA_CODE){
                //set to image view
                image.setImageURI(image_uri);
            }
        }
        super.onActivityResult(requestCode, resultCode, data);
    }
}
package com.example.cleanerappv1;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Toast;

public class register_cleaner extends AppCompatActivity {

    private EditText fullName1, age1, yearExp1;
    private RadioButton male, female;
    private CheckBox houseKeeping1, carCleaning1, springCleaning1, moveInCleaning1, officeCleaning1;
    private Button next;

    private String fullName, age, yearExp, gender;
    private String basicHouseKeeping, carCleaning, springCleaning, moveInOutCleaning, officeCommercialCleaning;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register_cleaner);

        next = findViewById(R.id.registerCleanerNextBtn);
        fullName1 = findViewById(R.id.registerCleanerFullName);
        age1 = findViewById(R.id.registerCleanerAge);
        yearExp1 = findViewById(R.id.registerCleanerExp);
        male = findViewById(R.id.registerCleanerMale);
        female = findViewById(R.id.registerCleanerFemale);
        houseKeeping1 = findViewById(R.id.basicHouseKeeping);
        carCleaning1 = findViewById(R.id.carCleaning);
        springCleaning1 = findViewById(R.id.springCleaning);
        moveInCleaning1 = findViewById(R.id.moveInOutCleaning);
        officeCleaning1 = findViewById(R.id.officeCommercialCleaning);

        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                inputData();
                Intent intent2next = new Intent(register_cleaner.this, register_cleaner_2.class);
                if(!fullName.isEmpty() && !age.isEmpty() && !yearExp.isEmpty()){
                    intent2next.putExtra("fullName", fullName);
                    intent2next.putExtra("age", age);
                    intent2next.putExtra("yearExp", yearExp);
                    intent2next.putExtra("gender", gender);
                    intent2next.putExtra("basicHouseKeeping", basicHouseKeeping);
                    intent2next.putExtra("carCleaning", carCleaning);
                    intent2next.putExtra("springCleaning", springCleaning);
                    intent2next.putExtra("moveInOutCleaning", moveInOutCleaning);
                    intent2next.putExtra("officeCommercialCleaning", officeCommercialCleaning);

                    if(houseKeeping1.isChecked() || carCleaning1.isChecked() || springCleaning1.isChecked() || moveInCleaning1.isChecked() || officeCleaning1.isChecked()){
                        startActivity(intent2next);
                    }
                    else{
                        Toast.makeText(register_cleaner.this, "Please choose at least 1 skill set!", Toast.LENGTH_SHORT).show();
                    }
                }
                else{
                    Toast.makeText(register_cleaner.this, "Some info missing!", Toast.LENGTH_SHORT).show();
                }

            }
        });

    }

    private void inputData(){
        fullName = fullName1.getText().toString().trim();
        age = age1.getText().toString().trim();
        yearExp = yearExp1.getText().toString().trim();
        if(male.isChecked()){
            gender = "Male";
        }
        else if(female.isChecked()){
            gender = "Female";
        }

        if(houseKeeping1.isChecked()){
            basicHouseKeeping = "Yes";
        }
        else if(!houseKeeping1.isChecked()){
            basicHouseKeeping = "No";
        }
        if(carCleaning1.isChecked()){
            carCleaning = "Yes";
        }
        else if(!carCleaning1.isChecked()){
            carCleaning = "No";
        }
        if(springCleaning1.isChecked()){
            springCleaning = "Yes";
        }
        else if(!springCleaning1.isChecked()){
            springCleaning = "No";
        }
        if(moveInCleaning1.isChecked()){
            moveInOutCleaning = "Yes";
        }
        else if(!moveInCleaning1.isChecked()){
            moveInOutCleaning = "No";
        }
        if(officeCleaning1.isChecked()){
            officeCommercialCleaning = "Yes";
        }
        else if(!officeCleaning1.isChecked()){
            officeCommercialCleaning = "No";
        }
    }
}
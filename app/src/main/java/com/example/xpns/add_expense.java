package com.example.xpns;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;
import android.text.InputType;
import java.util.Calendar;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Map;

public class add_expense extends AppCompatActivity {

    private FirebaseFirestore db;
    String userID;
    private FirebaseAuth mAuth;
    TextView description, amount;
    Button submitButton;

    private String expenseDescription, expenseDate;
    private String expenseAmount;

    DatePickerDialog picker;
    TextView eText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_expense);
        ActionBar actionBar = getSupportActionBar();
        actionBar.hide();
        mAuth = FirebaseAuth.getInstance();
        db = FirebaseFirestore.getInstance();

        description = (TextView) findViewById(R.id.description);
        amount = (TextView) findViewById(R.id.amount);
        submitButton = (Button) findViewById(R.id.submitButton);


        eText = (TextView) findViewById(R.id.editTextDate);
        eText.setInputType(InputType.TYPE_NULL);
        eText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final Calendar cldr = Calendar.getInstance();
                int day = cldr.get(Calendar.DAY_OF_MONTH);
                int month = cldr.get(Calendar.MONTH);
                int year = cldr.get(Calendar.YEAR);
                // date picker dialog
                picker = new DatePickerDialog(add_expense.this,
                        new DatePickerDialog.OnDateSetListener() {
                            @Override
                            public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                                eText.setText(dayOfMonth + "/" + ( monthOfYear + 1 ) + "/" + year);
                            }
                        }, year, month, day);
                picker.show();
            }
        });

        submitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                expenseDescription = (String) description.getText().toString();
                expenseAmount = (String) amount.getText().toString();
                expenseDate = (String) eText.getText().toString();
                userID = mAuth.getCurrentUser().getUid();


                if (TextUtils.isEmpty(expenseDescription)) {
                    description.setError("Please enter Expense Description");
                } else if (TextUtils.isEmpty(expenseAmount)) {
                    amount.setError("Please enter Expense Amount");
                } else if (TextUtils.isEmpty(expenseDate)) {
                    eText.setError("Please enter Expense Date");
                } else {
                    // calling method to add data to Firebase Firestore.
                    addDataToFirestore(userID,expenseDate,expenseDescription, expenseAmount);
                }


            }
        });
    }

    private void addDataToFirestore(String userID,String expenseDate, String expenseDescription, String expenseAmount) {

        // creating a collection reference
        // for our Firebase Firetore database.
        CollectionReference dbCourses = db.collection("expenses");

        // adding our data to our courses object class.
        UserExpense expenses = new UserExpense("NoSplit",userID,expenseDate, expenseDescription, expenseAmount);

        // below method is use to add data to Firebase Firestore.
        dbCourses.add(expenses).addOnSuccessListener(new OnSuccessListener<DocumentReference>() {
            @Override
            public void onSuccess(DocumentReference documentReference) {
                // after the data addition is successful
                // we are displaying a success toast message.
                startActivity(new Intent(add_expense.this,dashboard.class));
                Toast.makeText(add_expense.this, "Your Expense has been added.", Toast.LENGTH_SHORT).show();
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                // this method is called when the data addition process is failed.
                // displaying a toast message when data addition is failed.
                Toast.makeText(add_expense.this, "Fail to add Expenses \n" + e, Toast.LENGTH_SHORT).show();
            }
        });
    }
}






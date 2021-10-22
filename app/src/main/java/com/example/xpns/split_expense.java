package com.example.xpns;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.text.InputType;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;

public class split_expense extends AppCompatActivity {

    private FirebaseFirestore db;
    String userID,searchResultText,splitID;
    private FirebaseAuth mAuth;
    TextView description, amount,searchField;
    Button submitButton;

    FirebaseFirestore fStore ;

    private String expenseDescription, expenseDate,searchFieldText;
    private String expenseAmount;

    DatePickerDialog picker;
    TextView eText;
    ImageButton searchButton;
    TextView searchResult;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_split_expense);
        ActionBar actionBar = getSupportActionBar();
        actionBar.hide();

        mAuth = FirebaseAuth.getInstance();
        db = FirebaseFirestore.getInstance();

        searchField = (TextView) findViewById(R.id.searchField);
        searchResult = (TextView) findViewById(R.id.searchResult);
        searchResult.setVisibility(View.INVISIBLE);
        description = (TextView) findViewById(R.id.description);
        amount = (TextView) findViewById(R.id.amount);
        submitButton = (Button) findViewById(R.id.submitButton);
        searchButton = (ImageButton) findViewById(R.id.search);


        searchButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                searchFieldText = (String) searchField.getText().toString();

                if (TextUtils.isEmpty(searchFieldText)) {
                    searchField.setError("Please enter email to search");
                }
                else {
                    // calling method to search for user.
//                    FirebaseAuth.auth();

                    fStore = FirebaseFirestore.getInstance();

                    fStore.collection("users").document(searchFieldText).get().addOnSuccessListener(documentSnapshot -> {
                        String user_name = documentSnapshot.getString("fName");
                        searchResult.setText(user_name);
                        searchResult.setVisibility(View.VISIBLE);
                    });
                }
            }
        });


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
                picker = new DatePickerDialog(split_expense.this,
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
                splitID = searchFieldText;


                if (TextUtils.isEmpty(expenseDescription)) {
                    description.setError("Please enter Description");
                } else if (TextUtils.isEmpty(expenseAmount)) {
                    amount.setError("Please enter amount");
                } else if (TextUtils.isEmpty(expenseDate)) {
                    eText.setError("Please enter date");
                } else {
                    // calling method to add data to Firebase Firestore.
                    addDataToFirestore(splitID,userID,expenseDate,expenseDescription, expenseAmount);
                }


            }
        });
    }

    private void addDataToFirestore(String splitID,String userID,String expenseDate, String expenseDescription, String expenseAmount) {

        // creating a collection reference
        // for our Firebase Firetore database.
        CollectionReference dbCourses = db.collection("expenses");

        // adding our data to our courses object class.

        if(searchResult.getVisibility()==View.VISIBLE) {

        UserExpense expenses = new UserExpense(splitID,userID,expenseDate, expenseDescription, expenseAmount);
            dbCourses.add(expenses).addOnSuccessListener(new OnSuccessListener<DocumentReference>() {
                @Override
                public void onSuccess(DocumentReference documentReference) {
                    // after the data addition is successful
                    // we are displaying a success toast message.
                    Toast.makeText(split_expense.this, "Your Expense has been added to Firebase Firestore", Toast.LENGTH_SHORT).show();
                    startActivity(new Intent(split_expense.this,dashboard.class));
                }
            }).addOnFailureListener(new OnFailureListener() {
                @Override
                public void onFailure(@NonNull Exception e) {
                    // this method is called when the data addition process is failed.
                    // displaying a toast message when data addition is failed.
                    Toast.makeText(split_expense.this, "Fail to add course \n" + e, Toast.LENGTH_SHORT).show();
                }
            });
        }


    }
}
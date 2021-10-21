package com.example.xpns;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
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

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Map;

public class add_expense extends AppCompatActivity {

    FirebaseFirestore fStore;
    String userID;
    TextView description, amount;
    Button submitButton;
    RadioGroup radioGroup;
    private FirebaseAuth mAuth;
    RadioButton selectedRadioButton;
    DatePickerDialog picker;
    TextView eText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_expense);

        mAuth = FirebaseAuth.getInstance();
        fStore = FirebaseFirestore.getInstance();

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
    }

    public void addExpenseButton(View view) {

        int selectedRadioButtonId = radioGroup.getCheckedRadioButtonId();
        if (selectedRadioButtonId != -1) {
            selectedRadioButton = findViewById(selectedRadioButtonId);
            String selectedRbText = selectedRadioButton.getText().toString();
            userID = mAuth.getCurrentUser().getUid();
//            textView.setText(selectedRbText + " is Selected");
//            Toast.makeText(add_expense.this,userID + " User id Description -" + description.getText().toString() + " Amount-" + amount.getText().toString(),Toast.LENGTH_SHORT).show();

            DocumentReference documentReference = fStore.collection("expenses").document(userID);

            Map<String, Object> user = new HashMap<>();
            user.put("description", description.getText().toString());
            user.put("amount", amount.getText().toString());
            user.put("splitType", selectedRbText);
            user.put("date", eText.getText().toString().trim());

            documentReference.set(user).addOnSuccessListener(new OnSuccessListener<Void>() {
                @Override
                public void onSuccess(Void unused) {
                    Toast.makeText(add_expense.this, "Added Expense!", Toast.LENGTH_SHORT).show();
                    startActivity(new Intent(add_expense.this,dashboard.class));
                }
            });
        }
    }
}
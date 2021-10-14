package com.example.xpns;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Map;

public class add_expense extends AppCompatActivity {

    FirebaseFirestore fStore ;
    String userID;
    TextView description,amount;
    Button submitButton;
    RadioGroup radioGroup;
    private FirebaseAuth mAuth;
    RadioButton selectedRadioButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_expense);

        mAuth = FirebaseAuth.getInstance();
        fStore = FirebaseFirestore.getInstance();

        description = (TextView) findViewById(R.id.description);
        amount = (TextView) findViewById(R.id.amount);
        submitButton = (Button) findViewById(R.id.submitButton);
        radioGroup = (RadioGroup) findViewById((R.id.radioGroupSplitExpense));

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

            Map<String,Object> user = new HashMap<>();
            user.put("description",description.getText().toString() );
            user.put("amount",amount.getText().toString());
            user.put("splitType",selectedRbText);

            documentReference.set(user).addOnSuccessListener(new OnSuccessListener<Void>() {
                @Override
                public void onSuccess(Void unused) {
                    Toast.makeText(add_expense.this,"Added Expense!",Toast.LENGTH_SHORT).show();
                }
            });

        }

    }
}
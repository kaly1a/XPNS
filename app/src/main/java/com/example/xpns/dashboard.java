package com.example.xpns;

import android.content.Intent;
import android.media.Image;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentChange;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.firebase.firestore.QuerySnapshot;

import org.w3c.dom.Text;

public class dashboard extends AppCompatActivity implements View.OnClickListener {

    Button  add_expense;
    ImageButton logoutButton;
    ImageButton profileButton;

    private FirebaseAuth mAuth;
    FirebaseFirestore fStore ;
    String userID;
    TextView usernameText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard);
        ActionBar actionBar = getSupportActionBar();
        actionBar.hide();

        mAuth = FirebaseAuth.getInstance();

        add_expense = (Button) findViewById(R.id.addExpense);
        logoutButton = (ImageButton) findViewById(R.id.logOut);
        profileButton = (ImageButton) findViewById(R.id.profileButton);
        usernameText = (TextView) findViewById(R.id.usernameText);
        add_expense.setOnClickListener(this);
        logoutButton.setOnClickListener(this);
        profileButton.setOnClickListener(this);

        if(mAuth.getCurrentUser()!=null){

            fStore = FirebaseFirestore.getInstance();


            fStore.collection("users").addSnapshotListener(new EventListener<QuerySnapshot>() {
                @Override
                public void onEvent(QuerySnapshot documentSnapshots, FirebaseFirestoreException e) {

                    if (e !=null)
                    {

                    }

                    for (DocumentChange documentChange : documentSnapshots.getDocumentChanges())
                    {
                        String   fName =  documentChange.getDocument().getData().get("fName").toString();
//                        String  isCalender   =  documentChange.getDocument().getData().get("Calender").toString();
//                        String isEnablelocation = documentChange.getDocument().getData().get("Enable Location").toString();
                        usernameText.setText(fName);

                    }
                }
            });


        }else{
            Toast.makeText(this,"Not logged In",Toast.LENGTH_SHORT).show();
        }

    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.addExpense:
                startActivity(new Intent(this, com.example.xpns.add_expense.class));
                Toast.makeText(this,"Add Expense button Working",Toast.LENGTH_SHORT).show();
                break;
            case R.id.expenses:
                startActivity(new Intent(this, com.example.xpns.expenses.class));
                Toast.makeText(this,"Past Bills button Working",Toast.LENGTH_SHORT).show();
                break;
            case R.id.splitExpenseBtn:
                startActivity(new Intent(this, com.example.xpns.split_expense.class));
                Toast.makeText(this,"Split Expense button Working",Toast.LENGTH_SHORT).show();
                break;

            case R.id.logOut:
                mAuth.signOut();
                Toast.makeText(this,"Logged Out",Toast.LENGTH_SHORT).show();
                startActivity(new Intent(this, com.example.xpns.home.class));
                break;
            case R.id.profileButton:
//                Toast.makeText(this,"Dashboard button Working",Toast.LENGTH_SHORT).show();
                startActivity(new Intent(this, com.example.xpns.profileActivity.class));
                break;

        }

    }
}
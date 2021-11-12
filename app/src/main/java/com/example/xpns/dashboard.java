package com.example.xpns;

import android.content.Intent;
import android.graphics.Color;
import android.media.Image;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TableRow;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentChange;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import org.w3c.dom.Text;

public class dashboard extends AppCompatActivity implements View.OnClickListener {

    Button  add_expense;
    ImageButton logoutButton;
    ImageButton profileButton;
    String totalAmount;
    int totalAmountInt;


    private FirebaseAuth mAuth;
    FirebaseFirestore fStore ;
    String userID;
    TextView usernameText;
    TextView displayTextView;

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
        displayTextView = (TextView) findViewById(R.id.display);
        add_expense.setOnClickListener(this);
        logoutButton.setOnClickListener(this);
        profileButton.setOnClickListener(this);

        if(mAuth.getCurrentUser()!=null){

            userID = mAuth.getUid();

//            Toast.makeText(this,"Name is" +mAuth.getCurrentUser().getEmail() ,Toast.LENGTH_SHORT).show();


            fStore = FirebaseFirestore.getInstance();


            fStore.collection("users").document(mAuth.getCurrentUser().getEmail()).get().addOnSuccessListener(documentSnapshot -> {
                String user_name = documentSnapshot.getString("fName");
                usernameText.setText(user_name);
            });


        }else{
            Toast.makeText(this,"Not logged In",Toast.LENGTH_SHORT).show();
        }

//        ===================================================



        fStore.collection("expenses")
                .get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if (task.isSuccessful()) {
                            int i=1;
                            for (QueryDocumentSnapshot document : task.getResult()) {

//                                Toast.makeText(expenses.this, userID + "==" + document.get("userID").toString(), Toast.LENGTH_LONG).show();

                                if(userID.equals(document.get("userID").toString()) ) {


                                    totalAmount = document.get("expenseAmount").toString();

                                    totalAmountInt += Integer.parseInt(totalAmount);
                                    displayTextView.setText("₹"+totalAmountInt );
                                    if(totalAmountInt>0)
                                    {displayTextView.setTextColor(Color.parseColor("#3edf8e"));}
                                    else if (totalAmountInt<0)
                                    {displayTextView.setTextColor(Color.parseColor("#eb3639"));}
                                    else
                                    {displayTextView.setTextColor(Color.parseColor("#ffffff"));}



//                                    splitType = document.get("splitID").toString();
//                                    Toast.makeText(expenses.this,document.get("splitID").toString() , Toast.LENGTH_LONG).show();



                                }
                            }
                        } else {
                            Toast.makeText(dashboard.this, "Error getting documents.", Toast.LENGTH_SHORT).show();
//                            Log.w("dataCHECK1", "Error getting documents.", task.getException());
                        }


                    }


                });


//        ===================================================

    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.addExpense:
                startActivity(new Intent(this, com.example.xpns.add_expense.class));
//                Toast.makeText(this,"Add Expense button Working",Toast.LENGTH_SHORT).show();
                break;
            case R.id.expenses:
                startActivity(new Intent(this, com.example.xpns.past_expenses.class));
//                Toast.makeText(this,"Past Bills button Working",Toast.LENGTH_SHORT).show();
                break;
            case R.id.splitExpenseBtn:
                startActivity(new Intent(this, com.example.xpns.split_expense.class));
//                Toast.makeText(this,"Split Expense button Working",Toast.LENGTH_SHORT).show();
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
            case R.id.groupBtn:
                startActivity(new Intent(this, com.example.xpns.groups.class));
                break;
            case R.id.reqLoanBtn:
                startActivity(new Intent(this, com.example.xpns.reqLoan.class));
                break;
            case R.id.creatGrp:
                startActivity(new Intent(this, com.example.xpns.creatGroup.class));
                break;

        }

    }
}
package com.example.xpns;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
import android.graphics.Typeface;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentChange;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

public class expenses extends AppCompatActivity {

    FirebaseFirestore db;
    private FirebaseAuth mAuth;
    String   expenseDescription;
    String   expenseDate;
    String   expenseAmount;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_expenses);
        ActionBar actionBar = getSupportActionBar();
        actionBar.hide();

        mAuth = FirebaseAuth.getInstance();
        db = FirebaseFirestore.getInstance();


        init();
    }
    public void init() {
        TableLayout stk = (TableLayout) findViewById(R.id.table_main);
        TableRow tbrow0 = new TableRow(this);
        TextView tv0 = new TextView(this);
        tv0.setText("  S No.  ");
        tv0.setTextColor(Color.WHITE);
        tbrow0.addView(tv0);
        TextView tv1 = new TextView(this);
        tv1.setText("  Date  ");
        tv1.setTextColor(Color.WHITE);
        tbrow0.addView(tv1);
        TextView tv2 = new TextView(this);
        tv2.setText("  Amount  ");
        tv2.setTextColor(Color.WHITE);
        tbrow0.addView(tv2);
        TextView tv3 = new TextView(this);
        tv3.setText("  Description  ");
        tv3.setTextColor(Color.WHITE);
        tbrow0.addView(tv3);
        stk.addView(tbrow0);
//        =========================================================================================

        db = FirebaseFirestore.getInstance();

        db.collection("expenses")
                .get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if (task.isSuccessful()) {
                            int i=1;
                            for (QueryDocumentSnapshot document : task.getResult()) {

//                                if(mAuth.getCurrentUser().getUid() == document.get("userID").toString()){}


                                expenseDescription = document.get("expenseDescription").toString();
                                expenseDate = document.get("expenseDate").toString();
                                expenseAmount = document.get("expenseAmount").toString();

                                TableRow tbrow = new TableRow(expenses.this);
                                TextView t1v = new TextView(expenses.this);

                                t1v.setText(" " + i++ + " ");

                                t1v.setTextColor(Color.WHITE);
                                t1v.setGravity(Gravity.CENTER);
                                tbrow.addView(t1v);
                                TextView t2v = new TextView(expenses.this);

                                t2v.setText(expenseDate);

                                t2v.setTextColor(Color.WHITE);
                                t2v.setGravity(Gravity.CENTER);
                                tbrow.addView(t2v);

                                TextView t3v = new TextView(expenses.this);
                                t3v.setText("₹"+expenseAmount);

                                t3v.setTextColor(Color.WHITE);
                                t3v.setGravity(Gravity.CENTER);
                                tbrow.addView(t3v);

                                TextView t4v = new TextView(expenses.this);
                                t4v.setText(expenseDescription);

                                t4v.setTextColor(Color.WHITE);
                                t4v.setGravity(Gravity.CENTER);
                                tbrow.addView(t4v);

                                stk.addView(tbrow);

                                Toast.makeText(expenses.this, document.getId() + " => " + document.get("expenseDescription"), Toast.LENGTH_LONG).show();
//                                Log.d("dataCHECK", document.getId() + " => " + document.getData());
                            }
                        } else {
                            Toast.makeText(expenses.this, "Error getting documents.", Toast.LENGTH_SHORT).show();
//                            Log.w("dataCHECK1", "Error getting documents.", task.getException());
                        }

//                        for (int i = 0; i < 200; i++) {
//
//                            TableRow tbrow = new TableRow(expenses.this);
//                            TextView t1v = new TextView(expenses.this);
//
//                            t1v.setText(" " + i + " ");
//
//                            t1v.setTextColor(Color.WHITE);
//                            t1v.setGravity(Gravity.CENTER);
//                            tbrow.addView(t1v);
//                            TextView t2v = new TextView(expenses.this);
//
//                            t2v.setText("Date" + expenseDate);
//
//                            t2v.setTextColor(Color.WHITE);
//                            t2v.setGravity(Gravity.CENTER);
//                            tbrow.addView(t2v);
//
//                            TextView t3v = new TextView(expenses.this);
//                            t3v.setText("Amount" + expenseAmount);
//
//                            t3v.setTextColor(Color.WHITE);
//                            t3v.setGravity(Gravity.CENTER);
//                            tbrow.addView(t3v);
//
//                            TextView t4v = new TextView(expenses.this);
//                            t3v.setText("Desc" + expenseDescription);
//
//                            t4v.setTextColor(Color.WHITE);
//                            t4v.setGravity(Gravity.CENTER);
//                            tbrow.addView(t4v);
//
//                            stk.addView(tbrow);
//                        }
                    }


                });


//        db.collection("expenses").addSnapshotListener(new EventListener<QuerySnapshot>() {
//            @Override
//            public void onEvent(QuerySnapshot documentSnapshots, FirebaseFirestoreException e) {
//
//                if (e !=null)
//                {
//
//                }
//
//                for (DocumentChange documentChange : documentSnapshots.getDocumentChanges())
//                {
//                    expenseDescription =  documentChange.getDocument().getData().get("expenseDescription").toString();
////                    String  isCalender   =  documentChange.getDocument().getData().get("Calender").toString();
////                    String isEnablelocation = documentChange.getDocument().getData().get("Enable Location").toString();
//
//                }
//            }
//        });




//        =========================================================================================


    }
}
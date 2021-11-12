package com.example.xpns;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.Query;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class groups extends AppCompatActivity {

    private FirebaseAuth mAuth;
    private FirebaseFirestore firebaseFirestore;

    TextView group1,group2,group3;
    TextView member1,member2,member3;
    ImageView groupico1,groupico2,groupico3;
    FirebaseFirestore db;
    String groupName;
    String count;





    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_groups);
        ActionBar actionBar = getSupportActionBar();
        actionBar.hide();

        group1 = (TextView) findViewById(R.id.group1);
        group2 = (TextView) findViewById(R.id.group2);
        group3 = (TextView) findViewById(R.id.group3);
        member1 = (TextView) findViewById(R.id.members1);
        member2 = (TextView) findViewById(R.id.members2);
        member3 = (TextView) findViewById(R.id.members3);

        groupico1 = (ImageView) findViewById(R.id.groupIco1);
        groupico2 = (ImageView) findViewById(R.id.groupIco2);
        groupico3 = (ImageView) findViewById(R.id.groupIco3);


        group1.setVisibility(View.INVISIBLE);
        member1.setVisibility(View.INVISIBLE);
        group2.setVisibility(View.INVISIBLE);
        group3.setVisibility(View.INVISIBLE);
        member2.setVisibility(View.INVISIBLE);
        member3.setVisibility(View.INVISIBLE);
        groupico1.setVisibility(View.INVISIBLE);
        groupico2.setVisibility(View.INVISIBLE);
        groupico3.setVisibility(View.INVISIBLE);

        List<String> memberEmail=new ArrayList<String>();


        mAuth = FirebaseAuth.getInstance();
        firebaseFirestore = FirebaseFirestore.getInstance();
        db = FirebaseFirestore.getInstance();
        String userID = mAuth.getCurrentUser().getUid();
        FirebaseFirestore rootRef = FirebaseFirestore.getInstance();
        CollectionReference yourCollRef = rootRef.collection("groups");
        Query query = yourCollRef.whereEqualTo("memberEmail", mAuth.getCurrentUser().getEmail());

        db = FirebaseFirestore.getInstance();

        db.collection("groups")
                .get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @SuppressLint("SetTextI18n")
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if (task.isSuccessful()) {
                            int i = 1;
                            for (QueryDocumentSnapshot document : task.getResult()) {

//                                Toast.makeText(groups.this, "memberEmail" + "==" + document.get("memberEmail").toString(), Toast.LENGTH_LONG).show();

//                                if (userID.equals(document.get("userID").toString()) ) {
                                if (userID.equals(document.get("userID").toString()) || document.get("memberEmail").toString().contains(mAuth.getCurrentUser().getEmail())) {


                                    groupName = document.get("groupName").toString();
                                    count = document.get("memberCount").toString();

//                                    Toast.makeText(groups.this, document.get("groupName").toString(), Toast.LENGTH_LONG).show();


                                    String groupnameTemp = "group" + i;
                                    String membercount = "group" + i;

                                    if(groupnameTemp.equals("group1")){
                                        group1.setVisibility(View.VISIBLE);
                                        member1.setVisibility(View.VISIBLE);
                                        groupico1.setVisibility(View.VISIBLE);

                                        group1.setText(getString(R.string.groupName)+ " " +groupName.toUpperCase());
                                        member1.setText("Count: " + count);
                                        String s =groupName;

                                        if(s.toString().toLowerCase(Locale.ROOT).equals("food") || s.toString().toLowerCase(Locale.ROOT).equals("burger"))
                                        {
                                            groupico1.setImageResource(R.drawable.food);
                                        }
                                        else if(s.toString().toLowerCase(Locale.ROOT).equals("bus") || s.toString().toLowerCase(Locale.ROOT).equals("travel"))
                                        {
                                            groupico1.setImageResource(R.drawable.bus);
                                        }
                                        else if(s.toString().toLowerCase(Locale.ROOT).equals("car") || s.toString().toLowerCase(Locale.ROOT).equals("cab"))
                                        {
                                            groupico1.setImageResource(R.drawable.car);
                                        }
                                        else if(s.toString().toLowerCase(Locale.ROOT).equals("water"))
                                        {
                                            groupico1.setImageResource(R.drawable.water);
                                        }
                                        else
                                        {
                                            groupico1.setImageResource(R.drawable.groups);
                                        }

                                    }else if(groupnameTemp.equals("group2")){
                                        group2.setVisibility(View.VISIBLE);
                                        member2.setVisibility(View.VISIBLE);
                                        groupico2.setVisibility(View.VISIBLE);

                                        group2.setText(getString(R.string.groupName)+ " " + groupName.toUpperCase());
                                        member2.setText("Count: " + count);

                                        String s =groupName;

                                        if(s.toString().toLowerCase(Locale.ROOT).equals("food") || s.toString().toLowerCase(Locale.ROOT).equals("burger"))
                                        {
                                            groupico2.setImageResource(R.drawable.food);
                                        }
                                        else if(s.toString().toLowerCase(Locale.ROOT).equals("bus") || s.toString().toLowerCase(Locale.ROOT).equals("travel"))
                                        {
                                            groupico2.setImageResource(R.drawable.bus);
                                        }
                                        else if(s.toString().toLowerCase(Locale.ROOT).equals("car") || s.toString().toLowerCase(Locale.ROOT).equals("cab"))
                                        {
                                            groupico2.setImageResource(R.drawable.car);
                                        }
                                        else if(s.toString().toLowerCase(Locale.ROOT).equals("water"))
                                        {
                                            groupico2.setImageResource(R.drawable.water);
                                        }
                                        else
                                        {
                                            groupico2.setImageResource(R.drawable.groups);
                                        }

                                    }else if(groupnameTemp.equals("group3")){
                                        group3.setVisibility(View.VISIBLE);
                                        member3.setVisibility(View.VISIBLE);
                                        groupico3.setVisibility(View.VISIBLE);

                                        group3.setText(getString(R.string.groupName)+ " " +groupName.toUpperCase());
                                        member3.setText("Count: " + count);

                                        String s =groupName;

                                        if(s.toString().toLowerCase(Locale.ROOT).equals("food") || s.toString().toLowerCase(Locale.ROOT).equals("burger"))
                                        {
                                            groupico3.setImageResource(R.drawable.food);
                                        }
                                        else if(s.toString().toLowerCase(Locale.ROOT).equals("bus") || s.toString().toLowerCase(Locale.ROOT).equals("travel"))
                                        {
                                            groupico3.setImageResource(R.drawable.bus);
                                        }
                                        else if(s.toString().toLowerCase(Locale.ROOT).equals("car") || s.toString().toLowerCase(Locale.ROOT).equals("cab"))
                                        {
                                            groupico3.setImageResource(R.drawable.car);
                                        }
                                        else if(s.toString().toLowerCase(Locale.ROOT).equals("water"))
                                        {
                                            groupico3.setImageResource(R.drawable.water);
                                        }
                                        else
                                        {
                                            groupico3.setImageResource(R.drawable.groups);
                                        }
                                    }
                                    i+=1;
                                }
                            }
                        }
                    }
                });




    }
    public void onClick(View view) {
        startActivity(new Intent(this, com.example.xpns.creatGroup.class));
        }

}
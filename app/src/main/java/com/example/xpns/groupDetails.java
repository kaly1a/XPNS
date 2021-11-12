package com.example.xpns;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class groupDetails extends AppCompatActivity {
    private FirebaseAuth mAuth;
    private FirebaseFirestore firebaseFirestore;
    String memberFieldString;
    FirebaseFirestore db;
    int count=0;
    List<String> memberEmail=new ArrayList<String>();

    TextView groupNameField,memberField1,memberField2,memberField3,memberField4,memberField5;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_group_details);

        mAuth = FirebaseAuth.getInstance();
        firebaseFirestore = FirebaseFirestore.getInstance();
        db = FirebaseFirestore.getInstance();
        String userID = mAuth.getCurrentUser().getUid();
        FirebaseFirestore rootRef = FirebaseFirestore.getInstance();

        groupNameField=(TextView) findViewById(R.id.grpName);
        memberField1=(TextView) findViewById(R.id.member1);
        memberField2=(TextView) findViewById(R.id.member2);
        memberField3=(TextView) findViewById(R.id.member3);
        memberField4=(TextView) findViewById(R.id.member4);
        memberField5=(TextView) findViewById(R.id.member5);



        memberField1.setVisibility(View.INVISIBLE);
        memberField2.setVisibility(View.INVISIBLE);
        memberField3.setVisibility(View.INVISIBLE);
        memberField4.setVisibility(View.INVISIBLE);
        memberField5.setVisibility(View.INVISIBLE);



        Intent myIntent = getIntent();
        String groupNameString = myIntent.getStringExtra("groupName");

        groupNameField.setText(groupNameString);


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
                                if (groupNameString.equals(document.get("groupName").toString()) ) {

                                    memberEmail = (List) document.get("memberEmail");
                                    
                                    int memberCount= memberEmail.size();

                                    for (String email: memberEmail
                                         ) {
                                        count+=1;
                                        memberFieldString = "memberField"+count;

                                        if(memberFieldString.equals("memberField1")){

                                            int index = email.indexOf('@');

                                            memberField1.setText(""+email.substring(0, index));
                                            memberField1.setVisibility(View.VISIBLE);

                                        }else if(memberFieldString.equals("memberField2")){

                                            int index = email.indexOf('@');
                                            memberField2.setText(""+email.substring(0, index));
                                            memberField2.setVisibility(View.VISIBLE);
                                        }else if(memberFieldString.equals("memberField3")){
                                            int index = email.indexOf('@');
                                            memberField3.setText(""+email.substring(0, index));
                                            memberField3.setVisibility(View.VISIBLE);
                                        }else if(memberFieldString.equals("memberField4")){
                                            int index = email.indexOf('@');
                                            memberField4.setText(""+email.substring(0, index));
                                            memberField4.setVisibility(View.VISIBLE);
                                        }else if(memberFieldString.equals("memberField5")){
                                            int index = email.indexOf('@');
                                            memberField5.setText(""+email.substring(0, index));
                                            memberField5.setVisibility(View.VISIBLE);
                                        }


                                        
                                    }

//                                    Toast.makeText(groupDetails.this, memberEmail.toString(), Toast.LENGTH_LONG).show();





//                                    count = document.get("memberCount").toString();



                                    String groupnameTemp = "group" + i;
                                    String membercount = "group" + i;

//                                    if(groupnameTemp.equals("group1")){
//                                        group1.setVisibility(View.VISIBLE);
//                                        member1.setVisibility(View.VISIBLE);
//                                        groupico1.setVisibility(View.VISIBLE);
//
//                                        group1.setText(getString(R.string.groupName)+ " " +groupName.toUpperCase());
//                                        member1.setText("Members : " + count);
//                                        String s =groupName;
//                                        groupName1=groupName;
//
//                                        if(s.toString().toLowerCase(Locale.ROOT).equals("food") || s.toString().toLowerCase(Locale.ROOT).equals("burger"))
//                                        {
//                                            groupico1.setImageResource(R.drawable.food);
//                                        }
//                                        else if(s.toString().toLowerCase(Locale.ROOT).equals("bus") || s.toString().toLowerCase(Locale.ROOT).equals("travel"))
//                                        {
//                                            groupico1.setImageResource(R.drawable.bus);
//                                        }
//                                        else if(s.toString().toLowerCase(Locale.ROOT).equals("car") || s.toString().toLowerCase(Locale.ROOT).equals("cab"))
//                                        {
//                                            groupico1.setImageResource(R.drawable.car);
//                                        }
//                                        else if(s.toString().toLowerCase(Locale.ROOT).equals("water"))
//                                        {
//                                            groupico1.setImageResource(R.drawable.water);
//                                        }
//                                        else
//                                        {
//                                            groupico1.setImageResource(R.drawable.groups);
//                                        }
//
//                                    }
                                    i+=1;
                                }
                            }
                        }
                    }
                });












//        Bundle extras = getIntent().getExtras();
//        String username_string = extras.getString("EXTRA_USERNAME");
//        String password_string = extras.getString("EXTRA_PASSWORD");
    }
}
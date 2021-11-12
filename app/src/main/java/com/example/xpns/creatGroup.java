package com.example.xpns;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

public class creatGroup extends AppCompatActivity {

    private FirebaseFirestore db;
    String userID,searchResultText,searchFieldText;
    private FirebaseAuth mAuth;
    FirebaseFirestore fStore ;
    private ImageButton searchButton,imageButtonGreen,imageButtonRed;
    private EditText searchField;
    private TextView searchResult;
    private Button submitButton;
    int count=0;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_creat_group);
        ActionBar actionBar = getSupportActionBar();
        actionBar.hide();
        searchField = (EditText) findViewById(R.id.searchField);
        searchResult = (TextView) findViewById(R.id.searchResult);
        searchButton = (ImageButton) findViewById(R.id.search);
        searchResult.setVisibility(View.INVISIBLE);
        submitButton = (Button) findViewById(R.id.submitBut);
        mAuth = FirebaseAuth.getInstance();
        imageButtonGreen = (ImageButton) findViewById(R.id.imageButtonGreen);
        imageButtonRed = (ImageButton) findViewById(R.id.imageButtonRed);
        imageButtonRed.setVisibility(View.INVISIBLE);
        imageButtonGreen.setVisibility(View.INVISIBLE);

        List<String> memberEmail=new ArrayList<String>();
        EditText groupName = (EditText) findViewById(R.id.groupName);

        submitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (TextUtils.isEmpty(groupName.getText())) {
                    groupName.setError("Please enter Group Name");
                } else {

                    addDataToFirestore(userID,groupName.getText().toString(),memberEmail);
                }

            }
        });


        imageButtonGreen.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                memberEmail.add((String) searchField.getText().toString());


                Toast.makeText(creatGroup.this,searchField.getText().toString() +" has been successfully added",Toast.LENGTH_SHORT).show();
                searchField.setText("");
            }
        });

        imageButtonRed.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                for(int i=0;i<count;i++){
//                    Toast.makeText(creatGroup.this,memberEmail[i] +" is present",Toast.LENGTH_SHORT).show();
                }
            }
        });







        searchButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                searchFieldText = (String)searchField.getText().toString();
                if (TextUtils.isEmpty(searchFieldText)) {
                    searchField.setError("Please enter email to search");
                }
                else {

                    fStore = FirebaseFirestore.getInstance();

                    fStore.collection("users").document(searchFieldText).get().addOnSuccessListener(documentSnapshot -> {
                        String user_name = documentSnapshot.getString("fName");
                        searchResult.setText(user_name);
                        searchResult.setVisibility(View.VISIBLE);
                        imageButtonRed.setVisibility(View.VISIBLE);
                        imageButtonGreen.setVisibility(View.VISIBLE);
                    });
                }

            }
        });
        TextView textView2 =(TextView) findViewById(R.id.textView2);
        EditText grpname = (EditText) findViewById(R.id.groupName);
        ImageView grpIco = (ImageView) findViewById(R.id.groupIco);
        searchField.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence s, int i, int i1, int i2) {
                searchFieldText = (String)searchField.getText().toString();
                if (TextUtils.isEmpty(searchFieldText)) {
                    searchField.setError("Please enter email to search");
                }
                else {

                    fStore = FirebaseFirestore.getInstance();

                    fStore.collection("users").document(searchFieldText).get().addOnSuccessListener(documentSnapshot -> {
                        String user_name = documentSnapshot.getString("fName");
                        searchResult.setText(user_name);
                        searchResult.setVisibility(View.VISIBLE);
                        imageButtonRed.setVisibility(View.VISIBLE);
                        imageButtonGreen.setVisibility(View.VISIBLE);
                    });
                }
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });
        grpname.addTextChangedListener(new TextWatcher() {

            public void afterTextChanged(Editable s) {
            }

            public void beforeTextChanged(CharSequence s, int start,
                                          int count, int after) {
            }

            public void onTextChanged(CharSequence s, int start,
                                      int before, int count) {
               if(s.toString().toLowerCase(Locale.ROOT).equals("food") || s.toString().toLowerCase(Locale.ROOT).equals("burger"))
               {
                  grpIco.setImageResource(R.drawable.food);
               }
               else if(s.toString().toLowerCase(Locale.ROOT).equals("bus") || s.toString().toLowerCase(Locale.ROOT).equals("travel"))
               {
                   grpIco.setImageResource(R.drawable.bus);
               }
               else if(s.toString().toLowerCase(Locale.ROOT).equals("car") || s.toString().toLowerCase(Locale.ROOT).equals("cab"))
               {
                   grpIco.setImageResource(R.drawable.car);
               }
               else if(s.toString().toLowerCase(Locale.ROOT).equals("water"))
               {
                   grpIco.setImageResource(R.drawable.water);
               }
               else
               {
                   grpIco.setImageResource(R.drawable.groups);
               }

            }


        });
    }

    private void addDataToFirestore(String userID,String groupName,List<String> memberEmail) {
        userID = mAuth.getCurrentUser().getUid();


        if(searchResult.getVisibility()==View.VISIBLE) {

            DocumentReference documentReference = fStore.collection("groups").document(groupName);

            Map<String,Object> groups = new HashMap<>();
            groups.put("userID",userID);
            groups.put("groupName",groupName);
            groups.put("memberEmail",memberEmail);
            groups.put("memberCount",memberEmail.size());


            documentReference.set(groups).addOnSuccessListener(new OnSuccessListener<Void>() {
                @Override
                public void onSuccess(Void unused) {
                    Toast.makeText(creatGroup.this,"Group Added!",Toast.LENGTH_SHORT).show();
                    startActivity(new Intent(creatGroup.this,dashboard.class));
                }
            });

        }

    }

}
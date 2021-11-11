package com.example.xpns;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.Locale;

public class creatGroup extends AppCompatActivity {

    private FirebaseFirestore db;
    String userID,searchResultText,searchFieldText;
    private FirebaseAuth mAuth;
    FirebaseFirestore fStore ;
    private ImageButton searchButton;
    private EditText searchField;
    private TextView searchResult;

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
                    });
                }

            }
        });
        TextView textView2 =(TextView) findViewById(R.id.textView2);
        EditText grpname = (EditText) findViewById(R.id.groupName);
        ImageView grpIco = (ImageView) findViewById(R.id.groupIco);
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

}
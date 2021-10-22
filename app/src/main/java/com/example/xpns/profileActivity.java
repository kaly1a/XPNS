package com.example.xpns;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.firestore.FirebaseFirestore;

public class profileActivity extends AppCompatActivity {

//    private EditText employeeName;
//    private Button sendDatabtn;

    FirebaseDatabase firebaseDatabase;

    DatabaseReference databaseReference;

    User user;

    private FirebaseAuth mAuth;
    FirebaseFirestore fStore ;
    String userID;
    TextView fullName;
    TextView email;
    TextView mobile;







    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        fullName = (TextView) findViewById(R.id.fullName);
        email = (TextView) findViewById(R.id.email);
        mobile = (TextView) findViewById(R.id.mobNo);


        mAuth = FirebaseAuth.getInstance();

//        employeeName = findViewById(R.id.employeeNameBox);

        firebaseDatabase = FirebaseDatabase.getInstance();

        databaseReference = firebaseDatabase.getReference("User");

        user = new User();

//        =============================================================

        if(mAuth.getCurrentUser()!=null){

            userID = mAuth.getUid();

//            Toast.makeText(this,"Name is" +mAuth.getCurrentUser().getEmail() ,Toast.LENGTH_SHORT).show();


            fStore = FirebaseFirestore.getInstance();


            fStore.collection("users").document(mAuth.getCurrentUser().getEmail()).get().addOnSuccessListener(documentSnapshot -> {
                String user_name = documentSnapshot.getString("fName");
                fullName.setText(user_name);
                String user_email = documentSnapshot.getString("email");
                email.setText(user_email);
                String user_mobile = documentSnapshot.getString("mobile");
                mobile.setText(user_mobile);


            });


        }else{
            Toast.makeText(this,"Not logged In",Toast.LENGTH_SHORT).show();
        }

//        =============================================================


    }


    private void addDatatoFirebase(String name) {


    }

}
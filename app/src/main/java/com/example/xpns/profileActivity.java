package com.example.xpns;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class profileActivity extends AppCompatActivity {

    private EditText employeeName;
    private Button sendDatabtn;

    FirebaseDatabase firebaseDatabase;

    DatabaseReference databaseReference;

    User user;





    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        employeeName = findViewById(R.id.employeeNameBox);

        firebaseDatabase = FirebaseDatabase.getInstance();

        databaseReference = firebaseDatabase.getReference("User");

        user = new User();

        sendDatabtn = findViewById(R.id.idBtnSendData);

        sendDatabtn.setOnClickListener((new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String name = employeeName.getText().toString();

                if(TextUtils.isEmpty(name)){
                    Toast.makeText(profileActivity.this, "Please add some data.", Toast.LENGTH_SHORT).show();
                }else{

                    addDatatoFirebase(name);

                }
            }
        }));
    }


    private void addDatatoFirebase(String name) {

//
//
//        user.setEmployeeName(name);
//
//        databaseReference.addValueEventListener(new ValueEventListener() {
//            @Override
//            public void onDataChange(@NonNull DataSnapshot snapshot) {
//                databaseReference.setValue(user);
//                Toast.makeText(profileActivity.this, "data added", Toast.LENGTH_SHORT).show();
//            }
//
//            @Override
//            public void onCancelled(@NonNull DatabaseError error) {
//
//                Toast.makeText(profileActivity.this, "data failed", Toast.LENGTH_SHORT).show();
//
//            }
//        });
    }

}
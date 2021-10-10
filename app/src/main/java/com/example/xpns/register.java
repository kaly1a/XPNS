package com.example.xpns;

import android.content.Intent;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.firestore.DocumentChange;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Map;

public class register extends AppCompatActivity implements View.OnClickListener {

    private Button registerUserButton;
    private EditText username,userEmail,userPhone,userPassword,userConfirmPassword;
    private FirebaseAuth mAuth;
    FirebaseFirestore fStore ;
    String userID;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        mAuth = FirebaseAuth.getInstance();
        fStore = FirebaseFirestore.getInstance();

        registerUserButton = (Button) findViewById(R.id.login_button);
        registerUserButton.setOnClickListener(this);

        username = (EditText) findViewById(R.id.user_name);
        userEmail = (EditText) findViewById(R.id.user_email);
        userPhone = (EditText) findViewById(R.id.user_phone);
        userPassword = (EditText) findViewById(R.id.user_pswd);
        userConfirmPassword = (EditText) findViewById(R.id.confirm_pswd);
    }

    @Override
    public void onClick(View view) {
        switch(view.getId()){
            case R.id.login_button:
                registerUser();
                break;
        }
    }

    private void registerUser() {
        String emailText = userEmail.getText().toString().trim();
        String usernameText = username.getText().toString().trim();
        String phoneText = userPhone.getText().toString().trim();
        String passwordText = userPassword.getText().toString().trim();
        String confirmPasswordText = userConfirmPassword.getText().toString().trim();

        if(emailText.isEmpty()){
            userEmail.setError("Email cannot be empty");
            userEmail.requestFocus();
            return;
        }

        if(usernameText.isEmpty()){
            username.setError("Username cannot be empty");
            username.requestFocus();
            return;
        }

        if(phoneText.isEmpty()){
            userPhone.setError("Phone Number cannot be empty");
            userPhone.requestFocus();
            return;
        }

        if(passwordText.isEmpty()){
            userPassword.setError("Password cannot be empty");
            userPassword.requestFocus();
            return;
        }

        if(confirmPasswordText.isEmpty()){
            userConfirmPassword.setError("Password cannot be empty");
            userConfirmPassword.requestFocus();
            return;
        }

        if(!confirmPasswordText.equals(passwordText)){
            userPassword.setError("Password and Confirm Password Must be same");
            userPassword.requestFocus();
            return;
        }

        if(!Patterns.EMAIL_ADDRESS.matcher(emailText).matches()){
            userEmail.setError("Email not valid");
            userEmail.requestFocus();
            return;
        }

        if(passwordText.length()<6){
            userPassword.setError("Password cannot be less than 6");
            userPassword.requestFocus();
            return;
        }



        mAuth.createUserWithEmailAndPassword(emailText,passwordText)
                .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if(task.isSuccessful()){
                            Toast.makeText(register.this,"User Registration Success!",Toast.LENGTH_SHORT).show();

                            userID = mAuth.getCurrentUser().getUid();

                            DocumentReference documentReference = fStore.collection("users").document(userID);

                            Map<String,Object> user = new HashMap<>();
                            user.put("fName",usernameText);
                            user.put("email",emailText);
                            user.put("mobile",phoneText);

                            documentReference.set(user).addOnSuccessListener(new OnSuccessListener<Void>() {
                                @Override
                                public void onSuccess(Void unused) {
                                    Toast.makeText(register.this,"User Full Update!",Toast.LENGTH_SHORT).show();
                                }
                            });



                            startActivity(new Intent(register.this,home.class));




//
//                            User user = new User(usernameText,emailText,phoneText);
//
//                            FirebaseDatabase.getInstance().getReference("Users")
//                                    .child(FirebaseAuth.getInstance().getCurrentUser().getUid())
//                                    .setValue(user).addOnCompleteListener(new OnCompleteListener<Void>() {
//                                @Override
//                                public void onComplete(@NonNull Task<Void> task) {
//                                    if(task.isSuccessful()){
//                                        Toast.makeText(register.this,"User has been successfully registered",Toast.LENGTH_SHORT).show();
//
//                                    }else{
//                                        Toast.makeText(register.this,"User Registration Failed",Toast.LENGTH_SHORT).show();
//                                    }
//                                }
//                            });

                        }else{
                            Toast.makeText(register.this,"User Registration Failed!",Toast.LENGTH_SHORT).show();
                        }
                    }
                });



    }

}
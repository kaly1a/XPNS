package com.example.xpns;

import android.content.Intent;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class login extends AppCompatActivity implements View.OnClickListener {

    private EditText editTextEmail,editTextPassword;

    private Button signIn;
    private TextView forgetPass;

    private FirebaseAuth mAuth;
    private ProgressBar progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        mAuth = FirebaseAuth.getInstance();

        signIn = (Button) findViewById(R.id.login_button);
        signIn.setOnClickListener(this);

        editTextEmail = (EditText) findViewById(R.id.user_email);
        editTextPassword  = (EditText) findViewById(R.id.user_pswd);
        forgetPass  = (TextView) findViewById(R.id.forgetPass);

        forgetPass.setOnClickListener(this);

        progressBar = (ProgressBar) findViewById(R.id.progressBar);
        progressBar.setVisibility(View.INVISIBLE);


    }

    @Override
    public void onClick(View view) {
        switch(view.getId()){

            case R.id.login_button:
                userLogin();
                break;
            case R.id.forgetPass:
                resetPassword();

        }
    }

    private void resetPassword() {

//        FirebaseAuth auth = FirebaseAuth.getInstance();
//        String emailAddress = editTextEmail.getText().toString().trim();
//
//        auth.sendPasswordResetEmail(emailAddress)
//                .addOnCompleteListener(new OnCompleteListener<Void>() {
//                    @Override
//                    public void onComplete(@NonNull Task<Void> task) {
//                        if (task.isSuccessful()) {
//                            Toast.makeText(login.this,"Email Sent",Toast.LENGTH_SHORT).show();
//                        }else{
//                            Toast.makeText(login.this,"Email Not Sent",Toast.LENGTH_SHORT).show();
//
//                        }
//                    }
//                });
    }

    private void userLogin() {
        String email = editTextEmail.getText().toString().trim();
        String password = editTextPassword.getText().toString().trim();

        if(email.isEmpty()){
            editTextEmail.setError("Email required");
            editTextEmail.requestFocus();
            return;
        }

        if(!Patterns.EMAIL_ADDRESS.matcher(email).matches()){
            editTextEmail.setError("Email not valid");
            editTextEmail.requestFocus();
            return;
        }

        if(password.isEmpty()){
            editTextPassword.setError("Password required");
            editTextPassword.requestFocus();
            return;
        }

        if(password.length() < 6){
            editTextPassword.setError("password lesser than 6 characters");
            editTextPassword.requestFocus();
            return;
        }

        progressBar.setVisibility(View.VISIBLE);

        mAuth.signInWithEmailAndPassword(email,password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if(task.isSuccessful()){
                    progressBar.setVisibility(View.INVISIBLE);
                    Toast.makeText(login.this,"User has been successfully logged in",Toast.LENGTH_SHORT).show();
                    startActivity(new Intent(login.this,dashboard.class));

                }else{
                    progressBar.setVisibility(View.INVISIBLE);
                    Toast.makeText(login.this,"User login Failed",Toast.LENGTH_SHORT).show();
                }
            }
        });



    }
}
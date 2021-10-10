package com.example.xpns;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

public class home extends AppCompatActivity implements View.OnClickListener{

    private TextView register;
    private TextView login;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        ActionBar actionBar = getSupportActionBar();
        actionBar.hide();

        register = (TextView) findViewById(R.id.register);
        register.setOnClickListener(this);
        login = (TextView) findViewById(R.id.login);
        login.setOnClickListener(this);
    }
//    public void goToLogin(View view) {
//        Intent intent = new Intent(home.this, login.class);
//        startActivity(intent);
//    }
//    public void goToReg(View view) {
//        Intent intent = new Intent(home.this, register.class);
//        startActivity(intent);
//    }

    @Override
    public void onClick(View view) {

        switch (view.getId()){
            case R.id.register:
                startActivity(new Intent(this, com.example.xpns.register.class));
                Toast.makeText(this,"Register button Working",Toast.LENGTH_SHORT).show();
                break;
            case R.id.login:
                startActivity(new Intent(this, com.example.xpns.login.class));
                Toast.makeText(this,"Login button Working",Toast.LENGTH_SHORT).show();
                break;

        }

    }
}

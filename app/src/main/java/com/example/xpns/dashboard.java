package com.example.xpns;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

public class dashboard extends AppCompatActivity implements View.OnClickListener {

    Button  add_expense;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard);
        ActionBar actionBar = getSupportActionBar();
        actionBar.hide();

        add_expense = (Button) findViewById(R.id.addExpense);

        add_expense.setOnClickListener(this);

    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.addExpense:
                startActivity(new Intent(this, com.example.xpns.add_expense.class));
                Toast.makeText(this,"Add exp button Working",Toast.LENGTH_SHORT).show();
                break;
//            case R.id.login:
//                startActivity(new Intent(this, com.example.xpns.login.class));
//                Toast.makeText(this,"Login button Working",Toast.LENGTH_SHORT).show();
//                break;
//
//            case R.id.dashButton:
//                startActivity(new Intent(this, com.example.xpns.dashboard.class));
//                Toast.makeText(this,"Dashboard button Working",Toast.LENGTH_SHORT).show();
//                break;

        }

    }
}
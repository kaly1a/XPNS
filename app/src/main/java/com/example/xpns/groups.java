package com.example.xpns;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

public class groups extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_groups);
        ActionBar actionBar = getSupportActionBar();
        actionBar.hide();
    }
    public void onClick(View view) {
        startActivity(new Intent(this, com.example.xpns.creatGroup.class));
        }
}
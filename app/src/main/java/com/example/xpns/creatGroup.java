package com.example.xpns;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class creatGroup extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_creat_group);
        ActionBar actionBar = getSupportActionBar();
        actionBar.hide();

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
               if(s.length() ==4)
               {
                  ;grpIco.setImageResource(R.drawable.food);
               }
               else
               {
                   ;grpIco.setImageResource(R.drawable.groups);
               }

            }
        });
    }

}
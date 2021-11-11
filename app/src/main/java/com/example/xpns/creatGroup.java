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

import java.util.Locale;

public class creatGroup extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_creat_group);
        ActionBar actionBar = getSupportActionBar();
        actionBar.hide();
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
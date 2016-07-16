package com.example.abhinayas.muzimix;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

/**
 * Created by ABHINAYA  S on 16-07-2016.
 */
public class TrimMusic extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.trim_page);
        Bundle data=getIntent().getExtras();
        TextView name=(TextView)findViewById(R.id.name);
        String nameVal=data.getString("name");
        if(nameVal!=null)
        {
            name.setText(nameVal);
        }
    }
    }

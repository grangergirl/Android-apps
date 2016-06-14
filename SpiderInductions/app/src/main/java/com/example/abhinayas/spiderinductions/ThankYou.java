package com.example.abhinayas.spiderinductions;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class ThankYou extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {

       // Intent oldStuff = getIntent();
       // String message = startingIntent.getStringExtra("", value);
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_thank_you);
        Bundle bundle = getIntent().getExtras();
        String greeting= bundle.getString("abc");
        TextView regards = (TextView) findViewById(R.id.regards);
        regards.append(greeting);
      //  regards.setText(greeting);
        TextView timestamp= (TextView) findViewById(R.id.timestamp);
        Long timStamp = System.currentTimeMillis();
        String ts =timStamp.toString();
        timestamp.append(ts);


       // regards.setText(Username);
    }
    public void showNextActivity(View v){
        Intent intent = new Intent(this,MainActivity.class);
           startActivity(intent);
    }
}


     /*   Bundle gt=getIntent().getExtras();
        str=gt.getString("abc");
        txt1.setText(str);*/
package com.example.abhinayas.spiderapp;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Button;
import android.view.View;

public class MainActivity extends AppCompatActivity {
private int clicks=0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button press=(Button)findViewById(R.id.press);
        press.setOnClickListener(
                new Button.OnClickListener(){
                    public void onClick(View v){
                        clicks++;
                        TextView textbox=(TextView)findViewById(R.id.textbox);
                        textbox.setText("Clicks:"+clicks);
                    }
                }
        );
    }
}

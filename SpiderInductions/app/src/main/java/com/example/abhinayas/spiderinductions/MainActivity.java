package com.example.abhinayas.spiderinductions;

import android.app.Activity;
import android.content.DialogInterface;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.view.View;
import android.view.View.OnClickListener;
import android.content.Intent;
import android.widget.RelativeLayout;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

   /* @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }*/

    private Spinner spinner;
    private static final String[] paths = {"","Computer Science And Engineering", "Electronics And Communication Engineering ",
            "Electrical and Electronics Engineering","Instrumentation And Control Engineering","Chemical Engineering","Civil Engineering","Metallurgical Engineering",
            "Mechanical Engineering","Production Engineering","Architecture"};
    //EditText name = (EditText) findViewById(R.id.name);
   // EditText rollno = (EditText) findViewById(R.id.rollno);
    //Spinner dept=(Spinner)findViewById(R.id.spinner) ;
    String Username,User_rollno,Userdept,passovertext;
   private CheckBox checkBox1;
   private CheckBox checkBox2 ;
   private CheckBox checkBox3 ;
  private CheckBox checkBox4 ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        spinner = (Spinner) findViewById(R.id.spinner);
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(MainActivity.this,
                android.R.layout.simple_spinner_item, paths);

        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);
       // spinner.setOnItemSelectedListener(this);what happens when this gets selected

    }




   // public Button button=(Button)findViewById(R.id.button);

        public void showNextActivity(View v){
            EditText name = (EditText) findViewById(R.id.name);
            EditText rollno = (EditText) findViewById(R.id.rollno);
            Spinner dept=(Spinner)findViewById(R.id.spinner) ;
             checkBox1 = (CheckBox) findViewById(R.id.checkBox);
             checkBox2 = (CheckBox) findViewById(R.id.checkBox2);
             checkBox3 = (CheckBox) findViewById(R.id.checkBox3);
             checkBox4 = (CheckBox) findViewById(R.id.checkBox4);


            Username = name.getText().toString();
            User_rollno=rollno.getText().toString();
            Userdept=dept.getSelectedItem().toString();

            if (Username.matches("")) {
                Toast.makeText(this,"Kindly enter your Name",Toast.LENGTH_LONG).show();
                return;
            }
            else if(User_rollno.matches("")){
                Toast.makeText(this,"Your roll number is essential to us",Toast.LENGTH_LONG).show();
                return;
            }
           else if(Userdept.matches("")){
                Toast.makeText(this,"Select your department",Toast.LENGTH_LONG).show();
                return;
            }
            else if (( checkBox1.isChecked() == false) && ( checkBox2.isChecked() == false) && ( checkBox3.isChecked() == false) && ( checkBox4.isChecked() == false)){
                Toast.makeText(this,"Please select a profile",Toast.LENGTH_LONG).show();
                return;
            }
            else{

                Toast.makeText(this,"Redirecting",Toast.LENGTH_LONG).show();

            }
            passovertext="Thank you "+Username+", for your interest in Spider";
            Bundle thanks= new Bundle();
            thanks.putString("abc", passovertext);
            Intent intent = new Intent(this, ThankYou.class);
            intent.putExtras(thanks);
            startActivity(intent);
        }



}

   /* String s=et1.getText().toString();
    Bundle basket= new Bundle();
basket.putString("abc", s);
        Intent a=new Intent(MainActivity.this,SecondActivity.class);
        a.putExtras(basket);
        startActivity(a);*/
package com.example.abhinayas.omdb;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Arrays;

/**
 * Created by ABHINAYA  S on 07-07-2016.
 */
public class SearchPage extends AppCompatActivity{
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.search_page);
        ListView listView = (ListView) findViewById(R.id.listView);
        ArrayList<String> values = new ArrayList<String>();
        ArrayAdapter<String> adapter=new ArrayAdapter<String>(SearchPage.this,android.R.layout.simple_list_item_1,android.R.id.text1,values);
        listView.setAdapter(adapter);
        Bundle datums = getIntent().getExtras();
        if (datums != null) {
            ArrayList<String> foundData = new ArrayList<String>();

            foundData=datums.getStringArrayList("found");
            int i=0;
            while(i<=foundData.lastIndexOf(foundData))
            {
                String s=foundData.get(i);
                adapter.setNotifyOnChange(true);
                values.add(s);
                Toast.makeText(SearchPage.this, s, Toast.LENGTH_SHORT).show();

                i++;
            }

        }
        Button search=(Button)findViewById(R.id.button);
        search.setOnClickListener(new View.OnClickListener() {

            public void onClick(View v) {
               EditText searchVal=(EditText)findViewById(R.id.editText);
                Intent i=new Intent(SearchPage.this,MainActivity.class);
                i.putExtra("searchText",searchVal.getText().toString());
                startActivity(i);

            }
        });

    }

}

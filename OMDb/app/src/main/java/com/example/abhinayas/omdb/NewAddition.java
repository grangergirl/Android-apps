package com.example.abhinayas.omdb;

import android.app.ProgressDialog;
import android.content.Intent;
import android.net.Uri;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;
import org.apache.http.HttpStatus;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.params.BasicHttpParams;
import org.apache.http.util.EntityUtils;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import android.app.Activity;
import android.os.StrictMode;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;

import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.TextView;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.StatusLine;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;

import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;import android.app.Activity;
import android.os.StrictMode;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;

import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.TextView;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.StatusLine;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;

import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.net.URI;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLEncoder;

import org.apache.http.util.ByteArrayBuffer;
import org.json.JSONStringer;

import android.util.Log;
import android.widget.Toast;

import java.net.URL;

public class NewAddition extends AppCompatActivity {
    public String url = null;

    public InputStream is = null;
    public JSONObject jObj = null;
    public String json = "";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.add_new);
        Button go = (Button) findViewById(R.id.go);
        go.setOnClickListener(new View.OnClickListener() {

            public void onClick(View v) {
                EditText name=(EditText)findViewById(R.id.editText);
                String title=name.getText().toString();
                Intent i=new Intent(NewAddition.this,MainActivity.class);
                i.putExtra("title",title);
                startActivity(i);
               // new JSONAsyncTask().execute("http://www.omdbapi.com/?t=amelie&y=&plot=short&r=json");
               // Toast.makeText(NewAddition.this,"job done", Toast.LENGTH_SHORT).show();

                // new JSONAsyncTask().execute("http://www.omdbapi.com/?t="+ URLEncoder.encode(Name, "UTF-8")+"&y="+URLEncoder.encode(Year,"UTF-8")+"&plot="+URLEncoder.encode(Plot,"UTF-8")+"&r="+URLEncoder.encode(r,"UTF-8"));




            }
        });

    }
}

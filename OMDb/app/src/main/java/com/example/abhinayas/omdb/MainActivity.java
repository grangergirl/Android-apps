package com.example.abhinayas.omdb;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.TextView;
import android.widget.Toast;

import java.io.IOException;
import java.lang.reflect.Array;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import android.os.Bundle;
import android.app.Activity;
import android.graphics.Color;
import android.view.Menu;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.GridView;
import android.widget.RelativeLayout;
import android.widget.Toast;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;
import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by ABHINAYA  S on 07-07-2016.
 */
public class MainActivity extends AppCompatActivity {


    // public static List<String> collected_data = new List<String>("what");
    public static ArrayList<String> collected_data = new ArrayList<String>();
    public static Information[] movie=new Information[100];
    public static int infoIndex=-1;
    public ProgressDialog pd=null;
    public static int checker=0;
    public String searchText=null;

    public ArrayAdapter<String> adp = null;
public String title=null;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        GridView grid = (GridView) findViewById(R.id.gridView);
        adp = new ArrayAdapter<String>(this, android.R.layout.simple_dropdown_item_1line, collected_data);
        grid.setNumColumns(2);
        grid.setBackgroundColor(Color.CYAN);
        grid.setAdapter(adp);
        Bundle data = getIntent().getExtras();
        if (data != null) {
            searchText=data.getString("searchText");
            if(searchText==null) {
                title = (String) data.get("title");
                infoIndex++;
                new JSONAsyncTask().execute("http://www.omdbapi.com/?t=" + title + "&y=&plot=short&r=json");
            }
            else
            {

               int i = 0;


               Toast.makeText(MainActivity.this, "searching for "+searchText, Toast.LENGTH_SHORT).show();
               ArrayList<String> foundData = new ArrayList<String>();

                while(i<=infoIndex) {
                    String string=collected_data.get(i);

                    if (string.toLowerCase().indexOf(searchText.toLowerCase()) != -1) {
                    foundData.add(string);
                        Toast.makeText(MainActivity.this,string, Toast.LENGTH_SHORT).show();

                    } i++;

                }
                searchText=null;
              Intent in=new Intent(MainActivity.this,SearchPage.class);
                //in.putExtra("found",string);
                in.putStringArrayListExtra("found",foundData);
                startActivity(in);
            }

        }



        grid.setOnItemClickListener(new OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> arg0, View arg1, int arg2,
                                    long arg3) {
                // TODO Auto-generated method stub

                adp.setNotifyOnChange(true);

                Intent i=new Intent(MainActivity.this,ViewInfo.class);

                i.putExtra("object",movie[arg2]);

                startActivity(i);

            }
        });

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.main_activity_bar, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        //When Product action item is clicked
        if (id == R.id.action_search) {
            Intent intent = new Intent(getApplicationContext(), SearchPage.class);
            startActivity(intent);

            return true;
        } else if (id == R.id.action_add) {
            Intent intent = new Intent(getApplicationContext(), NewAddition.class);
            startActivity(intent);

            return true;
        }

        return super.onOptionsItemSelected(item);
    }


    class JSONAsyncTask extends AsyncTask<String, Void, Boolean> {
        public String s = null;

        protected void onPreExecute() {
            super.onPreExecute();
         pd = new ProgressDialog(MainActivity.this);
            pd.setMessage("Connecting...");
            pd.setTitle("Please wait");
            pd.show();
            pd.setCancelable(false);
        }

        @Override
        protected Boolean doInBackground(String... urls) {
// TODO Auto-generated method stub
            try {
                String url="http://www.omdbapi.com/?t="+ URLEncoder.encode(title, "UTF-8")+"&y=&plot=short&r=json";
                HttpGet httppost = new HttpGet(url);
                HttpClient httpclient = new DefaultHttpClient();
                HttpResponse response = httpclient.execute(httppost);

                int status = response.getStatusLine().getStatusCode();
                if (status == 200) {
                    HttpEntity entity = response.getEntity();
                    String data = EntityUtils.toString(entity);
                   JSONObject object = new JSONObject(data);

                        Information information = new Information(object.getString("Title"), object.getString("Genre"), object.getString("Actors"), object.getString("Poster"), object.getString("Director"), object.getString("imdbRating"), object.getString("Writer"), object.getString("Runtime"), object.getString("Plot"));
                        movie[infoIndex] = new Information(information);

                    return true;
                }


            } catch (IOException e) {

                e.printStackTrace();
            } catch (JSONException e) {
                e.printStackTrace();
            }
            return false;
        }


        @Override
        protected void onPostExecute(Boolean result) {
// TODO Auto-generated method stub



            adp.notifyDataSetChanged();
            collected_data.add(movie[infoIndex].title);

            pd.cancel();
            // adp.notifyDataSetChanged();
             // if (result == false)

                 // Toast.makeText(MainActivity.this, "Unable to fetch data from server", Toast.LENGTH_LONG).show();

        }
    }

}






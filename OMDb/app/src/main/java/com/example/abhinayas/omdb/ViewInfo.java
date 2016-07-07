package com.example.abhinayas.omdb;

import android.app.ProgressDialog;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.io.InputStream;


/**
 * Created by ABHINAYA  S on 07-07-2016.
 */
public class ViewInfo extends AppCompatActivity {
    public Information temp=null;
    public ProgressDialog pd=null;
    @Override
    protected void onCreate (Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.view_data);
        Bundle data = getIntent().getExtras();
        if (data != null) {
            TextView title=(TextView)findViewById(R.id.title);
            TextView actors=(TextView)findViewById(R.id.actors);
            TextView genre=(TextView)findViewById(R.id.genre);
            TextView director=(TextView)findViewById(R.id.director);
            TextView rating=(TextView)findViewById(R.id.rating);
            TextView writer=(TextView)findViewById(R.id.writer);
            TextView runtime=(TextView)findViewById(R.id.runtime);
            TextView plot=(TextView)findViewById(R.id.plot);
            ImageView img=(ImageView)findViewById(R.id.imageView);
            temp=data.getParcelable("object");
            title.setText(temp.title);
            actors.setText(temp.actors);
            genre.setText(temp.genre);
            director.setText(temp.director);
            rating.setText(temp.rating);
            writer.setText(temp.writer);
            runtime.setText(temp.runtime);
            plot.setText(temp.plot);
            new DownloadImageTask(img).execute("http://java.sogeti.nl/JavaBlog/wp-content/uploads/2009/04/android_icon_256.png");

        }
    }
   class DownloadImageTask extends AsyncTask<String, Void, Bitmap> {
        ImageView bmImage;

        public DownloadImageTask(ImageView bmImage) {
            this.bmImage = bmImage;
        }
       protected void onPreExecute() {
           super.onPreExecute();
           pd = new ProgressDialog(ViewInfo.this);
           pd.setMessage("Retrieving poster data...");
           pd.setTitle("Hold on");
           pd.show();
           pd.setCancelable(false);
       }

        protected Bitmap doInBackground(String... urls) {
            String urldisplay = temp.poster;
            Bitmap mIcon11 = null;
            try {
                InputStream in = new java.net.URL(urldisplay).openStream();
                mIcon11 = BitmapFactory.decodeStream(in);
            } catch (Exception e) {
                Log.e("Error", e.getMessage());
                e.printStackTrace();
            }
            return mIcon11;
        }

        protected void onPostExecute(Bitmap result) {
            pd.cancel();
            bmImage.setImageBitmap(result);
        }
    }
}

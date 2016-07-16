package com.example.abhinayas.muzimix;

import android.content.DialogInterface;
import android.content.Intent;
import android.media.MediaPlayer;
import android.media.MediaRecorder;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.view.menu.ExpandedMenuView;
import android.text.InputType;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;
import com.example.abhinayas.muzimix.R;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;


public class Dashboard extends AppCompatActivity {
    public MediaPlayer track=null;
    private static String mFileName = null;
    private MediaRecorder mRecorder = null;
    private static String trimFileName=null;
    private MediaPlayer   mPlayer = null;
    public int mStartRecording = 1;
    public int mStartPlaying = 1;
    private static final String LOG_TAG = "AudioRecordTest";
    public static ArrayList<String> clips = new ArrayList<String>();
    public static MergerList adapter=null;
    public Button addnew,saveFile;
    public static boolean flag;

    @Override
    public void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.dashboard);
        ListView list;
        adapter = new MergerList(Dashboard.this,clips);
        list=(ListView)findViewById(R.id.list);
        list.setAdapter(adapter);
        list.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> arg0, View arg1, int arg2,
                                    long arg3) {
                // TODO Auto-generated method stub
                Intent i=new Intent(Dashboard.this,TrimMusic.class);
                i.putExtra("name",clips.get(arg2));
                startActivity(i);

            }
        });
        addnew=(Button)findViewById(R.id.addnew);
        addnew.setOnClickListener(new View.OnClickListener() {

            public void onClick(View v) {
                try {
                    flag=true;
                    Intent pickTrack = new Intent(Intent.ACTION_PICK, MediaStore.Audio.Media.EXTERNAL_CONTENT_URI);
                    startActivityForResult(pickTrack , 1);//one can be replaced with any action code
                }
                catch(Exception e)
                {
                    Toast.makeText(Dashboard.this, e.getMessage(),Toast.LENGTH_SHORT).show();
                }
            }

        });
        saveFile=(Button)findViewById(R.id.save);
        saveFile.setOnClickListener(new View.OnClickListener() {

            public void onClick(View v) {
                try {
                    LayoutInflater li = LayoutInflater.from(Dashboard.this);
                    View promptsView = li.inflate(R.layout.audio_name, null);
                    AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(Dashboard.this);
                    alertDialogBuilder.setView(promptsView);
                    final EditText userInput = (EditText) promptsView
                            .findViewById(R.id.editTextDialogUserInput);

                    alertDialogBuilder
                            .setCancelable(false)
                            .setPositiveButton("Confirm",
                                    new DialogInterface.OnClickListener() {
                                        public void onClick(DialogInterface dialog,int id) {
                                            // get user input and set it to result
                                            // edit text
                                            String fileName=userInput.getText().toString();
                                            mFileName = Environment.getExternalStorageDirectory().getAbsolutePath() + "/" + fileName + "_rec.mp3";
                                            Toast.makeText(Dashboard.this,mFileName+"saved successfully!",Toast.LENGTH_SHORT).show();
                                            adapter.notifyDataSetChanged();
                                            clips.clear();
                                            clips=new ArrayList<String>();


                                        }
                                    })
                            .setNegativeButton("Cancel",
                                    new DialogInterface.OnClickListener() {
                                        public void onClick(DialogInterface dialog,int id) {
                                            dialog.cancel();
                                            Toast.makeText(Dashboard.this,"Cancelled",Toast.LENGTH_SHORT).show();

                                        }
                                    });

                    // create alert dialog
                    AlertDialog alertDialog = alertDialogBuilder.create();

                    // show it
                    alertDialog.show();

                }
                catch(Exception e)
                {
                    Toast.makeText(Dashboard.this, e.getMessage(),Toast.LENGTH_SHORT).show();
                }
            }

        });

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.dashboard_options, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        //When Product action item is clicked
        if (id == R.id.action_play) {
            if(track!=null)
            {
                track.pause();
                track.release();
                track=null;
            }
            flag=false;
            Intent pickTrack = new Intent(Intent.ACTION_PICK, MediaStore.Audio.Media.EXTERNAL_CONTENT_URI);
            startActivityForResult(pickTrack , 1);//one can be replaced with any action code

            return true;
        } else if (id == R.id.action_rec) {
            if (mStartRecording==1) {

                try {
                    onRecord(1);

                    mStartRecording=0;
                }
                catch (Exception e)
                {
                    Toast.makeText(Dashboard.this,e.getMessage().toString(),Toast.LENGTH_SHORT).show();
                }
            } else {

                try {
                    onRecord(0);
                    mStartRecording=1;
                }
                catch (Exception e)
                {
                    Toast.makeText(Dashboard.this,e.getMessage().toString(),Toast.LENGTH_SHORT).show();
                }
            }

            return true;
        }else if (id == R.id.action_playrec) {
            try {
                onPlay(mStartPlaying);
                if (mStartPlaying==1) {

                    mStartPlaying=0;

                } else {

                    mStartPlaying=1;
                }

            }
            catch (Exception e)
            {
                Toast.makeText(Dashboard.this,e.getMessage().toString(),Toast.LENGTH_SHORT).show();
            }

             return true;
        }

        return super.onOptionsItemSelected(item);
    }
    protected void onActivityResult(int requestCode, int resultCode, Intent trackIntent) {
        super.onActivityResult(requestCode, resultCode, trackIntent);


        switch(requestCode) {
            case 0:
                if(resultCode == RESULT_OK){
                    if(!flag) {
                        Uri trackUri=trackIntent.getData();
                        track = MediaPlayer.create(Dashboard.this, trackUri);
                        trimFileName = trackUri.toString();
                        track.start();
                    }
                    else
                    {  String fileName=trackIntent.getDataString();
                        adapter.notifyDataSetChanged();
                        clips.add(fileName);
                    }

                }

                break;
            case 1:
                if(resultCode == RESULT_OK){
                    if(!flag) {
                        Uri trackUri=trackIntent.getData();
                        track = MediaPlayer.create(Dashboard.this, trackUri);
                        trimFileName = trackUri.toString();
                        track.start();
                    }
                    else
                    { String fileName=trackIntent.getDataString();
                        adapter.notifyDataSetChanged();
                        clips.add(fileName);

                    }
                }
                break;
        }
    }
    public void onRecord(int start) {
        if (start==1) {
            startRecording();
        } else {
            stopRecording();
        }
    }

    public void onPlay(int start) {
        if (start==1) {
            startPlaying();
        } else {
            stopPlaying();
        }
    }
    public void startPlaying() {
        mPlayer = new MediaPlayer();
        try {


            mPlayer.setDataSource(mFileName);
            mPlayer.prepare();
            mPlayer.start();
        }catch (IOException e) {
            Log.e(LOG_TAG, "prepare() failed");
        }


    }

    public void stopPlaying() {
        mPlayer.release();
        mPlayer = null;
    }

    public void startRecording() {

        mRecorder = new MediaRecorder();
        mRecorder.reset();
        try{
            final Date createdTime=new Date();
            mFileName = Environment.getExternalStorageDirectory().getAbsolutePath() + "/" + createdTime + "_rec.mp3";
            Toast.makeText(Dashboard.this,mFileName.toString(),Toast.LENGTH_SHORT).show();
           mRecorder.setAudioSource(MediaRecorder.AudioSource.MIC);
            mRecorder.setOutputFormat(MediaRecorder.OutputFormat.MPEG_4);
            LayoutInflater li = LayoutInflater.from(Dashboard.this);
            View promptsView = li.inflate(R.layout.audio_name, null);
            AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(Dashboard.this);
            alertDialogBuilder.setView(promptsView);
            final EditText userInput = (EditText) promptsView
                    .findViewById(R.id.editTextDialogUserInput);

           alertDialogBuilder
                    .setCancelable(false)
                    .setPositiveButton("Confirm",
                            new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog,int id) {
                                    // get user input and set it to result
                                    // edit text
                                    String fileName=userInput.getText().toString();
                                    mFileName = Environment.getExternalStorageDirectory().getAbsolutePath() + "/" + fileName + "_rec.mp3";
                                    mRecorder.setOutputFile(mFileName);
                                    mRecorder.setAudioEncoder(MediaRecorder.AudioEncoder.AAC);
try {
    mRecorder.prepare();
}
catch (Exception e)
{
    Toast.makeText(Dashboard.this,"what nonsense",Toast.LENGTH_SHORT).show();
}
                                    mRecorder.start();
                                }
                            })
                    .setNegativeButton("Cancel",
                            new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog,int id) {
                                    dialog.cancel();
                                    mRecorder.setOutputFile(mFileName);
                                    mRecorder.setAudioEncoder(MediaRecorder.AudioEncoder.AAC);
                                    try {
                                        mRecorder.prepare();
                                    }
                                    catch (Exception e)
                                    {
                                        Toast.makeText(Dashboard.this,"what nonsense",Toast.LENGTH_SHORT).show();
                                    }

                                    mRecorder.start();
                                }
                            });

            // create alert dialog
            AlertDialog alertDialog = alertDialogBuilder.create();

            // show it
            alertDialog.show();


        } catch (Exception e) {
            //Log.e(LOG_TAG, "Voice record failure");
            Toast.makeText((Dashboard.this),e.getMessage().toString(),Toast.LENGTH_SHORT).show();
        }


    }

    public void stopRecording() {
        mRecorder.stop();
        mRecorder.release();
        mRecorder = null;
    }

    @Override
    public void onPause() {
        super.onPause();
        if (mRecorder != null) {
            mRecorder.release();
            mRecorder = null;
        }

        if (mPlayer != null) {
            mPlayer.release();
            mPlayer = null;
        }
    }
}



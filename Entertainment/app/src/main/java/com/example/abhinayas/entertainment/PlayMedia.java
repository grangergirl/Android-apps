package com.example.abhinayas.entertainment;

import android.app.Service;
import android.media.MediaPlayer;
import android.os.IBinder;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

public class PlayMedia extends Service {
    private Button play, stop;
    public MediaPlayer mediaPlayer;
    private Spinner spinner;
    private static String[] paths = {"", "A life full of love", "Cuppy cake", "Debussy","Here I am"};
    public TextView name;
    public int val;
    int mStartMode;// indicates how to behave if the service is killed
    IBinder mBinder;// interface for clients that bind

    boolean mAllowRebind;//indicates whether onRebind should be used


    @Override//Called when the service is being created.
    public void onCreate() {

    }

    /** The service is starting, due to a call to startService() */
    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        if(val==1)
        {
            mediaPlayer = MediaPlayer.create(this, R.raw.alife);}
        else if(val==2)
        {
            mediaPlayer = MediaPlayer.create(this, R.raw.cuppy);}
        else if(val==3)
        {
            mediaPlayer = MediaPlayer.create(this, R.raw.debussy);}
        else if(val==4)
        {
            mediaPlayer = MediaPlayer.create(this, R.raw.hereiam);}


        mediaPlayer.start();
        return mStartMode;
    }

    /** A client is binding to the service with bindService() */
    @Override
    public IBinder onBind(Intent intent) {
        return mBinder;
    }

    /** Called when all clients have unbound with unbindService() */
    @Override
    public boolean onUnbind(Intent intent) {
        return mAllowRebind;
    }

    /** Called when a client is binding to the service with bindService()*/
    @Override
    public void onRebind(Intent intent) {

    }

    /** Called when The service is no longer used and is being destroyed */
    @Override
    public void onDestroy() {
        mediaPlayer.release();
        mediaPlayer = null;
    }
}
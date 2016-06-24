package com.example.abhinayas.entertainment;

import android.os.AsyncTask;
import android.os.Build;

/**
 * Created by ABHINAYA  S on 22-06-2016.
 */
public class MultiTasks {

    private AsyncTask<Void, Void, Void> slideShow;
    private AsyncTask<Void, Void, Void> playAudio;
    private volatile int i;

    public MultiTasks() {
        createslideShow();
        createplayAudio();
        startTasks();
    }

    private void createslideShow() {
        slideShow = new AsyncTask<Void, Void, Void>() {
            @Override
            protected Void doInBackground(Void... voids) {
                doInBackgroundA();
                return null;
            }
        };
    }

    private void doInBackgroundA() {
        while (i < 5000) {
            i++;
        }
    }

    private void createplayAudio() {
        playAudio = new AsyncTask<Void, Void, Void>() {
            @Override
            protected Void doInBackground(Void... voids) {
                doInBackGroundB();
                return null;
            }
        };
    }

    private void doInBackGroundB() {
        while (i > 0) {
            i--;
        }
    }

    private void startTasks() {
        // AsyncTasks executed one one thread in Honeycomb+ unless executed in thread pool manually
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB) {
            slideShow.executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR);
            playAudio.executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR);
        } else {
            slideShow.execute();
            playAudio.execute();
        }
    }}

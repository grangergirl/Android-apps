package com.example.abhinayas.omdb;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by ABHINAYA  S on 07-07-2016.
 */

public class Information implements Parcelable {
    public String title;
    public String genre;
    public String actors;
    public String poster;
    public String director;
    public String rating;
    public String writer;
    public String runtime;
    public String plot;


    // Constructor
    public Information() {
        this.title = null;
        this.genre = null;
        this.actors =null;
        this.poster=null;
        this.director=null;
        this.rating=null;
        this.writer=null;
        this.runtime=null;
        this.plot=null;

    }

    public Information(String title, String genre, String actors,String poster,String director,String rating,String writer,String runtime,String plot) {
        this.title = title;
        this.genre = genre;
        this.actors = actors;
        this.poster=poster;
        this.director=director;
        this.rating=rating;
        this.writer=writer;
        this.runtime=runtime;
        this.plot=plot;

    }
    public Information(Information c) {
        this.title = c.title;
        this.genre = c.genre;
        this.actors = c.actors;
        this.poster=c.poster;
        this.director=c.director;
        this.rating=c.rating;
        this.writer=c.writer;
        this.runtime=c.runtime;
        this.plot=c.plot;

    }
    // Getter and setter methods


    // Parcelling part
    public Information(Parcel in) {
        String[] data = new String[9];
        in.readStringArray(data);

        this.title = data[0];
        this.genre = data[1];
        this.actors = data[2];
        this.poster=data[3];
        this.director=data[4];
        this.rating=data[5];
        this.writer=data[6];
        this.runtime=data[7];
        this.plot=data[8];


    }


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeStringArray(new String[]{this.title, this.genre, this.actors,this.poster,this.director,this.rating,this.writer,this.runtime,this.plot
        });
    }

    public static final Parcelable.Creator CREATOR = new Parcelable.Creator() {
        public Information createFromParcel(Parcel in) {
            return new Information(in);
        }

        public Information[] newArray(int size) {
            return new Information[size];
        }
    };
}
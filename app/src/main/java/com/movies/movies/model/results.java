package com.movies.movies.model;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by user on 3/3/2018.
 */

public class results implements Parcelable {

    private int id;
    private String title;
    private String poster_path;
    private  String overview;
    private  String release_date;
    private  String vote_average;
    private  String backdrop_path;

    public String getBackdrop_path() {
        return backdrop_path;
    }

    public void setBackdrop_path(String backdrop_path) {
        this.backdrop_path = backdrop_path;
    }

    public String getOverview() {
        return overview;
    }

    public void setOverview(String overview) {
        this.overview = overview;
    }

    public String getRelease_date() {
        return release_date;
    }

    public void setRelease_date(String release_date) {
        this.release_date = release_date;
    }

    public String getVote_average() {
        return vote_average;
    }

    public void setVote_average(String vote_average) {
        this.vote_average = vote_average;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getPoster_path() {
        return poster_path;
    }

    public void setPoster_path(String poster_path) {
        this.poster_path = poster_path;
    }


    public results() {
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(this.id);
        dest.writeString(this.title);
        dest.writeString(this.poster_path);
        dest.writeString(this.overview);
        dest.writeString(this.release_date);
        dest.writeString(this.vote_average);
        dest.writeString(this.backdrop_path);
    }

    protected results(Parcel in) {
        this.id = in.readInt();
        this.title = in.readString();
        this.poster_path = in.readString();
        this.overview = in.readString();
        this.release_date = in.readString();
        this.vote_average = in.readString();
        this.backdrop_path = in.readString();
    }

    public static final Creator<results> CREATOR = new Creator<results>() {
        @Override
        public results createFromParcel(Parcel source) {
            return new results(source);
        }

        @Override
        public results[] newArray(int size) {
            return new results[size];
        }
    };
}

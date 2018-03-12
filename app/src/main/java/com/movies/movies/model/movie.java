package com.movies.movies.model;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by user on 3/3/2018.
 */

public class movie implements Parcelable {

    public List<results> getResults() {
        return results;
    }

    public void setResults(List<com.movies.movies.model.results> results) {
        this.results = results;
    }

    private List<results> results;

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeList(this.results);
    }

    public movie() {
    }

    protected movie(Parcel in) {
        this.results = new ArrayList<com.movies.movies.model.results>();
        in.readList(this.results, com.movies.movies.model.results.class.getClassLoader());
    }

    public static final Parcelable.Creator<movie> CREATOR = new Parcelable.Creator<movie>() {
        @Override
        public movie createFromParcel(Parcel source) {
            return new movie(source);
        }

        @Override
        public movie[] newArray(int size) {
            return new movie[size];
        }
    };
}

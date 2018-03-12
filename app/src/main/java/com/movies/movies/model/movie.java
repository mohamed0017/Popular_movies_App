package com.movies.movies.model;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.List;

/**
 * Created by user on 3/3/2018.
 */

public class movie implements Parcelable {

    protected movie(Parcel in) {
    }

    public static final Creator<movie> CREATOR = new Creator<movie>() {
        @Override
        public movie createFromParcel(Parcel in) {
            return new movie(in);
        }

        @Override
        public movie[] newArray(int size) {
            return new movie[size];
        }
    };

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
    public void writeToParcel(Parcel parcel, int i) {
    }
}

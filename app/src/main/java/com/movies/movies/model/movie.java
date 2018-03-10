package com.movies.movies.model;

import java.util.List;

/**
 * Created by user on 3/3/2018.
 */

public class movie {

    public List<results> getResults() {
        return results;
    }

    public void setResults(List<com.movies.movies.model.results> results) {
        this.results = results;
    }

    private List<results> results;

}

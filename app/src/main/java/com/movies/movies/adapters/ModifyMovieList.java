package com.movies.movies.adapters;

import com.movies.movies.model.movie;
import com.movies.movies.model.results;

import java.util.Iterator;
import java.util.List;

/**
 * Created by user on 3/17/2018.
 */

public class ModifyMovieList {

    //I created a seperate class to include this method
    public static void modifyList(List<results> list, int[] deletedMoviesID){
        Iterator<results> iterator= list.iterator();
        while (iterator.hasNext()) {
            int id = iterator.next().getId();
            for (int deletedMovie : deletedMoviesID) {
                if (id == deletedMovie) {
                    iterator.remove();
                    break;
                }
            }
        }
    }
}

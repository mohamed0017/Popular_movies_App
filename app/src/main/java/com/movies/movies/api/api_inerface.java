package com.movies.movies.api;

import com.movies.movies.model.movie;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;

/**
 * Created by user on 3/3/2018.
 */

public interface api_inerface {

    String Api_key = "***************************";  // Enter your Api_key
    String BasicUrl = "https://api.themoviedb.org/3/movie/";

    @GET("/3/movie/popular")
    Call<movie> GetPopular();

    @GET("/3/movie/top_rated")
    Call<movie> Gettop_rated();
}

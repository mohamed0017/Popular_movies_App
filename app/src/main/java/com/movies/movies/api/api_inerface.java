package com.movies.movies.api;

import com.movies.movies.model.movie;

import retrofit2.Call;
import retrofit2.http.GET;

/**
 * Created by user on 3/3/2018.
 */

public interface api_inerface {

    String Api_key = "d06277cedd76c9b43c7264b803e1fa64";  // Enter your Api_key
    String BasicUrl = "api.themoviedb.org";

    @GET("/3/movie/popular")
    Call<movie> GetPopular();

    @GET("/3/movie/top_rated")
    Call<movie> Gettop_rated();
}

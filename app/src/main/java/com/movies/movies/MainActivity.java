package com.movies.movies;

import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

import com.movies.movies.adapters.PostersMoviesAdapter;
import com.movies.movies.api.FullRestAdapter;
import com.movies.movies.api.api_inerface;
import com.movies.movies.model.movie;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getMovies();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            Intent intent = new Intent(this, SettingsActivity.class);
            startActivity(intent);
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onResume() {
        super.onResume();

        getMovies();
    }


    public void getMovies() {

        SharedPreferences prefs = getSharedPreferences("sort_movies",
                MODE_PRIVATE);
        String string = prefs.getString("movieSort", "default_value");

        if (string.equals("Popular")) {

            Uri.Builder builder = new Uri.Builder();
            builder.scheme("https")
                    .authority(api_inerface.BasicUrl)
                    .appendPath("3")
                    .appendPath("movie")
                    .appendPath("popular");
            String FinalUrl = builder.build().toString();
            api_inerface api = FullRestAdapter.createAPI(FinalUrl + "/");
            Call<movie> resultsCall = api.GetPopular();
            resultsCall.enqueue(new Callback<movie>() {
                @Override
                public void onResponse(@NonNull Call<movie> call, @NonNull Response<movie> response) {
                    RecyclerView recyclerView = (RecyclerView) findViewById(R.id.recycleMoviesList);
                    RecyclerView.LayoutManager mLayoutManager = new GridLayoutManager(MainActivity.this, 2);
                    recyclerView.setLayoutManager(mLayoutManager);
                    Log.e("main_error_onresponse", response.message() + " ..");
                    Log.e("main_error_onresponse", response.body() + " ..");
                    PostersMoviesAdapter postersMoviesAdapter = new PostersMoviesAdapter(response.body().getResults(), MainActivity.this);
                    recyclerView.setAdapter(postersMoviesAdapter);
                }

                @Override
                public void onFailure(Call<movie> call, Throwable t) {

                    Log.e("main_error", t.getCause() + " ..");

                }
            });

        } else {

            Uri.Builder builder = new Uri.Builder();
            builder.scheme("https")
                    .authority(api_inerface.BasicUrl)
                    .appendPath("3")
                    .appendPath("movie")
                    .appendPath("top_rated");
            String FinalUrl = builder.build().toString();

            api_inerface api = FullRestAdapter.createAPI(FinalUrl + "/");
            Call<movie> resultsCall = api.Gettop_rated();
            resultsCall.enqueue(new Callback<movie>() {
                @Override
                public void onResponse(@NonNull Call<movie> call, @NonNull Response<movie> response) {
                    RecyclerView recyclerView = (RecyclerView) findViewById(R.id.recycleMoviesList);
                    RecyclerView.LayoutManager mLayoutManager = new GridLayoutManager(MainActivity.this, 2);
                    recyclerView.setLayoutManager(mLayoutManager);
                    Log.e("main_error_onresponse", response.message() + " ..");
                    Log.e("main_error_onresponse", response.body() + " ..");
                    PostersMoviesAdapter postersMoviesAdapter = new PostersMoviesAdapter(response.body().getResults(), MainActivity.this);
                    recyclerView.setAdapter(postersMoviesAdapter);
                }

                @Override
                public void onFailure(Call<movie> call, Throwable t) {
                    Log.e("main_error", t.getCause() + " ..");

                }
            });

        }
    }
}
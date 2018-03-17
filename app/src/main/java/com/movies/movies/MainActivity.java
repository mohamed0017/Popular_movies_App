package com.movies.movies;

import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.MenuItem;
import android.widget.Toast;

import com.movies.movies.adapters.ModifyMovieList;
import com.movies.movies.adapters.PostersMoviesAdapter;
import com.movies.movies.api.FullRestAdapter;
import com.movies.movies.api.api_inerface;
import com.movies.movies.model.movie;
import com.movies.movies.model.results;
import com.movies.movies.services.ConnectivityReceiver;
import com.movies.movies.ui.GridAutofitLayoutManager;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity implements ConnectivityReceiver.ConnectivityReceiverListener {

    private static MainActivity mInstance;

    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            switch (item.getItemId()) {
                case R.id.navigation_home:
                    getMoviesPoupler();
                    return true;
                case R.id.navigation_dashboard:
                    getMoviesTopRated();
                    return true;
            }
            return false;
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        mInstance = this;
        checkConnection();
        getMoviesPoupler();
        BottomNavigationView navigation = (BottomNavigationView) findViewById(R.id.navigation);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);
    }


    public void getMoviesPoupler() {

        checkConnection();
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
                if (response.isSuccessful()) {
                    GridAutofitLayoutManager gridAutofitLayoutManager = new GridAutofitLayoutManager(MainActivity.this, 480);
                    RecyclerView recyclerView = (RecyclerView) findViewById(R.id.recycleMoviesList);
                    recyclerView.setLayoutManager(gridAutofitLayoutManager);
                    Log.e("main_error_onresponse", response.message() + " ..");
                    Log.e("main_error_onresponse", response.body() + " ..");
                    modifyMovieList(response.body().getResults());
                    PostersMoviesAdapter postersMoviesAdapter = new PostersMoviesAdapter(response.body().getResults(), MainActivity.this);
                    recyclerView.setAdapter(postersMoviesAdapter);
                }

            }

            @Override
            public void onFailure(@NonNull Call<movie> call, @NonNull Throwable t) {

                Log.e("main_error", t.getCause() + " ..");

            }
        });

    }

    public void getMoviesTopRated() {
        checkConnection();
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
                if (response.isSuccessful()) {
                    GridAutofitLayoutManager gridAutofitLayoutManager = new GridAutofitLayoutManager(MainActivity.this, 480);
                    RecyclerView recyclerView = (RecyclerView) findViewById(R.id.recycleMoviesList);
                    recyclerView.setLayoutManager(gridAutofitLayoutManager);
                    Log.e("main_error_onresponse", response.message() + " ..");
                    Log.e("main_error_onresponse", response.body() + " ..");
                    modifyMovieList(response.body().getResults());
                    PostersMoviesAdapter postersMoviesAdapter = new PostersMoviesAdapter(response.body().getResults(), MainActivity.this);
                    recyclerView.setAdapter(postersMoviesAdapter);
                }

            }

            @Override
            public void onFailure(@NonNull Call<movie> call, @NonNull Throwable t) {
                Log.e("main_error", t.getCause() + " ..");

            }
        });
    }

    //this method in MainActivity to modify movieList and delete
    //movies before populating UI
    private void modifyMovieList(List<results> list) {
        int[] deletedMoviesID = getResources().getIntArray(R.array.deletedMovies);
        ModifyMovieList.modifyList(list, deletedMoviesID);
    }

    public static synchronized MainActivity getInstance() {
        return mInstance;
    }

    private void checkConnection() {
        boolean isConnected = ConnectivityReceiver.isConnected();
        if (isConnected == false) {
            Toast.makeText(this, "No Internet Connection ", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        // register connection status listener
        MyApplication.getInstance().setConnectivityListener(this);
    }

    @Override
    public void onNetworkConnectionChanged(boolean isConnected) {
        if (isConnected == false) {
            Toast.makeText(this, "No Internet Connection ", Toast.LENGTH_SHORT).show();
        }
    }
}

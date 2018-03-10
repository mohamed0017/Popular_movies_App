package com.movies.movies;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.movies.movies.model.movie;
import com.movies.movies.model.results;
import com.squareup.picasso.Picasso;

public class movie_details extends AppCompatActivity {

    String title, release, overView, vote, movePoster, moveBg;
    TextView textViewTitle, textViewReease, textViewVote, textViewOverView;
    ImageView imageViewPoster, imageViewBg;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movie_details);

        textViewTitle = (TextView) findViewById(R.id.textTitleDetails);
        textViewReease = (TextView) findViewById(R.id.textReleaseDetails);
        textViewVote = (TextView) findViewById(R.id.ratingDetails);
        textViewOverView = (TextView) findViewById(R.id.textOverViewDetails);
        imageViewPoster = (ImageView) findViewById(R.id.imagePosterDetails);
        imageViewBg = (ImageView) findViewById(R.id.imageBgDetails);
        try {
            title = getIntent().getExtras().getString("getTitle");
            release = getIntent().getExtras().getString("getRelease_date");
            overView = getIntent().getExtras().getString("getOverview");
            vote = getIntent().getExtras().getString("getVote_average");
            movePoster = getIntent().getExtras().getString("getPoster_path");
            moveBg = getIntent().getExtras().getString("get_bg");

            textViewTitle.setText(title);
            textViewReease.setText(release);
            textViewOverView.setText(overView);
            textViewVote.setText(vote);

            Picasso.with(this).load("http://image.tmdb.org/t/p/w500" + movePoster).into(imageViewPoster);
            Picasso.with(this).load("http://image.tmdb.org/t/p/original" + moveBg).into(imageViewBg);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}

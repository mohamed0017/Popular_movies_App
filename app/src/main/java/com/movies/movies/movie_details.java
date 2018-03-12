package com.movies.movies;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.movies.movies.model.movie;
import com.movies.movies.model.results;
import com.squareup.picasso.Picasso;

import butterknife.BindView;
import butterknife.ButterKnife;

public class movie_details extends AppCompatActivity {

    String title, release, overView, vote, movePoster, moveBg;
    @BindView(R.id.textTitleDetails)
    TextView textViewTitle;
    @BindView(R.id.textReleaseDetails)
    TextView textViewReease;
    @BindView(R.id.ratingDetails)
    TextView textViewVote;
    @BindView(R.id.textOverViewDetails)
    TextView textViewOverView;
    @BindView(R.id.imagePosterDetails)
    ImageView imageViewPoster;
    @BindView(R.id.imageBgDetails)
    ImageView imageViewBg;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movie_details);
        ButterKnife.bind(this);

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

            Picasso.with(this).load("http://image.tmdb.org/t/p/w500" + movePoster)
                    .placeholder(R.drawable.placeholder)
                    .error(R.drawable.placeholder).into(imageViewPoster);
            Picasso.with(this).load("http://image.tmdb.org/t/p/original" + moveBg)
                    .placeholder(R.drawable.placeholder_image4)
                    .error(R.drawable.placeholder_image4).into(imageViewBg);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}

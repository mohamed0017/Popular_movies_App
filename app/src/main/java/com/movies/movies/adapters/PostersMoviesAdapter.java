package com.movies.movies.adapters;

import android.content.Context;
import android.content.Intent;
import android.os.Parcelable;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.movies.movies.MainActivity;
import com.movies.movies.R;
import com.movies.movies.model.results;
import com.movies.movies.movie_details;
import com.squareup.picasso.Picasso;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by user on 3/3/2018.
 */

public class PostersMoviesAdapter extends RecyclerView.Adapter<PostersMoviesAdapter.posterHolder> {

    List<results> moviesList = new ArrayList<>();
    Context context;

    public PostersMoviesAdapter(List<results> moviesList, Context context) {
        this.moviesList = moviesList;
        this.context = context;
    }

    @Override
    public posterHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.movie_single_item, parent, false);
        posterHolder posterHolder = new posterHolder(view);

        return posterHolder;
    }

    @Override
    public void onBindViewHolder(posterHolder holder, int position) {

        final results movies = moviesList.get(position);

        Picasso.with(context).load("http://image.tmdb.org/t/p/w500" + movies.getPoster_path()).into(holder.imageView_poster);

        holder.linearLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context, movie_details.class);
                intent.putExtra("getTitle", movies.getTitle());
                intent.putExtra("getPoster_path", movies.getPoster_path());
                intent.putExtra("getOverview", movies.getOverview());
                intent.putExtra("getRelease_date", movies.getRelease_date());
                intent.putExtra("getVote_average", movies.getVote_average());
                intent.putExtra("get_bg", movies.getBackdrop_path());

                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return moviesList.size();
    }

    public class posterHolder extends RecyclerView.ViewHolder {
        ImageView imageView_poster;
        LinearLayout linearLayout;

        public posterHolder(View itemView) {
            super(itemView);
            imageView_poster = itemView.findViewById(R.id.img_poster);
            linearLayout = itemView.findViewById(R.id.SingleMovie);

        }
    }
}

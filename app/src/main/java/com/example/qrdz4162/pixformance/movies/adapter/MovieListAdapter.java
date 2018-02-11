package com.example.qrdz4162.pixformance.movies.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import com.example.qrdz4162.pixformance.R;
import com.example.qrdz4162.pixformance.application.PixFormanceApp;
import com.example.qrdz4162.pixformance.movies.model.entitiy.MovieItem;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by qrdz4162 on 2/7/2018.
 */

/**
 * MovieListAdapter create adapter for movieList holds movieListItem to display in recycler view
 */
public class MovieListAdapter extends RecyclerView.Adapter<MovieListAdapter.DataObjectHolder> {

    private ArrayList<MovieItem> mDataSet;
    private Context context;
    private static MovieClickListener mMovieClickListener;

    @Override
    public DataObjectHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item_product, parent, false);
        context = parent.getContext();

        DataObjectHolder dataObjectHolder = new DataObjectHolder(view);
        return dataObjectHolder;
    }

    @Override
    public void onBindViewHolder(DataObjectHolder holder, int position) {

        MovieItem movieItem = mDataSet.get(position);

        holder.movieNameTextView.setText(movieItem.getTitle());
        Picasso.with(context)
                .load(PixFormanceApp.BASE_IMAGE_URL + movieItem.getPoster_path())
                .error(R.drawable.ic_product_placeholder)
                .fit()
                .into(holder.movieImageView);
        holder.movie_rating_bar.setRating(movieItem.getVote_average()/2);
    }

    @Override
    public int getItemCount() {
        return mDataSet.size();
    }

    public static class DataObjectHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        @BindView(R.id.movie_rating_bar)
        RatingBar movie_rating_bar;

        @BindView(R.id.imageView_product_thumbnail)
        ImageView movieImageView;

        @BindView(R.id.textView_product_name)
        TextView movieNameTextView;

        public DataObjectHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);

            //adding listener...
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            mMovieClickListener.onMovieClick(getAdapterPosition(), v);
        }
    }


    public MovieListAdapter(ArrayList<MovieItem> mDataSet, MovieClickListener mMovieClickListener) {
        this.mDataSet = mDataSet;
        this.mMovieClickListener = mMovieClickListener;
    }

    public interface MovieClickListener {
        void onMovieClick(int position, View v);
    }
}

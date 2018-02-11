package com.example.qrdz4162.pixformance.moviedetails;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.Toolbar;

import com.example.qrdz4162.pixformance.R;
import com.example.qrdz4162.pixformance.base.view.BaseActivity;
import com.example.qrdz4162.pixformance.moviedetails.view.MovieDetailsFragment;
import com.example.qrdz4162.pixformance.movies.model.entitiy.MovieItem;
import com.example.qrdz4162.pixformance.movies.view.MovieFragment;
import com.example.qrdz4162.pixformance.utils.FragmentUtils;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by qrdz4162 on 2/7/2018.
 */

public class MovieDetails extends BaseActivity{

    private MovieItem movieItem;

    @BindView(R.id.toolbar)
    Toolbar toolbar;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_details);
        ButterKnife.bind(this);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayShowTitleEnabled(false);

        // receive object holds movie data in intent
        if (getIntent().getExtras() != null) {
            movieItem = getIntent().getExtras().getParcelable("movie_object");
        }

        MovieDetailsFragment movieDetailsFragment= new MovieDetailsFragment();
        Bundle bundle = new Bundle();
        bundle.putParcelable("movie_object", movieItem);
        movieDetailsFragment.setArguments(bundle);

        FragmentUtils.replaceFragment(this, movieDetailsFragment,R.id.fragment_movie_details_container, false, "");
    }
}

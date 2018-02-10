package com.example.qrdz4162.pixformance.movies;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.Toolbar;

import com.example.qrdz4162.pixformance.R;
import com.example.qrdz4162.pixformance.base.view.BaseActivity;
import com.example.qrdz4162.pixformance.movies.view.MovieFragment;
import com.example.qrdz4162.pixformance.utils.FragmentUtils;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by qrdz4162 on 2/7/2018.
 */

public class MovieActivity extends BaseActivity{

    @BindView(R.id.toolbar)
    Toolbar toolbar;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_movie);
        ButterKnife.bind(this);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayShowTitleEnabled(false);

        FragmentUtils.replaceFragment(this, new MovieFragment(),R.id.fragment_movie_container , false, "");
    }
}

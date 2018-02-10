package com.example.qrdz4162.pixformance.movies.view;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.ActivityOptionsCompat;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ProgressBar;

import com.example.qrdz4162.pixformance.R;
import com.example.qrdz4162.pixformance.base.injection.Injection;
import com.example.qrdz4162.pixformance.base.view.BaseFragment;
import com.example.qrdz4162.pixformance.moviedetails.MovieDetails;
import com.example.qrdz4162.pixformance.movies.adapter.MovieListAdapter;
import com.example.qrdz4162.pixformance.movies.model.entitiy.MovieItem;
import com.example.qrdz4162.pixformance.movies.presenter.MoviePresenter;
import com.example.qrdz4162.pixformance.movies.presenter.MoviePresenterImp;
import com.example.qrdz4162.pixformance.utils.DialogUtils;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by qrdz4162 on 2/7/2018.
 */

public class MovieFragment extends BaseFragment implements MovieView, MovieListAdapter.MovieClickListener {

    @BindView(R.id.movie_search_button)
    ImageView movie_search_button;

    @BindView(R.id.movie_search_et)
    EditText movie_search_et;

    @BindView(R.id.movies_recycler_view)
    RecyclerView movies_recycler_view;

    @BindView(R.id.progressBar)
    ProgressBar mProgressBar;

    @BindView(R.id.v_reload)
    View mReloadView;

    @BindView(R.id.no_movie_found)
    View mNoMovieView;

    String movieName;

    // declare product list components
    private RecyclerView.Adapter movieListAdapter;
    private RecyclerView.LayoutManager movieLayoutManager;
    private ArrayList<MovieItem> movieList;

    // declare main presenter
    private MoviePresenter moviePresenter;

    public MovieFragment() {

    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_movie, container, false);
        ButterKnife.bind(this, rootView);

        return rootView;
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {

        // initialize recyclerView
        movies_recycler_view.setHasFixedSize(true);
        movieLayoutManager = new LinearLayoutManager(getActivity());
        movies_recycler_view.setLayoutManager(movieLayoutManager);

        DividerItemDecoration dividerItemDecoration = new DividerItemDecoration(movies_recycler_view.getContext(),
                DividerItemDecoration.VERTICAL);
        movies_recycler_view.addItemDecoration(dividerItemDecoration);

        movieList = new ArrayList<>();
        movieListAdapter = new MovieListAdapter(movieList, this);
        movies_recycler_view.setAdapter(movieListAdapter);

        initializePresenter();
    }

    @OnClick(R.id.movie_search_button)
    public void getSearchMovie(){
        movieName = movie_search_et.getText().toString().trim();
        moviePresenter.loadMovies(movieName);
    }

    private void initializePresenter() {
        moviePresenter = new MoviePresenterImp(this, Injection.provideMovieListRepo());
    }

    @Override
    public void showProgressLoading() {
        mProgressBar.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideProgressLoading() {

        mProgressBar.setVisibility(View.GONE);
    }

    @Override
    public void showInlineError(String error) {
        DialogUtils.getSnackBar(getView(), error, null, null).show();
    }

    @Override
    public void showInlineConnectionError() {

        mReloadView.setVisibility(View.VISIBLE);
        mReloadView.findViewById(R.id.rl_reload).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mReloadView.setVisibility(View.GONE);
                moviePresenter.loadMovies(movieName);
            }
        });
    }

    @Override
    public void loadMovieList(List<MovieItem> movies) {
        movieList.clear();
        movieListAdapter.notifyDataSetChanged();

        movieList.addAll(movies);
        movieListAdapter.notifyDataSetChanged();
    }

    @Override
    public void loadNoMovieFoundView() {
        mNoMovieView.setVisibility(View.VISIBLE);
    }

    @Override
    public void onMovieClick(int position, View v) {

        Intent movieDetailsIntent = new Intent(getActivity(), MovieDetails.class);
        movieDetailsIntent.putExtra("movie_object",new MovieItem(movieList.get(position)));
        ActivityOptionsCompat options = ActivityOptionsCompat.makeSceneTransitionAnimation(getActivity(),
                v,   // Starting view
                getString(R.string.transition_name));
        ActivityCompat.startActivity(getActivity(), movieDetailsIntent, options.toBundle());
        startActivity(movieDetailsIntent);
    }

    @Override
    public void onResume() {
        super.onResume();
        moviePresenter.onViewAttached(this);
    }

    @Override
    public void onStop() {
        super.onStop();
        moviePresenter.onViewDetached();
    }
}
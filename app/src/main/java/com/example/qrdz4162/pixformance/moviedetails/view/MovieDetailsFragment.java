package com.example.qrdz4162.pixformance.moviedetails.view;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.ActivityCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.RatingBar;
import android.widget.TextView;

import com.example.qrdz4162.pixformance.R;
import com.example.qrdz4162.pixformance.application.PixFormanceApp;
import com.example.qrdz4162.pixformance.base.injection.Injection;
import com.example.qrdz4162.pixformance.base.view.BaseFragment;
import com.example.qrdz4162.pixformance.moviedetails.MovieDetails;
import com.example.qrdz4162.pixformance.movies.model.entitiy.MovieItem;
import com.example.qrdz4162.pixformance.movies.presenter.MoviePresenterImp;
import com.example.qrdz4162.pixformance.utils.DialogUtils;
import com.squareup.picasso.Picasso;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by qrdz4162 on 2/9/2018.
 */

public class MovieDetailsFragment extends BaseFragment implements MovieDetailsView {

    @BindView(R.id.textView_movie_name)
    TextView movieName;

    @BindView(R.id.textView_movie_overview)
    TextView movieOverView;

    @BindView(R.id.textView_movie_vote_count)
    TextView movieVoteCount;

    @BindView(R.id.imageView_movie_detail_thumbnail)
    ImageView moviePoster;

    @BindView(R.id.ratingBar)
    RatingBar movieCountAverage;

    @BindView(R.id.progressBarMovieView)
    ProgressBar progressBarMovieView;

    @BindView(R.id.v_reload)
    View mReloadMovieView;

    @BindView(R.id.fab_movie_list)
    FloatingActionButton movieListButton;


    MovieItem movieItem;

    public MovieDetailsFragment(){}

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.fragment_details, container, false);
        ButterKnife.bind(this, rootView);



        return rootView;
    }

    @OnClick(R.id.fab_movie_list)
    public void backToMovieList(){
        getActivity().finish();
    }


    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {

        if (getArguments().containsKey("movie_object")) {
            movieItem = getArguments().getParcelable("movie_object");
        }

        showMovieDetails(movieItem);
    }


    @Override
    public void showProgressLoading(){
    }

    @Override
    public void hideProgressLoading() {}

    @Override
    public void showInlineError(String error) {
        DialogUtils.getSnackBar(getView(), error, null, null).show();
    }

    @Override
    public void showInlineConnectionError() {
    }



    @Override
    public void showMovieDetails(MovieItem movieItem) {
        movieName.setText(movieItem.getTitle());
        movieCountAverage.setRating(movieItem.getVote_average()/2);
        movieVoteCount.setText(movieItem.getVote_count()+"");
        movieOverView.setText(movieItem.getOverview());
        Picasso.with(getActivity().getApplicationContext())
                .load(PixFormanceApp.BASE_IMAGE_URL + movieItem.getPoster_path())
                .error(R.drawable.ic_product_placeholder)
                .fit()
                .into(moviePoster);
    }

    @Override
    public void onResume() {
        super.onResume();
    }

    @Override
    public void onStop() {
        super.onStop();
    }

}

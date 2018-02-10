package com.example.qrdz4162.pixformance.movies.presenter;

import android.util.Log;

import com.example.qrdz4162.pixformance.R;
import com.example.qrdz4162.pixformance.base.presenter.BasePresenter;
import com.example.qrdz4162.pixformance.base.view.BaseView;
import com.example.qrdz4162.pixformance.movies.model.MovieListRepo;
import com.example.qrdz4162.pixformance.movies.model.entitiy.MovieItem;
import com.example.qrdz4162.pixformance.movies.view.MovieView;
import com.example.qrdz4162.pixformance.utils.TextUtils;

import java.net.UnknownHostException;
import java.util.List;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.annotations.NonNull;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import io.reactivex.observers.DisposableObserver;
import io.reactivex.schedulers.Schedulers;
import retrofit2.HttpException;

/**
 * Created by qrdz4162 on 2/7/2018.
 */

public class MoviePresenterImp implements MoviePresenter{

    private MovieView movieView;
    private MovieListRepo movieListRepo;
    private CompositeDisposable mCompositeDisposable;
    private boolean isViewAttached;


    public MoviePresenterImp (MovieView movieView, MovieListRepo movieListRepo){
        this.movieView = movieView;
        this.movieListRepo = movieListRepo;
        mCompositeDisposable = new CompositeDisposable();
    }

    @Override
    public void loadMovies(String searchInput) {

        movieView.showProgressLoading();

        Disposable disposable = movieListRepo.getMovies(searchInput)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(new DisposableObserver<List<MovieItem>>() {
                    @Override
                    public void onNext(@NonNull List<MovieItem> movieItems) {
                        if (isViewAttached) {
                            if(movieItems.size() > 0)
                                movieView.loadMovieList(movieItems);
                            else
                                movieView.loadNoMovieFoundView();
                        }
                    }

                    @Override
                    public void onError(@NonNull Throwable throwable) {

                        // Handle No Internet Connection
                        if (throwable instanceof UnknownHostException) {
                            if (isViewAttached) {
                                movieView.hideProgressLoading();
                                movieView.showInlineConnectionError();
                            }
                        }
                        // Handle HTTP Exceptions
                        else if (throwable instanceof HttpException) {
                            HttpException exception = (HttpException) throwable;
                            switch (exception.code()) {

                                case 500:
                                    // Handle code 500
                                    break;
                                default:
                                    break;
                            }
                        }
                        // Handle Unknown Exception
                        else {
                            if (isViewAttached) {
                                Log.e("ssss", throwable.getMessage().toString());
                                movieView.hideProgressLoading();
                                movieView.showInlineError(TextUtils.getString(R.string.unknown_error));
                            }
                        }


                    }

                    @Override
                    public void onComplete() {
                        if (isViewAttached) {
                            movieView.hideProgressLoading();
                        }
                    }
                });

        mCompositeDisposable.add(disposable);

    }


    @Override
    public void onViewAttached(BaseView view) {
        isViewAttached = true;
    }

    @Override
    public void onViewDetached() {
        isViewAttached = false;
        mCompositeDisposable.clear();
    }
}

package com.example.qrdz4162.pixformance.movies.presenter;

import android.content.Context;

import com.example.qrdz4162.pixformance.R;
import com.example.qrdz4162.pixformance.base.view.BaseView;
import com.example.qrdz4162.pixformance.movies.model.MovieListRepo;
import com.example.qrdz4162.pixformance.movies.model.entitiy.MovieItem;
import com.example.qrdz4162.pixformance.movies.model.entitiy.SearchQuery;
import com.example.qrdz4162.pixformance.movies.view.MovieView;
import com.example.qrdz4162.pixformance.utils.TextUtils;

import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.List;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.annotations.NonNull;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import io.reactivex.observers.DisposableObserver;
import io.reactivex.schedulers.Schedulers;
import io.realm.Realm;
import io.realm.RealmResults;
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
    public void loadMovies(final String searchInput) {

        movieView.showProgressLoading();

        Disposable disposable = movieListRepo.getMovies(searchInput)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(new DisposableObserver<List<MovieItem>>() {
                    @Override
                    public void onNext(@NonNull List<MovieItem> movieItems) {
                        if (isViewAttached) {
                            if(movieItems.size() > 0){
                                movieListRepo.addQueryToDB(searchInput);
                                movieView.loadMovieList(movieItems);
                            }
                            else
                                movieView.NoMovieFoundView();
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
    public ArrayList<SearchQuery> getRecentQueries() {
        return movieListRepo.getRecentQueries();
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

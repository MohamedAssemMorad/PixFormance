package com.example.qrdz4162.pixformance.movies.model.repo.remote;

import com.example.qrdz4162.pixformance.application.PixFormanceApp;
import com.example.qrdz4162.pixformance.movies.model.entitiy.MovieItem;
import com.example.qrdz4162.pixformance.movies.model.entitiy.MovieResponse;
import com.example.qrdz4162.pixformance.movies.model.entitiy.SearchQuery;
import com.example.qrdz4162.pixformance.movies.model.repo.MovieListDataSource;
import com.example.qrdz4162.pixformance.network.RetrofitClient;
import com.example.qrdz4162.pixformance.network.service.MovieApiService;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.Observable;
import io.reactivex.annotations.NonNull;
import io.reactivex.functions.Function;

/**
 * Created by qrdz4162 on 2/7/2018.
 */

public class MovieListRemoteDataSource implements MovieListDataSource {

    private static MovieListRemoteDataSource movieListDataSource;
    private final MovieApiService movieApiService;

    // prevent direct instantiation
    private MovieListRemoteDataSource (){
        movieApiService = RetrofitClient.getRetrofitInstance().create(MovieApiService.class);
    }

    // MovieListRemoteDataSource single instance instantiation using singleton design pattern
    public static MovieListRemoteDataSource getInstance(){
        if(movieListDataSource == null){
            movieListDataSource = new MovieListRemoteDataSource();
        }

        return movieListDataSource;
    }


    @Override
    public Observable<List<MovieItem>> getMovies(String searchInput) {
        return movieApiService.getSearchMovies(PixFormanceApp.API_KEY, searchInput)
                .map(new Function<MovieResponse, MovieResponse>() {
                    @Override
                    public MovieResponse apply(@NonNull MovieResponse movieResponse) throws Exception {
                        return movieResponse;
                    }
                })
                .map(new Function<MovieResponse, List<MovieItem>>() {
                    @Override
                    public List<MovieItem> apply(@NonNull MovieResponse movieResponse) throws Exception {
                        return movieResponse.getResults();
                    }
                });
    }

    @Override
    public ArrayList<SearchQuery> getRecentQueries() {
        return null;
    }

    @Override
    public void addQueryToDB(String query) {

    }
}

package com.example.qrdz4162.pixformance.movies.model;

import android.content.Context;

import com.example.qrdz4162.pixformance.movies.model.entitiy.MovieItem;
import com.example.qrdz4162.pixformance.movies.model.entitiy.SearchQuery;
import com.example.qrdz4162.pixformance.movies.model.repo.MovieListDataSource;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.Observable;

/**
 * Created by qrdz4162 on 2/7/2018.
 */

public class MovieListRepo implements MovieListDataSource{

    private static MovieListRepo INSTANCE = null;
    private final MovieListDataSource movieListRemoteDataSource;
    private final MovieListDataSource searchQueryLocalDataSource;

    // Prevent direct instantiation.
    private MovieListRepo(MovieListDataSource movieListRemoteDataSource, MovieListDataSource searchQueryLocalDataSource) {
        this.movieListRemoteDataSource = movieListRemoteDataSource;
        this.searchQueryLocalDataSource = searchQueryLocalDataSource;
    }

    /**
     * Returns the single instance of this class, creating it if necessary.
     *
     * @param movieListRemoteDataSource the backend data source
     * @return the {@link MovieListRepo} instance
     */
    public static MovieListRepo getInstance(MovieListDataSource movieListRemoteDataSource,
                                            MovieListDataSource searchQueryLocalDataSource) {
        if (INSTANCE == null) {
            INSTANCE = new MovieListRepo(movieListRemoteDataSource, searchQueryLocalDataSource);
        }
        return INSTANCE;
    }


    @Override
    public Observable<List<MovieItem>> getMovies(String searchInput) {
        return movieListRemoteDataSource.getMovies(searchInput);
    }

    @Override
    public ArrayList<SearchQuery> getRecentQueries() {
        return searchQueryLocalDataSource.getRecentQueries();
    }

    @Override
    public void addQueryToDB(String query) {
        searchQueryLocalDataSource.addQueryToDB(query);
    }
}

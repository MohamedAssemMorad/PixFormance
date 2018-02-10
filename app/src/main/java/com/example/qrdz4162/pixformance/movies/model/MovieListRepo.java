package com.example.qrdz4162.pixformance.movies.model;

import com.example.qrdz4162.pixformance.movies.model.entitiy.MovieItem;
import com.example.qrdz4162.pixformance.movies.model.repo.MovieListDataSource;

import java.util.List;

import io.reactivex.Observable;

/**
 * Created by qrdz4162 on 2/7/2018.
 */

public class MovieListRepo implements MovieListDataSource{

    private static MovieListRepo INSTANCE = null;
    private final MovieListDataSource movieListRemoteDataSource;

    // Prevent direct instantiation.
    private MovieListRepo(MovieListDataSource movieListRemoteDataSource) {
        this.movieListRemoteDataSource = movieListRemoteDataSource;
    }

    /**
     * Returns the single instance of this class, creating it if necessary.
     *
     * @param movieListRemoteDataSource the backend data source
     * @return the {@link MovieListRepo} instance
     */
    public static MovieListRepo getInstance(MovieListDataSource movieListRemoteDataSource) {
        if (INSTANCE == null) {
            INSTANCE = new MovieListRepo(movieListRemoteDataSource);
        }
        return INSTANCE;
    }


    @Override
    public Observable<List<MovieItem>> getMovies(String searchInput) {
        return movieListRemoteDataSource.getMovies(searchInput);
    }
}

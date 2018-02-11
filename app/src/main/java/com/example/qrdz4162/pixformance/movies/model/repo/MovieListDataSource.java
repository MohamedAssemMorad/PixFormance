package com.example.qrdz4162.pixformance.movies.model.repo;

import com.example.qrdz4162.pixformance.movies.model.entitiy.MovieItem;

import java.util.List;

import io.reactivex.Observable;

/**
 * Created by qrdz4162 on 2/7/2018.
 */

public interface MovieListDataSource {

    /**
     * @desc get list of movies from specific repo
     * @param searchInput string holds user input in search bar
     * @return returns list of MovieItem
     */
    Observable<List<MovieItem>> getMovies(String searchInput);
}

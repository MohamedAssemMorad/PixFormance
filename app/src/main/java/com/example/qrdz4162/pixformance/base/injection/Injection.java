package com.example.qrdz4162.pixformance.base.injection;

import com.example.qrdz4162.pixformance.movies.model.MovieListRepo;
import com.example.qrdz4162.pixformance.movies.model.repo.MovieListRemoteDataSource;

/**
 * Created by qrdz4162 on 2/8/2018.
 */

public class Injection {

    /**
     * Enables injection of mock implementations for
     * {@link MovieListRepo} at compile time. This is useful to isolate the dependencies.
     */
    public static MovieListRepo provideMovieListRepo() {
        return MovieListRepo.getInstance(MovieListRemoteDataSource.getInstance());
    }


}

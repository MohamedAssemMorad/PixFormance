package com.example.qrdz4162.pixformance.network.service;

import com.example.qrdz4162.pixformance.movies.model.entitiy.MovieItem;
import com.example.qrdz4162.pixformance.movies.model.entitiy.MovieResponse;

import java.util.List;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * Created by qrdz4162 on 2/7/2018.
 */

public interface MovieApiService {

    @GET("search/movie")
    Observable<MovieResponse> getSearchMovies(@Query("api_key") String api_key,
                                              @Query("query") String query);
}

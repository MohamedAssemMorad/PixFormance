package com.example.qrdz4162.pixformance.movies.view;

import com.example.qrdz4162.pixformance.base.view.BaseView;
import com.example.qrdz4162.pixformance.movies.model.entitiy.MovieItem;

import java.util.List;

/**
 * Created by qrdz4162 on 2/7/2018.
 */

public interface MovieView extends BaseView{

    void loadMovieList(List<MovieItem> movies);
    void loadNoMovieFoundView();
}

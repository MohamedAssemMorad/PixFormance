package com.example.qrdz4162.pixformance.movies.view;

import com.example.qrdz4162.pixformance.base.view.BaseView;
import com.example.qrdz4162.pixformance.movies.model.entitiy.MovieItem;

import java.util.List;

/**
 * Created by qrdz4162 on 2/7/2018.
 */

public interface MovieView extends BaseView{

    /**
     * @desc display list of movies returned from presenter in recyclerView
     * @param movies list of MovieItem holds movie data needed
     */
    void loadMovieList(List<MovieItem> movies);

    // display msg when list of movies returned is empty (when user enters wrong movie name)
    void NoMovieFoundView();
}

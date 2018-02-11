package com.example.qrdz4162.pixformance.moviedetails.view;

import com.example.qrdz4162.pixformance.base.view.BaseView;
import com.example.qrdz4162.pixformance.movies.model.entitiy.MovieItem;


/**
 * Created by qrdz4162 on 2/9/2018.
 */

public interface MovieDetailsView extends BaseView{

    /**
     * @desc display movie details on movie details screen
     * @param movieItems MovieItem object that holds movie data  to be displayed on screen
     *                   ex(title, posterPath, overView, ......)
     */
    void showMovieDetails(MovieItem movieItems);
}

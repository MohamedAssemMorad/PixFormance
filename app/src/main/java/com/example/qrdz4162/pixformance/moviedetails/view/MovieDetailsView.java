package com.example.qrdz4162.pixformance.moviedetails.view;

import com.example.qrdz4162.pixformance.base.view.BaseView;
import com.example.qrdz4162.pixformance.movies.model.entitiy.MovieItem;


/**
 * Created by qrdz4162 on 2/9/2018.
 */

public interface MovieDetailsView extends BaseView{
    void showMovieDetails(MovieItem movieItems);
}

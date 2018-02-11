package com.example.qrdz4162.pixformance.movies.presenter;

import com.example.qrdz4162.pixformance.base.presenter.BasePresenter;

/**
 * Created by qrdz4162 on 2/7/2018.
 */

public interface MoviePresenter extends BasePresenter{

    /**
     * @desc load movies list from model
     * @param searchInput string holds user input in search bar
     */
    void loadMovies(String searchInput);
}

package com.example.qrdz4162.pixformance.movies.presenter;

import com.example.qrdz4162.pixformance.base.presenter.BasePresenter;
import com.example.qrdz4162.pixformance.movies.model.entitiy.SearchQuery;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by qrdz4162 on 2/7/2018.
 */

public interface MoviePresenter extends BasePresenter{

    /**
     * @desc load movies list from model
     * @param searchInput string holds user input in search bar
     */
    void loadMovies(String searchInput);
    ArrayList<SearchQuery> getRecentQueries();
}

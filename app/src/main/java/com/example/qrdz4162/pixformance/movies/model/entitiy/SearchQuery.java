package com.example.qrdz4162.pixformance.movies.model.entitiy;

import io.realm.RealmObject;

/**
 * Created by qrdz4162 on 2/11/2018.
 */

public class SearchQuery extends RealmObject{

    public void setQueryName(String queryName) {
        this.queryName = queryName;
    }

    private String queryName;

    public SearchQuery() {}

    public String getQueryName() {
        return this.queryName;
    }
}

package com.example.qrdz4162.pixformance.movies.model.entitiy;

import java.security.Timestamp;
import java.util.Date;

import io.realm.RealmObject;

/**
 * Created by qrdz4162 on 2/11/2018.
 */

public class SearchQuery extends RealmObject{

    public void setQueryName(String queryName) {
        this.queryName = queryName;
    }

    private String queryName;

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    private Date date;

    public SearchQuery() {}

    public String getQueryName() {
        return this.queryName;
    }
}

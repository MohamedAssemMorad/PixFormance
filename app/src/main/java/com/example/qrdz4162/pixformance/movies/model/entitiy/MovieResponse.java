package com.example.qrdz4162.pixformance.movies.model.entitiy;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

/**
 * Created by qrdz4162 on 2/7/2018.
 */

public class MovieResponse {

    @SerializedName("results")
    private ArrayList<MovieItem> results;

    public ArrayList<MovieItem> getResults() {
        return results;
    }

    public void setResults(ArrayList<MovieItem> results) {
        this.results = results;
    }

    public int getPage() {
        return page;
    }

    public void setPage(int page) {
        this.page = page;
    }

    public int getTotal_results() {
        return total_results;
    }

    public void setTotal_results(int total_results) {
        this.total_results = total_results;
    }

    public int getTotal_pages() {
        return total_pages;
    }

    public void setTotal_pages(int total_pages) {
        this.total_pages = total_pages;
    }

    @SerializedName("page")
    private int page;

    @SerializedName("total_results")
    private int total_results;

    @SerializedName("total_pages")
    private int total_pages;


}

package com.example.qrdz4162.pixformance.movies.model.repo.local;

import android.content.Context;

import com.example.qrdz4162.pixformance.movies.model.entitiy.MovieItem;
import com.example.qrdz4162.pixformance.movies.model.entitiy.SearchQuery;
import com.example.qrdz4162.pixformance.movies.model.repo.MovieListDataSource;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import io.reactivex.Observable;
import io.realm.Realm;
import io.realm.RealmResults;
import io.realm.Sort;

/**
 * Created by qrdz4162 on 2/11/2018.
 */

public class SearchQueryLocalDataSource implements MovieListDataSource {

    private static SearchQueryLocalDataSource searchQueryDataSource;
    private Realm realm;

    // prevent direct instantiation
    private SearchQueryLocalDataSource(Context context) {
        realm.init(context);
        realm = Realm.getDefaultInstance();
    }

    // Realm single instance instantiation using singleton design pattern
    public static SearchQueryLocalDataSource getInstance(Context context) {
        if (searchQueryDataSource == null) {
            searchQueryDataSource = new SearchQueryLocalDataSource(context);
        }

        return searchQueryDataSource;
    }

    @Override
    public Observable<List<MovieItem>> getMovies(String searchInput) {
        return null;
    }

    /**
     * @desc get user last 10 queries (suggestion list) from db using realm
     * @param
     * @return list of object of SearchQuery
     */
    @Override
    public ArrayList<SearchQuery> getRecentQueries() {
        ArrayList<SearchQuery> res = new ArrayList<>();
        realm.beginTransaction();
        RealmResults<SearchQuery> result = realm.where(SearchQuery.class).distinct("queryName");
        result.sort("date", Sort.DESCENDING);
        for (int i = 0; i < result.size(); i++) {
            if (i >= 10)
                break;

            res.add(result.get(i));
        }
        realm.commitTransaction();
        return res;
    }


    /**
     * @desc add search user input to db using realm
     * @param query search input from user
     */
    @Override
    public void addQueryToDB(final String query) {

        // Add a query
        realm.beginTransaction();
        SearchQuery searchQuery = realm.createObject(SearchQuery.class);
        searchQuery.setQueryName(query);
        searchQuery.setDate(Calendar.getInstance().getTime());
        realm.commitTransaction();

    }
}

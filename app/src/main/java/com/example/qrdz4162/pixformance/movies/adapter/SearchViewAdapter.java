package com.example.qrdz4162.pixformance.movies.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.qrdz4162.pixformance.R;
import com.example.qrdz4162.pixformance.movies.model.entitiy.SearchQuery;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

/**
 * Created by qrdz4162 on 2/11/2018.
 */

public class SearchViewAdapter extends BaseAdapter {

    // Declare Variables

    Context mContext;
    LayoutInflater inflater;
    private List<SearchQuery> searchNamesList = null;
    private ArrayList<SearchQuery> arraylist;

    public SearchViewAdapter(Context context, List<SearchQuery> searchNamesList) {
        mContext = context;
        this.searchNamesList = searchNamesList;
        inflater = LayoutInflater.from(mContext);
        this.arraylist = new ArrayList<SearchQuery>();
        this.arraylist.addAll(searchNamesList);
    }

    public class ViewHolder {
        TextView name;
    }

    @Override
    public int getCount() {
        return searchNamesList.size();
    }

    @Override
    public SearchQuery getItem(int position) {
        return searchNamesList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    public View getView(final int position, View view, ViewGroup parent) {
        final ViewHolder holder;
        if (view == null) {
            holder = new ViewHolder();
            view = inflater.inflate(R.layout.search_view_item, null);
            // Locate the TextViews in listview_item.xml
            holder.name = (TextView) view.findViewById(R.id.name);
            view.setTag(holder);
        } else {
            holder = (ViewHolder) view.getTag();
        }
        // Set the results into TextViews
        holder.name.setText(searchNamesList.get(position).getQueryName());
        return view;
    }

    // Filter Class
    public void filter(String charText) {
        charText = charText.toLowerCase(Locale.getDefault());
        searchNamesList.clear();
        if (charText.length() == 0) {
            searchNamesList.addAll(arraylist);
        } else {
            for (SearchQuery wp : arraylist) {
                if (wp.getQueryName().toLowerCase(Locale.getDefault()).contains(charText)) {
                    searchNamesList.add(wp);
                }
            }
        }
        notifyDataSetChanged();
    }
}

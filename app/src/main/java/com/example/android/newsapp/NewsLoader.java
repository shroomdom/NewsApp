package com.example.android.newsapp;


import android.content.AsyncTaskLoader;
import android.content.Context;

import java.util.List;

public class NewsLoader extends AsyncTaskLoader<List<News>> {

    public NewsLoader(Context context) {
        super(context);
    }

    @Override
    protected void onStartLoading() {
        forceLoad();
    }

    /**
     * This is on a background thread
     */
    @Override
    public List<News> loadInBackground() {
        List<News> news = QueryUtils.getJsonAPI();
        return news;
    }
}


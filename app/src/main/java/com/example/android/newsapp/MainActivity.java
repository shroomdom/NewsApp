package com.example.android.newsapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.os.AsyncTask;
import android.net.Uri;
import android.app.LoaderManager;
import android.content.Loader;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;


/**
 * Display list of news on main page
 */
public class MainActivity extends AppCompatActivity implements LoaderManager.LoaderCallbacks<List<News>> {

    private ArrayList<News> newsList;
    private ListView listView;
    private NewsAdapter adapter;
    private static final int IDENTIFIER = 1;
    private TextView errorMessageView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        listView = (ListView) findViewById(R.id.activity_main_list_view);
        adapter = new NewsAdapter(this, new ArrayList<News>());

        errorMessageView = (TextView) findViewById(R.id.error_text_view);
        listView.setEmptyView(errorMessageView);

        // Set the adapter on the ListView
        // so the list can be populated in the user interface
        listView.setAdapter(adapter);

        // Set onClickListener to the list on news root page and opens other news articles
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {
                News currentNews = adapter.getItem(position);
                Uri newsUri = Uri.parse(currentNews.getURL());
                Intent websiteIntent = new Intent(Intent.ACTION_VIEW, newsUri);
                startActivity(websiteIntent);
            }
        });

        LoaderManager loaderManager = getLoaderManager();
        loaderManager.initLoader(IDENTIFIER, null, this);
    }

    @Override
    public Loader<List<News>> onCreateLoader(int i, Bundle bundle) {

        // Create a NewsLoader with the request URL
        return new NewsLoader(this);
    }

    @Override
    public void onLoadFinished(Loader<List<News>> loader, List<News> newsIncomingList) {

        // Clear the adapter of previous news data
        adapter.clear();

        // System.out.println("start of loader");
        // Add news to data if incoming news list isn't empty
        // If incoming news list is empty, return error message
        if (newsIncomingList != null) {
            // System.out.println("check if list is empty");
            if (newsIncomingList.isEmpty()) {
                errorMessageView.setText(R.string.error_finding_news);
                // System.out.println("this list is empty");
            } else {
                // System.out.println("list is not empty");
                adapter.addAll(newsIncomingList);
            }
        } else {
            // System.out.println("list is null");
            errorMessageView.setText(R.string.error_finding_news);
        }
    }

    @Override
    public void onLoaderReset(Loader<List<News>> loader) {
        // Reset loader so we can clear out our existing data from the adapter
        adapter.clear();
    }

    @Override
    protected void onResume() {
        super.onResume();

        // Get a reference to the LoaderManager, in order to interact with loaders
        LoaderManager loaderManager = getLoaderManager();

        // Restart the loader. Pass in the int ID constant defined above and pass in null for
        // the bundle. Pass in this activity for the LoaderCallbacks parameter (which is valid
        // because this activity implements the LoaderCallbacks interface).//
        loaderManager.restartLoader(IDENTIFIER, null, this);
    }
}



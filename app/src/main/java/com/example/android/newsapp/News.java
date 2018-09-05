package com.example.android.newsapp;

public class News {
    private String mTitle;
    private String mCategory;
    private String mDatePublished;
    private String mURL;

    /**
     * Create a new News object
     */
    public News(String title, String category, String datePublished, String url) {
        mTitle = title;
        mCategory = category;
        mDatePublished = datePublished;
        mURL = url;
    }

    /**
     * Get the article title.
     */
    public String getTitle() {
        return mTitle;
    }

    /**
     * Get the article category.
     */
    public String getCategory() {
        return mCategory;
    }

    /**
     * Get the article publish date.
     */
    public String getDatePublished() {
        return mDatePublished;
    }

    /**
     * Get the article URL.
     */
    public String getURL() {
        return mURL;
    }
}


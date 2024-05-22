package com.example.barangayservicehub.getter_class;

public class Get_News {

    private String newsTitle, newsArticle, newsDateAdded, newsImageURL;

    public Get_News(){

    }

    public Get_News(String newsTitle, String newsArticle, String newsDateAdded, String newsImageURL) {
        this.newsTitle = newsTitle;
        this.newsArticle = newsArticle;
        this.newsDateAdded = newsDateAdded;
        this.newsImageURL = newsImageURL;
    }

    public String getNewsTitle() {
        return newsTitle;
    }

    public String getNewsArticle() {
        return newsArticle;
    }

    public String getNewsDateAdded() {
        return newsDateAdded;
    }

    public String getNewsImageURL() {
        return newsImageURL;
    }
}

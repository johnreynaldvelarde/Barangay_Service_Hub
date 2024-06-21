package com.example.barangayservicehub.setter_class;

public class Set_News {

    String userID , newsTitle, newsArticle, newsImageURL, newsDateAdded;
    int newsStatus, isImportant, archive;

    public Set_News(String userID, String newsTitle, String newsArticle, String newsImageURL, String newsDateAdded, int newsStatus, int isImportant, int archive) {
        this.userID = userID;
        this.newsTitle = newsTitle;
        this.newsArticle = newsArticle;
        this.newsImageURL = newsImageURL;
        this.newsDateAdded = newsDateAdded;
        this.newsStatus = newsStatus;
        this.isImportant = isImportant;
        this.archive = archive;
    }

    public String getUserID() {
        return userID;
    }

    public void setUserID(String userID) {
        this.userID = userID;
    }

    public String getNewsTitle() {
        return newsTitle;
    }

    public void setNewsTitle(String newsTitle) {
        this.newsTitle = newsTitle;
    }

    public String getNewsArticle() {
        return newsArticle;
    }

    public void setNewsArticle(String newsArticle) {
        this.newsArticle = newsArticle;
    }

    public String getNewsImageURL() {
        return newsImageURL;
    }

    public void setNewsImageURL(String newsImageURL) {
        this.newsImageURL = newsImageURL;
    }

    public String getNewsDateAdded() {
        return newsDateAdded;
    }

    public void setNewsDateAdded(String newsDateAdded) {
        this.newsDateAdded = newsDateAdded;
    }

    public int getNewsStatus() {
        return newsStatus;
    }

    public void setNewsStatus(int newsStatus) {
        this.newsStatus = newsStatus;
    }

    public int getIsImportant() {
        return isImportant;
    }

    public void setIsImportant(int isImportant) {
        this.isImportant = isImportant;
    }

    public int getArchive() {
        return archive;
    }

    public void setArchive(int archive) {
        this.archive = archive;
    }
}

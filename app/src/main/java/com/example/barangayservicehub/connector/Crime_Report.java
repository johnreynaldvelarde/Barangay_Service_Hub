package com.example.barangayservicehub.connector;

public class Crime_Report {
    private String userId, title, location, comment, dateAdded, crimeImageURL;
    private int reportStatus;

    public Crime_Report(String userId, String title, String location, String comment, String dateAdded, String crimeImageURL, int reportStatus) {
        this.userId = userId;
        this.title = title;
        this.location = location;
        this.comment = comment;
        this.dateAdded = dateAdded;
        this.crimeImageURL = crimeImageURL;
        this.reportStatus = reportStatus;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public String getDateAdded() {
        return dateAdded;
    }

    public void setDateAdded(String dateAdded) {
        this.dateAdded = dateAdded;
    }

    public String getCrimeImageURL() {
        return crimeImageURL;
    }

    public void setCrimeImageURL(String crimeImageURL) {
        this.crimeImageURL = crimeImageURL;
    }

    public int getReportStatus() {
        return reportStatus;
    }

    public void setReportStatus(int reportStatus) {
        this.reportStatus = reportStatus;
    }
}

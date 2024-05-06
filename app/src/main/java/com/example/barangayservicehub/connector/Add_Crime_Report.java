package com.example.barangayservicehub.connector;

import java.util.Date;

public class Add_Crime_Report {
    private String userId, title, location, comment, dateAdded;
    private int reportStatus;

    public Add_Crime_Report(String userId, String title, String location, String comment, String dateAdded, int reportStatus) {
        this.userId = userId;
        this.title = title;
        this.location = location;
        this.comment = comment;
        this.dateAdded = dateAdded;
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

    public int getReportStatus() {
        return reportStatus;
    }

    public void setReportStatus(int reportStatus) {
        this.reportStatus = reportStatus;
    }
}

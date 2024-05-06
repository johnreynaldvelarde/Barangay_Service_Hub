package com.example.barangayservicehub.connector;

public class Add_Crime_Report {
    private String userId, title, location, comment, date;

    public Add_Crime_Report(String userId, String title, String location, String comment, String date) {
        this.userId = userId;
        this.title = title;
        this.location = location;
        this.comment = comment;
        this.date = date;
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

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }
}

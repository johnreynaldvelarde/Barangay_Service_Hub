package com.example.barangayservicehub.getter_class;

import com.google.firebase.database.IgnoreExtraProperties;

@IgnoreExtraProperties
public class Get_CrimeReport {

    private String getReportID, getTitle, getLocation, getComment, getDate, getReportImageURL;
    private int getStatus;

    public Get_CrimeReport(){

    }

    public Get_CrimeReport(String getReportID, String getTitle, String getLocation, String getComment, String getDate, String getReportImageURL, int getStatus) {
        this.getReportID = getReportID;
        this.getTitle = getTitle;
        this.getLocation = getLocation;
        this.getComment = getComment;
        this.getDate = getDate;
        this.getReportImageURL = getReportImageURL;
        this.getStatus = getStatus;
    }

    public String getGetReportID() {
        return getReportID;
    }

    public String getGetTitle() {
        return getTitle;
    }

    public String getGetLocation() {
        return getLocation;
    }

    public String getGetComment() {
        return getComment;
    }

    public String getGetDate() {
        return getDate;
    }

    public String getGetReportImageURL() {
        return getReportImageURL;
    }

    public int getGetStatus() {
        return getStatus;
    }
}

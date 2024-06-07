package com.example.barangayservicehub.getter_class;

import com.google.firebase.database.IgnoreExtraProperties;

@IgnoreExtraProperties
public class Get_CrimeReport {

    //private String getReportID, getUserID, getTitle, getLocation, getComment, getDate, getReportImageURL;
    private String title, dateAdded, crimeImageURL, comment;

    private  int reportStatus;

    public Get_CrimeReport(){

    }

    public Get_CrimeReport(String title, String dateAdded, String crimeImageURL, String comment, int reportStatus) {
        this.title = title;
        this.dateAdded = dateAdded;
        this.crimeImageURL = crimeImageURL;
        this.comment = comment;
        this.reportStatus = reportStatus;
    }

    public String getTitle() {
        return title;
    }

    public String getDateAdded() {
        return dateAdded;
    }

    public String getCrimeImageURL() {
        return crimeImageURL;
    }

    public String getComment() {
        return comment;
    }

    public int getReportStatus() {
        return reportStatus;
    }
}

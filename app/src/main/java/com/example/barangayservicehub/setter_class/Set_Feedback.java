package com.example.barangayservicehub.setter_class;

public class Set_Feedback {

    String userID, feedbackTitle, feedbackRating, feedbackComment, dateAdded;
    int isRead;

    public Set_Feedback(String userID, String feedbackTitle, String feedbackRating, String feedbackComment, String dateAdded, int isRead) {
        this.userID = userID;
        this.feedbackTitle = feedbackTitle;
        this.feedbackRating = feedbackRating;
        this.feedbackComment = feedbackComment;
        this.dateAdded = dateAdded;
        this.isRead = isRead;
    }

    public String getUserID() {
        return userID;
    }

    public void setUserID(String userID) {
        this.userID = userID;
    }

    public String getFeedbackTitle() {
        return feedbackTitle;
    }

    public void setFeedbackTitle(String feedbackTitle) {
        this.feedbackTitle = feedbackTitle;
    }

    public String getFeedbackRating() {
        return feedbackRating;
    }

    public void setFeedbackRating(String feedbackRating) {
        this.feedbackRating = feedbackRating;
    }

    public String getFeedbackComment() {
        return feedbackComment;
    }

    public void setFeedbackComment(String feedbackComment) {
        this.feedbackComment = feedbackComment;
    }

    public String getDateAdded() {
        return dateAdded;
    }

    public void setDateAdded(String dateAdded) {
        this.dateAdded = dateAdded;
    }

    public int getIsRead() {
        return isRead;
    }

    public void setIsRead(int isRead) {
        this.isRead = isRead;
    }
}

package com.example.barangayservicehub.connector;

public class Service_Request {

    String  userID, serviceID, requestName, requestAddress , requestDateAdded, requestPurpose;
    int requestAge, requestStatus, archive;

    public Service_Request(String userID, String serviceID, String requestName, String requestAddress, String requestDateAdded, String requestPurpose, int requestAge, int requestStatus, int archive) {
        this.userID = userID;
        this.serviceID = serviceID;
        this.requestName = requestName;
        this.requestAddress = requestAddress;
        this.requestDateAdded = requestDateAdded;
        this.requestPurpose = requestPurpose;
        this.requestAge = requestAge;
        this.requestStatus = requestStatus;
        this.archive = archive;
    }

    public String getUserID() {
        return userID;
    }

    public void setUserID(String userID) {
        this.userID = userID;
    }

    public String getServiceID() {
        return serviceID;
    }

    public void setServiceID(String serviceID) {
        this.serviceID = serviceID;
    }

    public String getRequestName() {
        return requestName;
    }

    public void setRequestName(String requestName) {
        this.requestName = requestName;
    }

    public String getRequestAddress() {
        return requestAddress;
    }

    public void setRequestAddress(String requestAddress) {
        this.requestAddress = requestAddress;
    }

    public String getRequestDateAdded() {
        return requestDateAdded;
    }

    public void setRequestDateAdded(String requestDateAdded) {
        this.requestDateAdded = requestDateAdded;
    }

    public String getRequestPurpose() {
        return requestPurpose;
    }

    public void setRequestPurpose(String requestPurpose) {
        this.requestPurpose = requestPurpose;
    }

    public int getRequestAge() {
        return requestAge;
    }

    public void setRequestAge(int requestAge) {
        this.requestAge = requestAge;
    }

    public int getRequestStatus() {
        return requestStatus;
    }

    public void setRequestStatus(int requestStatus) {
        this.requestStatus = requestStatus;
    }

    public int getArchive() {
        return archive;
    }

    public void setArchive(int archive) {
        this.archive = archive;
    }
}

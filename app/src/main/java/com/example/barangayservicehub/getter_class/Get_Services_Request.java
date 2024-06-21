package com.example.barangayservicehub.getter_class;

public class Get_Services_Request {

    String requestName, requestPurpose;

    public  Get_Services_Request(){

    }

    public Get_Services_Request(String requestName, String requestPurpose) {
        this.requestName = requestName;
        this.requestPurpose = requestPurpose;
    }

    public String getRequestName() {
        return requestName;
    }

    public String getRequestPurpose() {
        return requestPurpose;
    }
}

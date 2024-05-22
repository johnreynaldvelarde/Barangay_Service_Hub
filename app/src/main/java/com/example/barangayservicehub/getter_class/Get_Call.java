package com.example.barangayservicehub.getter_class;

public class Get_Call {

    private String numberName, contactNumber;

    public  Get_Call(){

    }

    public Get_Call(String numberName, String contactNumber) {
        this.numberName = numberName;
        this.contactNumber = contactNumber;
    }

    public String getNumberName() {
        return numberName;
    }

    public String getContactNumber() {
        return contactNumber;
    }
}

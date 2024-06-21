package com.example.barangayservicehub.setter_class;

public class Set_Number {

    String numberName, contactNumber;
    int availableStatus;

    public Set_Number(String numberName, String contactNumber, int availableStatus) {
        this.numberName = numberName;
        this.contactNumber = contactNumber;
        this.availableStatus = availableStatus;
    }

    public String getNumberName() {
        return numberName;
    }

    public void setNumberName(String numberName) {
        this.numberName = numberName;
    }

    public String getContactNumber() {
        return contactNumber;
    }

    public void setContactNumber(String contactNumber) {
        this.contactNumber = contactNumber;
    }

    public int getAvailableStatus() {
        return availableStatus;
    }

    public void setAvailableStatus(int availableStatus) {
        this.availableStatus = availableStatus;
    }
}

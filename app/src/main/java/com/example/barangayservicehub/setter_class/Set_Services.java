package com.example.barangayservicehub.setter_class;

public class Set_Services {

    String servicesName , dateAdded,  purposeID;
    int archive;

    public Set_Services(String servicesName, String dateAdded, String purposeID, int archive) {
        this.servicesName = servicesName;
        this.dateAdded = dateAdded;
        this.purposeID = purposeID;
        this.archive = archive;
    }

    public String getServicesName() {
        return servicesName;
    }

    public void setServicesName(String servicesName) {
        this.servicesName = servicesName;
    }

    public String getDateAdded() {
        return dateAdded;
    }

    public void setDateAdded(String dateAdded) {
        this.dateAdded = dateAdded;
    }

    public String getPurposeID() {
        return purposeID;
    }

    public void setPurposeID(String purposeID) {
        this.purposeID = purposeID;
    }

    public int getArchive() {
        return archive;
    }

    public void setArchive(int archive) {
        this.archive = archive;
    }
}

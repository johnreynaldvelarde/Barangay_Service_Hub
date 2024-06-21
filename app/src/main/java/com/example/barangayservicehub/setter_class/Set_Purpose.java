package com.example.barangayservicehub.setter_class;

public class Set_Purpose {

    String purposeName, dateAdded;

    int archive;

    public Set_Purpose(String purposeName, String dateAdded, int archive) {
        this.purposeName = purposeName;
        this.dateAdded = dateAdded;
        this.archive = archive;
    }

    public String getPurposeName() {
        return purposeName;
    }

    public void setPurposeName(String purposeName) {
        this.purposeName = purposeName;
    }

    public String getDateAdded() {
        return dateAdded;
    }

    public void setDateAdded(String dateAdded) {
        this.dateAdded = dateAdded;
    }

    public int getArchive() {
        return archive;
    }

    public void setArchive(int archive) {
        this.archive = archive;
    }
}

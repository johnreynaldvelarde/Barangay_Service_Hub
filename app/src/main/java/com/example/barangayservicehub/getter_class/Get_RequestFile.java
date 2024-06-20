package com.example.barangayservicehub.getter_class;

public class Get_RequestFile {

    private String id, fileName, availableStatus;

    public Get_RequestFile(){

    }

    public Get_RequestFile(String id, String fileName, String availableStatus) {
        this.id = id;
        this.fileName = fileName;
        this.availableStatus = availableStatus;
    }

    public String getId() {
        return id;
    }

    public String getFileName() {
        return fileName;
    }

    public String getAvailableStatus() {
        return availableStatus;
    }
}

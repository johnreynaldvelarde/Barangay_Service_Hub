package com.example.barangayservicehub.getter_class;

import java.util.List;

public class Get_RequestFile {

    private String id, fileName, availableStatus;
    private List<String> purposeList;

    public Get_RequestFile(){

    }

    public Get_RequestFile(String id, String fileName, String availableStatus, List<String> purposeList) {
        this.id = id;
        this.fileName = fileName;
        this.availableStatus = availableStatus;
        this.purposeList = purposeList;
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

    public List<String> getPurposeList() {
        return purposeList;
    }
}

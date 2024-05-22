package com.example.barangayservicehub.getter_class;

public class Get_RequestFile {

    private String fileName, fileDescription, fileURL;

    public Get_RequestFile(){

    }

    public Get_RequestFile(String fileName, String fileDescription, String fileURL) {
        this.fileName = fileName;
        this.fileDescription = fileDescription;
        this.fileURL = fileURL;
    }

    public String getFileName() {
        return fileName;
    }

    public String getFileDescription() {
        return fileDescription;
    }

    public String getFileURL() {
        return fileURL;
    }
}

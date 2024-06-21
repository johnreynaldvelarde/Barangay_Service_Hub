package com.example.barangayservicehub.getter_class;

public class Get_Programs {

    private String programName, programDescription;

    public Get_Programs(){

    }

    public Get_Programs(String programName, String programDescription) {
        this.programName = programName;
        this.programDescription = programDescription;
    }

    public String getProgramName() {
        return programName;
    }

    public String getProgramDescription() {
        return programDescription;
    }
}

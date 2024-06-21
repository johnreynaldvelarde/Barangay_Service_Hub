package com.example.barangayservicehub.setter_class;

public class Set_Program {

    String programName, programDescription;
    int availableStatus;

    public Set_Program(String programName, String programDescription, int availableStatus) {
        this.programName = programName;
        this.programDescription = programDescription;
        this.availableStatus = availableStatus;
    }

    public String getProgramName() {
        return programName;
    }

    public void setProgramName(String programName) {
        this.programName = programName;
    }

    public String getProgramDescription() {
        return programDescription;
    }

    public void setProgramDescription(String programDescription) {
        this.programDescription = programDescription;
    }

    public int getAvailableStatus() {
        return availableStatus;
    }

    public void setAvailableStatus(int availableStatus) {
        this.availableStatus = availableStatus;
    }
}

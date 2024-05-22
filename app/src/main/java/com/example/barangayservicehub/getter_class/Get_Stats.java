package com.example.barangayservicehub.getter_class;

public class Get_Stats {

    private String statsName, statsNumber;

    public Get_Stats(){

    }

    public Get_Stats(String statsName, String statsNumber) {
        this.statsName = statsName;
        this.statsNumber = statsNumber;
    }

    public String getStatsName() {
        return statsName;
    }

    public String getStatsNumber() {
        return statsNumber;
    }
}

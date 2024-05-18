package com.example.barangayservicehub.getter_class;

public class Get_Stats {

    private String population, household, establishment, landmark;

    public Get_Stats(){

    }

    public Get_Stats(String population, String household, String establishment, String landmark) {
        this.population = population;
        this.household = household;
        this.establishment = establishment;
        this.landmark = landmark;
    }

    public String getPopulation() {
        return population;
    }

    public String getHousehold() {
        return household;
    }

    public String getEstablishment() {
        return establishment;
    }

    public String getLandmark() {
        return landmark;
    }
}

package com.napier.devops;

public class PeoplePopulation {
    private long totalPopulation;
    private long cityPopulation;
    private long nonCityPopulation;

    private String level;
    private double cityPopulationPercentage;
    private double nonCityPopulationPercentage;

    public PeoplePopulation(String level, long totalPopulation) {
        this.level = level;
        this.totalPopulation = totalPopulation;
    }

    public long getTotalPopulation() {
        return totalPopulation;
    }

    public long getCityPopulation() {
        return cityPopulation;
    }

    public long getNonCityPopulation() {
        return nonCityPopulation;
    }

    public String getLevel() {
        return level;
    }

    public double getCityPopulationPercentage() {
        return cityPopulationPercentage;
    }

    public double getNonCityPopulationPercentage() {
        return nonCityPopulationPercentage;
    }
}

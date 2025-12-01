package com.napier.devops;

public class City {

    // City name
    public String name;

    // Country code
    public String countryCode;

    // District
    public String district;

    // Region
    public String region;

    // Continent
    public String continent;

    // Population
    public int population;

    public City(String name, String countryCode, String district, String region, String continent, int population) {
        this.name = name;
        this.countryCode = countryCode;
        this.district = district;
        this.region = region;
        this.continent = continent;
        this.population = population;
    }

    public String getName() {
        return name;
    }

    public String getCountryCode() {
        return countryCode;
    }

    public String getDistrict() {
        return district;
    }

    public String getRegion() {
        return region;
    }

    public String getContinent() {
        return continent;
    }

    public int getPopulation() {
        return population;
    }
}

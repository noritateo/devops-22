package com.napier.devops;

public class City
{
    // Public fields so existing code & tests still work
    public String name;
    public String countryName;   // keep this for your existing code
    public String countryCode;   // add this for their test
    public String district;
    public String region;
    public String continent;
    public int population;

    // No-arg constructor for cases where you set fields manually
    public City() {}

    // Full constructor used in tests and possibly in your code
    public City(String name,
                String countryCode,
                String district,
                String region,
                String continent,
                int population)
    {
        this.name = name;
        this.countryCode = countryCode;
        this.district = district;
        this.region = region;
        this.continent = continent;
        this.population = population;
    }

    // Getters required by CityTest
    public String getName()
    {
        return name;
    }

    public String getCountryCode()
    {
        return countryCode;
    }

    public String getDistrict()
    {
        return district;
    }

    public String getRegion()
    {
        return region;
    }

    public String getContinent()
    {
        return continent;
    }

    public int getPopulation()
    {
        return population;
    }
}

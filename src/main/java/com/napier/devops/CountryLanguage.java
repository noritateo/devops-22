package com.napier.devops;

public class CountryLanguage
{
    // Existing field
    public String countryCode;
    public String language;
    public boolean isOfficial;
    public float percentage; // % inside a specific country

    // NEW FIELDS (Required for the Language Report)
    public long population;          // Total speakers
    public double worldPercentage;   // % of world population

    // Default Constructor (Required for Unit Tests)
    public CountryLanguage() {
    }

    // Constructor for the Language Report (Required for App.java)
    public CountryLanguage(String language, double population, double worldPercentage) {
        this.language = language;
        this.population = (long) population;
        this.worldPercentage = worldPercentage;
    }
}
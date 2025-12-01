package com.napier.devops;

/**
 * Represents a language spoken in a country.
 * Maps to the CountryLanguage table in the database.
 */
public class CountryLanguage {

    private String language;
    private double percentage;
    private double world_percentage;

    public CountryLanguage(String language,double percentage, double world_percentage) {
        this.language = language;
        this.percentage = percentage;
        this.world_percentage = world_percentage;
    }

    public String getLanguage() {
        return language;
    }

    public double getPercentage() {
        return percentage;
    }

    public double getWorld_percentage() {
        return world_percentage;
    }
}

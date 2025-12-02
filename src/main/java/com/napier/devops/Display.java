package com.napier.devops;

import java.util.ArrayList;

public class Display {

    /**
     * Display all cities
     */
    public void displayAllCities(ArrayList<City> cities) {
        // Check cities is not null
        if (cities == null) {
            System.out.println("No cities");
            return;
        }

        // Check cities is not empty
        if (cities.isEmpty()) {
            System.out.println("No cities found.");
            return;
        }

        // Print header
        System.out.print(String.format(
                "%-25s %-40s %-20s %-30s %-15s %-10s%n",
                "City", "Country", "District", "Region", "Continent", "Population"
        ));

        // Loop over all cities in the list
        for (City city : cities) {
            // Check for null city
            if (city == null) continue;

            String line = String.format(
                    "%-25s %-40s %-20s %-30s %-15s %-10s%n",
                    city.getName(), city.getCountryCode(), city.getDistrict(),
                    city.getRegion(), city.getContinent(), city.getPopulation()
            );
            System.out.print(line);
        }
    }

    /**
     * Display all people population
     */
    public void displayAllPeoplePopulation(ArrayList<PeoplePopulation> peoplePopulation) {
        // Check peoplePopulation is not null
        if (peoplePopulation == null) {
            System.out.println("No population data");
            return;
        }

        // Check peoplePopulation is not empty
        if (peoplePopulation.isEmpty()) {
            System.out.println("No people population found.");
            return;
        }

        // Print header
        System.out.print(String.format(
                "%-40s %-20s%n",
                "Name", "Total Population"
        ));

        // Loop over all population in the list
        for (PeoplePopulation people : peoplePopulation) {
            // Check for null
            if (people == null) continue;

            String line = String.format(
                    "%-40s %-20d%n",
                    people.getLevel(), people.getTotalPopulation()
            );
            System.out.print(line);
        }
    }

    /**
     * Display language report
     */
    public void displaylanguages(ArrayList<CountryLanguage> countryLanguages) {
        // Check countryLanguages is not null
        if (countryLanguages == null) {
            System.out.println("No languages");
            return;
        }

        // Check countryLanguages is not empty
        if (countryLanguages.isEmpty()) {
            System.out.println("No languages found.");
            return;
        }

        // Print header
        System.out.print(String.format(
                "%-20s %-20s %-20s%n",
                "Language", "Total Speakers", "World Percentage"
        ));

        // Loop over all languages in the list
        for (CountryLanguage language : countryLanguages) {
            // Check for null language
            if (language == null) continue;

            String line = String.format(
                    "%-20s %-20d %-5.2f%%%n",
                    language.language,
                    language.population,
                    language.worldPercentage
            );
            System.out.print(line);
        }
    }
}

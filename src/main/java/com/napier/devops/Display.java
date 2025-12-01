package com.napier.devops;

import java.util.ArrayList;

public class Display {
    public void displayAllCities(ArrayList<City> cities) {
        if (cities == null || cities.isEmpty()) {
            System.out.println("No cities found.");
            return;
        }

        System.out.print(String.format(
                "%-25s %-40s %-20s %-30s %-15s %-10s%n",
                "City", "Country", "District", "Region", "Continent", "Population"
        ));

        for (City city : cities) {
            String line = String.format(
                    "%-25s %-40s %-20s %-30s %-15s %-10s%n",
                    city.getName(), city.getCountryCode(), city.getDistrict(), city.getRegion(), city.getContinent(), city.getPopulation()
            );
            System.out.print(line);
        }
    }

    public void displayAllPeoplePopulation(ArrayList<PeoplePopulation> peoplePopulation) {
        if (peoplePopulation == null || peoplePopulation.isEmpty()) {
            System.out.println("No people population found.");
            return;
        }

        System.out.print(String.format(
                "%-40s %-20s%n",
                "Name", "Total Population"
        ));

        for (PeoplePopulation people : peoplePopulation) {
            String line = String.format(
                    "%-40s %-20d%n",
                    people.getLevel(), people.getTotalPopulation()
            );
            System.out.print(line);
        }
    }

    public void displaylanguages(ArrayList<CountryLanguage> countryLanguages) {
        if (countryLanguages == null || countryLanguages.isEmpty()) {
            System.out.println("No people population found.");
            return;
        }

        System.out.print(String.format(
                "%-10s %-20s %-5s%n",
                "Language", "Total Speakers", "World Percentage"
        ));

        for (CountryLanguage language : countryLanguages) {
            String line = String.format(
                    "%-10s %, -20.0f %-5.2f%%%n",
                    language.getLanguage(),
                    language.getPercentage(),
                    language.getWorld_percentage()
            );
            System.out.print(line);
        }
    }
}

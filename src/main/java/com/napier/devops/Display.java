package com.napier.devops;

import java.util.ArrayList;

public class Display {

    public void displayAllCities(ArrayList<City> cities) {
        // Check if the list itself is null or empty [cite: 513-517]
        if (cities == null || cities.isEmpty()) {
            System.out.println("No cities found.");
            return;
        }

        System.out.print(String.format(
                "%-25s %-40s %-20s %-30s %-15s %-10s%n",
                "City", "Country", "District", "Region", "Continent", "Population"
        ));

        for (City city : cities) {
            // FIX: Check if a specific city object in the list is null
            if (city == null) {
                continue;
            }

            String line = String.format(
                    "%-25s %-40s %-20s %-30s %-15s %-10s%n",
                    city.getName(), city.getCountryCode(), city.getDistrict(), city.getRegion(), city.getContinent(), city.getPopulation()
            );
            System.out.print(line);
        }
    }

    public void displayAllPeoplePopulation(ArrayList<PeoplePopulation> peoplePopulation) {
        // Check if the list itself is null or empty
        if (peoplePopulation == null || peoplePopulation.isEmpty()) {
            System.out.println("No people population found.");
            return;
        }

        System.out.print(String.format(
                "%-40s %-20s%n",
                "Name", "Total Population"
        ));

        for (PeoplePopulation people : peoplePopulation) {
            // FIX: Check if a specific object in the list is null
            if (people == null) {
                continue;
            }

            String line = String.format(
                    "%-40s %-20d%n",
                    people.getLevel(), people.getTotalPopulation()
            );
            System.out.print(line);
        }
    }
}
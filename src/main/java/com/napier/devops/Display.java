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
}

package com.napier.devops;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import java.util.ArrayList;

public class DisplayTest
{
    static Display display;

    @BeforeAll
    static void init()
    {
        display = new Display();
    }

    // 1. Test passing null [cite: 436]
    @Test
    void displayCitiesTestNull()
    {
        display.displayAllCities(null);
    }

    // 2. Test passing empty list [cite: 437]
    @Test
    void displayCitiesTestEmpty()
    {
        ArrayList<City> cities = new ArrayList<>();
        display.displayAllCities(cities);
    }

    // 3. Test passing list with null member [cite: 438]
    @Test
    void displayCitiesTestContainsNull()
    {
        ArrayList<City> cities = new ArrayList<>();
        cities.add(null);
        display.displayAllCities(cities);
    }

    // 4. Test passing normal list [cite: 439]
    @Test
    void displayCitiesTestNormal()
    {
        ArrayList<City> cities = new ArrayList<>();
        City city = new City("Yangon", "MMR", "Rangoon", "Southeast Asia", "Asia", 3361700);
        cities.add(city);
        display.displayAllCities(cities);
    }
}
package com.napier.devops;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class MyTest
{
    static App app;

    @BeforeAll
    static void init()
    {
        app = new App();
    }

    /**
     * Test displayAllCountries() handles null
     */
    @Test
    void displayAllCountries_nullList()
    {
        app.displayAllCountries(null);
    }

    /**
     * Test displayAllCountries() handles empty list
     */
    @Test
    void displayAllCountries_emptyList()
    {
        List<Country> empty = new ArrayList<>();
        app.displayAllCountries(empty);
    }

    /**
     * Test displayAllCountries() prints a single country
     */
    @Test
    void displayAllCountries_singleCountry()
    {
        List<Country> list = new ArrayList<>();

        Country c = new Country();
        c.code = "SGP";
        c.name = "Singapore";
        c.continent = "Asia";
        c.region = "Southeast Asia";
        c.population = 5703600;
        c.capitalCity = "Singapore";

        list.add(c);

        app.displayAllCountries(list);
    }

    /**
     * Test getAllCountries() always returns a non-null list.
     */
    @Test
    void getAllCountries_returnsList()
    {
        List<Country> countries = app.getAllCountries();
        assertNotNull(countries, "List should never be null");
    }
}

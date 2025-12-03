package com.napier.devops;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class AppIntegrationTest {

    static App app;

    @BeforeAll
    static void init() {
        app = new App();
        // DB must be running via: docker compose up db
        app.connect("localhost:33060", 30000);
    }

    @Test
    void testGetAllCountriesReturnsData() {
        List<Country> countries = app.getAllCountries();

        assertNotNull(countries, "Countries list should not be null");
        assertFalse(countries.isEmpty(), "Countries list should not be empty");
    }

    @Test
    void testGetSingapore() {
        List<Country> countries = app.getAllCountries();

        assertNotNull(countries);
        assertFalse(countries.isEmpty());

        // Find Singapore in the list
        Country sg = null;
        for (Country c : countries) {
            if ("SGP".equals(c.code)) {
                sg = c;
                break;
            }
        }

        assertNotNull(sg, "Singapore (SGP) should exist in the world dataset");

        // Assert known stable fields
        assertEquals("Singapore", sg.name);
        assertEquals("Asia", sg.continent);
        assertEquals("Southeast Asia", sg.region);
        assertTrue(sg.population > 0, "Population should be greater than zero");

        // Capital city should also be Singapore
        assertEquals("Singapore", sg.capitalCity);
    }

}

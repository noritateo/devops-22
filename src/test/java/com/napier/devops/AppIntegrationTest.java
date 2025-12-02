package com.napier.devops;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.Test;
import java.util.ArrayList;
import static org.junit.jupiter.api.Assertions.*;

public class AppIntegrationTest
{
    static App app;

    @BeforeAll
    static void init()
    {
        app = new App();
        // Connect to localhost:33060 (The port defined in your docker-compose)
        app.connect("localhost:33060", 30000);
    }

    @Test
    void testGetTop10PopulatedCities()
    {
        ArrayList<City> cities = app.top10populatedCities();
        // Check that the list is not null and has 10 elements
        assertNotNull(cities, "Cities list should not be null");
        assertEquals(10, cities.size(), "Should return top 10 cities");
    }

    // FIX: Changed from @Test to @AfterAll
    // This ensures it runs LAST, preventing "No operations allowed after connection closed"
    @AfterAll
    static void tearDown()
    {
        app.disconnect();
    }
}
package com.napier.devops;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class CountryTest {

    @Test
    void testCountryFields() {
        // Arrange
        Country country = new Country();

        // Act
        country.code = "GBR";
        country.name = "United Kingdom";
        country.continent = "Europe";
        country.region = "British Islands";
        country.population = 67000000;
        country.capitalCity = "London";

        // Assert
        assertEquals("GBR", country.code);
        assertEquals("United Kingdom", country.name);
        assertEquals("Europe", country.continent);
        assertEquals("British Islands", country.region);
        assertEquals(67000000, country.population);
        assertEquals("London", country.capitalCity);
    }
}
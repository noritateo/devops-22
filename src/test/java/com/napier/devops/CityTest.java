package com.napier.devops;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class CityTest {

    @Test
    void testCityConstructorAndGetters() {
        // City creating something (Arrange)
        String expectedName = "Yangon";
        String expectedCountryCode = "MMR";
        String expectedDistrict = "Yangon";
        String expectedRegion = "Southeast Asia";
        String expectedContinent = "Asia";
        int expectedPopulation = 5000000;

        City city = new City(expectedName, expectedCountryCode, expectedDistrict, expectedRegion, expectedContinent, expectedPopulation);

        // testing if values are right or not (Act & Assert)
        assertEquals(expectedName, city.getName(), "City name should match");
        assertEquals(expectedCountryCode, city.getCountryCode(), "Country code should match");
        assertEquals(expectedDistrict, city.getDistrict(), "District should match");
        assertEquals(expectedRegion, city.getRegion(), "Region should match");
        assertEquals(expectedContinent, city.getContinent(), "Continent should match");
        assertEquals(expectedPopulation, city.getPopulation(), "Population should match");
    }

    @Test
    void testCityPublicFields() {
        // Public fields direct test
        City city = new City();
        city.name = "Mandalay";
        city.countryCode = "MMR";
        city.population = 1200000;

        assertEquals("Mandalay", city.name);
        assertEquals("MMR", city.countryCode);
        assertEquals(1200000, city.population);
    }
}
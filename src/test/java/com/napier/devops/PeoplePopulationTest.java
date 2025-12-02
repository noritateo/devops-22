package com.napier.devops;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class PeoplePopulationTest {

    @Test
    void testPeoplePopulationConstructor() {
        // Arrange
        String expectedLevel = "Europe";
        long expectedTotalPop = 746000000L;

        // Act
        PeoplePopulation pp = new PeoplePopulation(expectedLevel, expectedTotalPop);

        // Assert
        assertEquals(expectedLevel, pp.getLevel(), "Level should match constructor input");
        assertEquals(expectedTotalPop, pp.getTotalPopulation(), "Total Population should match constructor input");
    }

    @Test
    void testDefaultValues() {
        // Since the current class doesn't have setters or a full constructor,
        // we verify that the other fields default to 0 / 0.0
        PeoplePopulation pp = new PeoplePopulation("Asia", 4500000000L);

        assertEquals(0, pp.getCityPopulation());
        assertEquals(0, pp.getNonCityPopulation());
        assertEquals(0.0, pp.getCityPopulationPercentage());
        assertEquals(0.0, pp.getNonCityPopulationPercentage());
    }
}
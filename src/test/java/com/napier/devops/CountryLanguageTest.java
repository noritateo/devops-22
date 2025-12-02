package com.napier.devops;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class CountryLanguageTest {

    @Test
    void testCountryLanguageFields() {
        // Arrange
        CountryLanguage cl = new CountryLanguage();

        // Act
        cl.countryCode = "NLD";
        cl.language = "Dutch";
        cl.isOfficial = true;
        cl.percentage = 95.6f;

        // Assert
        assertEquals("NLD", cl.countryCode);
        assertEquals("Dutch", cl.language);
        assertTrue(cl.isOfficial, "Language should be official");
        assertEquals(95.6f, cl.percentage, 0.01); // 0.01 is the delta for float comparison
    }
}
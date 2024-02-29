package ru.tinkoff.ps.ops.test.swt.task3;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class PlanetTest {

    private Planet planet;

    @BeforeEach
    void setUp() {
        planet = new Planet(false);
    }

    @Test
    void planetShouldNotBePopulatedInitially() {
        assertFalse(planet.isPopulated(), "Planet should not be populated initially");
    }

    @Test
    void changePlanetPopulationStatus() {
        planet.setPopulated(true);
        assertTrue(planet.isPopulated(), "Planet population status should be able to change to true");
    }

    @Test
    void setAndGetPlanetSize() {
        assertTrue(planet.setSize(10), "Setting size should return true for valid value");
        assertEquals(10, planet.getSize(), "Planet size should be correctly updated");
    }

    @Test
    void setInvalidPlanetSize() {
        assertFalse(planet.setSize(-1), "Setting size should return false for invalid value");
        assertNotEquals(-1, planet.getSize(), "Planet size should not update to invalid value");
    }

    @Test
    void setAndGetPlanetBrightness() {
        assertTrue(planet.setBrightness(70), "Setting brightness should return true for valid value");
        assertEquals(70, planet.getBrightness(), "Planet brightness should be correctly updated");
    }

    @Test
    void setInvalidPlanetBrightness() {
        assertFalse(planet.setBrightness(-20), "Setting brightness should return false for invalid value");
        assertNotEquals(-20, planet.getBrightness(), "Planet brightness should not update to invalid value");
    }

    @Test
    void setAndGetPlanetName() {
        planet.setName("Earth");
        assertEquals("Earth", planet.getName(), "Planet name should be correctly updated");
    }
}

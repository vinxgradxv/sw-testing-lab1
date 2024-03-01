package ru.tinkoff.ps.ops.test.swt.task3;
import java.util.Arrays;
import java.util.HashMap;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class ScreenTest {

    private Screen screen;
    private Star star1, star2, distantStar;
    private Planet planet1, planet2;

    @BeforeEach
    void setUp() {
        screen = new Screen(new HashMap<>(), 100, 100);
        star1 = new Star();
        star2 = new Star();
        distantStar = new Star();
        planet1 = new Planet(false);
        planet2 = new Planet(true);
    }

    @Test
    @DisplayName("Добавление объекта на экран")
    void testAddObjectSuccess() {
        assertTrue(screen.addObject(planet1, 10, 10), "Should successfully add a planet");
        assertTrue(screen.addObject(star1, 20, 20), "Should successfully add a star");
    }

    @Test
    @DisplayName("Добавление на экран объектов с некорректными координатами")
    void testAddObjectInvalidCoordinates() {
        assertFalse(screen.addObject(planet1, -1, 10), "Should not add object with negative X coordinate");
        assertFalse(screen.addObject(star1, 10, -1), "Should not add object with negative Y coordinate");
    }

    @Test
    @DisplayName("Добавление на экран объекта с координатами, на которых уже находится объект")
    void testAddObjectToOccupiedSpace() {
        screen.addObject(planet1, 30, 30);
        assertFalse(screen.addObject(star1, 30, 30), "Should not add object to already occupied coordinates");
    }

    @Test
    @DisplayName("Добавление на экран объекта с координатами, на которых уже находится объект")
    void testRemoveObjectByInstance() {
        screen.addObject(planet1, 40, 40);
        assertTrue(screen.removeObject(planet1), "Should successfully remove the object by instance");
        assertFalse(screen.removeObject(planet1), "Removing the same object again should return false");
    }

    @Test
    @DisplayName("Удаление объекта с экрана")
    void testRemoveObjectByCoordinates() {
        screen.addObject(star1, 50, 50);
        assertTrue(screen.removeObject(50, 50), "Should successfully remove the object by coordinates");
        assertFalse(screen.removeObject(50, 50), "Removing the object again should return false");
    }

    @Test
    @DisplayName("Успешное изменение размеров экрана")
    void testReduceScreenSizeMovesObjectsWithinBounds() {
        screen.addObject(planet1, 95, 95);
        screen.addObject(star1, 10, 10);

        screen.setSize(50, 50);

        assertEquals(50, screen.getObjectCoordinates().get(planet1).getX(), "Planet X should be moved within new width");
        assertEquals(50, screen.getObjectCoordinates().get(planet1).getY(), "Planet Y should be moved within new height");

        assertEquals(10, screen.getObjectCoordinates().get(star1).getX(), "Star X should remain unchanged");
        assertEquals(10, screen.getObjectCoordinates().get(star1).getY(), "Star Y should remain unchanged");
    }

    @Test
    @DisplayName("Изменение размеров экрана на некорректные величины")
    void testSetSizeWithInvalidValuesDoesNotChangeSize() {
        screen.setSize(-10, -10);

        assertEquals(100, screen.getWidth(), "Screen width should remain unchanged");
        assertEquals(100, screen.getHeight(), "Screen height should remain unchanged");
    }

    @Test
    void testIncreaseScreenSizeKeepsObjectsInPlace() {
        screen.addObject(planet1, 20, 20);

        screen.setSize(200, 200);

        assertEquals(20, screen.getObjectCoordinates().get(planet1).getX(), "Planet X should remain unchanged");
        assertEquals(20, screen.getObjectCoordinates().get(planet1).getY(), "Planet Y should remain unchanged");
    }

    @Test
    void testMergeStarsCreatesStellarSystem() {
        screen.addObject(star1, 10, 10);
        screen.addObject(star2, 12, 12);

        StellarSystem stellarSystem = screen.mergeStars(Arrays.asList(star1, star2));

        assertNotNull(stellarSystem, "Stellar system should be created with closely located stars");
        assertEquals(2, stellarSystem.getStarsCount(), "Stellar system should contain two stars");
    }

    @Test
    void testMergeDistantStarsFails() {
        screen.addObject(star1, 10, 10);
        screen.addObject(distantStar, 50, 50);

        StellarSystem stellarSystem = screen.mergeStars(Arrays.asList(star1, distantStar));

        assertNull(stellarSystem, "Stellar system should not be created with distantly located stars");
    }

    @Test
    void testCreatePlanetSystemWithClosePlanets() {
        screen.addObject(star1, 20, 20);
        screen.addObject(planet1, 22, 22);
        screen.addObject(planet2, 23, 23);

        PlanetSystem planetSystem = screen.createPlanetSystem(Arrays.asList(planet1, planet2), star1);

        assertNotNull(planetSystem, "Planet system should be created with closely located planets");
        assertEquals(star1, planetSystem.getStar(), "The star of the system should be correctly set");
        assertTrue(planetSystem.getPlanets().containsAll(Arrays.asList(planet1, planet2)), "The planet system should contain the correct planets");
    }

    @Test
    void testCreatePlanetSystemWithDistantPlanetFails() {
        screen.addObject(star1, 20, 20);
        screen.addObject(planet1, 22, 22);
        screen.addObject(planet2, 50, 50);

        PlanetSystem planetSystem = screen.createPlanetSystem(Arrays.asList(planet1, planet2), star1);

        assertNull(planetSystem, "Planet system should not be created with distantly located planets");
    }
}

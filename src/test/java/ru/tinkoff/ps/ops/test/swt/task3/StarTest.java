package ru.tinkoff.ps.ops.test.swt.task3;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class StarTest {

    private Star star;

    @BeforeEach
    void setUp() {
        star = new Star();
    }

    @Test
    void testStarIsAliveInitially() {
        assertFalse(star.isDead(), "Star should be alive initially");
        assertNotEquals(Color.BLACK, star.getColor(), "Star color should not be BLACK initially");
    }

    @Test
    void testKillStarChangesItsStateAndColor() {
        boolean killed = star.kill();
        assertTrue(killed, "Kill method should return true when star is killed");
        assertTrue(star.isDead(), "Star should be marked as dead after being killed");
        assertEquals(Color.BLACK, star.getColor(), "Star color should be BLACK after it is killed");
    }

    @Test
    void testKillStarTwiceReturnsFalse() {
        star.kill();
        boolean killedAgain = star.kill();
        assertFalse(killedAgain, "Kill method should return false when trying to kill the star again");
    }

    @Test
    void testSetColorOnAliveStar() {
        boolean setColor = star.setColor(Color.RED);
        assertTrue(setColor, "SetColor should return true when setting color on alive star");
        assertEquals(Color.RED, star.getColor(), "Star color should change to RED");
    }

    @Test
    void testSetColorOnDeadStarToNonBlackFails() {
        star.kill();
        boolean setColor = star.setColor(Color.RED);
        assertFalse(setColor, "SetColor should return false when trying to set non-BLACK color on dead star");
        assertEquals(Color.BLACK, star.getColor(), "Star color should remain BLACK after attempt to change color on dead star");
    }

    @Test
    void testSetColorOnDeadStarToBlackSucceeds() {
        star.kill();
        boolean setColor = star.setColor(Color.BLACK);
        assertTrue(setColor, "SetColor should return true when setting BLACK color on dead star");
        assertEquals(Color.BLACK, star.getColor(), "Star color should be BLACK");
    }
}


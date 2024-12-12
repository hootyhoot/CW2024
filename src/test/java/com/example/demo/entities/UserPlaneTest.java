package com.example.demo.entities;

import javafx.application.Platform;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Test class for the UserPlane.
 * <p>
 * This class contains unit tests for the UserPlane class, which represents the player's plane in the game.
 *
 * @see <a href="https://github.com/hootyhoot/CW2024/blob/master/src/test/java/com/example/demo/entities/UserPlaneTest.java">Source code</a>
 */
class UserPlaneTest {

    /**
     * Initializes the JavaFX platform for testing.
     */
    @BeforeAll
    public static void initJFX() {
        Platform.startup(() -> {});
    }

    /**
     * Tests the initial health of the UserPlane.
     * <p>
     * This test verifies that the UserPlane is initialized with the correct health value.
     */
    @Test
    void testInitialHealth() {
        UserPlane userPlane = new UserPlane(3);
        assertEquals(3, userPlane.getHealth());
    }

    /**
     * Tests the upward movement of the UserPlane.
     * <p>
     * This test verifies that the UserPlane moves up correctly.
     */
    @Test
    void testMoveUp() {
        UserPlane userPlane = new UserPlane(3);
        userPlane.moveUp();
        userPlane.updateEntity();
        assertTrue(userPlane.getTranslateY() < 0);
    }

    /**
     * Tests the downward movement of the UserPlane.
     * <p>
     * This test verifies that the UserPlane moves down correctly.
     */
    @Test
    void testMoveDown() {
        UserPlane userPlane = new UserPlane(3);
        userPlane.moveDown();
        userPlane.updateEntity();
        assertTrue(userPlane.getTranslateY() > 0);
    }

    /**
     * Tests the rightward movement of the UserPlane.
     * <p>
     * This test verifies that the UserPlane moves right correctly.
     */
    @Test
    void testMoveRight() {
        UserPlane userPlane = new UserPlane(3);
        userPlane.moveRight();
        userPlane.updateEntity();
        assertTrue(userPlane.getHorizontalVelocityMultiplier() > 0);
    }

    /**
     * Tests the leftward movement of the UserPlane.
     * <p>
     * This test verifies that the UserPlane moves left correctly.
     */
    @Test
    void testMoveLeft() {
        UserPlane userPlane = new UserPlane(3);
        userPlane.moveLeft();
        userPlane.updateEntity();
        assertTrue(userPlane.getHorizontalVelocityMultiplier() < 0);
    }

    /**
     * Tests stopping the horizontal movement of the UserPlane.
     * <p>
     * This test verifies that the UserPlane stops moving horizontally correctly.
     */
    @Test
    void testStopHorizontally() {
        UserPlane userPlane = new UserPlane(3);
        userPlane.moveRight();
        userPlane.updateEntity();
        userPlane.stopHorizontally();
        userPlane.updateEntity();
        assertEquals(0, userPlane.getHorizontalVelocityMultiplier());
    }

    /**
     * Tests stopping the vertical movement of the UserPlane.
     * <p>
     * This test verifies that the UserPlane stops moving vertically correctly.
     */
    @Test
    void testStopVertically() {
        UserPlane userPlane = new UserPlane(3);
        userPlane.moveUp();
        userPlane.updateEntity();
        userPlane.stopVertically();
        userPlane.updateEntity();
        assertEquals(0, userPlane.getVerticalVelocityMultiplier());
    }

    /**
     * Tests firing a projectile from the UserPlane.
     * <p>
     * This test verifies that the UserPlane fires a projectile correctly.
     */
    @Test
    void testFireProjectile() {
        UserPlane userPlane = new UserPlane(3);
        UserProjectile projectile = (UserProjectile) userPlane.fireProjectile();
        assertNotNull(projectile);
        assertEquals(userPlane.getLayoutX() + 60, projectile.getLayoutX());
        assertEquals(userPlane.getLayoutY() + 20, projectile.getLayoutY());
    }

    /**
     * Tests incrementing the health of the UserPlane.
     * <p>
     * This test verifies that the UserPlane's health is incremented correctly.
     */
    @Test
    void testIncrementHealth() {
        UserPlane userPlane = new UserPlane(3);
        userPlane.incrementHealth();
        assertEquals(4, userPlane.getHealth());
    }

    /**
     * Tests incrementing the kill count of the UserPlane.
     * <p>
     * This test verifies that the UserPlane's kill count is incremented correctly.
     */
    @Test
    void testIncrementKillCount() {
        UserPlane userPlane = new UserPlane(3);
        userPlane.incrementKillCount();
        assertEquals(1, userPlane.getNumberOfKills());
    }
}
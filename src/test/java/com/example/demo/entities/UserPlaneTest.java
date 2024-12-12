package com.example.demo.entities;

import javafx.application.Platform;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class UserPlaneTest {

    @BeforeAll
    public static void initJFX() {
        Platform.startup(() -> {});
    }

    @Test
    void testInitialHealth() {
        UserPlane userPlane = new UserPlane(3);
        assertEquals(3, userPlane.getHealth());
    }

    @Test
    void testMoveUp() {
        UserPlane userPlane = new UserPlane(3);
        userPlane.moveUp();
        userPlane.updateEntity();
        assertTrue(userPlane.getTranslateY() < 0);
    }

    @Test
    void testMoveDown() {
        UserPlane userPlane = new UserPlane(3);
        userPlane.moveDown();
        userPlane.updateEntity();
        assertTrue(userPlane.getTranslateY() > 0);
    }

    @Test
    void testMoveRight() {
        UserPlane userPlane = new UserPlane(3);
        userPlane.moveRight();
        userPlane.updateEntity();
        assertTrue(userPlane.getHorizontalVelocityMultiplier() > 0);
    }

    @Test
    void testMoveLeft() {
        UserPlane userPlane = new UserPlane(3);
        userPlane.moveLeft();
        userPlane.updateEntity();
        assertTrue(userPlane.getHorizontalVelocityMultiplier() < 0);
    }

    @Test
    void testStopHorizontally() {
        UserPlane userPlane = new UserPlane(3);
        userPlane.moveRight();
        userPlane.updateEntity();
        userPlane.stopHorizontally();
        userPlane.updateEntity();
        assertEquals(0, userPlane.getHorizontalVelocityMultiplier());
    }

    @Test
    void testStopVertically() {
        UserPlane userPlane = new UserPlane(3);
        userPlane.moveUp();
        userPlane.updateEntity();
        userPlane.stopVertically();
        userPlane.updateEntity();
        assertEquals(0, userPlane.getVerticalVelocityMultiplier());
    }

    @Test
    void testFireProjectile() {
        UserPlane userPlane = new UserPlane(3);
        UserProjectile projectile = (UserProjectile) userPlane.fireProjectile();
        assertNotNull(projectile);
        assertEquals(userPlane.getLayoutX() + 60, projectile.getLayoutX());
        assertEquals(userPlane.getLayoutY() + 20, projectile.getLayoutY());
    }

    @Test
    void testIncrementHealth() {
        UserPlane userPlane = new UserPlane(3);
        userPlane.incrementHealth();
        assertEquals(4, userPlane.getHealth());
    }

    @Test
    void testIncrementKillCount() {
        UserPlane userPlane = new UserPlane(3);
        userPlane.incrementKillCount();
        assertEquals(1, userPlane.getNumberOfKills());
    }
}

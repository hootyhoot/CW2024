package com.example.demo.handlers;

import com.example.demo.entities.*;
import javafx.application.Platform;
import javafx.scene.Group;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class DestructibleEntityHandlerTest {

    private DestructibleEntityHandler handler;
    private Group root;

    @BeforeAll
    public static void initJFX() {
        Platform.startup(() -> {});
    }

    @BeforeEach
    void setUp() {
        root = new Group();
        handler = new DestructibleEntityHandler(root, 3);
    }

    @Test
    void testAddEnemyUnit() {
        EnemyPlane enemy = new EnemyPlane(0, 0, 0.5);
        handler.addEnemyUnit(enemy);
        assertTrue(handler.getEnemyUnits().contains(enemy));
        assertTrue(root.getChildren().contains(enemy));
    }

    @Test
    void testAddPowerup() {
        HeartPowerup powerup = new HeartPowerup(0, 0);
        handler.addPowerup(powerup);
        assertTrue(handler.getPowerups().contains(powerup));
        assertTrue(root.getChildren().contains(powerup));
    }

    @Test
    void testGenerateEnemyFire() {
        EnemyPlane enemy = new EnemyPlane(1000, 300, 0.5);
        handler.addEnemyUnit(enemy);
        handler.generateEnemyFire();
        assertFalse(handler.getEnemyProjectiles().isEmpty());
    }

    @Test
    void testUpdateEntity() {
        EnemyPlane enemy = new EnemyPlane(0, 0, 0.5);
        handler.addEnemyUnit(enemy);
        handler.updateEntity();
        assertNotEquals(0, enemy.getTranslateX());
    }

    @Test
    void testRemoveAllDestroyedEntities() {
        EnemyPlane enemy = new EnemyPlane(0, 0, 0.5);
        handler.addEnemyUnit(enemy);
        enemy.destroy();
        handler.removeAllDestroyedEntities();
        assertFalse(handler.getEnemyUnits().contains(enemy));
        assertFalse(root.getChildren().contains(enemy));
    }

    @Test
    void testGetCurrentNumberOfEnemies() {
        assertEquals(0, handler.getCurrentNumberOfEnemies());
        handler.addEnemyUnit(new EnemyPlane(0, 0, 0.5));
        assertEquals(1, handler.getCurrentNumberOfEnemies());
    }

    @Test
    void testGetCurrentNumberOfPowerups() {
        assertEquals(0, handler.getCurrentNumberOfPowerups());
        handler.addPowerup(new HeartPowerup(0, 0));
        assertEquals(1, handler.getCurrentNumberOfPowerups());
    }

    @Test
    void testGetUser() {
        assertNotNull(handler.getUser());
    }
}

package com.example.demo.handlers;

import com.example.demo.entities.*;
import javafx.application.Platform;
import javafx.scene.Group;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Test class for the DestructibleEntityHandler.
 * <p>
 * This class contains unit tests for the DestructibleEntityHandler class, which manages the addition,
 * removal, and updating of destructible entities in the game, such as enemy units and powerups.
 *
 * @see <a href="https://github.com/hootyhoot/CW2024/blob/master/src/test/java/com/example/demo/handlers/DestructibleEntityHandlerTest.java">Source code</a>
 */
class DestructibleEntityHandlerTest {

    /**
     * The DestructibleEntityHandler instance used for managing destructible entities.
     * <p>
     * This field holds the DestructibleEntityHandler instance, which manages the addition,
     * removal, and updating of destructible entities in the game.
     */
    private DestructibleEntityHandler handler;

    /**
     * The root group of the scene graph.
     * <p>
     * This field holds the root group, which contains all the elements of the scene graph
     * for the game.
     */
    private Group root;

    /**
     * Initializes the JavaFX platform for testing.
     */
    @BeforeAll
    public static void initJFX() {
        Platform.startup(() -> {});
    }

    /**
     * Sets up the test environment before each test.
     * <p>
     * This method initializes the root group and the DestructibleEntityHandler instance.
     */
    @BeforeEach
    void setUp() {
        root = new Group();
        handler = new DestructibleEntityHandler(root, 3);
    }

    /**
     * Tests the addition of an enemy unit.
     * <p>
     * This test verifies that an enemy unit is correctly added to the handler and the root group.
     */
    @Test
    void testAddEnemyUnit() {
        EnemyPlane enemy = new EnemyPlane(0, 0, 0.5);
        handler.addEnemyUnit(enemy);
        assertTrue(handler.getEnemyUnits().contains(enemy));
        assertTrue(root.getChildren().contains(enemy));
    }

    /**
     * Tests the addition of a powerup.
     * <p>
     * This test verifies that a powerup is correctly added to the handler and the root group.
     */
    @Test
    void testAddPowerup() {
        HeartPowerup powerup = new HeartPowerup(0, 0);
        handler.addPowerup(powerup);
        assertTrue(handler.getPowerups().contains(powerup));
        assertTrue(root.getChildren().contains(powerup));
    }

    /**
     * Tests the updating of entities.
     * <p>
     * This test verifies that entities are updated correctly, such as moving an enemy unit.
     */
    @Test
    void testUpdateEntity() {
        EnemyPlane enemy = new EnemyPlane(0, 0, 0.5);
        handler.addEnemyUnit(enemy);
        handler.updateEntity();
        assertNotEquals(0, enemy.getTranslateX());
    }

    /**
     * Tests the removal of all destroyed entities.
     * <p>
     * This test verifies that destroyed entities are removed from the handler and the root group.
     */
    @Test
    void testRemoveAllDestroyedEntities() {
        EnemyPlane enemy = new EnemyPlane(0, 0, 0.5);
        handler.addEnemyUnit(enemy);
        enemy.destroy();
        handler.removeAllDestroyedEntities();
        assertFalse(handler.getEnemyUnits().contains(enemy));
        assertFalse(root.getChildren().contains(enemy));
    }

    /**
     * Tests the retrieval of the current number of enemies.
     * <p>
     * This test verifies that the handler correctly returns the current number of enemy units.
     */
    @Test
    void testGetCurrentNumberOfEnemies() {
        assertEquals(0, handler.getCurrentNumberOfEnemies());
        handler.addEnemyUnit(new EnemyPlane(0, 0, 0.5));
        assertEquals(1, handler.getCurrentNumberOfEnemies());
    }

    /**
     * Tests the retrieval of the current number of powerups.
     * <p>
     * This test verifies that the handler correctly returns the current number of powerups.
     */
    @Test
    void testGetCurrentNumberOfPowerups() {
        assertEquals(0, handler.getCurrentNumberOfPowerups());
        handler.addPowerup(new HeartPowerup(0, 0));
        assertEquals(1, handler.getCurrentNumberOfPowerups());
    }

    /**
     * Tests the retrieval of the user.
     * <p>
     * This test verifies that the handler correctly returns the user plane.
     */
    @Test
    void testGetUser() {
        assertNotNull(handler.getUser());
    }
}
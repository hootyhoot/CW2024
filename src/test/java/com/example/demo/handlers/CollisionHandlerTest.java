package com.example.demo.handlers;

import com.example.demo.entities.*;
import com.example.demo.gui.LevelView;
import javafx.application.Platform;
import javafx.scene.Group;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Test class for the CollisionHandler.
 * <p>
 * This class contains unit tests for the CollisionHandler class, which handles various collision scenarios
 * in the game, such as plane collisions, projectile collisions, and powerup collisions.
 *
 * @see <a href="https://github.com/hootyhoot/CW2024/blob/master/src/test/java/com/example/demo/handlers/CollisionHandlerTest.java">Source code</a>
 */
class CollisionHandlerTest {

    /**
     * Initializes the JavaFX platform for testing.
     */
    @BeforeAll
    public static void initJFX() {
        Platform.startup(() -> {});
    }

    /**
     * Tests the handling of plane collisions.
     * <p>
     * This test verifies that when a friendly plane collides with an enemy plane, the friendly plane's health
     * is reduced, and the enemy plane is destroyed.
     */
    @Test
    void testHandlePlaneCollisions() {
        LevelView levelView = new LevelView(new Group(), 3);
        CollisionHandler collisionHandler = new CollisionHandler(levelView);

        UserPlane friendly = new UserPlane(3);
        EnemyPlane enemy = new EnemyPlane(5, 300, 0.5);

        List<DestructibleEntity> friendlyUnits = new ArrayList<>();
        List<DestructibleEntity> enemyUnits = new ArrayList<>();
        friendlyUnits.add(friendly);
        enemyUnits.add(enemy);

        collisionHandler.handlePlaneCollisions(friendlyUnits, enemyUnits);

        assertEquals(2, friendly.getHealth());
        assertTrue(enemy.isDestroyed());
    }

    /**
     * Tests the handling of user projectile collisions.
     * <p>
     * This test verifies that when a user projectile collides with an enemy plane, both the projectile and
     * the enemy plane are destroyed.
     */
    @Test
    void testHandleUserProjectileCollisions() {
        LevelView levelView = new LevelView(new Group(), 3);
        CollisionHandler collisionHandler = new CollisionHandler(levelView);

        UserProjectile projectile = new UserProjectile(10, 0);
        EnemyPlane enemy = new EnemyPlane(0, 0, 0.5);

        List<DestructibleEntity> projectiles = new ArrayList<>();
        List<DestructibleEntity> enemyUnits = new ArrayList<>();
        projectiles.add(projectile);
        enemyUnits.add(enemy);

        collisionHandler.handleUserProjectileCollisions(projectiles, enemyUnits);

        assertTrue(projectile.isDestroyed());
        assertTrue(enemy.isDestroyed());
    }

    /**
     * Tests the handling of enemy projectile collisions.
     * <p>
     * This test verifies that when an enemy projectile collides with a friendly plane, the projectile is
     * destroyed, and the friendly plane's health is reduced.
     */
    @Test
    void testHandleEnemyProjectileCollisions() {
        LevelView levelView = new LevelView(new Group(), 3);
        CollisionHandler collisionHandler = new CollisionHandler(levelView);

        EnemyProjectile projectile = new EnemyProjectile(5, 300);
        UserPlane friendly = new UserPlane(3);

        List<DestructibleEntity> projectiles = new ArrayList<>();
        List<DestructibleEntity> friendlyUnits = new ArrayList<>();
        projectiles.add(projectile);
        friendlyUnits.add(friendly);

        collisionHandler.handleEnemyProjectileCollisions(projectiles, friendlyUnits);

        assertTrue(projectile.isDestroyed());
        assertEquals(2, friendly.getHealth());
    }

    /**
     * Tests the handling of enemy penetration.
     * <p>
     * This test verifies that when an enemy plane penetrates the screen boundary, it is destroyed.
     */
    @Test
    void testHandleEnemyPenetration() {
        LevelView levelView = new LevelView(new Group(), 3);
        CollisionHandler collisionHandler = new CollisionHandler(levelView);

        EnemyPlane enemy = new EnemyPlane(0, 300, 0.5);
        UserPlane user = new UserPlane(3);

        List<DestructibleEntity> enemyUnits = new ArrayList<>();
        enemyUnits.add(enemy);
        enemyUnits.forEach(DestructibleEntity::updateEntity); //update the enemy one frame which moves it 6 pixels and out of bounds

        collisionHandler.handleEnemyPenetration(enemyUnits, user, 5); //set the screen width a value less than the per frame movement of the enemy

        assertTrue(enemy.isDestroyed());
    }

    /**
     * Tests the handling of powerup collisions.
     * <p>
     * This test verifies that when a user plane collides with a powerup, the powerup is destroyed.
     */
    @Test
    void testHandlePowerupCollisions() {
        LevelView levelView = new LevelView(new Group(), 3);
        CollisionHandler collisionHandler = new CollisionHandler(levelView);

        UserPlane user = new UserPlane(3);
        HeartPowerup powerup = new HeartPowerup(5, 300);

        List<DestructibleEntity> powerups = new ArrayList<>();
        powerups.add(powerup);

        collisionHandler.handlePowerupCollisions(powerups, user);

        assertTrue(powerup.isDestroyed());
    }
}
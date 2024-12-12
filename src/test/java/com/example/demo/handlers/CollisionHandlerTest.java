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

class CollisionHandlerTest {

    @BeforeAll
    public static void initJFX() {
        Platform.startup(() -> {});
    }

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
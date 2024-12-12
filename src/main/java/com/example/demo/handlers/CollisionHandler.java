package com.example.demo.handlers;

import com.example.demo.entities.DestructibleEntity;
import com.example.demo.entities.Powerup;
import com.example.demo.entities.UserPlane;
import com.example.demo.gui.LevelView;

import java.util.List;

/**
 * Handles collisions between various entities in the game.
 * Manages collision detection and response for friendly units, enemy units, projectiles, and powerups.
 *
 * @see <a href="https://github.com/hootyhoot/CW2024/blob/master/src/main/java/com/example/demo/handlers/CollisionHandler.java">Source code</a>
 */
public class CollisionHandler {

    /**
     * The level view associated with this collision handler.
     */
    private final LevelView levelView;

    /**
     * Constructs a CollisionHandler with the specified level view.
     *
     * @param levelView the level view
     */
    public CollisionHandler(LevelView levelView) {
        this.levelView = levelView;
    }

    /**
     * Handles collisions between friendly and enemy units.
     *
     * @param friendlyUnits the list of friendly units
     * @param enemyUnits the list of enemy units
     */
    public void handlePlaneCollisions(List<DestructibleEntity> friendlyUnits, List<DestructibleEntity> enemyUnits) {
        handleCollisions(friendlyUnits, enemyUnits);
    }

    /**
     * Handles collisions between user projectiles and enemy units.
     *
     * @param userProjectiles the list of user projectiles
     * @param enemyUnits the list of enemy units
     */
    public void handleUserProjectileCollisions(List<DestructibleEntity> userProjectiles, List<DestructibleEntity> enemyUnits) {
        handleCollisions(userProjectiles, enemyUnits);
    }

    /**
     * Handles collisions between enemy projectiles and friendly units.
     *
     * @param enemyProjectiles the list of enemy projectiles
     * @param friendlyUnits the list of friendly units
     */
    public void handleEnemyProjectileCollisions(List<DestructibleEntity> enemyProjectiles, List<DestructibleEntity> friendlyUnits) {
        handleCollisions(enemyProjectiles, friendlyUnits);
    }

    /**
     * Handles enemy units penetrating the screen border and damaging the user.
     *
     * @param enemyUnits the list of enemy units
     * @param user the user plane
     * @param screenWidth the width of the screen
     */
    public void handleEnemyPenetration(List<DestructibleEntity> enemyUnits, UserPlane user, double screenWidth) {
        for (DestructibleEntity enemy : enemyUnits) {
            if (isEnemyPastScreenBorder(enemy, screenWidth)) {
                user.takeDamage();
                enemy.destroy();
            }
        }
    }

    /**
     * Handles collisions between powerups and the user.
     *
     * @param powerups the list of powerups
     * @param user the user plane
     */
    public void handlePowerupCollisions(List<DestructibleEntity> powerups, UserPlane user) {
        for (DestructibleEntity powerup : powerups) {
            if (powerup.getBoundsInParent().intersects(user.getBoundsInParent())) {
                ((Powerup) powerup).applyEffect(levelView, user);
                powerup.destroy();
            }
        }
    }

    /**
     * Checks if an enemy unit has passed the screen border.
     *
     * @param enemy the enemy unit
     * @param screenWidth the width of the screen
     * @return true if the enemy is past the screen border, false otherwise
     */
    private boolean isEnemyPastScreenBorder(DestructibleEntity enemy, double screenWidth) {
        return Math.abs(enemy.getTranslateX()) > screenWidth;
    }

    /**
     * Handles collisions between two lists of entities.
     *
     * @param entity1 the first list of entities
     * @param entity2 the second list of entities
     */
    private void handleCollisions(List<DestructibleEntity> entity1, List<DestructibleEntity> entity2) {
        for (DestructibleEntity entity : entity2) {
            for (DestructibleEntity otherEntity : entity1) {
                if (entity.getBoundsInParent().intersects(otherEntity.getBoundsInParent())) {
                    entity.takeDamage();
                    otherEntity.takeDamage();
                }
            }
        }
    }
}
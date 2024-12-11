package com.example.demo.handlers;

import com.example.demo.entities.DestructibleEntity;
import com.example.demo.entities.Powerup;
import com.example.demo.entities.UserPlane;
import com.example.demo.gui.LevelView;

import java.util.List;

public class CollisionHandler {

    private final LevelView levelView;

    public CollisionHandler(LevelView levelView) {
        this.levelView = levelView;
    }

    public void handlePlaneCollisions(List<DestructibleEntity> friendlyUnits, List<DestructibleEntity> enemyUnits) {
        handleCollisions(friendlyUnits, enemyUnits);
    }

    public void handleUserProjectileCollisions(List<DestructibleEntity> userProjectiles, List<DestructibleEntity> enemyUnits) {
        handleCollisions(userProjectiles, enemyUnits);
    }

    public void handleEnemyProjectileCollisions(List<DestructibleEntity> enemyProjectiles, List<DestructibleEntity> friendlyUnits) {
        handleCollisions(enemyProjectiles, friendlyUnits);
    }

    public void handleEnemyPenetration(List<DestructibleEntity> enemyUnits, UserPlane user, double screenWidth) {
        for (DestructibleEntity enemy : enemyUnits) {
            if (isEnemyPastScreenBorder(enemy, screenWidth)) {
                user.takeDamage();
                enemy.destroy();
            }
        }
    }

    public void handlePowerupCollisions(List<DestructibleEntity> powerups, UserPlane user) {
        for (DestructibleEntity powerup : powerups) {
            if (powerup.getBoundsInParent().intersects(user.getBoundsInParent())) {
                ((Powerup) powerup).applyEffect(levelView, user);
                powerup.destroy();
            }
        }
    }

    private boolean isEnemyPastScreenBorder(DestructibleEntity enemy, double screenWidth) {
        return Math.abs(enemy.getTranslateX()) > screenWidth;
    }

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
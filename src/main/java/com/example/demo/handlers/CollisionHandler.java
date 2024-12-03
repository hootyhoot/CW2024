package com.example.demo.handlers;

import com.example.demo.entities.DestructibleEntity;
import com.example.demo.entities.UserPlane;

import java.util.List;

public class CollisionHandler {

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

    private boolean isEnemyPastScreenBorder(DestructibleEntity enemy, double screenWidth) {
        return Math.abs(enemy.getTranslateX()) > screenWidth;
    }

    private void handleCollisions(List<DestructibleEntity> entity1, List<DestructibleEntity> entity2) { //extract to new class
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

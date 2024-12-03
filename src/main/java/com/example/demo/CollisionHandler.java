package com.example.demo;

import java.util.List;

public class CollisionHandler {

    protected void handlePlaneCollisions(List<DestructibleEntity> friendlyUnits, List<DestructibleEntity> enemyUnits) {
        handleCollisions(friendlyUnits, enemyUnits);
    }

    protected void handleUserProjectileCollisions(List<DestructibleEntity> userProjectiles, List<DestructibleEntity> enemyUnits) {
        handleCollisions(userProjectiles, enemyUnits);
    }

    protected void handleEnemyProjectileCollisions(List<DestructibleEntity> enemyProjectiles, List<DestructibleEntity> friendlyUnits) {
        handleCollisions(enemyProjectiles, friendlyUnits);
    }

    protected void handleEnemyPenetration(List<DestructibleEntity> enemyUnits, UserPlane user, double screenWidth) {
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

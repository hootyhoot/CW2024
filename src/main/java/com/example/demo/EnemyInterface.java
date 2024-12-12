package com.example.demo;

import com.example.demo.entities.DestructibleEntity;

/**
 * Represents an interface for enemy entities.
 * This interface defines methods for managing enemy units.
 *
 * @see <a href="https://github.com/hootyhoot/CW2024/blob/master/src/main/java/com/example/demo/EnemyInterface.java">Source code</a>
 */
public interface EnemyInterface {

    /**
     * Gets the current number of enemies.
     *
     * @return the current number of enemies
     */
    int getCurrentNumberOfEnemies();

    /**
     * Adds an enemy unit to the entity handler.
     *
     * @param enemy the enemy unit to add
     */
    void addEnemyUnit(DestructibleEntity enemy);
}
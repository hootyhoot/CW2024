package com.example.demo;

import com.example.demo.entities.DestructibleEntity;

public interface EnemyInterface {
    int getCurrentNumberOfEnemies();
    void addEnemyUnit(DestructibleEntity enemy);
}
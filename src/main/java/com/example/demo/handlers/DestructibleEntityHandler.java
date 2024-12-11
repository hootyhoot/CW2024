package com.example.demo.handlers;

import com.example.demo.EnemyInterface;
import com.example.demo.entities.DestructibleEntity;
import com.example.demo.entities.FighterPlane;
import com.example.demo.entities.UserPlane;
import javafx.scene.Group;
import java.util.ArrayList;
import java.util.List;

public class DestructibleEntityHandler implements EnemyInterface {

    private final Group m_Root;
    private final UserPlane m_User;
    private final List<DestructibleEntity> m_FriendlyUnits;
    private final List<DestructibleEntity> m_EnemyUnits;
    private final List<DestructibleEntity> m_UserProjectiles;
    private final List<DestructibleEntity> m_EnemyProjectiles;
    private final List<DestructibleEntity> m_Powerups;

    public DestructibleEntityHandler(Group root, int playerInitialHealth) {
        this.m_FriendlyUnits = new ArrayList<>();
        this.m_EnemyUnits = new ArrayList<>();
        this.m_UserProjectiles = new ArrayList<>();
        this.m_EnemyProjectiles = new ArrayList<>();
        this.m_Powerups = new ArrayList<>();
        this.m_User = new UserPlane(playerInitialHealth);
        this.m_Root = root;
        m_FriendlyUnits.add(m_User);
    }

    public List<DestructibleEntity> getFriendlyUnits() {
        return m_FriendlyUnits;
    }

    public List<DestructibleEntity> getEnemyUnits() {
        return m_EnemyUnits;
    }

    public List<DestructibleEntity> getUserProjectiles() {
        return m_UserProjectiles;
    }

    public List<DestructibleEntity> getEnemyProjectiles() {
        return m_EnemyProjectiles;
    }

    public List<DestructibleEntity> getPowerups() {
        return m_Powerups;
    }

    @Override
    public int getCurrentNumberOfEnemies() {
        return m_EnemyUnits.size();
    }

    public int getCurrentNumberOfPowerups() {
        return m_Powerups.size();
    }

    public UserPlane getUser() {
        return m_User;
    }

    @Override
    public void addEnemyUnit(DestructibleEntity enemy) {
        m_EnemyUnits.add(enemy);
        m_Root.getChildren().add(enemy);
    }

    public void addPowerup(DestructibleEntity powerup) {
        if (powerup!= null) {
            m_Powerups.add(powerup);
            m_Root.getChildren().add(powerup);
        }
    }

    public void generateEnemyFire() {
        m_EnemyUnits.forEach(enemy -> spawnEnemyProjectile(((FighterPlane) enemy).fireProjectile()));
    }

    public void updateEntity() {
        m_FriendlyUnits.forEach(DestructibleEntity::updateEntity);
        m_EnemyUnits.forEach(DestructibleEntity::updateEntity);
        m_UserProjectiles.forEach(DestructibleEntity::updateEntity);
        m_EnemyProjectiles.forEach(DestructibleEntity::updateEntity);
        m_Powerups.forEach(DestructibleEntity::updateEntity);
    }

    public void removeAllDestroyedEntities() {
        removeDestroyedEntities(m_FriendlyUnits);
        removeDestroyedEntities(m_EnemyUnits);
        removeDestroyedEntities(m_UserProjectiles);
        removeDestroyedEntities(m_EnemyProjectiles);
        removeDestroyedEntities(m_Powerups);
    }

    private void spawnEnemyProjectile(DestructibleEntity projectile) {
        if (projectile != null) {
            m_Root.getChildren().add(projectile);
            m_EnemyProjectiles.add(projectile);
        }
    }

    private void removeDestroyedEntities(List<DestructibleEntity> entities) {
        List<DestructibleEntity> destroyedEntities = entities.stream().filter(DestructibleEntity::isDestroyed)
                .toList();
        m_Root.getChildren().removeAll(destroyedEntities);
        entities.removeAll(destroyedEntities);
    }
}
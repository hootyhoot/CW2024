package com.example.demo.handlers;

import com.example.demo.EnemyInterface;
import com.example.demo.entities.DestructibleEntity;
import com.example.demo.entities.FighterPlane;
import com.example.demo.entities.UserPlane;
import javafx.scene.Group;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class DestructibleEntityHandler implements EnemyInterface {

    private final Group m_Root;
    private final UserPlane m_User;
    private final List<DestructibleEntity> m_FriendlyUnits;
    private final List<DestructibleEntity> m_EnemyUnits;
    private final List<DestructibleEntity> m_UserProjectiles;
    private final List<DestructibleEntity> m_EnemyProjectiles;

    public DestructibleEntityHandler(Group root, int playerInitialHealth) {
        this.m_FriendlyUnits = new ArrayList<>();
        this.m_EnemyUnits = new ArrayList<>();
        this.m_UserProjectiles = new ArrayList<>();
        this.m_EnemyProjectiles = new ArrayList<>();
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

    @Override
    public int getCurrentNumberOfEnemies() {
        return m_EnemyUnits.size();
    }

    public UserPlane getUser() {
        return m_User;
    }

    @Override
    public void addEnemyUnit(DestructibleEntity enemy) {
        m_EnemyUnits.add(enemy);
        m_Root.getChildren().add(enemy);
    }

    public void generateEnemyFire() {
        m_EnemyUnits.forEach(enemy -> spawnEnemyProjectile(((FighterPlane) enemy).fireProjectile()));
    }

    public void updateEntity() {
        m_FriendlyUnits.forEach(plane -> plane.updateEntity());
        m_EnemyUnits.forEach(enemy -> enemy.updateEntity());
        m_UserProjectiles.forEach(projectile -> projectile.updateEntity());
        m_EnemyProjectiles.forEach(projectile -> projectile.updateEntity());
    }

    public void removeAllDestroyedEntities() {
        removeDestroyedEntities(m_FriendlyUnits);
        removeDestroyedEntities(m_EnemyUnits);
        removeDestroyedEntities(m_UserProjectiles);
        removeDestroyedEntities(m_EnemyProjectiles);
    }

    private void spawnEnemyProjectile(DestructibleEntity projectile) {
        if (projectile != null) {
            m_Root.getChildren().add(projectile);
            m_EnemyProjectiles.add(projectile);
        }
    }

    private void removeDestroyedEntities(List<DestructibleEntity> entities) {
        List<DestructibleEntity> destroyedEntities = entities.stream().filter(entity -> entity.isDestroyed())
                .collect(Collectors.toList());
        m_Root.getChildren().removeAll(destroyedEntities);
        entities.removeAll(destroyedEntities);
    }
}
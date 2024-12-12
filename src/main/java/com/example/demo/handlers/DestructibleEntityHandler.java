package com.example.demo.handlers;

import com.example.demo.EnemyInterface;
import com.example.demo.entities.DestructibleEntity;
import com.example.demo.entities.FighterPlane;
import com.example.demo.entities.UserPlane;
import javafx.scene.Group;
import java.util.ArrayList;
import java.util.List;

/**
 * Handles destructible entities in the game.
 * Manages the creation, updating, and removal of friendly units, enemy units, projectiles, and powerups.
 *
 * @see <a href="https://github.com/hootyhoot/CW2024/blob/master/src/main/java/com/example/demo/handlers/DestructibleEntityHandler.java">Source code</a>
 */
public class DestructibleEntityHandler implements EnemyInterface {

    /**
     * The root group for the destructible entities.
     */
    private final Group m_Root;

    /**
     * The user plane.
     */
    private final UserPlane m_User;

    /**
     * The list of friendly units.
     */
    private final List<DestructibleEntity> m_FriendlyUnits;

    /**
     * The list of enemy units.
     */
    private final List<DestructibleEntity> m_EnemyUnits;

    /**
     * The list of user projectiles.
     */
    private final List<DestructibleEntity> m_UserProjectiles;

    /**
     * The list of enemy projectiles.
     */
    private final List<DestructibleEntity> m_EnemyProjectiles;

    /**
     * The list of powerups.
     */
    private final List<DestructibleEntity> m_Powerups;

    /**
     * Constructs a DestructibleEntityHandler with the specified root and initial player health.
     *
     * @param root the root group
     * @param playerInitialHealth the initial health of the player
     */
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

    /**
     * Returns the list of friendly units.
     *
     * @return the list of friendly units
     */
    public List<DestructibleEntity> getFriendlyUnits() {
        return m_FriendlyUnits;
    }

    /**
     * Returns the list of enemy units.
     *
     * @return the list of enemy units
     */
    public List<DestructibleEntity> getEnemyUnits() {
        return m_EnemyUnits;
    }

    /**
     * Returns the list of user projectiles.
     *
     * @return the list of user projectiles
     */
    public List<DestructibleEntity> getUserProjectiles() {
        return m_UserProjectiles;
    }

    /**
     * Returns the list of enemy projectiles.
     *
     * @return the list of enemy projectiles
     */
    public List<DestructibleEntity> getEnemyProjectiles() {
        return m_EnemyProjectiles;
    }

    /**
     * Returns the list of powerups.
     *
     * @return the list of powerups
     */
    public List<DestructibleEntity> getPowerups() {
        return m_Powerups;
    }

    /**
     * Returns the current number of enemies.
     *
     * @return the current number of enemies
     */
    @Override
    public int getCurrentNumberOfEnemies() {
        return m_EnemyUnits.size();
    }

    /**
     * Returns the current number of powerups.
     *
     * @return the current number of powerups
     */
    public int getCurrentNumberOfPowerups() {
        return m_Powerups.size();
    }

    /**
     * Returns the user plane.
     *
     * @return the user plane
     */
    public UserPlane getUser() {
        return m_User;
    }

    /**
     * Adds an enemy unit to the list and the root group.
     *
     * @param enemy the enemy unit
     */
    @Override
    public void addEnemyUnit(DestructibleEntity enemy) {
        m_EnemyUnits.add(enemy);
        m_Root.getChildren().add(enemy);
    }

    /**
     * Adds a powerup to the list and the root group.
     *
     * @param powerup the powerup
     */
    public void addPowerup(DestructibleEntity powerup) {
        if (powerup != null) {
            m_Powerups.add(powerup);
            m_Root.getChildren().add(powerup);
        }
    }

    /**
     * Generates enemy fire by spawning enemy projectiles.
     */
    public void generateEnemyFire() {
        m_EnemyUnits.forEach(enemy -> spawnEnemyProjectile(((FighterPlane) enemy).fireProjectile()));
    }

    /**
     * Updates all entities.
     */
    public void updateEntity() {
        m_FriendlyUnits.forEach(DestructibleEntity::updateEntity);
        m_EnemyUnits.forEach(DestructibleEntity::updateEntity);
        m_UserProjectiles.forEach(DestructibleEntity::updateEntity);
        m_EnemyProjectiles.forEach(DestructibleEntity::updateEntity);
        m_Powerups.forEach(DestructibleEntity::updateEntity);
    }

    /**
     * Removes all destroyed entities from the lists and the root group.
     */
    public void removeAllDestroyedEntities() {
        removeDestroyedEntities(m_FriendlyUnits);
        removeDestroyedEntities(m_EnemyUnits);
        removeDestroyedEntities(m_UserProjectiles);
        removeDestroyedEntities(m_EnemyProjectiles);
        removeDestroyedEntities(m_Powerups);
    }

    /**
     * Spawns an enemy projectile.
     *
     * @param projectile the enemy projectile
     */
    private void spawnEnemyProjectile(DestructibleEntity projectile) {
        if (projectile != null) {
            m_Root.getChildren().add(projectile);
            m_EnemyProjectiles.add(projectile);
        }
    }

    /**
     * Removes destroyed entities from the specified list and the root group.
     *
     * @param entities the list of entities
     */
    private void removeDestroyedEntities(List<DestructibleEntity> entities) {
        List<DestructibleEntity> destroyedEntities = entities.stream().filter(DestructibleEntity::isDestroyed)
                .toList();
        m_Root.getChildren().removeAll(destroyedEntities);
        entities.removeAll(destroyedEntities);
    }
}
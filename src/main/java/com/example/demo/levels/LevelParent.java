package com.example.demo.levels;

import java.util.*;

import com.example.demo.entities.DestructibleEntity;
import com.example.demo.entities.EnemyPlane;
import com.example.demo.entities.HeartPowerup;
import com.example.demo.entities.UserPlane;
import com.example.demo.gui.EndMenu;
import com.example.demo.gui.LevelView;
import com.example.demo.handlers.CollisionHandler;
import com.example.demo.handlers.ControlsHandler;
import com.example.demo.handlers.DestructibleEntityHandler;
import javafx.animation.*;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.image.*;
import javafx.util.Duration;

/**
 * Represents a parent class for game levels.
 * Manages the initialization, updating, and control of game levels, including spawning enemies and powerups, handling collisions, and managing the game loop.
 *
 * @see <a href="https://github.com/hootyhoot/CW2024/blob/master/src/main/java/com/example/demo/levels/LevelParent.java">Source code</a>
 */
public abstract class LevelParent extends Observable {

	/**
	 * Adjustment value for the screen height.
	 */
	private static final double SCREEN_HEIGHT_ADJUSTMENT = 150;

	/**
	 * Delay in milliseconds for the game loop.
	 */
	private static final int MILLISECOND_DELAY = 50;

	/**
	 * Default probability of spawning a powerup.
	 */
	private static final double DEFAULT_POWERUP_SPAWN_PROBABILITY = 0.05;

	/**
	 * Default maximum number of powerups on screen.
	 */
	private static final int DEFAULT_MAX_POWERUPS_ON_SCREEN = 4;

	/**
	 * Default maximum number of enemies on screen.
	 */
	private static final int DEFAULT_MAX_ENEMIES_ON_SCREEN = 5;

	/**
	 * Default probability of spawning an enemy.
	 */
	private static final double DEFAULT_ENEMY_SPAWN_PROBABILITY = 0.20;

	/**
	 * Default fire rate of the enemy.
	 */
	private static final double DEFAULT_ENEMY_FIRE_RATE = 0.01;

	/**
	 * Height of the screen.
	 */
	private final double m_ScreenHeight;

	/**
	 * Width of the screen.
	 */
	private final double m_ScreenWidth;

	/**
	 * Maximum Y position for enemies.
	 */
	private final double m_EnemyMaximumYPosition;

	/**
	 * Root group for the scene graph.
	 */
	private final Group m_Root;

	/**
	 * Timeline for the game loop.
	 */
	private final Timeline m_Timeline;

	/**
	 * Scene for the game.
	 */
	private final Scene m_Scene;

	/**
	 * Background image view for the level.
	 */
	private final ImageView m_Background;

	/**
	 * Handler for collision detection.
	 */
	private final CollisionHandler m_CollisionHandler;

	/**
	 * Handler for user controls.
	 */
	private final ControlsHandler m_ControlsHandler;

	/**
	 * Handler for destructible entities.
	 */
	private final DestructibleEntityHandler m_DestructibleEntityHandler;

	/**
	 * Current number of enemies in the level.
	 */
	private int m_CurrentNumberOfEnemies;

	/**
	 * View for the level.
	 */
	private final LevelView m_LevelView;

	/**
	 * Constructs a LevelParent with the specified parameters.
	 *
	 * @param backgroundImageName the name of the background image
	 * @param screenHeight the height of the screen
	 * @param screenWidth the width of the screen
	 * @param playerInitialHealth the initial health of the player
	 */
	public LevelParent(String backgroundImageName, double screenHeight, double screenWidth, int playerInitialHealth) {
		this.m_Root = new Group();
		this.m_Scene = new Scene(m_Root, screenWidth, screenHeight);
		this.m_Timeline = new Timeline();
		this.m_LevelView = instantiateLevelView();
		this.m_CollisionHandler = new CollisionHandler(m_LevelView);
		this.m_DestructibleEntityHandler = new DestructibleEntityHandler(m_Root, playerInitialHealth);
		this.m_ControlsHandler = new ControlsHandler(this);
		this.m_Background = new ImageView(new Image(Objects.requireNonNull(getClass().getResource(backgroundImageName)).toExternalForm()));
		this.m_ScreenHeight = screenHeight;
		this.m_ScreenWidth = screenWidth;
		this.m_EnemyMaximumYPosition = screenHeight - SCREEN_HEIGHT_ADJUSTMENT;
		this.m_CurrentNumberOfEnemies = 0;
		initializeTimeline();
	}

	/**
	 * Initializes the scene.
	 *
	 * @return the initialized scene
	 */
	public Scene initializeScene() {
		initializeBackground();
		m_ControlsHandler.createControlsListeners(m_Scene, getUser());
		initializeFriendlyUnits();
		m_LevelView.showHeartDisplay();
		return m_Scene;
	}

	/**
	 * Fires a projectile from the user plane.
	 */
	public void fireProjectile() {
		DestructibleEntity projectile = getUser().fireProjectile();
		m_Root.getChildren().add(projectile);
		m_DestructibleEntityHandler.getUserProjectiles().add(projectile);
	}

	/**
	 * Starts the game.
	 */
	public void startGame() {
		m_Background.requestFocus();
		m_Timeline.play();
	}

	/**
	 * Goes to the next level.
	 *
	 * @param levelName the name of the next level
	 */
	public void goToNextLevel(String levelName) {
		getUser().destroy();
		m_Timeline.stop();
		m_Timeline.getKeyFrames().clear();
		setChanged();
		notifyObservers(levelName);
	}

	/**
	 * Initializes friendly units.
	 */
	protected abstract void initializeFriendlyUnits();

	/**
	 * Checks if the game is over.
	 */
	protected abstract void isGameOver();

	/**
	 * Spawns enemy units.
	 *
	 * @param maxEnemiesOnScreen the maximum number of enemies on screen
	 * @param enemySpawnProbability the probability of spawning an enemy
	 * @param enemyFireRate the fire rate of the enemy
	 */
	protected void spawnEnemyUnits(int maxEnemiesOnScreen, double enemySpawnProbability, double enemyFireRate) {
		int currentNumberOfEnemies = getEntityHandler().getCurrentNumberOfEnemies();
		for (int i = 0; i < maxEnemiesOnScreen - currentNumberOfEnemies; i++) {
			if (Math.random() < enemySpawnProbability) {
				double newEnemyInitialYPosition = Math.random() * getEnemyMaximumYPosition();
				DestructibleEntity newEnemy = new EnemyPlane(getScreenWidth(), newEnemyInitialYPosition, enemyFireRate);
				getEntityHandler().addEnemyUnit(newEnemy);
			}
		}
	}

	/**
	 * Spawns powerups.
	 *
	 * @param maxPowerupsOnScreen the maximum number of powerups on screen
	 * @param powerupSpawnProbability the probability of spawning a powerup
	 */
	protected void spawnPowerups(int maxPowerupsOnScreen, double powerupSpawnProbability) {
		int currentNumberOfPowerups = getEntityHandler().getCurrentNumberOfPowerups();
		for (int i = 0; i < maxPowerupsOnScreen - currentNumberOfPowerups; i++) {
			if (Math.random() < powerupSpawnProbability) {
				double newPowerupInitialYPosition = Math.random() * getEnemyMaximumYPosition();
				DestructibleEntity newPowerup = new HeartPowerup(getScreenWidth(), newPowerupInitialYPosition);
				getEntityHandler().addPowerup(newPowerup);
			}
		}
	}

	/**
	 * Instantiates the level view.
	 *
	 * @return the level view
	 */
	protected abstract LevelView instantiateLevelView();

	/**
	 * Wins the game.
	 */
	protected void winGame() {
		m_Timeline.stop();
		EndMenu.showEndMenu(m_Root, true);
	}

	/**
	 * Loses the game.
	 */
	protected void loseGame() {
		m_Timeline.stop();
		EndMenu.showEndMenu(m_Root, false);
	}

	/**
	 * Pauses the game.
	 */
	public void pauseGame() {
		m_Timeline.pause();
	}

	/**
	 * Resumes the game.
	 */
	public void resumeGame() {
		m_Timeline.play();
	}

	/**
	 * Checks if the user is destroyed.
	 *
	 * @return true if the user is destroyed, false otherwise
	 */
	protected boolean isUserDestroyed() {
		return getUser().isDestroyed();
	}

	/**
	 * Returns the user plane.
	 *
	 * @return the user plane
	 */
	UserPlane getUser() {
		return m_DestructibleEntityHandler.getUser();
	}

	/**
	 * Returns the root group.
	 *
	 * @return the root group
	 */
	Group getRoot() {
		return m_Root;
	}

	/**
	 * Returns the entity handler.
	 *
	 * @return the entity handler
	 */
	DestructibleEntityHandler getEntityHandler() {
		return m_DestructibleEntityHandler;
	}

	/**
	 * Returns the maximum Y position for enemies.
	 *
	 * @return the maximum Y position for enemies
	 */
	double getEnemyMaximumYPosition() {
		return m_EnemyMaximumYPosition;
	}

	/**
	 * Returns the screen width.
	 *
	 * @return the screen width
	 */
	double getScreenWidth() {
		return m_ScreenWidth;
	}

	/**
	 * Updates the scene.
	 */
	private void updateScene() {
		spawnEnemyUnits(DEFAULT_MAX_ENEMIES_ON_SCREEN, DEFAULT_ENEMY_SPAWN_PROBABILITY, DEFAULT_ENEMY_FIRE_RATE);
		spawnPowerups(DEFAULT_MAX_POWERUPS_ON_SCREEN, DEFAULT_POWERUP_SPAWN_PROBABILITY);
		m_DestructibleEntityHandler.updateEntity();
		m_DestructibleEntityHandler.generateEnemyFire();
		updateNumberOfEnemies();
		m_CollisionHandler.handleEnemyPenetration(m_DestructibleEntityHandler.getEnemyUnits(), getUser(), m_ScreenWidth);
		m_CollisionHandler.handleUserProjectileCollisions(m_DestructibleEntityHandler.getUserProjectiles(), m_DestructibleEntityHandler.getEnemyUnits());
		m_CollisionHandler.handleEnemyProjectileCollisions(m_DestructibleEntityHandler.getEnemyProjectiles(), m_DestructibleEntityHandler.getFriendlyUnits());
		m_CollisionHandler.handlePlaneCollisions(m_DestructibleEntityHandler.getFriendlyUnits(), m_DestructibleEntityHandler.getEnemyUnits());
		m_CollisionHandler.handlePowerupCollisions(m_DestructibleEntityHandler.getPowerups(), getUser());
		m_DestructibleEntityHandler.removeAllDestroyedEntities();
		updateKillCount();
		updateLevelView();
		isGameOver();
	}

	/**
	 * Initializes the timeline.
	 */
	private void initializeTimeline() {
		m_Timeline.setCycleCount(Timeline.INDEFINITE);
		KeyFrame gameLoop = new KeyFrame(Duration.millis(MILLISECOND_DELAY), e -> updateScene());
		m_Timeline.getKeyFrames().add(gameLoop);
	}

	/**
	 * Initializes the background.
	 */
	private void initializeBackground() {
		m_Background.setFocusTraversable(true);
		m_Background.setFitHeight(m_ScreenHeight);
		m_Background.setFitWidth(m_ScreenWidth);
		m_Root.getChildren().add(m_Background);
	}

	/**
	 * Updates the level view.
	 */
	private void updateLevelView() {
		m_LevelView.removeHearts(getUser().getHealth());
	}

	/**
	 * Updates the kill count.
	 */
	private void updateKillCount() {
		for (int i = 0; i < m_CurrentNumberOfEnemies - m_DestructibleEntityHandler.getEnemyUnits().size(); i++) {
			getUser().incrementKillCount();
		}
	}

	/**
	 * Updates the number of enemies.
	 */
	private void updateNumberOfEnemies() {
		m_CurrentNumberOfEnemies = m_DestructibleEntityHandler.getEnemyUnits().size();
	}
}
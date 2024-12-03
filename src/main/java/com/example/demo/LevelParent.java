package com.example.demo;

import java.util.*;
import javafx.animation.*;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.image.*;
import javafx.util.Duration;

public abstract class LevelParent extends Observable {

	private static final double SCREEN_HEIGHT_ADJUSTMENT = 150;
	private static final int MILLISECOND_DELAY = 50;
	private final double m_ScreenHeight;
	private final double m_ScreenWidth;
	private final double m_EnemyMaximumYPosition;

	private final Group m_Root;
	private final Timeline m_Timeline;
	private final Scene m_Scene;
	private final ImageView m_Background;
	private CollisionHandler m_CollisionHandler;
	private ControlsHandler m_ControlsHandler;
	private final DestructibleEntityHandler m_DestructibleEntityHandler;

	private int m_CurrentNumberOfEnemies;
	private LevelView m_LevelView;

	public LevelParent(String backgroundImageName, double screenHeight, double screenWidth, int playerInitialHealth) {
		this.m_Root = new Group();
		this.m_Scene = new Scene(m_Root, screenWidth, screenHeight);
		this.m_Timeline = new Timeline();
		this.m_CollisionHandler = new CollisionHandler();
		this.m_DestructibleEntityHandler = new DestructibleEntityHandler(m_Root, playerInitialHealth);
		this.m_ControlsHandler = new ControlsHandler(this);
		this.m_Background = new ImageView(new Image(getClass().getResource(backgroundImageName).toExternalForm()));
		this.m_ScreenHeight = screenHeight;
		this.m_ScreenWidth = screenWidth;
		this.m_EnemyMaximumYPosition = screenHeight - SCREEN_HEIGHT_ADJUSTMENT;
		this.m_LevelView = instantiateLevelView();
		this.m_CurrentNumberOfEnemies = 0;
		initializeTimeline();
	}

	protected UserPlane getUser() {
		return m_DestructibleEntityHandler.getUser();
	}

	public Group getRoot() {
		return m_Root;
	}

	public DestructibleEntityInterface getEntityHandler() {
		return m_DestructibleEntityHandler;
	}

	protected double getEnemyMaximumYPosition() {
		return m_EnemyMaximumYPosition;
	}

	public double getScreenWidth() {
		return m_ScreenWidth;
	}

	protected abstract void initializeFriendlyUnits();

	protected abstract void isGameOver();

	protected abstract void spawnEnemyUnits();

	protected abstract LevelView instantiateLevelView();

	public Scene initializeScene() {
		initializeBackground();
		m_ControlsHandler.createControlsListeners(m_Background, getUser(), m_Timeline);
		initializeFriendlyUnits();
		m_LevelView.showHeartDisplay();
		return m_Scene;
	}

	public void startGame() {
		m_Background.requestFocus();
		m_Timeline.play();
	}

	public void goToNextLevel(String levelName) {
		getUser().destroy(); //all entities are removed and destroyed except user, so destroy user before next level to fix high memory usage on level up
		m_Timeline.stop();
		m_Timeline.getKeyFrames().clear();
		setChanged();
		notifyObservers(levelName);
	}

	protected void winGame() {
		m_Timeline.stop();
		m_LevelView.showWinImage();
	}

	protected void loseGame() {
		m_Timeline.stop();
		m_LevelView.showGameOverImage();
	}

	protected boolean isUserDestroyed() {
		return getUser().isDestroyed();
	}

	private void updateScene() {
		spawnEnemyUnits();
		m_DestructibleEntityHandler.updateEntity();
		m_DestructibleEntityHandler.generateEnemyFire();
		updateNumberOfEnemies();
		m_CollisionHandler.handleEnemyPenetration(m_DestructibleEntityHandler.getEnemyUnits(), getUser(), m_ScreenWidth);
		m_CollisionHandler.handleUserProjectileCollisions(m_DestructibleEntityHandler.getUserProjectiles(), m_DestructibleEntityHandler.getEnemyUnits());
		m_CollisionHandler.handleEnemyProjectileCollisions(m_DestructibleEntityHandler.getEnemyProjectiles(), m_DestructibleEntityHandler.getFriendlyUnits());
		m_CollisionHandler.handlePlaneCollisions(m_DestructibleEntityHandler.getFriendlyUnits(), m_DestructibleEntityHandler.getEnemyUnits());
		m_DestructibleEntityHandler.removeAllDestroyedEntities();
		updateKillCount();
		updateLevelView();
		isGameOver();
	}

	private void initializeTimeline() {
		m_Timeline.setCycleCount(Timeline.INDEFINITE);
		KeyFrame gameLoop = new KeyFrame(Duration.millis(MILLISECOND_DELAY), e -> updateScene());
		m_Timeline.getKeyFrames().add(gameLoop);
	}

	private void initializeBackground() {
		m_Background.setFocusTraversable(true);
		m_Background.setFitHeight(m_ScreenHeight);
		m_Background.setFitWidth(m_ScreenWidth);
		m_Root.getChildren().add(m_Background);
	}

	protected void fireProjectile() {
		DestructibleEntity projectile = getUser().fireProjectile();
		m_Root.getChildren().add(projectile);
		m_DestructibleEntityHandler.getUserProjectiles().add(projectile);
	}

	private void updateLevelView() {
		m_LevelView.removeHearts(getUser().getHealth());
	}

	private void updateKillCount() {
		for (int i = 0; i < m_CurrentNumberOfEnemies - m_DestructibleEntityHandler.getEnemyUnits().size(); i++) {
			getUser().incrementKillCount();
		}
	}

	private void updateNumberOfEnemies() {
		m_CurrentNumberOfEnemies = m_DestructibleEntityHandler.getEnemyUnits().size();
	}
}
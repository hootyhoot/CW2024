package com.example.demo;

import java.util.*;
import java.util.stream.Collectors;

import javafx.animation.*;
import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.image.*;
import javafx.scene.input.*;
import javafx.util.Duration;

public abstract class LevelParent extends Observable {

	private static final double SCREEN_HEIGHT_ADJUSTMENT = 150;
	private static final int MILLISECOND_DELAY = 50;
	private final double m_ScreenHeight;
	private final double m_ScreenWidth;
	private final double m_EnemyMaximumYPosition;

	private final Group m_Root;
	private final Timeline m_Timeline;
	private final UserPlane m_User;
	private final Scene m_Scene;
	private final ImageView m_Background;

	private final List<ActiveActorDestructible> m_FriendlyUnits; //extract to new class
	private final List<ActiveActorDestructible> m_EnemyUnits;
	private final List<ActiveActorDestructible> m_UserProjectiles;
	private final List<ActiveActorDestructible> m_EnemyProjectiles;
	
	private int m_CurrentNumberOfEnemies;
	private LevelView m_LevelView;

	public LevelParent(String backgroundImageName, double screenHeight, double screenWidth, int playerInitialHealth) {
		this.m_Root = new Group();
		this.m_Scene = new Scene(m_Root, screenWidth, screenHeight);
		this.m_Timeline = new Timeline();
		this.m_User = new UserPlane(playerInitialHealth);
		this.m_FriendlyUnits = new ArrayList<>(); //shave down const
		this.m_EnemyUnits = new ArrayList<>();
		this.m_UserProjectiles = new ArrayList<>();
		this.m_EnemyProjectiles = new ArrayList<>();

		this.m_Background = new ImageView(new Image(getClass().getResource(backgroundImageName).toExternalForm()));
		this.m_ScreenHeight = screenHeight;
		this.m_ScreenWidth = screenWidth;
		this.m_EnemyMaximumYPosition = screenHeight - SCREEN_HEIGHT_ADJUSTMENT;
		this.m_LevelView = instantiateLevelView();
		this.m_CurrentNumberOfEnemies = 0;
		initializeTimeline();
		m_FriendlyUnits.add(m_User);
	}

	protected UserPlane getUser() {
		return m_User;
	}

	protected Group getRoot() {
		return m_Root;
	}

	protected int getCurrentNumberOfEnemies() {
		return m_EnemyUnits.size();
	}

	protected double getEnemyMaximumYPosition() {
		return m_EnemyMaximumYPosition;
	}

	protected double getScreenWidth() {
		return m_ScreenWidth;
	}

	protected abstract void initializeFriendlyUnits();

	protected abstract void isGameOver();

	protected abstract void spawnEnemyUnits();

	protected abstract LevelView instantiateLevelView();

	public Scene initializeScene() {
		initializeBackground();
		initializeFriendlyUnits();
		m_LevelView.showHeartDisplay();
		return m_Scene;
	}

	public void startGame() {
		m_Background.requestFocus();
		m_Timeline.play();
	}

	public void goToNextLevel(String levelName) {
		m_User.destroy(); //all actors are removed and destroyed except user, so destroy user before next level to fix high memory usage on level up
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


	protected void addEnemyUnit(ActiveActorDestructible enemy) {
		m_EnemyUnits.add(enemy);
		m_Root.getChildren().add(enemy);
	}

	protected boolean isUserDestroyed() {
		return m_User.isDestroyed();
	}

	private void updateScene() {
		spawnEnemyUnits();
		updateActors();
		generateEnemyFire();
		updateNumberOfEnemies();
		handleEnemyPenetration();
		handleUserProjectileCollisions();
		handleEnemyProjectileCollisions();
		handlePlaneCollisions();
		removeAllDestroyedActors();
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
		m_Background.setOnKeyPressed(new EventHandler<KeyEvent>() {
			public void handle(KeyEvent e) { //move out of this func to user extract
				KeyCode kc = e.getCode();
				if (kc == KeyCode.UP) m_User.moveUp();
				if (kc == KeyCode.DOWN) m_User.moveDown();
				if (kc == KeyCode.RIGHT) m_User.moveRight();
				if (kc == KeyCode.LEFT) m_User.moveLeft();
				if (kc == KeyCode.SPACE) fireProjectile();
			}
		});
		m_Background.setOnKeyReleased(new EventHandler<KeyEvent>() {
			public void handle(KeyEvent e) {
				KeyCode kc = e.getCode();
				if (kc == KeyCode.UP || kc == KeyCode.DOWN) m_User.stopVertically();
				if (kc == KeyCode.LEFT || kc == KeyCode.RIGHT) m_User.stopHorizontally();
			}
		});
		m_Root.getChildren().add(m_Background);
	}

	private void fireProjectile() {
		ActiveActorDestructible projectile = m_User.fireProjectile();
		m_Root.getChildren().add(projectile);
		m_UserProjectiles.add(projectile);
	}

	private void generateEnemyFire() {
		m_EnemyUnits.forEach(enemy -> spawnEnemyProjectile(((FighterPlane) enemy).fireProjectile()));
	}

	private void spawnEnemyProjectile(ActiveActorDestructible projectile) {
		if (projectile != null) {
			m_Root.getChildren().add(projectile);
			m_EnemyProjectiles.add(projectile);
		}
	}

	private void updateActors() {
		m_FriendlyUnits.forEach(plane -> plane.updateActor());
		m_EnemyUnits.forEach(enemy -> enemy.updateActor());
		m_UserProjectiles.forEach(projectile -> projectile.updateActor());
		m_EnemyProjectiles.forEach(projectile -> projectile.updateActor());
	}

	private void removeAllDestroyedActors() {
		removeDestroyedActors(m_FriendlyUnits);
		removeDestroyedActors(m_EnemyUnits);
		removeDestroyedActors(m_UserProjectiles);
		removeDestroyedActors(m_EnemyProjectiles);
	}

	private void removeDestroyedActors(List<ActiveActorDestructible> actors) {
		List<ActiveActorDestructible> destroyedActors = actors.stream().filter(actor -> actor.isDestroyed())
				.collect(Collectors.toList());
		m_Root.getChildren().removeAll(destroyedActors);
		actors.removeAll(destroyedActors);
	}

	private void handlePlaneCollisions() {
		handleCollisions(m_FriendlyUnits, m_EnemyUnits);
	}

	private void handleUserProjectileCollisions() {
		handleCollisions(m_UserProjectiles, m_EnemyUnits);
	}

	private void handleEnemyProjectileCollisions() {
		handleCollisions(m_EnemyProjectiles, m_FriendlyUnits);
	}

	private void handleCollisions(List<ActiveActorDestructible> actors1,
			List<ActiveActorDestructible> actors2) { //extract to new class
		for (ActiveActorDestructible actor : actors2) {
			for (ActiveActorDestructible otherActor : actors1) {
				if (actor.getBoundsInParent().intersects(otherActor.getBoundsInParent())) {
					actor.takeDamage();
					otherActor.takeDamage();
				}
			}
		}
	}

	private void handleEnemyPenetration() {
		for (ActiveActorDestructible enemy : m_EnemyUnits) {
			if (enemyHasPenetratedDefenses(enemy)) {
				m_User.takeDamage();
				enemy.destroy();
			}
		}
	}

	private void updateLevelView() {
		m_LevelView.removeHearts(m_User.getHealth());
	}

	private void updateKillCount() {
		for (int i = 0; i < m_CurrentNumberOfEnemies - m_EnemyUnits.size(); i++) {
			m_User.incrementKillCount();
		}
	}

	private boolean enemyHasPenetratedDefenses(ActiveActorDestructible enemy) {
		return Math.abs(enemy.getTranslateX()) > m_ScreenWidth;
	}

	private void updateNumberOfEnemies() {
		m_CurrentNumberOfEnemies = m_EnemyUnits.size();
	}

}

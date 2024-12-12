package com.example.demo.levels;

import com.example.demo.gui.LevelView;
import com.example.demo.entities.Boss;

/**
 * Represents the second level of the game.
 * This class extends `LevelParent` and sets up the specific parameters and behaviors for level two.
 */
public class LevelTwo extends LevelParent {

	/**
	 * The background image name for level two.
	 */
	private static final String BACKGROUND_IMAGE_NAME = "/com/example/demo/images/background2.jpg";

	/**
	 * The name of the next level.
	 */
	private static final String NEXT_LEVEL = "com.example.demo.levels.LevelThree";

	/**
	 * The initial health of the player.
	 */
	private static final int PLAYER_INITIAL_HEALTH = 5;

	/**
	 * The fire rate of the boss.
	 */
	public static final double BOSS_FIRE_RATE = 0.01;

	/**
	 * The boss entity.
	 */
	private final Boss m_Boss;

	/**
	 * Constructs a new LevelTwo instance.
	 *
	 * @param screenHeight the height of the screen
	 * @param screenWidth the width of the screen
	 */
	public LevelTwo(double screenHeight, double screenWidth) {
		super(BACKGROUND_IMAGE_NAME, screenHeight, screenWidth, PLAYER_INITIAL_HEALTH);
		m_Boss = new Boss(BOSS_FIRE_RATE);
	}

	/**
	 * Initializes friendly units by adding the user to the root.
	 */
	@Override
	protected void initializeFriendlyUnits() {
		getRoot().getChildren().add(getUser());
	}

	/**
	 * Checks if the game is over.
	 * If the user is destroyed, the game is lost.
	 * If the boss is destroyed, advances to the next level.
	 */
	@Override
	protected void isGameOver() {
		if (isUserDestroyed()) {
			loseGame();
		} else if (m_Boss.isDestroyed()) {
			goToNextLevel(NEXT_LEVEL);
		}
	}

	/**
	 * Spawns enemy units with the specified parameters.
	 * Adds the boss to the enemy units if there are no current enemies.
	 *
	 * @param maxEnemiesOnScreen the maximum number of enemies on screen
	 * @param enemySpawnProbability the probability of spawning an enemy
	 * @param enemyFireRate the fire rate of the enemy
	 */
	@Override
	protected void spawnEnemyUnits(int maxEnemiesOnScreen, double enemySpawnProbability, double enemyFireRate) {
		if (getEntityHandler().getCurrentNumberOfEnemies() == 0) {
			getEntityHandler().addEnemyUnit(m_Boss);
			getRoot().getChildren().add(m_Boss.getShieldImage());
		}
	}

	/**
	 * Spawns powerups with the specified parameters.
	 * No powerups are spawned in level two.
	 *
	 * @param maxPowerupsOnScreen the maximum number of powerups on screen
	 * @param powerupSpawnProbability the probability of spawning a powerup
	 */
	@Override
	protected void spawnPowerups(int maxPowerupsOnScreen, double powerupSpawnProbability) {
		// No powerups in level two
	}

	/**
	 * Instantiates the level view.
	 *
	 * @return the instantiated level view
	 */
	@Override
	protected LevelView instantiateLevelView() {
		return new LevelView(getRoot(), PLAYER_INITIAL_HEALTH);
	}
}
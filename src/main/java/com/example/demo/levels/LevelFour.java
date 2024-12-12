package com.example.demo.levels;

import com.example.demo.gui.LevelView;
import com.example.demo.entities.Boss;

/**
 * Represents the fourth level of the game.
 * This class extends `LevelParent` and sets up the specific parameters and behaviors for level four.
 *
 * @see <a href="https://github.com/hootyhoot/CW2024/blob/master/src/main/java/com/example/demo/levels/LevelFour.java">Source code</a>
 */
public class LevelFour extends LevelParent {

    /**
     * The background image name for level four.
     */
    private static final String BACKGROUND_IMAGE_NAME = "/com/example/demo/images/background2.jpg";

    /**
     * The initial health of the player.
     */
    private static final int PLAYER_INITIAL_HEALTH = 5;

    /**
     * The maximum number of powerups on screen.
     */
    private static final int MAX_POWERUPS_ON_SCREEN = 2;

    /**
     * The maximum number of enemies on screen.
     */
    private static final int MAX_ENEMIES_ON_SCREEN = 3;

    /**
     * The probability of spawning a powerup.
     */
    private static final double POWERUP_SPAWN_PROBABILITY = .05;

    /**
     * The number of kills required to advance to the next level.
     */
    private static final int KILLS_TO_ADVANCE = 3;

    /**
     * The fire rate of the boss.
     */
    public static final double BOSS_FIRE_RATE = 0.02;

    /**
     * Constructs a new LevelFour instance.
     *
     * @param screenHeight the height of the screen
     * @param screenWidth the width of the screen
     */
    public LevelFour(double screenHeight, double screenWidth) {
        super(BACKGROUND_IMAGE_NAME, screenHeight, screenWidth, PLAYER_INITIAL_HEALTH);
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
     * If the user has reached the kill target, the game is won.
     */
    @Override
    protected void isGameOver() {
        if (isUserDestroyed()) {
            loseGame();
        } else if (isUserAtKillTarget()) {
            winGame();
        }
    }

    /**
     * Checks if the user has reached the kill target.
     *
     * @return true if the user has reached the kill target, false otherwise
     */
    private boolean isUserAtKillTarget() {
        return getUser().getNumberOfKills() >= KILLS_TO_ADVANCE;
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
        int currentNumberOfEnemies = getEntityHandler().getCurrentNumberOfEnemies();
        for (int i = 0; i < MAX_ENEMIES_ON_SCREEN - currentNumberOfEnemies - getUser().getNumberOfKills(); i++) {
            Boss m_Boss = new Boss(BOSS_FIRE_RATE);
            getEntityHandler().addEnemyUnit(m_Boss);
            getRoot().getChildren().add(m_Boss.getShieldImage());
        }
    }

    /**
     * Spawns powerups with the specified parameters.
     *
     * @param maxPowerupsOnScreen the maximum number of powerups on screen
     * @param powerupSpawnProbability the probability of spawning a powerup
     */
    @Override
    protected void spawnPowerups(int maxPowerupsOnScreen, double powerupSpawnProbability) {
        super.spawnPowerups(MAX_POWERUPS_ON_SCREEN, POWERUP_SPAWN_PROBABILITY);
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
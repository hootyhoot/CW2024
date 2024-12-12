package com.example.demo.levels;

import com.example.demo.gui.LevelView;

/**
 * Represents the third level of the game.
 * This class extends `LevelParent` and sets up the specific parameters and behaviors for level three.
 */
public class LevelThree extends LevelParent {

    /**
     * The background image name for level three.
     */
    private static final String BACKGROUND_IMAGE_NAME = "/com/example/demo/images/background1.jpg";

    /**
     * The name of the next level.
     */
    private static final String NEXT_LEVEL = "com.example.demo.levels.LevelFour";

    /**
     * The maximum number of enemies on screen.
     */
    private static final int MAX_ENEMIES_ON_SCREEN = 7;

    /**
     * The maximum number of powerups on screen.
     */
    private static final int MAX_POWERUPS_ON_SCREEN = 4;

    /**
     * The number of kills required to advance to the next level.
     */
    private static final int KILLS_TO_ADVANCE = 15; //set to 30 after testing

    /**
     * The probability of spawning an enemy.
     */
    private static final double ENEMY_SPAWN_PROBABILITY = .40;

    /**
     * The probability of spawning a powerup.
     */
    private static final double POWERUP_SPAWN_PROBABILITY = .05;

    /**
     * The initial health of the player.
     */
    private static final int PLAYER_INITIAL_HEALTH = 3;

    /**
     * The fire rate of the enemy.
     */
    private static final double ENEMY_FIRE_RATE = 0.02;

    /**
     * Constructs a new LevelThree instance.
     *
     * @param screenHeight the height of the screen
     * @param screenWidth the width of the screen
     */
    public LevelThree(double screenHeight, double screenWidth) {
        super(BACKGROUND_IMAGE_NAME, screenHeight, screenWidth, PLAYER_INITIAL_HEALTH);
    }

    /**
     * Checks if the game is over.
     * If the user is destroyed, the game is lost.
     * If the user has reached the kill target, advances to the next level.
     */
    @Override
    protected void isGameOver() {
        if (isUserDestroyed()) {
            loseGame();
        } else if (isUserAtKillTarget()) {
            goToNextLevel(NEXT_LEVEL);
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
     * Initializes friendly units by adding the user to the root.
     */
    @Override
    protected void initializeFriendlyUnits() {
        getRoot().getChildren().add(getUser());
    }

    /**
     * Spawns enemy units with the specified parameters.
     *
     * @param maxEnemiesOnScreen the maximum number of enemies on screen
     * @param enemySpawnProbability the probability of spawning an enemy
     * @param enemyFireRate the fire rate of the enemy
     */
    @Override
    protected void spawnEnemyUnits(int maxEnemiesOnScreen, double enemySpawnProbability, double enemyFireRate) {
        super.spawnEnemyUnits(MAX_ENEMIES_ON_SCREEN, ENEMY_SPAWN_PROBABILITY, ENEMY_FIRE_RATE);
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
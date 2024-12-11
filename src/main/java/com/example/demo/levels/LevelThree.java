package com.example.demo.levels;

import com.example.demo.gui.LevelView;
import com.example.demo.entities.DestructibleEntity;
import com.example.demo.entities.EnemyPlane;
import com.example.demo.entities.HeartPowerup;

public class LevelThree extends LevelParent {

    private static final String BACKGROUND_IMAGE_NAME = "/com/example/demo/images/background1.jpg";
    private static final String NEXT_LEVEL = "com.example.demo.levels.LevelFour";
    private static final int MAX_ENEMIES_ON_SCREEN = 7;
    private static final int MAX_POWERUPS_ON_SCREEN = 4;
    private static final int KILLS_TO_ADVANCE = 10; //set to 30 after testing
    private static final double ENEMY_SPAWN_PROBABILITY = .40;
    private static final double POWERUP_SPAWN_PROBABILITY = .05;
    private static final int PLAYER_INITIAL_HEALTH = 3;

    public LevelThree(double screenHeight, double screenWidth) {
        super(BACKGROUND_IMAGE_NAME, screenHeight, screenWidth, PLAYER_INITIAL_HEALTH);
    }

    @Override
    protected void isGameOver() {
        if (isUserDestroyed()) {
            loseGame();
        } else if (isUserAtKillTarget()) {
//            goToNextLevel(NEXT_LEVEL);
            winGame();
        }
    }

    private boolean isUserAtKillTarget() {
        return getUser().getNumberOfKills() >= KILLS_TO_ADVANCE;
    }

    @Override
    protected void initializeFriendlyUnits() {
        getRoot().getChildren().add(getUser());
    }

    @Override
    protected void spawnEnemyUnits() {
        int currentNumberOfEnemies = getEntityHandler().getCurrentNumberOfEnemies();
        for (int i = 0; i < MAX_ENEMIES_ON_SCREEN - currentNumberOfEnemies; i++) {
            if (Math.random() < ENEMY_SPAWN_PROBABILITY) {
                double newEnemyInitialYPosition = Math.random() * getEnemyMaximumYPosition();
                DestructibleEntity newEnemy = new EnemyPlane(getScreenWidth(), newEnemyInitialYPosition, 0.02);
                getEntityHandler().addEnemyUnit(newEnemy);
            }
        }
    }

    @Override
    protected void spawnPowerups() {
        int currentNumberOfPowerups = getEntityHandler().getCurrentNumberOfPowerups();
        for (int i = 0; i < MAX_POWERUPS_ON_SCREEN - currentNumberOfPowerups; i++) {
            if (Math.random() < POWERUP_SPAWN_PROBABILITY) {
                double newPowerupInitialYPosition = Math.random() * getEnemyMaximumYPosition();
                DestructibleEntity newPowerup = new HeartPowerup(getScreenWidth(), newPowerupInitialYPosition);
                getEntityHandler().addPowerup(newPowerup);
            }
        }
    }

    @Override
    protected LevelView instantiateLevelView() {
        return new LevelView(getRoot(), PLAYER_INITIAL_HEALTH);
    }
}
package com.example.demo.entities;

import com.example.demo.gui.LevelView;

/**
 * Represents a heart power-up in the game.
 *
 * @see <a href="https://github.com/hootyhoot/CW2024/blob/master/src/main/java/com/example/demo/entities/HeartPowerup.java">Source code</a>
 */
public class HeartPowerup extends Powerup {

    /**
     * The image name for the heart power-up.
     */
    private static final String IMAGE_NAME = "heart.png";

    /**
     * The height of the heart power-up image.
     */
    private static final int IMAGE_HEIGHT = 50;

    /**
     * The horizontal velocity of the heart power-up.
     */
    private static final int HORIZONTAL_VELOCITY = -5;

    /**
     * Constructs a HeartPowerup with the specified initial position.
     *
     * @param initialXPos the initial X position of the heart power-up
     * @param initialYPos the initial Y position of the heart power-up
     */
    public HeartPowerup(double initialXPos, double initialYPos) {
        super(IMAGE_NAME, IMAGE_HEIGHT, initialXPos, initialYPos);
    }

    /**
     * Updates the HeartPowerup entity, including its position.
     */
    @Override
    public void updateEntity() {
        updatePosition();
    }

    /**
     * Applies the effect of the heart power-up, adding a heart to the level view and incrementing the user's plane health.
     *
     * @param levelView the level view to which the heart is added
     * @param userPlane the user's plane whose health is incremented
     */
    @Override
    public void applyEffect(LevelView levelView, UserPlane userPlane) {
        if (levelView != null && userPlane != null) {
            levelView.addHearts(1);
            userPlane.incrementHealth();
        }
    }

    /**
     * Updates the position of the HeartPowerup by moving it horizontally.
     */
    @Override
    public void updatePosition() {
        moveHorizontally(HORIZONTAL_VELOCITY);
    }
}
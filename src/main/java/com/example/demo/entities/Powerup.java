package com.example.demo.entities;

import com.example.demo.gui.LevelView;

/**
 * Represents a power-up in the game.
 *
 * @see <a href="https://github.com/hootyhoot/CW2024/blob/master/src/main/java/com/example/demo/entities/Powerup.java">Source code</a>
 */
public abstract class Powerup extends DestructibleEntity {

    /**
     * Constructs a Powerup with the specified image name, image height, and initial position.
     *
     * @param imageName the name of the image representing the power-up
     * @param imageHeight the height of the image
     * @param initialXPos the initial X position of the power-up
     * @param initialYPos the initial Y position of the power-up
     */
    public Powerup(String imageName, int imageHeight, double initialXPos, double initialYPos) {
        super(imageName, imageHeight, initialXPos, initialYPos);
    }

    /**
     * Applies the effect of the power-up.
     *
     * @param levelView the level view to which the effect is applied
     * @param userPlane the user's plane to which the effect is applied
     */
    public abstract void applyEffect(LevelView levelView, UserPlane userPlane);

    /**
     * Takes damage, destroying the power-up.
     */
    @Override
    public void takeDamage() {
        this.destroy();
    }

    /**
     * Updates the position of the power-up.
     */
    @Override
    public void updatePosition() {
    }
}
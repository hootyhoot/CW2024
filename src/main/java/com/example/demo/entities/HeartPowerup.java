package com.example.demo.entities;

import com.example.demo.gui.LevelView;

public class HeartPowerup extends Powerup {

    private static final String IMAGE_NAME = "heart.png";
    private static final int IMAGE_HEIGHT = 50;
    private static final int HORIZONTAL_VELOCITY = -5;

    public HeartPowerup(double initialXPos, double initialYPos) {
        super(IMAGE_NAME, IMAGE_HEIGHT, initialXPos, initialYPos);
    }

    @Override
    public void updateEntity() {
        updatePosition();
    }

    @Override
    public void applyEffect(LevelView levelView, UserPlane userPlane) {
        if (levelView != null && userPlane != null) {
            levelView.addHearts(1);
            userPlane.incrementHealth();
        }
    }

    @Override
    public void updatePosition() {
        moveHorizontally(HORIZONTAL_VELOCITY);
    }
}
package com.example.demo.entities;

import com.example.demo.gui.LevelView;

public abstract class Powerup extends DestructibleEntity {

    public Powerup(String imageName, int imageHeight, double initialXPos, double initialYPos) {
        super(imageName, imageHeight, initialXPos, initialYPos);
    }

    public abstract void applyEffect(LevelView levelView, UserPlane userPlane);

    @Override
    public void takeDamage() {
        this.destroy();
    }

    @Override
    public void updatePosition() {
    }
}
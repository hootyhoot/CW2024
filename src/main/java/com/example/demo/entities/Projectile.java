package com.example.demo.entities;

public abstract class Projectile extends DestructibleEntity {

	public Projectile(String imageName, int imageHeight, double initialXPos, double initialYPos) {
		super(imageName, imageHeight, initialXPos, initialYPos);
	}

	@Override
	public void takeDamage() {
		this.destroy();
	}

	@Override
	abstract void updatePosition();
}

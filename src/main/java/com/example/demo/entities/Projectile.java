package com.example.demo.entities;

/**
 * Represents a projectile in the game.
 */
public abstract class Projectile extends DestructibleEntity {

	/**
	 * Constructs a Projectile with the specified image name, image height, and initial position.
	 *
	 * @param imageName the name of the image representing the projectile
	 * @param imageHeight the height of the image
	 * @param initialXPos the initial X position of the projectile
	 * @param initialYPos the initial Y position of the projectile
	 */
	public Projectile(String imageName, int imageHeight, double initialXPos, double initialYPos) {
		super(imageName, imageHeight, initialXPos, initialYPos);
	}

	/**
	 * Takes damage, destroying the projectile.
	 */
	@Override
	public void takeDamage() {
		this.destroy();
	}

	/**
	 * Updates the position of the projectile.
	 */
	@Override
	abstract void updatePosition();
}
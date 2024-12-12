package com.example.demo.entities;

/**
 * Represents a projectile fired by the user in the game.
 */
public class UserProjectile extends Projectile {

	/**
	 * The image name for the user's projectile.
	 */
	private static final String IMAGE_NAME = "userProjectile.png";

	/**
	 * The height of the user's projectile image.
	 */
	private static final int IMAGE_HEIGHT = 12;

	/**
	 * The horizontal velocity of the user's projectile.
	 */
	private static final int HORIZONTAL_VELOCITY = 15;

	/**
	 * Constructs a UserProjectile with the specified initial position.
	 *
	 * @param initialXPos the initial X position of the projectile
	 * @param initialYPos the initial Y position of the projectile
	 */
	public UserProjectile(double initialXPos, double initialYPos) {
		super(IMAGE_NAME, IMAGE_HEIGHT, initialXPos, initialYPos);
	}

	/**
	 * Updates the UserProjectile entity, including its position.
	 */
	@Override
	public void updateEntity() {
		updatePosition();
	}

	/**
	 * Updates the position of the UserProjectile.
	 */
	@Override
	void updatePosition() {
		moveHorizontally(HORIZONTAL_VELOCITY);
	}
}
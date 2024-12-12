package com.example.demo.entities;

/**
 * Represents a projectile fired by the Boss entity.
 *
 * @see <a href="https://github.com/hootyhoot/CW2024/blob/master/src/main/java/com/example/demo/entities/BossProjectile.java">Source code</a>
 */
public class BossProjectile extends Projectile {

	/**
	 * The image name for the Boss projectile.
	 */
	private static final String IMAGE_NAME = "fireball.png";

	/**
	 * The height of the Boss projectile image.
	 */
	private static final int IMAGE_HEIGHT = 75;

	/**
	 * The horizontal velocity of the Boss projectile.
	 */
	private static final int HORIZONTAL_VELOCITY = -15;

	/**
	 * The initial X position of the Boss projectile.
	 */
	private static final int INITIAL_X_POSITION = 950;

	/**
	 * Constructs a BossProjectile with the specified initial Y position.
	 *
	 * @param initialYPos the initial Y position of the projectile
	 */
	public BossProjectile(double initialYPos) {
		super(IMAGE_NAME, IMAGE_HEIGHT, INITIAL_X_POSITION, initialYPos);
	}

	/**
	 * Updates the BossProjectile entity, including its position.
	 */
	@Override
	public void updateEntity() {
		updatePosition();
	}

	/**
	 * Updates the position of the BossProjectile by moving it horizontally.
	 */
	@Override
	void updatePosition() {
		moveHorizontally(HORIZONTAL_VELOCITY);
	}
}
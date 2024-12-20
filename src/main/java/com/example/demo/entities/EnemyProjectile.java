package com.example.demo.entities;

/**
 * Represents a projectile fired by an enemy plane.
 *
 * @see <a href="https://github.com/hootyhoot/CW2024/blob/master/src/main/java/com/example/demo/entities/EnemyProjectile.java">Source code</a>
 */
public class EnemyProjectile extends Projectile {

	/**
	 * The image name for the enemy projectile.
	 */
	private static final String IMAGE_NAME = "enemyFire.png";

	/**
	 * The height of the enemy projectile image.
	 */
	private static final int IMAGE_HEIGHT = 25;

	/**
	 * The horizontal velocity of the enemy projectile.
	 */
	private static final int HORIZONTAL_VELOCITY = -10;

	/**
	 * Constructs an EnemyProjectile with the specified initial position.
	 *
	 * @param initialXPos the initial X position of the projectile
	 * @param initialYPos the initial Y position of the projectile
	 */
	public EnemyProjectile(double initialXPos, double initialYPos) {
		super(IMAGE_NAME, IMAGE_HEIGHT, initialXPos, initialYPos);
	}

	/**
	 * Updates the EnemyProjectile entity, including its position.
	 */
	@Override
	public void updateEntity() {
		updatePosition();
	}

	/**
	 * Updates the position of the EnemyProjectile by moving it horizontally.
	 */
	@Override
	void updatePosition() {
		moveHorizontally(HORIZONTAL_VELOCITY);
	}
}
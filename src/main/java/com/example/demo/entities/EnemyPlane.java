package com.example.demo.entities;

/**
 * Represents an enemy plane in the game.
 *
 * @see <a href="https://github.com/hootyhoot/CW2024/blob/master/src/main/java/com/example/demo/entities/EnemyPlane.java">Source code</a>
 */
public class EnemyPlane extends FighterPlane {

	/**
	 * The image name for the enemy plane.
	 */
	private static final String IMAGE_NAME = "enemyPlane.png";

	/**
	 * The height of the enemy plane image.
	 */
	private static final int IMAGE_HEIGHT = 50;

	/**
	 * The horizontal velocity of the enemy plane.
	 */
	private static final int HORIZONTAL_VELOCITY = -6;

	/**
	 * The X position offset for the enemy plane's projectiles.
	 */
	private static final double PROJECTILE_X_POSITION_OFFSET = -100.0;

	/**
	 * The Y position offset for the enemy plane's projectiles.
	 */
	private static final double PROJECTILE_Y_POSITION_OFFSET = 13.0;

	/**
	 * The initial health of the enemy plane.
	 */
	private static final int INITIAL_HEALTH = 1;

	/**
	 * The fire rate of the enemy plane.
	 */
	private final double m_FireRate;

	/**
	 * Constructs an EnemyPlane with the specified initial position and fire rate.
	 *
	 * @param initialXPos the initial X position of the enemy plane
	 * @param initialYPos the initial Y position of the enemy plane
	 * @param fireRate the rate at which the enemy plane fires projectiles
	 */
	public EnemyPlane(double initialXPos, double initialYPos, double fireRate) {
		super(IMAGE_NAME, IMAGE_HEIGHT, initialXPos, initialYPos, INITIAL_HEALTH);
		m_FireRate = fireRate;
	}

	/**
	 * Fires a projectile from the enemy plane.
	 *
	 * @return a new EnemyProjectile if the enemy plane is firing in the current frame, otherwise null
	 */
	@Override
	public DestructibleEntity fireProjectile() {
		if (Math.random() < m_FireRate) {
			double projectileXPosition = getProjectileXPosition(PROJECTILE_X_POSITION_OFFSET);
			double projectileYPosition = getProjectileYPosition(PROJECTILE_Y_POSITION_OFFSET);
			return new EnemyProjectile(projectileXPosition, projectileYPosition);
		}
		return null;
	}

	/**
	 * Updates the enemy plane entity, including its position.
	 */
	@Override
	public void updateEntity() {
		updatePosition();
	}

	/**
	 * Updates the position of the enemy plane by moving it horizontally.
	 */
	@Override
	void updatePosition() {
		moveHorizontally(HORIZONTAL_VELOCITY);
	}
}
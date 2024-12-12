package com.example.demo.entities;

/**
 * Represents the user's plane in the game.
 */
public class UserPlane extends FighterPlane {

	/**
	 * The image name for the user's plane.
	 */
	private static final String IMAGE_NAME = "userPlane.png";

	/**
	 * The upper bound for the Y position of the user's plane.
	 */
	private static final double Y_UPPER_BOUND = 0;

	/**
	 * The lower bound for the Y position of the user's plane.
	 */
	private static final double Y_LOWER_BOUND = 670.0;

	/**
	 * The upper bound for the X position of the user's plane.
	 */
	private static final double X_UPPER_BOUND = 1125.0;

	/**
	 * The lower bound for the X position of the user's plane.
	 */
	private static final double X_LOWER_BOUND = 0;

	/**
	 * The initial X position of the user's plane.
	 */
	private static final double INITIAL_X_POSITION = 5.0;

	/**
	 * The initial Y position of the user's plane.
	 */
	private static final double INITIAL_Y_POSITION = 300.0;

	/**
	 * The height of the user's plane image.
	 */
	private static final int IMAGE_HEIGHT = 50;

	/**
	 * The vertical velocity of the user's plane.
	 */
	private static final int VERTICAL_VELOCITY = 8;

	/**
	 * The horizontal velocity of the user's plane.
	 */
	private static final int HORIZONTAL_VELOCITY = 8;

	/**
	 * The X position of the projectile fired by the user's plane.
	 */
	private static final int PROJECTILE_X_POSITION = 60;

	/**
	 * The Y position offset of the projectile fired by the user's plane.
	 */
	private static final int PROJECTILE_Y_POSITION_OFFSET = 20;

	/**
	 * The horizontal velocity multiplier for the user's plane.
	 */
	private int m_HorizontalVelocityMultiplier;

	/**
	 * The vertical velocity multiplier for the user's plane.
	 */
	private int m_VerticalVelocityMultiplier;

	/**
	 * The number of kills made by the user's plane.
	 */
	private int m_NumberOfKills;

	/**
	 * Constructs a UserPlane with the specified initial health.
	 *
	 * @param initialHealth the initial health of the user's plane
	 */
	public UserPlane(int initialHealth) {
		super(IMAGE_NAME, IMAGE_HEIGHT, INITIAL_X_POSITION, INITIAL_Y_POSITION, initialHealth);
		m_HorizontalVelocityMultiplier = 0;
		m_VerticalVelocityMultiplier = 0;
	}

	/**
	 * Returns the number of kills made by the user's plane.
	 *
	 * @return the number of kills
	 */
	public int getNumberOfKills() {
		return m_NumberOfKills;
	}

	public int getHorizontalVelocityMultiplier() {
		return m_HorizontalVelocityMultiplier;
	}

	public int getVerticalVelocityMultiplier() {
		return m_VerticalVelocityMultiplier;
	}

	/**
	 * Updates the UserPlane entity, including its position.
	 */
	@Override
	public void updateEntity() {
		updatePosition();
	}

	/**
	 * Fires a projectile from the user's plane.
	 *
	 * @return the fired projectile
	 */
	@Override
	public DestructibleEntity fireProjectile() {
		double projectileX = getProjectileXPosition(PROJECTILE_X_POSITION);
		double projectileY = getProjectileYPosition(PROJECTILE_Y_POSITION_OFFSET);
		return new UserProjectile(projectileX, projectileY);
	}

	/**
	 * Increments the health of the user's plane.
	 */
	public void incrementHealth() {
		m_Health++;
	}

	/**
	 * Moves the user's plane up.
	 */
	public void moveUp() {
		m_VerticalVelocityMultiplier = -1;
	}

	/**
	 * Moves the user's plane down.
	 */
	public void moveDown() {
		m_VerticalVelocityMultiplier = 1;
	}

	/**
	 * Moves the user's plane to the right.
	 */
	public void moveRight() {
		m_HorizontalVelocityMultiplier = 1;
	}

	/**
	 * Moves the user's plane to the left.
	 */
	public void moveLeft() {
		m_HorizontalVelocityMultiplier = -1;
	}

	/**
	 * Stops the horizontal movement of the user's plane.
	 */
	public void stopHorizontally() {
		m_HorizontalVelocityMultiplier = 0;
	}

	/**
	 * Stops the vertical movement of the user's plane.
	 */
	public void stopVertically() {
		m_VerticalVelocityMultiplier = 0;
	}

	/**
	 * Increments the kill count of the user's plane.
	 */
	public void incrementKillCount() {
		m_NumberOfKills++;
	}

	/**
	 * Updates the position of the user's plane.
	 */
	@Override
	void updatePosition() {
		if (isMovingVertically()) {
			double initialTranslateY = getTranslateY();
			this.moveVertically(VERTICAL_VELOCITY * m_VerticalVelocityMultiplier);
			double newPosition = getLayoutY() + getTranslateY();
			if (newPosition < Y_UPPER_BOUND || newPosition > Y_LOWER_BOUND) {
				this.setTranslateY(initialTranslateY);
			}
		}
		if (isMovingHorizontally()) {
			double initialTranslateX = getTranslateX();
			this.moveHorizontally(HORIZONTAL_VELOCITY * m_HorizontalVelocityMultiplier);
			double newPosition = getLayoutX() + getTranslateX();
			if (newPosition < X_LOWER_BOUND || newPosition > X_UPPER_BOUND) {
				this.setTranslateX(initialTranslateX);
			}
		}
	}

	/**
	 * Checks if the user's plane is moving horizontally.
	 *
	 * @return true if the plane is moving horizontally, false otherwise
	 */
	private boolean isMovingHorizontally() {
		return m_HorizontalVelocityMultiplier != 0;
	}

	/**
	 * Checks if the user's plane is moving vertically.
	 *
	 * @return true if the plane is moving vertically, false otherwise
	 */
	private boolean isMovingVertically() {
		return m_VerticalVelocityMultiplier != 0;
	}
}
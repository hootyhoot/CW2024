package com.example.demo.entities;

/**
 * Represents a fighter plane in the game.
 *
 * @see <a href="https://github.com/hootyhoot/CW2024/blob/master/src/main/java/com/example/demo/entities/FighterPlane.java">Source code</a>
 */
public abstract class FighterPlane extends DestructibleEntity {

	/**
	 * The health of the fighter plane.
	 */
	protected int m_Health;

	/**
	 * Constructs a FighterPlane with the specified image name, image height, initial position, and health.
	 *
	 * @param imageName the name of the image representing the fighter plane
	 * @param imageHeight the height of the image
	 * @param initialXPos the initial X position of the fighter plane
	 * @param initialYPos the initial Y position of the fighter plane
	 * @param health the initial health of the fighter plane
	 */
	public FighterPlane(String imageName, int imageHeight, double initialXPos, double initialYPos, int health) {
		super(imageName, imageHeight, initialXPos, initialYPos);
		this.m_Health = health;
	}

	/**
	 * Returns the health of the fighter plane.
	 *
	 * @return the health of the fighter plane
	 */
	public int getHealth() {
		return m_Health;
	}

	/**
	 * Takes damage, reducing the health of the fighter plane.
	 * If health reaches zero, the fighter plane is destroyed.
	 */
	@Override
	public void takeDamage() {
		m_Health--;
		if (isHealthAtZero()) {
			this.destroy();
		}
	}

	/**
	 * Fires a projectile from the fighter plane.
	 *
	 * @return a new DestructibleEntity representing the projectile
	 */
	public abstract DestructibleEntity fireProjectile();

	/**
	 * Returns the X position for the projectile, adjusted by the specified offset.
	 *
	 * @param xPositionOffset the offset to apply to the X position
	 * @return the adjusted X position for the projectile
	 */
	double getProjectileXPosition(double xPositionOffset) {
		return getLayoutX() + getTranslateX() + xPositionOffset;
	}

	/**
	 * Returns the Y position for the projectile, adjusted by the specified offset.
	 *
	 * @param yPositionOffset the offset to apply to the Y position
	 * @return the adjusted Y position for the projectile
	 */
	double getProjectileYPosition(double yPositionOffset) {
		return getLayoutY() + getTranslateY() + yPositionOffset;
	}

	/**
	 * Checks if the health of the fighter plane is zero.
	 *
	 * @return true if the health is zero, otherwise false
	 */
	private boolean isHealthAtZero() {
		return m_Health == 0;
	}
}
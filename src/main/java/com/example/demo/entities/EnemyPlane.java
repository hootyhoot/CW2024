package com.example.demo.entities;

public class EnemyPlane extends FighterPlane {

	private static final String IMAGE_NAME = "enemyPlane.png";
	private static final int IMAGE_HEIGHT = 50;
	private static final int HORIZONTAL_VELOCITY = -6;
	private static final double PROJECTILE_X_POSITION_OFFSET = -100.0;
	private static final double PROJECTILE_Y_POSITION_OFFSET = 13.0;
	private static final int INITIAL_HEALTH = 1;

	private double m_FireRate;

	public EnemyPlane(double initialXPos, double initialYPos, double fireRate) {
		super(IMAGE_NAME, IMAGE_HEIGHT, initialXPos, initialYPos, INITIAL_HEALTH);
		m_FireRate = fireRate;
	}

	@Override
	public DestructibleEntity fireProjectile() {
		if (Math.random() < m_FireRate) {
			double projectileXPosition = getProjectileXPosition(PROJECTILE_X_POSITION_OFFSET);
			double projectileYPosition = getProjectileYPosition(PROJECTILE_Y_POSITION_OFFSET);
			return new EnemyProjectile(projectileXPosition, projectileYPosition);
		}
		return null;
	}

	@Override
	public void updateEntity() {
		updatePosition();
	}

	@Override
	void updatePosition() {
		moveHorizontally(HORIZONTAL_VELOCITY);
	}
}

package com.example.demo.entities;

public class EnemyProjectile extends Projectile {
	
	private static final String IMAGE_NAME = "enemyFire.png";
	private static final int IMAGE_HEIGHT = 25;
	private static final int HORIZONTAL_VELOCITY = -10;

	public EnemyProjectile(double initialXPos, double initialYPos) {
		super(IMAGE_NAME, IMAGE_HEIGHT, initialXPos, initialYPos);
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

package com.example.demo.entities;

public class UserProjectile extends Projectile {

	private static final String IMAGE_NAME = "userfire.png";
	private static final int IMAGE_HEIGHT = 12;
	private static final int HORIZONTAL_VELOCITY = 15;

	public UserProjectile(double initialXPos, double initialYPos) {
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

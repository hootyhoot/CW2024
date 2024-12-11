package com.example.demo.entities;

public abstract class FighterPlane extends DestructibleEntity {

	protected int m_Health;

	public FighterPlane(String imageName, int imageHeight, double initialXPos, double initialYPos, int health) {
		super(imageName, imageHeight, initialXPos, initialYPos);
		this.m_Health = health;
	}

	public int getHealth() {
		return m_Health;
	}

	@Override
	public void takeDamage() {
		m_Health--;
		if (isHealthAtZero()) {
			this.destroy();
		}
	}

	public abstract DestructibleEntity fireProjectile();

	double getProjectileXPosition(double xPositionOffset) {
		return getLayoutX() + getTranslateX() + xPositionOffset;
	}

	double getProjectileYPosition(double yPositionOffset) {
		return getLayoutY() + getTranslateY() + yPositionOffset;
	}

	private boolean isHealthAtZero() {
		return m_Health == 0;
	}
}

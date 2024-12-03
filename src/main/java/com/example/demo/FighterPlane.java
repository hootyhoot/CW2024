package com.example.demo;

public abstract class FighterPlane extends DestructibleEntity {

	private int m_Health;

	public FighterPlane(String imageName, int imageHeight, double initialXPos, double initialYPos, int health) {
		super(imageName, imageHeight, initialXPos, initialYPos);
		this.m_Health = health;
	}

	public int getHealth() {
		return m_Health;
	}

	protected double getProjectileXPosition(double xPositionOffset) {
		return getLayoutX() + getTranslateX() + xPositionOffset;
	}

	protected double getProjectileYPosition(double yPositionOffset) {
		return getLayoutY() + getTranslateY() + yPositionOffset;
	}

	public abstract DestructibleEntity fireProjectile();
	
	@Override
	public void takeDamage() {
		m_Health--;
		if (isHealthAtZero()) {
			this.destroy();
		}
	}

	private boolean isHealthAtZero() {
		return m_Health == 0;
	}

}

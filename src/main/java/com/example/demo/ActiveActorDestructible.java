package com.example.demo;

public abstract class ActiveActorDestructible extends ActiveActor implements Destructible {

	private boolean m_IsDestroyed;

	public ActiveActorDestructible(String imageName, int imageHeight, double initialXPos, double initialYPos) {
		super(imageName, imageHeight, initialXPos, initialYPos);
		m_IsDestroyed = false;
	}

	@Override
	public abstract void updatePosition();

	public abstract void updateActor();

	@Override
	public abstract void takeDamage();

	@Override
	public void destroy() {
		setDestroyed(true);
	}

	public boolean isDestroyed() {
		return m_IsDestroyed;
	}

	protected void setDestroyed(boolean isDestroyed) {
		this.m_IsDestroyed = isDestroyed;
	}
}

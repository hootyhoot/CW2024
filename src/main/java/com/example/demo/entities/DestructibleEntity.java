package com.example.demo.entities;

import com.example.demo.DestructibleInterface;

public abstract class DestructibleEntity extends GameEntity implements DestructibleInterface {

	private boolean m_IsDestroyed;

	public DestructibleEntity(String imageName, int imageHeight, double initialXPos, double initialYPos) {
		super(imageName, imageHeight, initialXPos, initialYPos);
		m_IsDestroyed = false;
	}

	public abstract void updateEntity();

	@Override
	public abstract void takeDamage();

	@Override
	abstract void updatePosition();

	@Override
	public void destroy() {
		setDestroyed(true);
	}

	public boolean isDestroyed() {
		return m_IsDestroyed;
	}

	private void setDestroyed(boolean isDestroyed) {
		this.m_IsDestroyed = isDestroyed;
	}
}

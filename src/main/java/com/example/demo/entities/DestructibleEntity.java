package com.example.demo.entities;

import com.example.demo.DestructibleInterface;

/**
 * Represents an entity in the game that can be destroyed.
 *
 * @see <a href="https://github.com/hootyhoot/CW2024/blob/master/src/main/java/com/example/demo/entities/DestructibleEntity.java">Source code</a>
 */
public abstract class DestructibleEntity extends GameEntity implements DestructibleInterface {

	/**
	 * Indicates whether the entity is destroyed.
	 */
	private boolean m_IsDestroyed;

	/**
	 * Constructs a DestructibleEntity with the specified image name, image height, and initial position.
	 *
	 * @param imageName the name of the image representing the entity
	 * @param imageHeight the height of the image
	 * @param initialXPos the initial X position of the entity
	 * @param initialYPos the initial Y position of the entity
	 */
	public DestructibleEntity(String imageName, int imageHeight, double initialXPos, double initialYPos) {
		super(imageName, imageHeight, initialXPos, initialYPos);
		m_IsDestroyed = false;
	}

	/**
	 * Updates the DestructibleEntity, including its position and other properties.
	 */
	public abstract void updateEntity();

	/**
	 * Takes damage, potentially destroying the entity.
	 */
	@Override
	public abstract void takeDamage();

	/**
	 * Updates the position of the DestructibleEntity.
	 */
	@Override
	abstract void updatePosition();

	/**
	 * Destroys the entity, marking it as destroyed.
	 */
	@Override
	public void destroy() {
		setDestroyed();
	}

	/**
	 * Checks if the entity is destroyed.
	 *
	 * @return true if the entity is destroyed, otherwise false
	 */
	public boolean isDestroyed() {
		return m_IsDestroyed;
	}

	/**
	 * Sets the entity as destroyed.
	 */
	private void setDestroyed() {
		this.m_IsDestroyed = true;
	}
}
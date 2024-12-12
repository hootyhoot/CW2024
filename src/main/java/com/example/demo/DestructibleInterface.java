package com.example.demo;

/**
 * Represents an interface for destructible entities.
 * This interface defines methods for taking damage and being destroyed.
 */
public interface DestructibleInterface {

	/**
	 * Inflicts damage on the entity.
	 */
	void takeDamage();

	/**
	 * Destroys the entity.
	 */
	void destroy();
}
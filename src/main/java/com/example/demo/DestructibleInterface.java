package com.example.demo;

/**
 * Represents an interface for destructible entities.
 * This interface defines methods for taking damage and being destroyed.
 *
 * @see <a href="https://github.com/hootyhoot/CW2024/blob/master/src/main/java/com/example/demo/DestructibleInterface.java">Source code</a>
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
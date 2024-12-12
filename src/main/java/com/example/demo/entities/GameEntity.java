package com.example.demo.entities;

import javafx.scene.image.*;
import java.util.Objects;

/**
 * Represents a game entity with an image.
 */
public abstract class GameEntity extends ImageView {

	/**
	 * The directory of the images for the game entities.
	 */
	private static final String IMAGE_LOCATION = "/com/example/demo/images/";

	/**
	 * Constructs a GameEntity with the specified image name, image height, and initial position.
	 *
	 * @param imageName the name of the image representing the entity
	 * @param imageHeight the height of the image
	 * @param initialXPos the initial X position of the entity
	 * @param initialYPos the initial Y position of the entity
	 */
	public GameEntity(String imageName, int imageHeight, double initialXPos, double initialYPos) {
		String imagePath = IMAGE_LOCATION + imageName;
		Image image = new Image(Objects.requireNonNull(getClass().getResource(imagePath), "Image resource not found: " + imagePath).toExternalForm());
		this.setImage(image);
		this.setLayoutX(initialXPos);
		this.setLayoutY(initialYPos);
		this.setFitHeight(imageHeight);
		this.setPreserveRatio(true);
	}

	/**
	 * Updates the position of the GameEntity.
	 */
	abstract void updatePosition();

	/**
	 * Moves the GameEntity horizontally by the specified amount.
	 *
	 * @param horizontalMove the amount to move the entity horizontally
	 */
	void moveHorizontally(double horizontalMove) {
		this.setTranslateX(getTranslateX() + horizontalMove);
	}

	/**
	 * Moves the GameEntity vertically by the specified amount.
	 *
	 * @param verticalMove the amount to move the entity vertically
	 */
	void moveVertically(double verticalMove) {
		this.setTranslateY(getTranslateY() + verticalMove);
	}
}
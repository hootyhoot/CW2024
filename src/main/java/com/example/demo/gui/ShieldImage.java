package com.example.demo.gui;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import java.util.Objects;

/**
 * Represents an image of a shield in the GUI.
 * Extends the `ImageView` class to display the shield image.
 *
 * @see <a href="https://github.com/hootyhoot/CW2024/blob/master/src/main/java/com/example/demo/gui/ShieldImage.java">Source code</a>
 */
public class ShieldImage extends ImageView {

	/**
	 * The image name for the shield icon.
	 */
	private static final String IMAGE_NAME = "/com/example/demo/images/shield.png";

	/**
	 * The size of the shield icon.
	 */
	private static final int SHIELD_SIZE = 100;

	/**
	 * Constructs a ShieldImage with the specified position.
	 *
	 * @param xPosition the x position of the shield image
	 * @param yPosition the y position of the shield image
	 */
	public ShieldImage(double xPosition, double yPosition) {
		this.setLayoutX(xPosition);
		this.setLayoutY(yPosition);
		this.setImage(new Image(Objects.requireNonNull(getClass().getResource(IMAGE_NAME)).toExternalForm()));
		this.setVisible(false);
		this.setFitHeight(SHIELD_SIZE);
		this.setFitWidth(SHIELD_SIZE);
	}

	/**
	 * Shows the shield image.
	 */
	public void showShield() {
		this.setVisible(true);
	}

	/**
	 * Hides the shield image.
	 */
	public void hideShield() {
		this.setVisible(false);
	}

}
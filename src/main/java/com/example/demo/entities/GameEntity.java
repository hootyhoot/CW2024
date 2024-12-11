package com.example.demo.entities;

import javafx.scene.image.*;

import java.util.Objects;

public abstract class GameEntity extends ImageView {

	private static final String IMAGE_LOCATION = "/com/example/demo/images/";

	public GameEntity(String imageName, int imageHeight, double initialXPos, double initialYPos) {
		String imagePath = IMAGE_LOCATION + imageName;
		Image image = new Image(Objects.requireNonNull(getClass().getResource(imagePath), "Image resource not found: " + imagePath).toExternalForm());
		this.setImage(image);
		this.setLayoutX(initialXPos);
		this.setLayoutY(initialYPos);
		this.setFitHeight(imageHeight);
		this.setPreserveRatio(true);
	}

	abstract void updatePosition();

	void moveHorizontally(double horizontalMove) {
		this.setTranslateX(getTranslateX() + horizontalMove);
	}

	void moveVertically(double verticalMove) {
		this.setTranslateY(getTranslateY() + verticalMove);
	}
}
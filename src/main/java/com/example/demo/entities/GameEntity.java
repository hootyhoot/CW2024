package com.example.demo.entities;

import javafx.scene.image.*;

public abstract class GameEntity extends ImageView {
	
	private static final String IMAGE_LOCATION = "/com/example/demo/images/";

	public GameEntity(String imageName, int imageHeight, double initialXPos, double initialYPos) {
		this.setImage(new Image(getClass().getResource(IMAGE_LOCATION + imageName).toExternalForm()));
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

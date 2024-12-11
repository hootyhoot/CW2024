package com.example.demo.gui;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;

import java.util.Objects;

public class HeartDisplay {

	private static final String HEART_IMAGE_NAME = "/com/example/demo/images/heart.png";
	private static final Image HEART_IMAGE = new Image(Objects.requireNonNull(HeartDisplay.class.getResource(HEART_IMAGE_NAME)).toExternalForm());
	private static final int HEART_HEIGHT = 50;
	private static final int INDEX_OF_FIRST_ITEM = 0;
	private HBox m_Container;
	private final double m_ContainerXPosition;
	private final double m_ContainerYPosition;
	private final int m_NumberOfHeartsToDisplay;

	public HeartDisplay(double xPosition, double yPosition, int heartsToDisplay) {
		this.m_ContainerXPosition = xPosition;
		this.m_ContainerYPosition = yPosition;
		this.m_NumberOfHeartsToDisplay = heartsToDisplay;
		initializeContainer();
		initializeHearts();
	}

	public HBox getContainer() {
		return m_Container;
	}

	void removeHeart() {
		if (!m_Container.getChildren().isEmpty())
			m_Container.getChildren().remove(INDEX_OF_FIRST_ITEM);
	}

	void addHeart() {
		ImageView heart = new ImageView(HEART_IMAGE);
		heart.setFitHeight(HEART_HEIGHT);
		heart.setPreserveRatio(true);
		m_Container.getChildren().add(heart);
	}

	private void initializeContainer() {
		m_Container = new HBox();
		m_Container.setLayoutX(m_ContainerXPosition);
		m_Container.setLayoutY(m_ContainerYPosition);
	}

	private void initializeHearts() {
		for (int i = 0; i < m_NumberOfHeartsToDisplay; i++) {
			addHeart();
		}
	}
}
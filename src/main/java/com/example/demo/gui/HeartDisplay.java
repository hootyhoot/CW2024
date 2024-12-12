package com.example.demo.gui;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;

import java.util.Objects;

/**
 * Represents a display of hearts in the GUI.
 */
public class HeartDisplay {

	/**
	 * The image name for the heart icon.
	 */
	private static final String HEART_IMAGE_NAME = "/com/example/demo/images/heart.png";

	/**
	 * The image object for the heart icon.
	 */
	private static final Image HEART_IMAGE = new Image(Objects.requireNonNull(HeartDisplay.class.getResource(HEART_IMAGE_NAME)).toExternalForm());

	/**
	 * The height of the heart icon.
	 */
	private static final int HEART_HEIGHT = 50;

	/**
	 * The index of the first item in the container.
	 */
	private static final int INDEX_OF_FIRST_ITEM = 0;

	/**
	 * The container for the hearts.
	 */
	private HBox m_Container;

	/**
	 * The X position of the heart display container.
	 */
	private final double m_ContainerXPosition;

	/**
	 * The Y position of the heart display container.
	 */
	private final double m_ContainerYPosition;

	/**
	 * The number of hearts to display.
	 */
	private final int m_NumberOfHeartsToDisplay;

	/**
	 * Constructs a HeartDisplay with the specified position and number of hearts to display.
	 *
	 * @param xPosition the X position of the heart display
	 * @param yPosition the Y position of the heart display
	 * @param heartsToDisplay the number of hearts to display
	 */
	public HeartDisplay(double xPosition, double yPosition, int heartsToDisplay) {
		this.m_ContainerXPosition = xPosition;
		this.m_ContainerYPosition = yPosition;
		this.m_NumberOfHeartsToDisplay = heartsToDisplay;
		initializeContainer();
		initializeHearts();
	}

	/**
	 * Returns the container holding the hearts.
	 *
	 * @return the container holding the hearts
	 */
	public HBox getContainer() {
		return m_Container;
	}

	/**
	 * Removes a heart from the display.
	 */
	void removeHeart() {
		if (!m_Container.getChildren().isEmpty())
			m_Container.getChildren().remove(INDEX_OF_FIRST_ITEM);
	}

	/**
	 * Adds a heart to the display.
	 */
	void addHeart() {
		ImageView heart = new ImageView(HEART_IMAGE);
		heart.setFitHeight(HEART_HEIGHT);
		heart.setPreserveRatio(true);
		m_Container.getChildren().add(heart);
	}

	/**
	 * Initializes the container for the hearts.
	 */
	private void initializeContainer() {
		m_Container = new HBox();
		m_Container.setLayoutX(m_ContainerXPosition);
		m_Container.setLayoutY(m_ContainerYPosition);
	}

	/**
	 * Initializes the hearts in the display.
	 */
	private void initializeHearts() {
		for (int i = 0; i < m_NumberOfHeartsToDisplay; i++) {
			addHeart();
		}
	}
}
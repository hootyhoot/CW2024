package com.example.demo.gui;

import javafx.scene.Group;

/**
 * Represents the view for a level in the game.
 */
public class LevelView {

	/**
	 * The X position of the heart display.
	 */
	private static final double HEART_DISPLAY_X_POSITION = 5;

	/**
	 * The Y position of the heart display.
	 */
	private static final double HEART_DISPLAY_Y_POSITION = 25;

	/**
	 * The root group for the level view.
	 */
	private final Group m_Root;

	/**
	 * The heart display in the level view.
	 */
	private final HeartDisplay m_HeartDisplay;

	/**
	 * Constructs a LevelView with the specified root and number of hearts to display.
	 *
	 * @param root the root group
	 * @param heartsToDisplay the number of hearts to display
	 */
	public LevelView(Group root, int heartsToDisplay) {
		this.m_Root = root;
		this.m_HeartDisplay = new HeartDisplay(HEART_DISPLAY_X_POSITION, HEART_DISPLAY_Y_POSITION, heartsToDisplay);
	}

	/**
	 * Shows the heart display in the level view.
	 */
	public void showHeartDisplay() {
		m_Root.getChildren().add(m_HeartDisplay.getContainer());
	}

	/**
	 * Removes hearts from the heart display.
	 *
	 * @param heartsRemaining the number of hearts remaining
	 */
	public void removeHearts(int heartsRemaining) {
		int currentNumberOfHearts = m_HeartDisplay.getContainer().getChildren().size();
		for (int i = 0; i < currentNumberOfHearts - heartsRemaining; i++) {
			m_HeartDisplay.removeHeart();
		}
	}

	/**
	 * Adds hearts to the heart display.
	 *
	 * @param heartsToAdd the number of hearts to add
	 */
	public void addHearts(int heartsToAdd) {
		for (int i = 0; i < heartsToAdd; i++) {
			m_HeartDisplay.addHeart();
		}
	}
}
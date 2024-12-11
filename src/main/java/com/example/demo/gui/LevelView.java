package com.example.demo.gui;

import javafx.scene.Group;

public class LevelView {
	
	private static final double HEART_DISPLAY_X_POSITION = 5;
	private static final double HEART_DISPLAY_Y_POSITION = 25;
	private final Group m_Root;
	private final HeartDisplay m_HeartDisplay;
	
	public LevelView(Group root, int heartsToDisplay) {
		this.m_Root = root;
		this.m_HeartDisplay = new HeartDisplay(HEART_DISPLAY_X_POSITION, HEART_DISPLAY_Y_POSITION, heartsToDisplay);
	}
	
	public void showHeartDisplay() {
		m_Root.getChildren().add(m_HeartDisplay.getContainer());
	}

	public void removeHearts(int heartsRemaining) {
		int currentNumberOfHearts = m_HeartDisplay.getContainer().getChildren().size();
		for (int i = 0; i < currentNumberOfHearts - heartsRemaining; i++) {
			m_HeartDisplay.removeHeart();
		}
	}

	public void addHearts(int heartsToAdd) {
		for (int i = 0; i < heartsToAdd; i++) {
			m_HeartDisplay.addHeart();
		}
	}

}

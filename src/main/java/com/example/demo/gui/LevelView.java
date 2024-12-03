package com.example.demo.gui;

import javafx.scene.Group;

public class LevelView {
	
	private static final double HEART_DISPLAY_X_POSITION = 5;
	private static final double HEART_DISPLAY_Y_POSITION = 25;
	private static final int WIN_IMAGE_X_POSITION = 355;
	private static final int WIN_IMAGE_Y_POSITION = 175;
	private static final int LOSS_SCREEN_X_POSITION = 0;
	private static final int LOSS_SCREEN_Y_POSITION = -300;
	private final Group m_Root;
	private final WinImage m_WinImage;
	private final GameOverImage m_GameOverImage;
	private final HeartDisplay m_HeartDisplay;
	
	public LevelView(Group root, int heartsToDisplay) {
		this.m_Root = root;
		this.m_HeartDisplay = new HeartDisplay(HEART_DISPLAY_X_POSITION, HEART_DISPLAY_Y_POSITION, heartsToDisplay);
		this.m_WinImage = new WinImage(WIN_IMAGE_X_POSITION, WIN_IMAGE_Y_POSITION);
		this.m_GameOverImage = new GameOverImage(LOSS_SCREEN_X_POSITION, LOSS_SCREEN_Y_POSITION);
	}
	
	public void showHeartDisplay() {
		m_Root.getChildren().add(m_HeartDisplay.getContainer());
	}

	public void showWinImage() {
		m_Root.getChildren().add(m_WinImage);
		m_WinImage.showWinImage();
	}
	
	public void showGameOverImage() {
		m_Root.getChildren().add(m_GameOverImage);
	}
	
	public void removeHearts(int heartsRemaining) {
		int currentNumberOfHearts = m_HeartDisplay.getContainer().getChildren().size();
		for (int i = 0; i < currentNumberOfHearts - heartsRemaining; i++) {
			m_HeartDisplay.removeHeart();
		}
	}

}

package com.example.demo.levels;

import com.example.demo.gui.LevelView;
import com.example.demo.entities.Boss;

public class LevelTwo extends LevelParent {

	private static final String BACKGROUND_IMAGE_NAME = "/com/example/demo/images/background2.jpg";
	private static final int PLAYER_INITIAL_HEALTH = 5;
	private final Boss m_Boss;
	private LevelView m_LevelView;

	public LevelTwo(double screenHeight, double screenWidth) {
		super(BACKGROUND_IMAGE_NAME, screenHeight, screenWidth, PLAYER_INITIAL_HEALTH);
		m_Boss = new Boss();
	}

	@Override
	protected void initializeFriendlyUnits() {
		getRoot().getChildren().add(getUser());
	}

	@Override
	protected void isGameOver() {
		if (isUserDestroyed()) {
			loseGame();
		} else if (m_Boss.isDestroyed()) {
			winGame();
		}
	}

	@Override
	protected void spawnEnemyUnits() {
		if (getEntityHandler().getCurrentNumberOfEnemies() == 0) {
			getEntityHandler().addEnemyUnit(m_Boss);
			getRoot().getChildren().add(m_Boss.getShieldImage());
		}
	}

	@Override
	protected LevelView instantiateLevelView() {
		m_LevelView = new LevelView(getRoot(), PLAYER_INITIAL_HEALTH);
		return m_LevelView;
	}
}